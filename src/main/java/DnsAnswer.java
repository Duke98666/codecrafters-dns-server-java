import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class DnsAnswer {

    private final ByteBuffer bufRespBuffer;

    DnsAnswer(ByteBuffer bufRespBuffer) {
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

    public void setClass(short answerClass) {
        bufRespBuffer.putShort(answerClass);
    }

    public void setTTL(int ttl) {
        bufRespBuffer.putInt(ttl);
    }

    public void setLength(short length) {
        bufRespBuffer.putShort(length);
    }

    public void setData(String data) {
        String[] digits = data.split("\\.");
        for (String digit : digits) {
            byte[] bytes = digit.getBytes(StandardCharsets.UTF_8);
            bufRespBuffer.put(bytes);
        }
    }

}
