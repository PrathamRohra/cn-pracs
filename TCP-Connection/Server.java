import java.net.*;
import java.io.*;

public class Server {
    private Socket socket = null;                // Create a socket for accepting client connections.
    private ServerSocket server = null;          // Create a server socket for listening to incoming connections.
    private DataInputStream in = null;           // Create an input stream for receiving data from the client.
    private DataOutputStream out = null;         // Create an output stream for sending data to the client.

    public Server(int port) {
        try {
            server = new ServerSocket(port);     // Start the server on the specified port.
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            socket = server.accept();            // Wait for a client to connect and accept the connection.
            System.out.println("Client accepted");

            in = new DataInputStream(new BufferedInputStream(socket.getInputStream())); // Create input stream for receiving data from the client.
            out = new DataOutputStream(socket.getOutputStream());                       // Create output stream for sending data to the client.

            String line = "";

            while (!line.equals("Over")) {
                try {
                    line = in.readUTF();      // Receive an expression from the client.
                    if (!line.equals("Over")) {
                        double result = evaluateExpression(line); // Evaluate the expression and get the result.
                        out.writeUTF(Double.toString(result));    // Send the result back to the client.
                    }
                } catch (IOException i) {
                    System.out.println(i);
                }
            }

            System.out.println("Closing connection");
            socket.close();                // Close the socket.
            in.close();                   // Close the input stream.
            out.close();                  // Close the output stream.
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    private double evaluateExpression(String expression) {
        // Parse and evaluate a mathematical expression sent by the client.
        // The result is sent back to the client.
        // Supported operations: +, -, *, /
        // Handles division by zero and invalid operators.

        String[] tokens = expression.split(" ");
        double operand1 = Double.parseDouble(tokens[0]);
        String operator = tokens[1];
        double operand2 = Double.parseDouble(tokens[2]);

        double result = 0.0;

        switch (operator) {
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "*":
                result = operand1 * operand2;
                break;
            case "/":
                if (operand2 != 0) {
                    result = operand1 / operand2;
                } else {
                    System.out.println("Division by zero is not allowed.");
                }
                break;
            default:
                System.out.println("Invalid operator.");
        }

        return result;
    }


    public static void main(String args[]) {
        Server server = new Server(5000);   // Start the server on port 5000.
    }
}
