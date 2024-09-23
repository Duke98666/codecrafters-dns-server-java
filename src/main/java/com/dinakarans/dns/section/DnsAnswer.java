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

    @Override
    public String toString() {
        return "DnsAnswer{" +
                "name=" + getName() +
                ", type=" + getType() +
                ", class=" + getClazz() +
                ", ttl=" + ttl +
                ", rdLength=" + rdLength +
                ", rData='"
                    + String.valueOf(rData[0]) + "."
                    + String.valueOf(rData[1]) + "."
                    + String.valueOf(rData[2]) + "."
                    + String.valueOf(rData[3])
                + '\'' +
                '}';
    }
}
