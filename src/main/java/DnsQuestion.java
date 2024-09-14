import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

public class DnsQuestion {

    private final ByteBuffer bufRespBuffer;

    private int typePosition;

    DnsQuestion(byte[] bufResponse) {
        bufRespBuffer = ByteBuffer.wrap(bufResponse)
                .order(ByteOrder.BIG_ENDIAN);
    }

    public void setName(String name) {
        bufRespBuffer.position(96);
        String[] labels = name.split(".");
        for (String label : labels) {
            byte[] bytes = label.getBytes(StandardCharsets.UTF_8);
            bufRespBuffer.put((byte) bytes.length).put(bytes);
        }
        bufRespBuffer.put((byte) 0); // null byte
        // name.length() - the number of content characters
        // labels.length - the number of length bytes for each label
        // null byte - 1 byte
        typePosition = name.length() + labels.length + 1;
    }

    public void setType(short type) {
        bufRespBuffer.position(typePosition)
                .putShort(type);
    }

    public void setClass(short questionClass) {
        bufRespBuffer.position(typePosition + 16)
                .putShort(questionClass);
    }

}
