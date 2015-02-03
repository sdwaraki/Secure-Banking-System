package bankapp.DAO;
import java.util.List;

import bankapp.Objects.Transaction;

public interface TransactionDAO {
	public String createTransaction(Transaction a);
	public List<Transaction> getTransationsByAccountId(String accountId);
	public void updateTransactionStatus(Transaction a);
	public Transaction getTransactionById(String id);
	
}
