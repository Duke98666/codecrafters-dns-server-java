package com.dinakarans.dns.section;

import com.dinakarans.dns.util.DnsUtil;

import java.nio.ByteBuffer;

public class DnsQuestion extends Section {

    private int nameLength;

    public DnsQuestion(ByteBuffer byteBuffer) {
        super(byteBuffer);
    }

    public String getName() {
        return DnsUtil.parseName(byteBuffer);
    }

    public void setName(String name) {
        byte[] nameBytes = DnsUtil.getNameBytes(name);
        byteBuffer.put(getPosition(Field.Q_Name), nameBytes);
        nameLength = nameBytes.length;
    }

    public short getType() {
        return DnsUtil.getShort(byteBuffer, getPosition(Field.Q_Type));
    }

    public void setType(short type) {
        DnsUtil.setShort(byteBuffer, getPosition(Field.Q_Type), type);
    }

    public short getClass_() {
        return DnsUtil.getShort(byteBuffer, getPosition(Field.Q_Class));
    }

    public void setClass_(short class_) {
        DnsUtil.setShort(byteBuffer, getPosition(Field.Q_Class), class_);
    }

    @Override
    public int getPosition(Field field) {
        return switch (field) {
            case Q_Name -> 0;
            case Q_Type -> nameLength;
            case Q_Class -> nameLength + 2;
            default -> nameLength + 4;
        };
    }
}
