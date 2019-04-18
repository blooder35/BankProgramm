package system.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import system.model.BankTransaction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public final class TransactionDao {
    @Autowired
    JdbcTemplate jdbcTemplate;


    public List<BankTransaction> getUserSenderTransactions(int clientID) {
        String update = "Select * from transaction where sender in (select id from bankaccount where ownerID = ?)";
        List<BankTransaction> list = jdbcTemplate.query(update, new Object[]{clientID}, new BeanPropertyRowMapper<>(BankTransaction.class));
        return list;
    }

    public List<BankTransaction> getUserRecipientTransactions(int clientID) {
        String update = "Select * from transaction where recipient in (select id from bankaccount where ownerID = ?)";
        List<BankTransaction> list = jdbcTemplate.query(update, new Object[]{clientID}, new BeanPropertyRowMapper<>(BankTransaction.class));
        return list;
    }

    public List<BankTransaction> getAccountSenderTransactions(int accountID) {
        String update = "Select * from transaction where sender=?";
        List<BankTransaction> list = jdbcTemplate.query(update, new Object[]{accountID}, new BeanPropertyRowMapper<>(BankTransaction.class));
        return list;
    }

    public List<BankTransaction> getAccountRecipientTransactions(int accountID) {
        String update = "Select * from transaction where recipient=?";
        List<BankTransaction> list = jdbcTemplate.query(update, new Object[]{accountID}, new BeanPropertyRowMapper<>(BankTransaction.class));
        return list;
    }

    public void addTransaction(BankTransaction bankTransaction) {
        String update = "Insert into transaction(amount,sender,recipient,dateAndTime) values(?,?,?,?)";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        jdbcTemplate.update(update, bankTransaction.getAmount(), bankTransaction.getSender(), bankTransaction.getRecipient(), dtf.format(LocalDateTime.now()));
    }

}
