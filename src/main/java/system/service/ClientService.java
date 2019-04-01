package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.ClientDao;
import system.model.BankClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class ClientService {
    @Autowired
    ClientDao clientDao;


    public String registerClient(BankClient bankClient) {
        if (validateClient(bankClient)) {
            int count = clientDao.addClientToDatabase(bankClient);
            if (count > 0) {
                return "Client added";
            } else {
                return "Client with this passport already exists";
            }
        } else {
            return "something wrong with your input";
        }
    }

    public List<BankClient> listClients() {
        List<BankClient> list = clientDao.getAllClients();
        return list;
    }

    public List<BankClient> getAlikeClients(BankClient bankClient) {
        List<BankClient> list = clientDao.getAlikeClients(bankClient);
        return list;
    }

    private boolean validateClient(BankClient bankClient) {
        try {
            Long a = Long.parseLong(bankClient.getPassportNumber()) / 1000000000;
            if (a > 10 || a == 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        try {
            Date date = sdf.parse(bankClient.getDateOfBirth());
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int year = localDate.getYear();
            LocalDate currentDate = LocalDate.now();
            int currentYear = currentDate.getYear();
            if (year < 1900 || year > currentYear) {
                return false;
            }

        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
