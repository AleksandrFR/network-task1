import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketApp {
    public static void main(String[] args) throws IOException {
        int port = 8089;

        while (true) {
            ServerSocket serverSocket = new ServerSocket(port); // порт можете выбрать любой в доступном диапазоне 0-65536. Но чтобы не нарваться на уже занятый - рекомендуем использовать около 8080
            Socket clientSocket = serverSocket.accept(); // ждем подключения
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.printf("New connection accepted. Port.%d%n", clientSocket.getPort());

            out.println("Hi visitor, what is your name?");
            String username = in.readLine();
            out.println(username + ", are you child? (yes/no)");
            String ansver = in.readLine();
            out.println(ansver.equals("yes") ? "Welcome to the kids area, " + username + "! Let's play!" :
                    "Welcome to the adult zone, " + username + "! Have a good rest, or a good working day!");
            serverSocket.close();
        }
    }
}
