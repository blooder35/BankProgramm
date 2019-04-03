package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.AccountDao;
import system.model.BankAccount;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountDao accountDao;

    public List<BankAccount> listUserAccounts(int clientID) {
        return accountDao.getUserAccounts(clientID);
    }

    public String registerAccount(int ownerID) {
        int count = 0;
        count = accountDao.registerAccount(ownerID);
        if (count > 0) {
            return "Account successfully created";
        } else {
            return "Error when creating account";
        }
    }

    public synchronized String closeAccount(int accountID) {
        synchronized (AccountDao.class) {
            accountDao.closeAccount(accountID);
            return "Account closed";
        }
    }
}
