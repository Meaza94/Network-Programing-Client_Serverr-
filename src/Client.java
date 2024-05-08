import java.io.*;
import java.net.Socket;
import java.util.Scanner;

// The Client class connects to a server and sends messages.
public class Client {
    // The main method is the entry point of the client application.
    public static void main(String[] args) {
        // Establishes a connection to the server at 'localhost' on port 1254.
        try (Socket socket = new Socket("localhost", 1254);
             // Writer to send messages to the server.
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             // Reader to receive messages from the server.
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				// Scanner to read user input from the console.
			Scanner scanner = new Scanner(System.in)) {

            // Continuously reads user input and sends it to the server.
            while (true) {
                // Reads a line of text from the console.
            	System.out.println("Enter a message to send to the server:");
                String messageToSend = scanner.nextLine();
                // Sends the message to the server.
                writer.write(messageToSend);
                writer.newLine();
                writer.flush();

                // Waits for and prints the response from the server.
                String messageFromServer = reader.readLine();
                System.out.println("Server: " + messageFromServer);

                // Breaks the loop if 'exit' is typed, closing the connection.
                if (messageToSend.equalsIgnoreCase("exit")) {
                    break;
                }
            }
            
        // Catches and prints any IOException that may occur during the operation.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
