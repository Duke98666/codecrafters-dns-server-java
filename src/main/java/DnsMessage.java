import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class DnsMessage {

    private final byte[] bufResponse;

    private final DnsHeader header;

    private final DnsQuestion question;

    public DnsMessage() {
        bufResponse = new byte[512];
        header = new DnsHeader(bufResponse);
        question = new DnsQuestion(bufResponse);
        init();
    }

    private void init() {
        setHeader();
        setQuestion();
    }

    public byte[] getBufResponse() {
        return Arrays.copyOf(bufResponse, bufResponse.length);
    }

    private void setHeader() {
        // Packet Identifier (ID) - 16 bits = 2 bytes = short
        short ID = (short) 1234;
        header.setID(ID);

        // Query/Response Indicator (QR) - 1 bit
        // Operation Code (OPCODE) - 4 bits
        // Authoritative Answer (AA) - 1 bit
        // Truncation (TC) - 1 bit
        // Recursion Desired (RD) - 1 bit
        // Recursion Available (RA) - 1 bit
        // Reserved (Z) - 3 bits
        // Response Code (RCODE) - 4 bits
        // 1 + 4 + 1 + 1 + 1 + 1 + 3 + 4 = 16 bits = short
        short QOATRR = (short) (1 << 15);
        header.setQOATRR(QOATRR);

        // Question Count (QDCOUNT) - 16 bits = 2 bytes = short
        short QDCOUNT = 0;
        header.setQDCOUNT(QDCOUNT);

        // Answer Record Count (ANCOUNT) - 16 bits = 2 bytes = short
        short ANCOUNT = 0;
        header.setANCOUNT(ANCOUNT);

        // Authority Record Count (NSCOUNT) - 16 bits = 2 bytes = short
        short NSCOUNT = 0;
        header.setNSCOUNT(NSCOUNT);

        // Additional Record Count (ARCOUNT) - 16 bits = 2 bytes = short
        short ARCOUNT = 0;
        header.setARCOUNT(ARCOUNT);
    }

    private void setQuestion() {
        // Name - bytes
        String name = "codecrafters.io";
        question.setName(name);

        // Type - 2 bytes = short
        short type = (short) 1;
        question.setType(type);

        // Class - 2 bytes = short
        short questionClass = (short) 1;
        question.setClass(questionClass);

        // Incrementing question count
        header.setQDCOUNT((short) (header.getQDCOUNT() + 1));
    }

}
