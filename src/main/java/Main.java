import com.dinakarans.dns.DnsQuery;
import com.dinakarans.dns.DnsResponse;
import com.dinakarans.dns.section.DnsHeader;
import com.dinakarans.dns.section.DnsQuestion;
import com.dinakarans.dns.util.DnsUtil;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class Main {
    public static void main(String[] args) {
        String resolverIP = args[1].split(":")[0];
        int resolverPort = Integer.parseInt(args[1].split(":")[1]);
        SocketAddress resolver = new InetSocketAddress(resolverIP, resolverPort);

        try (DatagramSocket serverSocket = new DatagramSocket(2053)) {
            while (true) {
                final byte[] queryBytes = new byte[512];
                final DatagramPacket packet = new DatagramPacket(queryBytes, queryBytes.length);
                serverSocket.receive(packet);

                DnsQuery query = DnsUtil.readQuery(queryBytes);
                DnsResponse response = new DnsResponse();
                response.setHeader(new DnsHeader(query.getHeader()));
                response.setQuestions(query.getQuestions());

                DnsQuery resolverQuery = new DnsQuery();
                resolverQuery.setHeader(new DnsHeader(query.getHeader()));
                resolverQuery.getHeader().setQdcount((short) 1);

                for (DnsQuestion question : query.getQuestions()) {
                    resolverQuery.getQuestions().add(question);

                    byte[] resolverRequestBytes = resolverQuery.array();
                    DatagramPacket resolverPacket = new DatagramPacket(resolverRequestBytes,
                            resolverRequestBytes.length, resolver);
                    serverSocket.send(resolverPacket);

                    byte[] resolverResponseBytes = new byte[512];
                    DatagramPacket resolverResponsePacket = new DatagramPacket(resolverResponseBytes,
                            resolverResponseBytes.length);
                    serverSocket.receive(resolverResponsePacket);

                    DnsResponse resolverResponse = DnsUtil.readResponse(resolverResponseBytes);
                    if (!resolverResponse.getAnswers().isEmpty()) {
                        response.getAnswers().add(resolverResponse.getAnswers().getFirst());
                    }

                    resolverQuery.getQuestions().clear();
                }

                response.getHeader().setAncount(response.getHeader().getQdcount());
                byte[] responseBytes = response.array();
                final DatagramPacket packetResponse = new DatagramPacket(responseBytes, responseBytes.length,
                        packet.getSocketAddress());
                serverSocket.send(packetResponse);
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
