package com.dinakarans.dns.section;

import java.util.StringJoiner;

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
        StringJoiner rDataBuilder = new StringJoiner(".");
        for (int i = 0; i < rdLength; ++i) {
            rDataBuilder.add(String.valueOf(rData[i]));
        }
        return "DnsAnswer{" +
                "name=" + getName() +
                ", type=" + getType() +
                ", class=" + getClazz() +
                ", ttl=" + ttl +
                ", rdLength=" + rdLength +
                ", rData='"
                    + rDataBuilder.toString()
                + '\'' +
                '}';
    }
}
