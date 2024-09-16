import java.nio.ByteBuffer;
import java.util.Arrays;

public abstract class DnsMessage {

    protected final ByteBuffer byteBuffer;

    protected final DnsHeader header;

    protected final DnsQuestion question;

    public DnsMessage(byte[] bufferBytes) {
        // Position starting from question section - 12
        byteBuffer = ByteBuffer.wrap(bufferBytes).position(12);
        header = new DnsHeader(byteBuffer);
        question = new DnsQuestion(byteBuffer);
        init();
    }

    private void init() {
        setQuestion();
    }

    public byte[] getBufferBytes() {
        int position = byteBuffer.position();
        byte[] bufferBytes = byteBuffer.rewind().array();
        byteBuffer.position(position);
        return Arrays.copyOf(bufferBytes, bufferBytes.length);
    }

    private void setQuestion() {
        question.setName(DnsConstants.DnsQuestion.NAME);
        question.setType(DnsConstants.DnsQuestion.TYPE);
        question.setClass(DnsConstants.DnsQuestion.CLASS);

        // Incrementing question count
        header.setQDCOUNT((short) (header.getQDCOUNT() + 1));
    }

}
