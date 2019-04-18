package system.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.exceptions.NotValidClientException;
import system.utility.StringConstants;
import system.dao.ClientDao;
import system.model.BankClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public final class ClientService {
    @Autowired
    ClientDao clientDao;
    Logger logger = LoggerFactory.getLogger(StringConstants.USER_LOGGER);


    public int registerClient(BankClient bankClient) throws NotValidClientException {
        logger.debug("entered registerClient method with bankClient:{}",bankClient.toString());
        if (validateClient(bankClient)) {
            int count = clientDao.addClientToDatabase(bankClient);
            return count;
        } else {
            logger.debug("left registerClient with wrong Input bankClient:{}",bankClient.toString());
            throw new NotValidClientException();
        }
    }

    public List<BankClient> listClients() {
        logger.debug("entered listClients method");
        List<BankClient> list = clientDao.getAllClients();
        logger.debug("left listClients method with bankClientList:{}",list);
        return list;
    }

    public List<BankClient> getAlikeClients(BankClient bankClient) {
        logger.debug("entered getAlikeClients method with bankClient:{}", bankClient);
        List<BankClient> list = clientDao.getAlikeClients(bankClient);
        logger.debug("left getAlikeClients method with bankClientList:{}", list);
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
