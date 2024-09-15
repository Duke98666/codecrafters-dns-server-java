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
        bufRespBuffer.position(2)
                .putShort(qoatrr);
    }

    public short getQDCOUNT() {
        return bufRespBuffer.position(4)
                .getShort();
    }

    public void setQDCOUNT(short qdcount) {
        bufRespBuffer.position(4)
                .putShort(qdcount);
    }

    public void setANCOUNT(short ancount) {
        bufRespBuffer.position(6)
                .putShort(ancount);
    }

    public void setNSCOUNT(short nscount) {
        bufRespBuffer.position(8)
                .putShort(nscount);
    }

    public void setARCOUNT(short arcount) {
        bufRespBuffer.position(10)
                .putShort(arcount);
    }

}
