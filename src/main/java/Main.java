import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Main {
    public static void main(String[] args) {
        try (DatagramSocket serverSocket = new DatagramSocket(2053)) {
            while (true) {
                final byte[] buf = new byte[512];
                final DatagramPacket packet = new DatagramPacket(buf, buf.length);
                serverSocket.receive(packet);
                System.out.println("Received data");

                final byte[] respBuf = new byte[512];
                DnsMessage message = new DnsResponse(respBuf, new DnsQuery(buf));
                byte[] bufResponse = message.getBufferBytes();
                final DatagramPacket packetResponse = new DatagramPacket(bufResponse, bufResponse.length,
                        packet.getSocketAddress());
                serverSocket.send(packetResponse);
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
