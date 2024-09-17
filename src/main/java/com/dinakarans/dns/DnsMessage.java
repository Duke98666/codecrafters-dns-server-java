package com.dinakarans.dns;

import com.dinakarans.dns.section.DnsHeader;
import com.dinakarans.dns.section.DnsQuestion;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public abstract class DnsMessage {

    protected final ByteBuffer byteBuffer;

    protected final DnsHeader header;

    protected final List<DnsQuestion> questions;

    public DnsMessage(byte[] bufferBytes) {
        byteBuffer = ByteBuffer.wrap(bufferBytes);
        header = new DnsHeader(byteBuffer);
        questions = new ArrayList<>();
    }

    abstract void setQuestions();

    public byte[] getBufferBytes() {
        byte[] value = new byte[512];
        byteBuffer.get(0, value);
        return value;
    }

}
