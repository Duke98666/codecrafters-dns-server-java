public class DnsResponse extends DnsMessage {

    private final DnsQuery query;

    private final DnsAnswer answer;

    public DnsResponse(byte[] respBufBytes, DnsQuery query) {
        super(respBufBytes);
        this.query = query;
        answer = new DnsAnswer(byteBuffer);
        init();
    }

    private void init() {
        setHeader();
        setAnswer();
    }

    private void setHeader() {
        header.setID(query.header.getID());

        // Mimic OPCODE and RD
        short queryQOATRZR = (short) (0B0_1111_0_0_1_0_000_0000 & query.header.getQOATRZR());
        short queryOPCODE = (short) (0B0_1111_0_0_0_0_000_0000 & queryQOATRZR);
        short rd = (short) (queryOPCODE == 0 ? 0 : 4);
        short qoatrzr = (short) (DnsConstants.DnsHeader.QR | queryQOATRZR | rd);
        header.setQOATRZR(qoatrzr);
    }

    private void setAnswer() {
        answer.setName(DnsConstants.DnsAnswer.NAME);
        answer.setType(DnsConstants.DnsAnswer.TYPE);
        answer.setClass(DnsConstants.DnsAnswer.CLASS);
        answer.setTTL(DnsConstants.DnsAnswer.TTL);
        answer.setLength(DnsConstants.DnsAnswer.RDLENGTH);
        answer.setData(DnsConstants.DnsAnswer.RDATA);

        // Incrementing answer count
        header.setANCOUNT((short) (header.getANCOUNT() + 1));
    }

}
