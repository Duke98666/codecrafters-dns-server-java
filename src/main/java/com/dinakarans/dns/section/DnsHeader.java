package com.dinakarans.dns.section;

import com.dinakarans.dns.util.DnsUtil;

import java.nio.ByteBuffer;

public class DnsHeader {

    private short id;

    private short flags;

    private short qdcount;

    private short ancount;

    private short nscount;

    private short arcount;

    public DnsHeader() {
        super();
    }

    public DnsHeader(DnsHeader header) {
        this.id = header.id;
        this.flags = header.flags;
        this.qdcount = header.qdcount;
        this.ancount = header.ancount;
        this.nscount = header.nscount;
        this.arcount = header.arcount;
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public short getFlags() {
        return flags;
    }

    public void setFlags(short flags) {
        this.flags = flags;
    }

    public short getQdcount() {
        return qdcount;
    }

    public void setQdcount(short qdcount) {
        this.qdcount = qdcount;
    }

    public short getAncount() {
        return ancount;
    }

    public void setAncount(short ancount) {
        this.ancount = ancount;
    }

    public short getNscount() {
        return nscount;
    }

    public void setNscount(short nscount) {
        this.nscount = nscount;
    }

    public short getArcount() {
        return arcount;
    }

    public void setArcount(short arcount) {
        this.arcount = arcount;
    }
}
