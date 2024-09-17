package com.dinakarans.dns.section;

import java.nio.ByteBuffer;

abstract class Section {

    protected final ByteBuffer byteBuffer;

    Section(ByteBuffer byteBuffer) {
        this.byteBuffer = byteBuffer.slice();
    }

    abstract int getPosition(Field field);

    public ByteBuffer getByteBuffer() {
        return byteBuffer.rewind();
    }

    public ByteBuffer getNextByteBuffer() {
        return byteBuffer.position(getPosition(Field.LIMIT));
    }

}
