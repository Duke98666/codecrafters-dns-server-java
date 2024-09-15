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

    private void set(int position, short value) {
        int currPosition = bufRespBuffer.position();
        bufRespBuffer.position(position).putShort(value).position(currPosition);
    }

    public void setID(short id) {
        set(DnsConstants.DnsHeader.ID_POSITION, id);
    }

    public void setQOATRR(short qoatrr) {
        set(DnsConstants.DnsHeader.QOATRR_POSITION, qoatrr);
    }

    public short getQDCOUNT() {
        return get(DnsConstants.DnsHeader.QDCOUNT_POSITION);
    }

    public void setQDCOUNT(short qdcount) {
        set(DnsConstants.DnsHeader.QDCOUNT_POSITION, qdcount);
    }

    public short getANCOUNT() {
        return get(DnsConstants.DnsHeader.ANCOUNT_POSITION);
    }

    public void setANCOUNT(short ancount) {
        set(DnsConstants.DnsHeader.ANCOUNT_POSITION, ancount);
    }

    public void setNSCOUNT(short nscount) {
        set(DnsConstants.DnsHeader.NSCOUNT_POSITION, nscount);
    }

    public void setARCOUNT(short arcount) {
        set(DnsConstants.DnsHeader.ARCOUNT_POSITION, arcount);
    }

}
