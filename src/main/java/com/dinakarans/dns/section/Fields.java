package com.dinakarans.dns.section;

public class Fields {

    public static enum Header {
        ID, QR, OPCODE, AA, TC, RD, RA, Z, RCODE, QDCOUNT, ANCOUNT, NSCOUNT, ARCOUNT
    }

    public static enum Question {
        Name, Type, Class
    }

    public static enum Answer {
        Name, Type, Class, TTL, RDLength, RData
    }

}
