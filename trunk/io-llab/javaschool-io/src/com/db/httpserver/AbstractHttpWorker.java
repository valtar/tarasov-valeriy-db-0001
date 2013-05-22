package com.db.httpserver;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public abstract class AbstractHttpWorker {

	protected static final int HTTP_BAD_REQUEST = 400;
    protected static final int HTTP_NOT_FOUND = 404;
    protected static final int HTTP_BAD_METHOD = 405;
    protected static final int HTTP_OK = 200;

    protected static final String CRLF = "\r\n";

    private Socket s;

    public final void handleRequest(Socket s) throws IOException {
        this.s = s;
        final BufferedInputStream is = new BufferedInputStream(s.getInputStream());
        final PrintStream ps = new PrintStream(s.getOutputStream(), true);
        final Request request = new RequestScanner(is).scan();
        switch (request.getMethod()) {
            case RequestScanner.HEAD :
                doHead(request, ps);
                break;
            case RequestScanner.GET :
                doGet(request, ps);
                break;
            case RequestScanner.ERROR:
                ps.println("HTTP/1.0 " + HTTP_BAD_REQUEST + " syntax-error in request.");
                break;        
            default :
                ps.print("HTTP/1.0 " + HTTP_BAD_METHOD + " unsupported method type: ");
                ps.println(request.getURI());
        }
        ps.close();
    }

    protected final String getClientName() {
        return s.getInetAddress().getHostAddress() + "(" + s.getPort() + ")";
    }

    protected abstract void doHead(Request r, PrintStream ps) throws IOException;

    protected abstract void doGet(Request r, PrintStream ps) throws IOException;
}
