package com.dinakarans.dns.section;

import com.dinakarans.dns.util.DnsUtil;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class DnsAnswer implements Section {

    private final ByteBuffer byteBuffer;

    private final Section previousSection;

    private int nameLength;

    private int limit;

    public DnsAnswer(ByteBuffer byteBuffer, Section previousSection) {
        this.byteBuffer = byteBuffer;
        this.previousSection = previousSection;
    }

    private int getPosition(Fields.Answer field) {
        return previousSection.getLimit() + switch (field) {
            case Name -> 0;
            case Type -> nameLength;
            case Class -> nameLength + 2;
            case TTL -> nameLength + 4;
            case RDLength -> nameLength + 8;
            case RData -> nameLength + 10;
        };
    }

    private void setLimit(int nameLength) {
        this.nameLength = nameLength;
        // name length + type (2 bytes)
        // + class (2 bytes) + ttl (4 bytes)
        // + rdlength (2 bytes) + rdata (2 bytes)
        limit = previousSection.getLimit() + nameLength + 12;
    }

    public void setName(byte[] name) {
        int position = getPosition(Fields.Answer.Name);
        byteBuffer.put(position, name);
        setLimit(name.length);
    }

    public void setType(short type) {
        int position = getPosition(Fields.Answer.Type);
        DnsUtil.setShort(byteBuffer, position, type);
    }

    public void setClass_(short class_) {
        int position = getPosition(Fields.Answer.Class);
        DnsUtil.setShort(byteBuffer, position, class_);
    }

    public void setTTL(int ttl) {
        int position = getPosition(Fields.Answer.TTL);
        DnsUtil.setInt(byteBuffer, position, ttl);
    }

    public void setRLength(short length) {
        int position = getPosition(Fields.Answer.RDLength);
        DnsUtil.setShort(byteBuffer, position, length);
    }

    public void setRData(int data) {
        int position = getPosition(Fields.Answer.RData);
        DnsUtil.setInt(byteBuffer, position, data);
    }

    @Override
    public int getLimit() {
        return limit;
    }
}
