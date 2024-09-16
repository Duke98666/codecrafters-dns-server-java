package com.dinakarans.dns.section;

import com.dinakarans.dns.util.DnsUtil;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class DnsAnswer implements Section {

    private final ByteBuffer byteBuffer;

    private final Section prevousSection;

    private int nameLength;

    private int limit;

    public DnsAnswer(ByteBuffer byteBuffer, Section previousSection) {
        this.byteBuffer = byteBuffer;
        this.prevousSection = previousSection;
    }

    private int getPosition(Fields.Answer field) {
        return prevousSection.getLimit() + switch (field) {
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
        limit = nameLength + 12;
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
        byteBuffer.putInt(ttl);
    }

    public void setRLength(short length) {
        int position = getPosition(Fields.Answer.RDLength);
        DnsUtil.setShort(byteBuffer, position, length);
    }

    public void setRData(String data) {
        byteBuffer.position(getPosition(Fields.Answer.RData));
        String[] digits = data.split("\\.");
        for (String digit : digits) {
            byte[] bytes = digit.getBytes(StandardCharsets.UTF_8);
            byteBuffer.put(bytes);
        }
    }

    @Override
    public int getLimit() {
        return limit;
    }
}
