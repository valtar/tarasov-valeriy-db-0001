package com.db.httpserver;


public final class Request {

	private final int method;
    
	private final String uri;
    
	private final String version;

    public Request(int method, String uri, String version) {
		this.method = method;
		this.uri = uri;
		this.version = version;
	}

	public int getMethod() {
        return method;
    }

    public String getURI() {
        return uri;
    }

    public String getVersion() {
        return version;
    }
    
}