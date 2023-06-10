import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class MultiChatServer {
    private List<Socket> clients;
    private ServerSocket serverSocket;

    public MultiChatServer(int port) {
        clients = new ArrayList<>();

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        System.out.println("시작");

        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                clients.add(clientSocket);
                System.out.println("새로운 연결이 생성되었습니다." + clientSocket);

                ClientHandler clientHandler = new ClientHandler(clientSocket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void broadcastMessage(String message, Socket sender) {
        for (Socket client : clients) {
            if (client != sender) {
                try {
                    PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
                    writer.println(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        int port = 1112;
        MultiChatServer server = new MultiChatServer(port);
        server.start();
    }

    private class ClientHandler implements Runnable {
        private Socket clientSocket;
        private BufferedReader reader;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;

            try {
                reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println("클라이언트 메세지:" + message);
                    broadcastMessage(message, clientSocket);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clients.remove(clientSocket);
                    clientSocket.close();
                    System.out.println("연결을 끊었습니다. " + clientSocket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
