import java.nio.ByteBuffer;
import java.util.Arrays;

public class DnsMessage {

    private final ByteBuffer byteBuffer;

    private final DnsHeader header;

    private final DnsQuestion question;

    private final DnsAnswer answer;

    public DnsMessage() {
        byteBuffer = ByteBuffer.allocate(512);
        header = new DnsHeader(byteBuffer);
        question = new DnsQuestion(byteBuffer);
        answer = new DnsAnswer(byteBuffer);
        init();
    }

    private void init() {
        setHeader();
        setQuestion();
        setAnswer();
    }

    public byte[] getBufResponse() {
        byte[] bufResponse = byteBuffer.rewind().array();
        return Arrays.copyOf(bufResponse, bufResponse.length);
    }

    private void setHeader() {
        header.setID(DnsConstants.DnsHeader.ID);
        header.setQOATRR(DnsConstants.DnsHeader.QOATRR);
        header.setQDCOUNT(DnsConstants.DnsHeader.QDCOUNT);
        header.setANCOUNT(DnsConstants.DnsHeader.ANCOUNT);
        header.setNSCOUNT(DnsConstants.DnsHeader.NSCOUNT);
        header.setARCOUNT(DnsConstants.DnsHeader.ARCOUNT);
    }

    private void setQuestion() {
        question.setName(DnsConstants.DnsQuestion.NAME);
        question.setType(DnsConstants.DnsQuestion.TYPE);
        question.setClass(DnsConstants.DnsQuestion.CLASS);

        // Incrementing question count
        header.setQDCOUNT((short) (header.getQDCOUNT() + 1));
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
