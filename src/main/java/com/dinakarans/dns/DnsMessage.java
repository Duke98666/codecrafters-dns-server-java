package com.dinakarans.dns;

import com.dinakarans.dns.section.DnsHeader;
import com.dinakarans.dns.section.DnsQuestion;

import java.nio.ByteBuffer;

public abstract class DnsMessage {

    protected final ByteBuffer byteBuffer;

    protected final DnsHeader header;

    protected final DnsQuestion question;

    public DnsMessage(byte[] bufferBytes) {
        byteBuffer = ByteBuffer.wrap(bufferBytes);
        header = new DnsHeader(byteBuffer);
        question = new DnsQuestion(byteBuffer, header);
    }

    public byte[] getBufferBytes() {
        byte[] value = new byte[512];
        byteBuffer.get(0, value);
        return value;
    }

}
