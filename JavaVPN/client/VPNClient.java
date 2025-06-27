package client;
import common.SSLUtils;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.util.Scanner;

public class VPNClient {
    public static void main(String[] args) throws Exception {
        SSLSocketFactory factory = SSLUtils.getClientSocketFactory("keystore/clienttruststore.jks", "password");
        SSLSocket socket = (SSLSocket) factory.createSocket("localhost", 8443);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter URL (e.g., http://example.com): ");
        String url = scanner.nextLine();
        out.write(url);
        out.newLine();
        out.flush();
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
        socket.close();
    }
}
