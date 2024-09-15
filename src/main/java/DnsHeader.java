import java.nio.ByteBuffer;

public class DnsHeader {

    private final ByteBuffer bufRespBuffer;

    DnsHeader(ByteBuffer bufRespBuffer) {
        this.bufRespBuffer = bufRespBuffer;
    }

    private short get(int position) {
        int currPosition = bufRespBuffer.position();
        short value = bufRespBuffer.position(position).getShort();
        bufRespBuffer.position(currPosition);
        return value;
    }

    public void setID(short id) {
        bufRespBuffer.putShort(id);
    }

    public void setQOATRR(short qoatrr) {
        bufRespBuffer.putShort(qoatrr);
    }

    public short getQDCOUNT() {
        return get(DnsConstants.DnsHeader.QDCOUNT_POSITION);
    }

    public void setQDCOUNT(short qdcount) {
        bufRespBuffer.putShort(qdcount);
    }

    public short getANCOUNT() {
        return get(DnsConstants.DnsHeader.ANCOUNT_POSITION);
    }

    public void setANCOUNT(short ancount) {
        bufRespBuffer.putShort(ancount);
    }

    public void setNSCOUNT(short nscount) {
        bufRespBuffer.putShort(nscount);
    }

    public void setARCOUNT(short arcount) {
        bufRespBuffer.putShort(arcount);
    }

}
