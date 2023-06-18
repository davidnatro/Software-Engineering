import constants.UserRegistrationStatus;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements AutoCloseable {
    private ServerSocket server;

    private Socket client;

    private DataInputStream inputStream;

    private DataOutputStream outputStream;

    private final Map<String, Socket> users = new HashMap<String, Socket>();

    public void run() {
        try {
            server = new ServerSocket(ChatConfiguration.PORT);
        } catch (final IOException exception) {
            System.out.println(exception.getMessage());
        }

        ExecutorService es = Executors.newSingleThreadExecutor();
        es.execute(this::monitorNewMessages);

        while (true) {
            try {
                client = server.accept();
                if (client != null) {
                    outputStream = new DataOutputStream(client.getOutputStream());
                    inputStream = new DataInputStream(client.getInputStream());

                    String name = inputStream.readUTF();
                    if (users.containsKey(name)) {
                        outputStream.writeUTF(UserRegistrationStatus.FAIL.toString());
                        client.close();
                        continue;
                    }

                    users.put(name, client);
                    outputStream.writeUTF(UserRegistrationStatus.OK.toString());
                }
            } catch (final IOException exception) {
                System.out.println(exception.getMessage());
            }
        }

    }

    private void monitorNewMessages() {
        DataInputStream inputStream;
        DataOutputStream outputStream;

        while (true) {
            try {
                for (final Socket socket1 : users.values()) {
                    inputStream = new DataInputStream(socket1.getInputStream());
                    if (inputStream.available() > 0) {
                        String message = inputStream.readUTF();
                        for (final Socket socket2 : users.values()) {
                            outputStream = new DataOutputStream(socket2.getOutputStream());
                            outputStream.writeUTF(message);
                        }
                    }
                }

                Thread.sleep(0);
            } catch (final IOException | InterruptedException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    @Override
    public void close() throws IOException {
        client.close();
        inputStream.close();
        outputStream.close();
    }
}
