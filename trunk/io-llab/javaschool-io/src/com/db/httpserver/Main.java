package com.db.httpserver;


public final class Main {
	
    public static void main(String[] args) throws Exception {
        int port = -1;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        WorkerThread.startPool(port);
    }
    
}
