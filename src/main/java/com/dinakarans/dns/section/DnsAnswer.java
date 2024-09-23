package com.dinakarans.dns.section;

public class DnsAnswer extends DnsQuestion {
    private int ttl;

    private short rdLength;

    private String rData;

    public DnsAnswer() {
        super();
    }

    public DnsAnswer(DnsAnswer answer) {
        this.ttl = answer.ttl;
        this.rdLength = answer.rdLength;
        this.rData = answer.rData;
    }

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

    public String getRData() {
        return rData;
    }

    public void setRData(String rData) {
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
                ", rData='" + rData + '\'' +
                '}';
    }
}
