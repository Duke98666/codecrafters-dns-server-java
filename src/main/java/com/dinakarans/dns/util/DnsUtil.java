package com.dinakarans.dns.util;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.StringJoiner;

public final class DnsUtil {

    public static short getShort(ByteBuffer byteBuffer, int position) {
        int currPosition = byteBuffer.position();
        short value = byteBuffer.position(position).getShort();
        byteBuffer.position(currPosition);
        return value;
    }

    public static void setShort(ByteBuffer byteBuffer, int position, short value) {
        int currPosition = byteBuffer.position();
        if (currPosition == position) {
            byteBuffer.putShort(value);
        } else {
            byteBuffer.position(position).putShort(value).position(currPosition);
        }
    }

    public static void setInt(ByteBuffer byteBuffer, int position, int value) {
        int currPosition = byteBuffer.position();
        if (currPosition == position) {
            byteBuffer.putInt(value);
        } else {
            byteBuffer.position(position).putInt(value).position(currPosition);
        }
    }

    public static String parseName(ByteBuffer byteBuffer) {
        StringJoiner name = new StringJoiner(".");
        int labelLength;
        byteBuffer.rewind();
        while ((labelLength = byteBuffer.get()) != 0) {
            if ((labelLength & 0B11_00_00_00) == 0B11_00_00_00) {
                int offset = ((labelLength & 0B00_11_11_11) << 8) | (byteBuffer.get() & 0B11_11_11_11);
                byteBuffer.position(offset);
            } else {
                byte[] label = new byte[labelLength];
                byteBuffer.get(label);
                name.add(new String(label, StandardCharsets.UTF_8));
            }
        }
        byteBuffer.rewind();
        return name.toString();
    }

    public static byte[] getNameBytes(String name) {
        String[] labels = name.split("\\.");
        // n labels are separated by (n - 1) dots (.)
        // total content characters (x1) = (name.length() - labels.length + 1)
        // total prefix label length bytes (x2) = labels.length
        // null byte (x3) = 1
        // total = x1 + x2 + x3 = name.length() + 2
        int nameBytesLength = name.length() + 2;
        byte[] nameBytes = new byte[nameBytesLength];
        ByteBuffer nameBuffer = ByteBuffer.wrap(nameBytes);
        for (String label : labels) {
            nameBuffer.put((byte) label.length()).put(label.getBytes(StandardCharsets.UTF_8));
        }
        nameBuffer.put((byte) 0);
        return nameBytes;
    }

    private DnsUtil() {
        // Utility class
    }
}
