package atm.server;

/**
 * Created by IntelliJ IDEA.
 * User: shesdmi
 * Date: 2/19/13
 * Time: 3:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class Account {
	private static int ID = 0;
	
    

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	private double balance;
    private String id;
    private int accountId = ID++;

    public Account(String id) {
        this.id = id;
    }

    
//    public Account(double balance, String id) {
//		this.balance = balance;
//		this.id = id;
//	}


	public boolean transferTo(Account to, double delta) {

        if(balance > delta) {
            to.balance = to.balance + delta;
            this.balance = this.balance - delta;



                Thread.yield();

            return true;
        }

        return false;

    }

    public void increase(double delta) {
        balance = balance + delta;
    }

    public boolean withdraw(double delta) {
        if(balance - delta > 0.00000001) {
            balance = balance - delta;
            return true;
        }

        return false;
    }

    public Account createCopy() {
        Account res = new Account(id);
        res.balance = balance;
        return res;
    }

    public void rollbackToCopy(Account account) {
        if(id.equals(account.id)) {
            balance = account.balance;
        }
    }

    public double getBalance() {
        return balance;
    }

    public String getId() {
        return id;
    }


	public int getAccountId() {
		return accountId;
	}


}
