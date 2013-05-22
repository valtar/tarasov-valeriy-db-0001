package com.db.httpserver;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class WorkerThread implements Runnable {
	private Socket socket;
	private RequestScanner requestScanner;
	private PrintStream log = Parameter.getLog();
	
	
    public WorkerThread(Socket socket) {
		this.socket = socket;
	}

	public static void startPool(int suggestedPort) throws IOException {

		Parameter.setPort(suggestedPort);
        Parameter.print();
        int port = Parameter.getPort();
        int workers = Parameter.getWorkers();
        int timeout = Parameter.getTimeout(); 
        
        //create pool of worker threads
        ExecutorService executors = Executors.newFixedThreadPool(workers);
        
        //create server socket
        ServerSocket serverSocket = new ServerSocket(port);
        
        //accept connections
        Socket socket;
        while(true){
        	
        	socket = serverSocket.accept();
        	
        	Parameter.getLog().println("accept connetion");
        	
        	executors.execute(new WorkerThread(socket));
        }
        
    }

    public void run(){
    	
    	// Worker.handleRequest(socket)
    	try{
    		
    		AbstractHttpWorker worker = new Worker();
			worker.handleRequest(socket);
		} catch (IOException e) {
			
			log.println("Exception: " + e);
		}finally{
			
			log.println("worker thread out");
		}
    }
}