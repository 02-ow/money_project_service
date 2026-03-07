package com.wuzl.utils;


import java.net.NetworkInterface;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Enumeration;

public class UserIDUtil {


    private static final long EPOCH = 1609459200000L; // 2021-01-01 00:00:00
    private static final long WORKER_ID_BITS = 5L;
    private static final long SEQUENCE_BITS = 8L;

    private final long workerId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    // 添加数字字符集（0-9）
    private static final String NUMERIC = "0123456789";

    public UserIDUtil() {
        this.workerId = generateWorkerId();
    }

    /**
     * 获取账号方法（静态方法）
     */
    public static String generateUserID() {
        UserIDUtil userIDUtil = new UserIDUtil();
        return userIDUtil.generateAccount();
    }

    /**
     * 生成12位数字账号
     */
    public synchronized String generateAccount() {
        long timestamp = Instant.now().toEpochMilli();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException("时钟回拨异常");
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & ((1 << SEQUENCE_BITS) - 1);
            if (sequence == 0) {
                timestamp = waitNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        // 组合ID：时间戳差 + 工作机器ID + 序列号
        long id = ((timestamp - EPOCH) << (WORKER_ID_BITS + SEQUENCE_BITS))
                | (workerId << SEQUENCE_BITS)
                | sequence;

        // 方法1：使用数字格式化（保证12位）
        return String.format("%012d", Math.abs(id) % 1000000000000L);
    }

    /**
     * 方法2：使用自定义进制转换（如果需要更分散的数字）
     */
    public synchronized String generateAccountV2() {
        long timestamp = Instant.now().toEpochMilli();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException("时钟回拨异常");
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & ((1 << SEQUENCE_BITS) - 1);
            if (sequence == 0) {
                timestamp = waitNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        // 组合ID
        long id = ((timestamp - EPOCH) << (WORKER_ID_BITS + SEQUENCE_BITS))
                | (workerId << SEQUENCE_BITS)
                | sequence;

        // 转换为10进制字符串，确保12位
        String numericId = convertToNumeric(Math.abs(id), 12);

        return numericId;
    }

    /**
     * 转换为指定长度的数字字符串
     */
    private String convertToNumeric(long value, int length) {
        // 使用10进制转换
        String numericStr = Long.toString(value);

        // 如果长度不够，前面补0
        if (numericStr.length() < length) {
            return String.format("%0" + length + "d", value);
        }

        // 如果太长，截取后12位
        if (numericStr.length() > length) {
            return numericStr.substring(numericStr.length() - length);
        }

        return numericStr;
    }

    /**
     * 方法3：结合时间戳的12位数字（推荐，保证唯一性和可读性）
     */
    public synchronized String generateAccountV3() {
        long timestamp = Instant.now().toEpochMilli();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException("时钟回拨异常");
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & ((1 << SEQUENCE_BITS) - 1);
            if (sequence == 0) {
                timestamp = waitNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        // 使用时间戳的后9位
        String timePart = String.valueOf(timestamp % 1000000000L);
        timePart = String.format("%09d", Long.parseLong(timePart));

        // 使用序列号的3位
        String seqPart = String.format("%03d", sequence);

        return timePart + seqPart;
    }

    private long generateWorkerId() {
        try {
            StringBuilder sb = new StringBuilder();
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();
                byte[] mac = ni.getHardwareAddress();
                if (mac != null) {
                    for (byte b : mac) {
                        sb.append(String.format("%02X", b));
                    }
                    break;
                }
            }
            return Math.abs(sb.toString().hashCode()) % 32;
        } catch (Exception e) {
            return new SecureRandom().nextInt(32);
        }
    }

    private long waitNextMillis(long lastTimestamp) {
        long timestamp = Instant.now().toEpochMilli();
        while (timestamp <= lastTimestamp) {
            timestamp = Instant.now().toEpochMilli();
        }
        return timestamp;
    }
}
