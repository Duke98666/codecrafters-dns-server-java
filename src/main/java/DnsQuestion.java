import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class DnsQuestion {

    private final ByteBuffer bufRespBuffer;

    DnsQuestion(ByteBuffer bufRespBuffer) {
        this.bufRespBuffer = bufRespBuffer;
    }

    public void setName(String name) {
        String[] labels = name.split("\\.");
        for (String label : labels) {
            byte[] bytes = label.getBytes(StandardCharsets.UTF_8);
            // content length + content
            bufRespBuffer.put((byte) bytes.length).put(bytes);
        }
        bufRespBuffer.put((byte) 0); // null byte
    }

    public void setType(short type) {
        bufRespBuffer.putShort(type);
    }

    public void setClass(short questionClass) {
        bufRespBuffer.putShort(questionClass);
    }

}
