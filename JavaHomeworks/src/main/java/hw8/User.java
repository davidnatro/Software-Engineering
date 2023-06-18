package hw8;

import hw8.constants.Errors;
import hw8.constants.Messages;
import hw8.constants.UserRegistrationStatus;
import hw8.exceptions.UserAlreadyExistsException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User implements Runnable, AutoCloseable {
    @EqualsAndHashCode.Include
    @Getter @Setter private String name;

    private final Socket client;

    private final DataOutputStream outputStream;

    private final DataInputStream inputStream;

    public User(final String name) throws IOException, UserAlreadyExistsException {
        this.name = name;
        client = new Socket("127.0.0.1", ChatConfiguration.PORT);
        outputStream = new DataOutputStream(client.getOutputStream());
        inputStream = new DataInputStream(client.getInputStream());

        outputStream.writeUTF(name);
        String response = inputStream.readUTF();
        if (!UserRegistrationStatus.OK.toString().equals(response)) {
            if (UserRegistrationStatus.FAIL.toString().equals(response)) {
                throw new UserAlreadyExistsException(Errors.USER_ALREADY_EXISTS);
            }
        }
    }

    @Override
    public void run() {
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(this::send);
        es.execute(this::receive);
    }

    private void send() {
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print(Messages.INPUT_SIGN);
            try {
                String message = name + ": " + streamReader.readLine();
                outputStream.writeUTF(message);
            } catch (final IOException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private void receive() {
        String message;
        while(true) {
            try {
                message = inputStream.readUTF();
                if (!message.isBlank()) {
                    System.out.println(message);
                    System.out.print(Messages.INPUT_SIGN);
                }
                message = null;
            } catch (final IOException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    @Override
    public void close() throws IOException {
        client.close();
        outputStream.close();
        inputStream.close();
    }
}
