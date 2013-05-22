package com.db.httpserver;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Properties;

public final class Parameter {
    
    /* the web server's virtual root */
    private static File root = new File(System.getProperty("user.dir"));

    /* the web server's port number */
    private static int port = 8080;

    /* timeout on client connections */
    private static int timeout = 5000;

    /* max # worker threads */
    private static int workers = 5;

    /* where to log to */
    private static PrintStream log = System.out;

    private static Properties props = new Properties();
    
    static {
        try {
            loadProperties();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    private Parameter() { /* is never called. */
    }

    public static File getRoot() {
        return root;
    }

    public static void setPort(int port) {
        if (port > 0) {
            Parameter.port = port;
        }
    }

    public static int getPort() {
        return port;
    }

    public static int getTimeout() {
        return timeout;
    }

    public static int getWorkers() {
        return workers;
    }

    public static PrintStream getLog() {
        return log;
    }

    public static void print() {
        System.out.println("root=" + root);
        System.out.println("timeout=" + timeout);
        System.out.println("workers=" + workers);
        System.out.println("port= " + port);
    }

    private static void loadProperties() throws IOException {
        File f = new File("server.properties");
        if (f.exists()) {
            final InputStream is = new BufferedInputStream(new FileInputStream(f));
            props.load(is);
            is.close();
            String r = props.getProperty("root");
            if (r != null) {
                root = new File(r);
                if (!root.exists()) {
                    throw new Error(root + " doesn't exist as server root");
                }
            }
            r = props.getProperty("port");
            if (r != null) {
                port = Integer.parseInt(r);
            }
            r = props.getProperty("timeout");
            if (r != null) {
                timeout = Integer.parseInt(r);
            }
            r = props.getProperty("workers");
            if (r != null) {
                workers = Integer.parseInt(r);
            }
            r = props.getProperty("log");
            if (r != null) {
                System.out.println("opening log file: " + r);
                log = new PrintStream(new BufferedOutputStream(new FileOutputStream(r)));
            }
        }
    }
}