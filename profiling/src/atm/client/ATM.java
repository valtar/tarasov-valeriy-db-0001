package atm.client;


import atm.protocol.ClientConnection;

/**
 * Created by IntelliJ IDEA.
 * User: shesdmi
 * Date: 2/20/13
 * Time: 4:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class ATM {

    ClientTransport clientTransport;
    String sessionId;
    String currentUser;

    public ATM(ClientConnection connection) {
        clientTransport = new ClientTransport(connection);
    }

    public void logon(String userId, byte[] credentials) {

        currentUser = userId;
        sessionId = clientTransport.sendLogon(currentUser, credentials);

        if(sessionId == null ) {
            throw new RuntimeException("Can't logon");
        }

    }

    public void logout() {
        clientTransport.sendLogout(sessionId);
        currentUser = null;
        sessionId = null;
    }


    public void withdraw(double amount) {
        clientTransport.withdraw(sessionId, amount);
    }

    public void increase(double amount) {
        clientTransport.increase(sessionId, amount);
    }

    public void transferTo(double amount, String accountId) {

        clientTransport.transferTo(sessionId, amount, accountId);
    }

    public double getAccountValue() {

        return clientTransport.getAccountValue(sessionId);

    }


}

