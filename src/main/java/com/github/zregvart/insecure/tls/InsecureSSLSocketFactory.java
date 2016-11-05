package com.github.zregvart.insecure.tls;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class InsecureSSLSocketFactory extends SSLSocketFactory {

    private static final class TrustAll implements X509TrustManager {
        private static final X509Certificate[] EMPTY = new X509Certificate[0];

        public void checkClientTrusted(final X509Certificate[] chain, final String authType)
                throws CertificateException {
        }

        public void checkServerTrusted(final X509Certificate[] chain, final String authType)
                throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return EMPTY;
        }
    }

    private final SSLSocketFactory delegate;

    public InsecureSSLSocketFactory() throws GeneralSecurityException {
        final SSLContext context = SSLContext.getInstance("TLS");
        context.init(null, new TrustManager[] { new TrustAll() }, null);

        delegate = context.getSocketFactory();
    }

    @Override
    public Socket createSocket(final InetAddress host, final int port) throws IOException {
        return delegate.createSocket(host, port);
    }

    @Override
    public Socket createSocket(final InetAddress address, final int port, final InetAddress localAddress,
            final int localPort) throws IOException {
        return delegate.createSocket(address, port, localAddress, localPort);
    }

    @Override
    public Socket createSocket(final Socket s, final String host, final int port, final boolean autoClose)
            throws IOException {
        return delegate.createSocket(s, host, port, autoClose);
    }

    @Override
    public Socket createSocket(final String host, final int port) throws IOException, UnknownHostException {
        return delegate.createSocket(host, port);
    }

    @Override
    public Socket createSocket(final String host, final int port, final InetAddress localHost, final int localPort)
            throws IOException, UnknownHostException {
        return delegate.createSocket(host, port, localHost, localPort);
    }

    @Override
    public String[] getDefaultCipherSuites() {
        return delegate.getDefaultCipherSuites();
    }

    @Override
    public String[] getSupportedCipherSuites() {
        return delegate.getSupportedCipherSuites();
    }

}
