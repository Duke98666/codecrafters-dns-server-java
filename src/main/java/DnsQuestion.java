import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class DnsQuestion {

    private final ByteBuffer byteBuffer;

    DnsQuestion(ByteBuffer byteBuffer) {
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

    public void setClass(short questionClass) {
        byteBuffer.putShort(questionClass);
    }

}
