package system.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.utility.StringConstants;
import system.dao.AccountDao;
import system.model.BankAccount;

import java.util.List;

@Service
public final class AccountService {
    @Autowired
    AccountDao accountDao;
    Logger logger = LoggerFactory.getLogger(StringConstants.USER_LOGGER);

    public List<BankAccount> listUserAccounts(int clientID) {
        logger.debug("entered listUserAccounts method with clientID:{}", clientID);
        List<BankAccount> userAccounts = accountDao.getUserAccounts(clientID);
        logger.debug("left listUserAccounts method with userAccounts:{}",userAccounts.toString());
        return userAccounts;
    }

    public int registerAccount(int ownerID) {

        logger.debug("Entered registerAccount method with ownerID={}", ownerID);
        int count = 0;
        count = accountDao.registerAccount(ownerID);
        logger.debug("left registerAccount method with count={}", count);
        return count;
    }

    public synchronized int closeAccount(int accountID) {
        logger.debug("entered closeAccount method with accountID={}", accountID);
        int count=accountDao.closeAccount(accountID);
        logger.debug("successfully left closeAccount method with count:{}",count);
        return count;
    }
}
