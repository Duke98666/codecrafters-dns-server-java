import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class DnsAnswer {

    private final ByteBuffer byteBuffer;

    DnsAnswer(ByteBuffer byteBuffer) {
        this.byteBuffer = byteBuffer;
    }

    public void setName(String name) {
        String[] labels = name.split("\\.");
        for (String label : labels) {
            byte[] bytes = label.getBytes(StandardCharsets.UTF_8);
            // content length + content
            byteBuffer.put((byte) bytes.length).put(bytes);
        }
        byteBuffer.put((byte) 0); // null byte
    }

    public void setType(short type) {
        byteBuffer.putShort(type);
    }

    public void setClass(short answerClass) {
        byteBuffer.putShort(answerClass);
    }

    public void setTTL(int ttl) {
        byteBuffer.putInt(ttl);
    }

    public void setLength(short length) {
        byteBuffer.putShort(length);
    }

    public void setData(String data) {
        String[] digits = data.split("\\.");
        for (String digit : digits) {
            byte[] bytes = digit.getBytes(StandardCharsets.UTF_8);
            byteBuffer.put(bytes);
        }
    }

}
