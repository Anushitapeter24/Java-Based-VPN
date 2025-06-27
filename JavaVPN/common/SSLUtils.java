package common;
import javax.net.ssl.*;
import java.io.FileInputStream;
import java.security.KeyStore;

public class SSLUtils {
    public static SSLServerSocketFactory getServerSocketFactory(String keystorePath, String password) throws Exception {
        KeyStore ks = KeyStore.getInstance("JKS");
        ks.load(new FileInputStream(keystorePath), password.toCharArray());
        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(ks, password.toCharArray());
        SSLContext ctx = SSLContext.getInstance("TLS");
        ctx.init(kmf.getKeyManagers(), null, null);
        return ctx.getServerSocketFactory();
    }

    public static SSLSocketFactory getClientSocketFactory(String truststorePath, String password) throws Exception {
        KeyStore ts = KeyStore.getInstance("JKS");
        ts.load(new FileInputStream(truststorePath), password.toCharArray());
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(ts);
        SSLContext ctx = SSLContext.getInstance("TLS");
        ctx.init(null, tmf.getTrustManagers(), null);
        return ctx.getSocketFactory();
    }
}
