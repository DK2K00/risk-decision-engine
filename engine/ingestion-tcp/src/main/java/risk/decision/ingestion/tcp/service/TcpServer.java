package risk.decision.ingestion.tcp.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

@Service
public class TcpServer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topic = "transactions";

    public TcpServer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostConstruct
    public void startServer() {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(9090)) {
                System.out.println("TCP Server listening on port 9090...");

                while (true) {
                    Socket socket = serverSocket.accept();
                    BufferedReader reader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                    );

                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println("Received TCP message: " + line);
                        kafkaTemplate.send(topic, line);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
