package com.dinakarans.dns.section;

import com.dinakarans.dns.util.DnsUtil;

import java.nio.ByteBuffer;

public class DnsQuestion implements Section {

    private final ByteBuffer byteBuffer;

    private final Section prevousSection;

    private int nameLength;

    private int limit;

    public DnsQuestion(ByteBuffer byteBuffer, Section previousSection) {
        this.byteBuffer = byteBuffer;
        this.prevousSection = previousSection;
    }

    private int getPosition(Fields.Question field) {
        return prevousSection.getLimit() + switch (field) {
            case Name -> 0;
            case Type -> nameLength;
            case Class -> nameLength + 2;
        };
    }

    private void setLimit(int nameLength) {
        this.nameLength = nameLength;
        // name length + type (2 bytes) + class (2 bytes)
        limit = nameLength + 4;
    }

    public byte[] getName() {
        int position = getPosition(Fields.Question.Name);
        if (nameLength == 0) {
            byte[] name = DnsUtil.parseName(byteBuffer, position);
            setLimit(name.length);
            return name;
        }
        byte[] name = new byte[nameLength];
        byteBuffer.get(position, name);
        return name;
    }

    public void setName(byte[] name) {
        int position = getPosition(Fields.Question.Name);
        byteBuffer.put(position, name);
        setLimit(name.length);
    }

    public short getType() {
        int position = getPosition(Fields.Question.Type);
        return DnsUtil.getShort(byteBuffer, position);
    }

    public void setType(short type) {
        int position = getPosition(Fields.Question.Type);
        DnsUtil.setShort(byteBuffer, position, type);
    }

    public short getClass_() {
        int position = getPosition(Fields.Question.Class);
        return DnsUtil.getShort(byteBuffer, position);
    }

    public void setClass_(short class_) {
        int position = getPosition(Fields.Question.Class);
        DnsUtil.setShort(byteBuffer, position, class_);
    }

    @Override
    public int getLimit() {
        return limit;
    }
}
