package com.db.httpserver;

import java.util.HashMap;
import java.util.Map;

public final class MimeTypes {
	
	/* mapping of file extensions to content-types */
    private static Map<String, String> mimeTyps = new HashMap<String, String>();

    static {
    	mimeTyps.put("", "content/unknown");
    	mimeTyps.put(".uu", "application/octet-stream");
    	mimeTyps.put(".exe", "application/octet-stream");
    	mimeTyps.put(".ps", "application/postscript");
    	mimeTyps.put(".zip", "application/zip");
    	mimeTyps.put(".sh", "application/x-shar");
    	mimeTyps.put(".tar", "application/x-tar");
    	mimeTyps.put(".snd", "audio/basic");
    	mimeTyps.put(".au", "audio/basic");
    	mimeTyps.put(".wav", "audio/x-wav");
    	mimeTyps.put(".gif", "image/gif");
        mimeTyps.put(".jpg", "image/jpeg");
        mimeTyps.put(".jpeg", "image/jpeg");
        mimeTyps.put(".htm", "text/html");
        mimeTyps.put(".html", "text/html");
        mimeTyps.put(".text", "text/plain");
        mimeTyps.put(".c", "text/plain");
        mimeTyps.put(".cc", "text/plain");
        mimeTyps.put(".c++", "text/plain");
        mimeTyps.put(".h", "text/plain");
        mimeTyps.put(".pl", "text/plain");
        mimeTyps.put(".txt", "text/plain");
        mimeTyps.put(".java", "text/plain");
    }
	
    private MimeTypes() {
    	
    }

    public static String getType(String fileName) {
        int index = fileName.lastIndexOf('.');
        String ct = null;
        if (index > 0) {
            ct = mimeTyps.get(fileName.substring(index));
        }
        if (ct == null) {
            ct = "unknown/unknown";
        }
        return ct;
    }

    
}
