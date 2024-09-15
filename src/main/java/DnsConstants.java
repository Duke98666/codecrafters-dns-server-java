class DnsConstants {

    static class DnsHeader {
        // Packet Identifier (ID) - 16 bits = 2 bytes = short
        static final short ID = (short) 1234;

        // Query/Response Indicator (QR) - 1 bit
        // Operation Code (OPCODE) - 4 bits
        // Authoritative Answer (AA) - 1 bit
        // Truncation (TC) - 1 bit
        // Recursion Desired (RD) - 1 bit
        // Recursion Available (RA) - 1 bit
        // Reserved (Z) - 3 bits
        // Response Code (RCODE) - 4 bits
        // 1 + 4 + 1 + 1 + 1 + 1 + 3 + 4 = 16 bits = short
        static final short QOATRR = (short) (1 << 15);

        // Question Count (QDCOUNT) - 16 bits = 2 bytes = short
        static final short QDCOUNT = 0;

        // Answer Record Count (ANCOUNT) - 16 bits = 2 bytes = short
        static final short ANCOUNT = 0;

        // Authority Record Count (NSCOUNT) - 16 bits = 2 bytes = short
        static final short NSCOUNT = 0;

        // Additional Record Count (ARCOUNT) - 16 bits = 2 bytes = short
        static final short ARCOUNT = 0;

        // Position QDCOUNT
        static final int QDCOUNT_POSITION = 4;

        // Position QDCOUNT
        static final int ANCOUNT_POSITION = 6;
    }

    static class DnsQuestion {
        // Name - bytes
        static final String NAME = "codecrafters.io";

        // Type - 2 bytes = short
        static final short TYPE = (short) 1;

        // Class - 2 bytes = short
        static final short CLASS = (short) 1;
    }

    static class DnsAnswer {
        // Name - bytes
        static final String NAME = "codecrafters.io";

        // Type - 2 bytes = short
        static final short TYPE = (short) 1;

        // Class - 2 bytes = short
        static final short CLASS = (short) 1;

        // TTL - Time To Live - 4 bytes = integer
        static final int TTL = 60;

        // TTL - Time To Live - 4 bytes = integer
        static final short RDLENGTH = 4;

        // Data - IP Address - bytes
        static final String RDATA = "8.8.8.8";
    }

}
