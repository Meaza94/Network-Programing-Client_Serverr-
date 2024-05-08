
// Server Code
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

// The Server class listens for client connections and receives messages.	
public class Server {
       // The main method is the entry point of the server application.
    public static void main(String[] args) {
        // Try-with-resources statement to ensure the server socket is closed properly.
        try (ServerSocket serverSocket = new ServerSocket(1254)) {
            // Server starts listening on port 1254.
            System.out.println("Server is on port " + 1254);
            
            // Infinite loop to keep the server running.
            while (true) {
                // Accepts a connection from a client.
                try (Socket socket = serverSocket.accept();
                     // Reader to receive messages from the client.
                     BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     // Writer to send messages to the client.
                     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

                    String messageFromClient;
                    // Reads messages from the client until "exit" is received.
                    while ((messageFromClient = reader.readLine()) != null) {
                        // Prints the client's message to the console.
                        System.out.println("Client: " + messageFromClient);

                        // Sends an acknowledgment message to the client.
                        writer.write("Message received");
                        writer.newLine();
                        writer.flush();

                        // Breaks the loop if "exit" message is received.
                        if (messageFromClient.equalsIgnoreCase("exit")) {
                            break;
                        }
                    }
                // Catches any IOException during the interaction with the client.
                } catch (IOException e) {
                    System.out.println("Exception in connection with a client: " + e.getMessage());
                }
            }
        // Catches any IOException when trying to open the server socket.
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
        }
    }
}
