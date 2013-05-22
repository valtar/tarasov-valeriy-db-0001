package com.db.httpserver;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public final class RequestScanner {

    public static final int EOF = -1;
    public static final int ERROR = -1;
    public static final int OTHER = 0;
    public static final int HEAD = 1;
    public static final int GET = 2;
    public static final int CRLF = 3;

    private final BufferedReader in;
    
    private int currChar;
    
    private String lastWord;

    public RequestScanner(InputStream is) throws IOException {
        this(new InputStreamReader(is));
    }

    public RequestScanner(Reader reader) throws IOException {
        in = new BufferedReader(reader);
        currChar = nextChar();
    }
    
    public Request scan() throws IOException {
        int method = nextToken();
        String uri = null;
        String version = null;
        if (method == RequestScanner.OTHER) {
            uri = getContents();
        } else {
            nextToken();
            uri = getContents().replace('/', File.separatorChar);
            nextToken();
            version = getContents();
            if (nextToken()!= RequestScanner.CRLF) {
                method = RequestScanner.ERROR;
            }
        }
        return new Request(method, uri, version);
    }

    public String getContents() {
        return lastWord;
    }

    public int nextToken() throws IOException {
        boolean afterCRLF = false;
        try {
            while (true) {
            	if (afterCRLF && (currChar != ' ' && currChar != '\t')) {
                    afterCRLF = false;
                    return CRLF;
                }
                afterCRLF = false;
                if (currChar == '\r') {
                    currChar = nextChar();
                    if (currChar == '\n') {
                        currChar = nextChar();
                        afterCRLF = true;
                    }
                } else if (Character.isWhitespace((char)currChar)) {
                    currChar = nextChar();
                } else if (currChar != -1) {
                    final StringBuilder b = new StringBuilder();
                    while (currChar > 0 && !Character.isWhitespace((char)currChar)) {
                        if (currChar == '%') {
                            b.append(((char)(16 * hexDigit() + hexDigit())));
                        } else {
                            b.append((char)currChar);
                        }
                        currChar = nextChar();
                    }
                    lastWord = b.toString();
                    if (lastWord.equals("GET")) {
                        return GET;
                    } else if (lastWord.equals("HEAD")) {
                        return HEAD;
                    } else {
                        return OTHER;
                    }
                } else {
                    return EOF;
                }
            }
        } catch (EOFException e) {
            return EOF;
        }
    }

    private int nextChar() throws IOException {
        final int result = in.read();
        if (result < 0 && currChar < 0) {
            throw new EOFException("premature EOF");
        }
        return result;
    }

    private int hexDigit() throws IOException {
        final int c = Character.toLowerCase((char)nextChar());
        if (c >= '0' && c <= '9') {
            return c - '0';
        } else if (c >= 'a' && c <= 'f') {
            return c - 'a' + 10;
        } else {
            throw new EOFException("illegal hex digit");
        }
    }
}
