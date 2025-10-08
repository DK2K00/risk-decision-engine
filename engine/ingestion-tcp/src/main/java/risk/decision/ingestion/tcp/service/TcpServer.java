package risk.decision.ingestion.tcp.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import risk.decision.common.util.JsonValidator;

import jakarta.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

@Service
public class TcpServer {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostConstruct
    public void startServer() {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(9090)) {
                System.out.println("TCP Server started on port 9090...");

                while (true) {
                    Socket socket = serverSocket.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                    String inputLine = in.readLine();

                    try {
                        JSONObject json = new JSONObject(inputLine);
                        JsonValidator.validateTransaction(json.toString());

                        kafkaProducerService.sendTransaction(json.toString());
                        out.println("Transaction accepted and published to Kafka");
                    } catch (Exception e) {
                        out.println("Invalid transaction: " + e.getMessage());
                    } finally {
                        socket.close();
                    }
                }

            } catch (Exception e) {
                throw new RuntimeException("Error in TCP server", e);
            }
        }).start();
    }
}
