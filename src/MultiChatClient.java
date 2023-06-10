import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MultiChatClient {
    private String serverHost;
    private int serverPort;
    private Socket clientSocket;
    private BufferedReader reader;
    private PrintWriter writer;

    public MultiChatClient(String serverHost, int serverPort) {
        this.serverHost = serverHost;
        this.serverPort = serverPort;
    }

    public void start() {
        try {
            clientSocket = new Socket(serverHost, serverPort);
            System.out.println("연결되었습니다." + serverHost + ":" + serverPort);

            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new PrintWriter(clientSocket.getOutputStream(), true);

            Thread inputThread = new Thread(new InputThread());
            inputThread.start();

            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println("메세지:" + message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null)
                    reader.close();
                if (writer != null)
                    writer.close();
                if (clientSocket != null)
                    clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) {
        writer.println(message);
    }

    public static void main(String[] args) {
        String serverHost = "localhost";
        int serverPort = 1112;

        MultiChatClient client = new MultiChatClient(serverHost, serverPort);
        client.start();
    }

    private class InputThread implements Runnable {
        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);
            String message;
            while (true) {
                message = scanner.nextLine();
                sendMessage(message);
            }
        }
    }
}
