package test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

import org.junit.*;

import atm.client.ATM;
import atm.protocol.ClientConnection;
import atm.protocol.impls.InProcessConnectionImpl;
import atm.protocol.impls.ServerConnectionProxy;
import atm.protocol.impls.ServerConnectionStub;

public class MyTest {

	@Test
	public void run() {
		ServerConnectionStub stub = new ServerConnectionStub();
		ServerConnectionProxy proxy = new ServerConnectionProxy(stub);
        ClientConnection clientConnection = new InProcessConnectionImpl(proxy);

		ATM atm = new ATM(clientConnection);
		String id = "user";
		
		atm.logon(id, id.getBytes());
		atm.increase(10000);

		atm.transferTo(200, "acc1");

		atm.transferTo(200, "acc1");
		atm.logout();

	}
}
