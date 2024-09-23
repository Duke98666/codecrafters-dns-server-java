package com.dinakarans.dns.section;

public class DnsAnswer extends DnsQuestion {
    private int ttl;

    private short rdLength;

    private byte[] rData;

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public short getRdLength() {
        return rdLength;
    }

    public void setRdLength(short rdLength) {
        this.rdLength = rdLength;
    }

    public byte[] getRData() {
        return rData;
    }

    public void setRData(byte[] rData) {
        this.rData = rData;
    }
}
