package com.dinakarans.dns.section;

public class DnsQuestion {

    private String name;

    private short type;

    private short clazz;

    public DnsQuestion() {
        super();
    }

    public DnsQuestion(DnsQuestion question) {
        this.name = new String(question.name);
        this.type = question.type;
        this.clazz = question.clazz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public short getClazz() {
        return clazz;
    }

    public void setClazz(short clazz) {
        this.clazz = clazz;
    }
}
