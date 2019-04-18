package system.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import system.exceptions.AccountsNotFoundException;
import system.exceptions.NotEnoughBalanceException;
import system.utility.StringConstants;
import system.dao.AccountDao;
import system.dao.TransactionDao;
import system.model.BankAccount;
import system.model.BankTransaction;

import java.util.List;

@Service
public final class TransactionService {
    @Autowired
    TransactionDao transactionDao;
    Logger logger = LoggerFactory.getLogger(StringConstants.USER_LOGGER);

    @Autowired
    AccountDao accountDao;

    public List<BankTransaction> getUserSenderTransactions(int clientID) {
        logger.debug("entered getUserSenderTransactions with clientID:{}", clientID);
        List<BankTransaction> userSenderTransactions = transactionDao.getUserSenderTransactions(clientID);
        logger.debug("left getUserSenderTransactions with userSenderTransactionsList:{}", userSenderTransactions);
        return userSenderTransactions;
    }

    public List<BankTransaction> getUserRecipientTransactions(int clientID) {
        logger.debug("entered getUserRecipientTransactions with clientID:{}", clientID);
        List<BankTransaction> userRecipientTransactions = transactionDao.getUserRecipientTransactions(clientID);
        logger.debug("left getUserRecipientTransactions with userRecipientTransactionsList:{}", userRecipientTransactions);
        return userRecipientTransactions;
    }

    public List<BankTransaction> getAccountSenderTransactions(int accountID) {
        logger.debug("entered getAccountSenderTransactions with accountID:{}", accountID);
        List<BankTransaction> accountSenderTransactions = transactionDao.getAccountSenderTransactions(accountID);
        logger.debug("left getAccountSenderTransactions with accountSenderTransactionsList:{}", accountSenderTransactions);
        return accountSenderTransactions;
    }

    public List<BankTransaction> getAccountRecipientTransactions(int accountID) {
        logger.debug("entered getAccountRecipientTransactions with accountID:{}", accountID);
        List<BankTransaction> accountRecipientTransactions = transactionDao.getAccountRecipientTransactions(accountID);
        logger.debug("left getAccountRecipientTransactions with accountRecipientTransactionsList:{}", accountRecipientTransactions);
        return accountRecipientTransactions;
    }

    @Transactional
    public void newTransaction(BankTransaction bankTransaction) throws NotEnoughBalanceException, AccountsNotFoundException {
        logger.debug("entered newTransaction method with bankTransaction:{}", bankTransaction.toString());
        int senderBalance;
        List<BankAccount> accounts =
                accountDao.getTransactionAccountsAndLock(bankTransaction.getSender(), bankTransaction.getRecipient());
        logger.debug("locked database fields for transaction:{}", bankTransaction.toString());
        if (bankTransaction.getSender() == 0 && accounts.size() == 1) {
            logger.debug("proceeded transaction as deposit bankTransaction:{}", bankTransaction.toString());
            proceedTransaction(bankTransaction);
            logger.debug("proceedTransaction completed successfully");
        } else if (accounts.size() == 2) {
            logger.debug("proceeded transaction as transfer bankTransaction:{}", bankTransaction.toString());
            senderBalance = (accounts.get(0).getId() == bankTransaction.getSender()) ? accounts.get(0).getBalance() : accounts.get(1).getBalance();
            proceedTransaction(senderBalance, bankTransaction);
            logger.debug("proceedTransaction completed successfully");
        } else {
            logger.debug("exited newTransaction with error: AccountNotFound");
            throw new AccountsNotFoundException();
        }
    }

    private void proceedTransaction(int senderBalance, BankTransaction bankTransaction) throws NotEnoughBalanceException {
        if (senderBalance > bankTransaction.getAmount()) {
            makeTransaction(bankTransaction);
        } else {
            logger.debug("proceedTransaction error: NotEnoughBalance");
            throw new NotEnoughBalanceException();
        }
    }

    private void proceedTransaction(BankTransaction bankTransaction) {
        makeTransaction(bankTransaction);
    }

    private void makeTransaction(BankTransaction bankTransaction) {
        transactionDao.addTransaction(bankTransaction);
        logger.debug("Transaction added to transactions table:{}",bankTransaction.toString());
        accountDao.removeFunds(bankTransaction.getSender(), bankTransaction.getAmount());
        logger.debug("Funds removed from sender account:{} amount:{}",bankTransaction.getSender(),bankTransaction.getAmount());
        accountDao.addFunds(bankTransaction.getRecipient(), bankTransaction.getAmount());
        logger.debug("Funds added to recipient account:{} amount:{}",bankTransaction.getRecipient(),bankTransaction.getAmount());
    }

}
