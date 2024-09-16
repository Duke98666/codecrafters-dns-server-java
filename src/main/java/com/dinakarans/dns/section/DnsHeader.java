package com.dinakarans.dns.section;

import com.dinakarans.dns.util.DnsConstants;
import com.dinakarans.dns.util.DnsUtil;

import java.nio.ByteBuffer;

public class DnsHeader implements Section {

    private final ByteBuffer byteBuffer;

    public DnsHeader(ByteBuffer byteBuffer) {
        this.byteBuffer = byteBuffer;
    }

    public short getID() {
        return DnsUtil.getShort(byteBuffer, DnsConstants.DnsHeader.ID_POSITION);
    }

    public void setID(short id) {
        DnsUtil.setShort(byteBuffer, DnsConstants.DnsHeader.ID_POSITION, id);
    }

    public short getQOATRZR() {
        return DnsUtil.getShort(byteBuffer, DnsConstants.DnsHeader.QOATRZR_POSITION);
    }

    public void setQOATRZR(short qoatrzr) {
        DnsUtil.setShort(byteBuffer, DnsConstants.DnsHeader.QOATRZR_POSITION, qoatrzr);
    }

    public short getQDCOUNT() {
        return DnsUtil.getShort(byteBuffer, DnsConstants.DnsHeader.QDCOUNT_POSITION);
    }

    public void setQDCOUNT(short qdcount) {
        DnsUtil.setShort(byteBuffer, DnsConstants.DnsHeader.QDCOUNT_POSITION, qdcount);
    }

    public short getANCOUNT() {
        return DnsUtil.getShort(byteBuffer, DnsConstants.DnsHeader.ANCOUNT_POSITION);
    }

    public void setANCOUNT(short ancount) {
        DnsUtil.setShort(byteBuffer, DnsConstants.DnsHeader.ANCOUNT_POSITION, ancount);
    }

    @Override
    public int getLimit() {
        return 12;
    }

}
