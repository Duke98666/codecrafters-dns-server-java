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
        bufRespBuffer.position(12);
        // position - 12
        typePosition = 12;
        String[] labels = name.split("\\.");
        for (String label : labels) {
            byte[] bytes = label.getBytes(StandardCharsets.UTF_8);
            bufRespBuffer.put((byte) bytes.length).put(bytes);
            // content length (bytes.length) - 1 byte
            // content characters (bytes) - bytes.length bytes
            typePosition += 1 + bytes.length;
        }
        bufRespBuffer.put((byte) 0); // null byte
        // null byte - 1 byte
        ++typePosition;
    }

    public void setType(short type) {
        bufRespBuffer.position(typePosition)
                .putShort(type);
    }

    public void setClass(short questionClass) {
        bufRespBuffer.position(typePosition + 2)
                .putShort(questionClass);
    }

}
