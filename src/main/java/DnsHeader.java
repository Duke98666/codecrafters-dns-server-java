import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class DnsHeader {

    private final ByteBuffer bufRespBuffer;

    DnsHeader(byte[] bufResponse) {
        bufRespBuffer = ByteBuffer.wrap(bufResponse)
                .order(ByteOrder.BIG_ENDIAN);
    }

    public void setID(short id) {
        bufRespBuffer.position(0)
                .putShort(id);
    }

    public void setQOATRR(short qoatrr) {
        bufRespBuffer.position(16)
                .putShort(qoatrr);
    }

    public short getQDCOUNT() {
        return bufRespBuffer.position(32)
                .getShort();
    }

    public void setQDCOUNT(short qdcount) {
        bufRespBuffer.position(32)
                .putShort(qdcount);
    }

    public void setANCOUNT(short ancount) {
        bufRespBuffer.position(48)
                .putShort(ancount);
    }

    public void setNSCOUNT(short nscount) {
        bufRespBuffer.position(64)
                .putShort(nscount);
    }

    public void setARCOUNT(short arcount) {
        bufRespBuffer.position(80)
                .putShort(arcount);
    }

}
