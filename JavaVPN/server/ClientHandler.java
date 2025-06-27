package server;
import javax.net.ssl.SSLSocket;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClientHandler implements Runnable {
    private SSLSocket socket;

    public ClientHandler(SSLSocket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String urlStr = in.readLine();
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader urlIn = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = urlIn.readLine()) != null) {
                out.write(line);
                out.newLine();
            }
            out.flush();
            urlIn.close();
            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            try {
                socket.close();
            } catch (IOException ignored) {}
        }
    }
}
