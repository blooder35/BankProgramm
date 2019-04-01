package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import system.dao.AccountDao;
import system.dao.TransactionDao;
import system.model.BankTransaction;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionDao transactionDao;
    @Autowired
    AccountDao accountDao;

    public List<BankTransaction> getUserSenderTransactions(int clientID) {
        return transactionDao.getUserSenderTransactions(clientID);
    }

    public List<BankTransaction> getUserRecipientTransactions(int clientID) {
        return transactionDao.getUserRecipientTransactions(clientID);
    }

    public List<BankTransaction> getAccountSenderTransactions(int accountID) {
        return transactionDao.getAccountSenderTransactions(accountID);
    }

    public List<BankTransaction> getAccountRecipientTransactions(int accountID) {
        return transactionDao.getAccountRecipientTransactions(accountID);
    }

    @Transactional
    public String newTransaction(BankTransaction bankTransaction) {
        boolean senderExists;
        boolean recipientExists;
        if (bankTransaction.getSender() == 0) {
            senderExists = true;
        } else {
            senderExists = accountDao.accountExist(bankTransaction.getSender());
        }
        recipientExists = accountDao.accountExist(bankTransaction.getRecipient());

        if (senderExists && recipientExists) {
            if (bankTransaction.getSender() == 0 || accountDao.getBalance(bankTransaction.getSender()) > bankTransaction.getAmount()) {
                transactionDao.addTransaction(bankTransaction);
                accountDao.removeFunds(bankTransaction.getSender(), bankTransaction.getAmount());
                accountDao.addFunds(bankTransaction.getRecipient(), bankTransaction.getAmount());
            } else {
                return "Not enough funds";
            }
        } else {
            return "Sender or recipient account does not exist";
        }
        return "completed";
    }
}
