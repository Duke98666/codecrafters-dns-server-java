package com.dinakarans.dns.util;

public class DnsConstants {

    public static class DnsHeader {
        // Query/Response Indicator (QR) - 1 bit
        // Operation Code (OPCODE) - 4 bits
        // Authoritative Answer (AA) - 1 bit
        // Truncation (TC) - 1 bit
        // Recursion Desired (RD) - 1 bit
        // Recursion Available (RA) - 1 bit
        // Reserved (Z) - 3 bits
        // Response Code (RCODE) - 4 bits
        // 1 + 4 + 1 + 1 + 1 + 1 + 3 + 4 = 16 bits = short
        public static final short QOATRZR = (short) (0B1_0000_0_0_0_0_000_0000);

        // ID Position
        public static final short ID_POSITION = 0;

        // QOATRZR Position
        public static final short QOATRZR_POSITION = 2;

        // QDCOUNT Position
        public static final int QDCOUNT_POSITION = 4;

        // ANCOUNT Position
        public static final int ANCOUNT_POSITION = 6;

        // NSCOUNT Position
        public static final short NSCOUNT_POSITION = 8;

        // ARCOUNT Position
        public static final short ARCOUNT_POSITION = 10;
    }

    public static class DnsAnswer {
        // TTL - Time To Live - 4 bytes = integer
        public static final int TTL = 60;

        // TTL - Time To Live - 4 bytes = integer
        public static final short RDLENGTH = 4;

        // Data - IP Address - bytes
        public static final int RDATA = 0X08080808;
    }

}
