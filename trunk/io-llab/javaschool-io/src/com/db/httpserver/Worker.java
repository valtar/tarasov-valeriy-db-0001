package com.db.httpserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Date;

public final class Worker extends AbstractHttpWorker {
	
	private final byte[] buffer = new byte[2048];

    @Override
	protected void doHead(Request rq, PrintStream ps) {
        final File target = getSource(rq);
        Parameter.getLog().print("HEAD");
        printHeader(ps, target, target.exists());
    }

    @Override
	protected void doGet(Request rq, PrintStream ps) throws IOException {
        final File target = getSource(rq);
        Parameter.getLog().print("GET");
        if (target.exists()) {
            printHeader(ps, target, true);
            sendFile(ps, target);
        } else {
            printHeader(ps, target, false);
            send404(ps);
        }
    }

    private File getSource(Request rq) {
        String fileName = rq.getURI();
        while (fileName.startsWith(File.separator)) {
            fileName = fileName.substring(1);
        }
        File target = new File(Parameter.getRoot(), fileName);
        if (target.isDirectory()) {
            File indexHtml = new File(target, "index.html");
            if (indexHtml.exists()) {
                target = indexHtml;
            }
        }
        return target;
    }

    private void printHeader(PrintStream ps, File target, boolean exists) {
        int rCode;
        if (!exists) {
            rCode = HTTP_NOT_FOUND;
            ps.print("HTTP/1.0 " + rCode + " not found" + CRLF);
        } else {
            rCode = HTTP_OK;
            ps.print("HTTP/1.0 " + rCode + " OK" + CRLF);
        }
        Parameter.getLog().println(" from " + getClientName() + ": " + target.getAbsolutePath() + "-->" + rCode);
        Parameter.getLog().flush();
        ps.print("Server: Simple java" + CRLF);
        ps.print("Date: " + (new Date()) + CRLF);
        if (exists) {
            printContentType(ps, target);
        }
    }

    private void printContentType(PrintStream ps, File target) {
        if (!target.isDirectory()) {
            ps.print("Content-length: " + target.length() + CRLF);
            ps.print("Last Modified: " + (new Date(target.lastModified())) + CRLF);
            String name = target.getName();
            ps.print("Content-type: " + MimeTypes.getType(name) + CRLF);
        } else {
            ps.print("Content-type: text/html" + CRLF);
        }
    }

    private void send404(PrintStream ps) {
        ps.print(CRLF);
        ps.print(CRLF);
        ps.print("Not Found");
        ps.print(CRLF);
        ps.print("The requested resource was not found.");
        ps.print(CRLF);
    }

    private void sendFile(PrintStream ps, File target) throws IOException {
        ps.print(CRLF);
        if (target.isDirectory()) {
            listDirectory(ps, target);
        } else {
            copyFile(ps, new FileInputStream(target.getAbsolutePath()));
        }
    }

    private void copyFile(PrintStream ps, InputStream is) throws IOException {
        try {
            int n;
            while ((n = is.read(buffer)) > 0) {
                ps.write(buffer, 0, n);
            }
        } finally {
            is.close();
        }
    }

    private void listDirectory(PrintStream ps, File dir) {
        ps.println("<TITLE>Directory listing</TITLE><P>\n");
        ps.println("<A HREF=\"..\">Parent Directory</A><BR>\n");
        for (String fileName : dir.list()) {
            final File f = new File(dir, fileName);
            if (f.isDirectory()) {
                ps.println("<A HREF=\"" + fileName + "/\">" + fileName + "/</A><BR>");
            } else {
                ps.println("<A HREF=\"" + fileName + "\">" + fileName + "</A><BR");
            }
        }
        ps.println("<P><HR><BR><I>" + (new Date()) + "</I>");
    }
}