package com.dinakarans.dns.section;

import com.dinakarans.dns.util.DnsUtil;

import java.nio.ByteBuffer;

public class DnsHeader extends Section {

    public DnsHeader(ByteBuffer byteBuffer) {
        super(byteBuffer);
    }

    public short getID() {
        return DnsUtil.getShort(byteBuffer, getPosition(Field.H_ID));
    }

    public void setID(short id) {
        DnsUtil.setShort(byteBuffer, getPosition(Field.H_ID), id);
    }

    public short getQOATRZR() {
        return DnsUtil.getShort(byteBuffer, getPosition(Field.H_QOATRZR));
    }

    public void setQOATRZR(short qoatrzr) {
        DnsUtil.setShort(byteBuffer, getPosition(Field.H_QOATRZR), qoatrzr);
    }

    public short getQDCOUNT() {
        return DnsUtil.getShort(byteBuffer, getPosition(Field.H_QDCOUNT));
    }

    public void setQDCOUNT(short qdcount) {
        DnsUtil.setShort(byteBuffer, getPosition(Field.H_QDCOUNT), qdcount);
    }

    public void setANCOUNT(short ancount) {
        DnsUtil.setShort(byteBuffer, getPosition(Field.H_ANCOUNT), ancount);
    }

    @Override
    int getPosition(Field field) {
        return switch (field) {
            case H_ID -> 0;
            case H_QOATRZR -> 2;
            case H_QDCOUNT -> 4;
            case H_ANCOUNT -> 6;
            case H_NSCOUNT -> 8;
            case H_ARCOUNT -> 10;
            default -> 12;
        };
    }
}
