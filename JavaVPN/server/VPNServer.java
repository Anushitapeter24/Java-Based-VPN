package server;
import common.SSLUtils;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSocket;
import java.io.IOException;

public class VPNServer {
    public static void main(String[] args) throws Exception {
        SSLServerSocket serverSocket = (SSLServerSocket) SSLUtils.getServerSocketFactory("keystore/serverkeystore.jks", "password").createServerSocket(8443);
        while (true) {
            SSLSocket socket = (SSLSocket) serverSocket.accept();
            new Thread(new ClientHandler(socket)).start();
        }
    }
}
