package load;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/*-Xloggc:gc.log -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+PrintTenuringDistribution -XX:+UseConcMarkSweepGC  -XX:+UseParNewGC*/
public class LoadTest {
	ObjectOutputStream oos;
	ObjectInputStream ois;

	public void send(String msg) {
		try {
			oos.writeObject(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws UnknownHostException,
			IOException, InterruptedException {
		new LoadTest().load();
	}

	public void load() throws UnknownHostException, IOException,
			InterruptedException {
		Socket socket = new Socket("localhost", 5555);
		OutputStream os = socket.getOutputStream();
		InputStream is = socket.getInputStream();

		oos = new ObjectOutputStream(os);
		ois = new ObjectInputStream(is);

		Thread thread = new Thread(new Reader());
		thread.setDaemon(true);
		thread.start();

		String msg = "LOGIN;MYNAME";
		send(msg);
		String s1 = "ORDER;";
		String s2 = ";ASK;GOLD;95;10";
		for (int i = 0; i < 100_000; i++) {
			msg = s1 + i + s2;
			send(msg);
		}

		Thread.sleep(10_000);
	}

	public class Reader implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					ois.readObject();
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
