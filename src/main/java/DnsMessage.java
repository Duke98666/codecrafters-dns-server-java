import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class DnsMessage {

    private final byte[] bufResponse;

    public DnsMessage() {
        // Packet Identifier (ID) - 16 bits = 2 bytes = short
        short ID = (short) 1234;

        // Query/Response Indicator (QR) - 1 bit
        // Operation Code (OPCODE) - 4 bits
        // Authoritative Answer (AA) - 1 bit
        // Truncation (TC) - 1 bit
        // Recursion Desired (RD) - 1 bit
        // 1 + 4 + 1 + 1 + 1 = 8 bits = byte
        byte QOATR = (byte) (1 << 7);

        // Recursion Available (RA) - 1 bit
        // Reserved (Z) - 3 bits
        // Response Code (RCODE) - 4 bits
        // 1 + 3 + 4 = 8 bits = byte
        byte RZR = 0;

        // Question Count (QDCOUNT) - 16 bits = 2 bytes = short
        short QDCOUNT = 0;

        // Answer Record Count (ANCOUNT) - 16 bits = 2 bytes = short
        short ANCOUNT = 0;

        // Authority Record Count (NSCOUNT) - 16 bits = 2 bytes = short
        short NSCOUNT = 0;

        // Additional Record Count (ARCOUNT) - 16 bits = 2 bytes = short
        short ARCOUNT = 0;

        bufResponse = ByteBuffer.allocate(512)
                .order(ByteOrder.BIG_ENDIAN)
                .putShort(ID)
                .put(QOATR)
                .put(RZR)
                .putShort(QDCOUNT)
                .putShort(ANCOUNT)
                .putShort(NSCOUNT)
                .putShort(ARCOUNT)
                .array();
    }

    public byte[] getBufResponse() {
        return Arrays.copyOf(bufResponse, bufResponse.length);
    }

}
