package com.dinakarans.dns.section;

import com.dinakarans.dns.util.DnsUtil;

import java.nio.ByteBuffer;

public class DnsAnswer extends Section {

    private int nameLength;

    public DnsAnswer(ByteBuffer byteBuffer) {
        super(byteBuffer);
    }

    public void setName(String name) {
        byte[] nameBytes = DnsUtil.getNameBytes(name);
        byteBuffer.put(getPosition(Field.A_Name), nameBytes);
        nameLength = nameBytes.length;
    }

    public void setType(short type) {
        DnsUtil.setShort(byteBuffer, getPosition(Field.A_Type), type);
    }

    public void setClass_(short class_) {
        DnsUtil.setShort(byteBuffer, getPosition(Field.A_Class), class_);
    }

    public void setTTL(int ttl) {
        DnsUtil.setInt(byteBuffer, getPosition(Field.A_TTL), ttl);
    }

    public void setRDLength(short length) {
        DnsUtil.setShort(byteBuffer, getPosition(Field.A_RDLength), length);
    }

    public void setRData(int data) {
        DnsUtil.setInt(byteBuffer, getPosition(Field.A_RData), data);
    }

    @Override
    int getPosition(Field field) {
        return switch (field) {
            case A_Name -> 0;
            case A_Type -> nameLength;
            case A_Class -> nameLength + 2;
            case A_TTL -> nameLength + 4;
            case A_RDLength -> nameLength + 8;
            case A_RData -> nameLength + 10;
            default -> nameLength + 14;
        };
    }
}
