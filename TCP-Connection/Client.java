import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {
    private Socket socket = null;                // Create a socket for connecting to the server.
    private DataOutputStream out = null;         // Create an output stream for sending data to the server.
    private DataInputStream in = null;           // Create an input stream for receiving data from the server.
    private Scanner scanner = new Scanner(System.in);

    public Client(String address, int port) {
        try {
            socket = new Socket(address, port);   // Connect to the server at the specified address and port.
            System.out.println("Connected to server");

            out = new DataOutputStream(socket.getOutputStream());      // Create output stream for sending data to the server.
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream())); // Create input stream for receiving data from the server.

            while (true) {
                System.out.print("Enter an expression (or 'Over' to quit): ");
                String expression = scanner.nextLine();
                out.writeUTF(expression);          // Send the user's input to the server.

                if (expression.equals("Over")) {
                    break;
                }

                String result = in.readUTF();      // Receive the result from the server.
                System.out.println("Result: " + result);
            }

            System.out.println("Closing connection");
            socket.close();                      // Close the socket.
            out.close();                         // Close the output stream.
            in.close();                          // Close the input stream.
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) {
        String serverAddress = "127.0.0.1";
        int serverPort = 5000;
        Client client = new Client(serverAddress, serverPort);
    }
}
