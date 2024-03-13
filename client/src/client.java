import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class client {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        InputStreamReader inReader = null;
        OutputStreamWriter outWriter = null;
        BufferedReader bfIn = null;
        BufferedWriter bfOut = null;

            try {
                socket = new Socket("localhost", 8080);
                inReader = new InputStreamReader(socket.getInputStream());
                outWriter = new OutputStreamWriter(socket.getOutputStream());

                bfIn = new BufferedReader(inReader);
                bfOut = new BufferedWriter(outWriter);

                Scanner scanner = new Scanner(System.in);

                while (true) {
                    String msg = scanner.nextLine();

                    bfOut.write(msg);
                    bfOut.newLine();
                    bfOut.flush();

                    System.out.println("server: " + bfIn.readLine());

                    if (msg.equalsIgnoreCase("exit")) break;

                }


            } catch (IOException e) {
                throw new RuntimeException(e);

            } finally {
                try {
                    if (socket != null) socket.close();
                    if (inReader != null) inReader.close();
                    if (outWriter != null) outWriter.close();
                    if (bfIn != null) bfIn.close();
                    if (bfOut != null) bfOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
    }
}
