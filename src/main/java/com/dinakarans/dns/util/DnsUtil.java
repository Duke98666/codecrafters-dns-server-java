package com.dinakarans.dns.util;

import com.dinakarans.dns.section.Fields;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

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

    public static byte[] parseName(ByteBuffer byteBuffer, int position) {
        int index = position;
        int length = byteBuffer.get(index);
        while (length != 0) {
            index += length + 1;
            length = byteBuffer.get(index);
        }
        byte[] value = new byte[index - position];
        byteBuffer.get(position, value);
        return value;
    }

    private DnsUtil() {
        // Utility class
    }

}
