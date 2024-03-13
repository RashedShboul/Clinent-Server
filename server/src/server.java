import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        ServerSocket serverSocket = null;
        InputStreamReader inReader = null;
        OutputStreamWriter outWriter = null;
        BufferedReader bfReader = null;
        BufferedWriter bfWriter = null;

        serverSocket = new ServerSocket(8080);

        // first while loop is to accept a client connection
        while (true) {
            try {
                socket = serverSocket.accept();

                inReader = new InputStreamReader(socket.getInputStream());
                outWriter = new OutputStreamWriter(socket.getOutputStream());

                bfReader = new BufferedReader(inReader);
                bfWriter = new BufferedWriter(outWriter);

                //second while loop to receive a message from client
                while (true) {
                    String clientMsg = bfReader.readLine();
                    // if the client terminate we won't get NullPointerException
                    if (clientMsg == null) break;
                    System.out.println("client: " + clientMsg);

                    bfWriter.write("message received successfully.");
                    bfWriter.newLine();
                    bfWriter.flush();

                    if (clientMsg.equalsIgnoreCase("exit")) break;

                }

                /*socket.close();
                bfWriter.close();
                bfReader.close();
                inReader.close();
                outWriter.close();*/
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }finally {
                try {
                    if (socket != null) socket.close();
                    if (bfWriter != null) bfWriter.close();
                    if (bfReader != null) bfReader.close();
                    if (inReader != null) inReader.close();
                    if (outWriter != null) outWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
