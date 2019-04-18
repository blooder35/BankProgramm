package system.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import system.model.BankAccount;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class AccountDao {
    @Autowired
    JdbcTemplate jdbcTemplate;


    public List<BankAccount> getUserAccounts(int clientID) {
        String update = "Select * from bankaccount where ownerID in (select id from bankclient where ownerID = ?) and active=1";
        List<BankAccount> list = jdbcTemplate.query(update, new Object[]{clientID}, new BeanPropertyRowMapper<>(BankAccount.class));
        return list;
    }

    public int registerAccount(int ownerID) {
        String update = "insert into bankaccount(ownerID,dateOfOpening,balance) values(?,?,0)";
        int count = 0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        count = jdbcTemplate.update(update, ownerID, dtf.format(LocalDateTime.now()));
        return count;
    }

    public int closeAccount(int accountID) {
        String update = "update bankaccount set active=0 where id=?";
        return jdbcTemplate.update(update, accountID);
    }

    public int removeFunds(int id, int amount) {
        String update = "update bankaccount set balance=balance-? where id=?";
        return jdbcTemplate.update(update, amount, id);
    }

    public int addFunds(int id, int amount) {
        String update = "update bankaccount set balance=balance+? where id=?";
        return jdbcTemplate.update(update, amount, id);
    }

    public List<BankAccount> getTransactionAccountsAndLock(int sender, int recipient) {
        String update = "select * from bankaccount where (id=? or id=?) and active=1 for update";
        List<BankAccount> list = jdbcTemplate.query(update, new Object[]{sender, recipient}, new BeanPropertyRowMapper<>(BankAccount.class));
        return list;
    }
}
