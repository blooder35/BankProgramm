package system.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import system.model.BankClient;

import java.util.List;

@Repository
public class ClientDao {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public int addClientToDatabase(BankClient bankClient) {
        int count = 0;
        String update = "insert into bankclient(name,surName,patrName,passportNumber,dateOfBirth) values (?,?,?,?,?)";
        try {
            count = jdbcTemplate.update(update, bankClient.getName(), bankClient.getSurName(), bankClient.getPatrName(), bankClient.getPassportNumber(), bankClient.getDateOfBirth());
        } catch (DuplicateKeyException e) {
            //при дубликате паспорта в базе данных
            return count;
        }
        return count;
    }


    public List<BankClient> getAllClients() {
        String update = "select * from bankclient";
        List<BankClient> clients = jdbcTemplate.query(update, new BeanPropertyRowMapper<>(BankClient.class));
        return clients;
    }

    public List<BankClient> getAlikeClients(BankClient bankClient) {
        String update = "select * from bankclient where name like ? and surName  like ? and patrName like ? and passportNumber like ? and dateOfBirth like ?";
        String name = bankClient.getName().replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_")
                .replace("[", "![");
        String surName = bankClient.getSurName().replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_")
                .replace("[", "![");
        String patrName = bankClient.getPatrName().replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_")
                .replace("[", "![");
        String passportNumber = bankClient.getPassportNumber().replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_")
                .replace("[", "![");
        String dateOfBirth = bankClient.getDateOfBirth().replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_")
                .replace("[", "![");
        Object[] params = new Object[]{"%" + name + "%", "%" + surName + "%", "%" + patrName + "%", "%" + passportNumber + "%", "%" + dateOfBirth + "%"};
        List<BankClient> clients = jdbcTemplate.query(update, params, new BeanPropertyRowMapper<>(BankClient.class));
        return clients;
    }
}
