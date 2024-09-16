import java.nio.ByteBuffer;

public class DnsHeader {

    private final ByteBuffer byteBuffer;

    DnsHeader(ByteBuffer byteBuffer) {
        this.byteBuffer = byteBuffer;
    }

    private short get(int position) {
        int currPosition = byteBuffer.position();
        short value = byteBuffer.position(position).getShort();
        byteBuffer.position(currPosition);
        return value;
    }

    private void set(int position, short value) {
        int currPosition = byteBuffer.position();
        byteBuffer.position(position).putShort(value).position(currPosition);
    }

    public short getID() {
        return get(DnsConstants.DnsHeader.ID_POSITION);
    }

    public void setID(short id) {
        set(DnsConstants.DnsHeader.ID_POSITION, id);
    }

    public short getQOATRZR() {
        return get(DnsConstants.DnsHeader.QOATRZR_POSITION);
    }

    public void setQOATRZR(short qoatrzr) {
        set(DnsConstants.DnsHeader.QOATRZR_POSITION, qoatrzr);
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
