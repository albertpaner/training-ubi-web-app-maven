package services.user;

import model.bean.UtenteBean;
import model.dao.UtenteDao;
import model.dto.UtenteDto;
import services.UtenteService;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

public class UtentePlaygroundService extends UtenteService {

    public UtentePlaygroundService(UtenteDao utenteDao) {
        super(utenteDao);
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        UtentePlaygroundService utentePlaygroundService = new UtentePlaygroundService(new UtenteDao());
        List<UtenteDto> utenti = utentePlaygroundService.sortByNameLexicographically();

        /*
        utenti.forEach(new Consumer<UtenteDto>() {
            @Override
            public void accept(UtenteDto utenteDto) {
                System.out.println(utenteDto.getFirstName() + " " + utenteDto.getLastName());
            }
        });*/
        //utenti.forEach(utente -> System.out.println(utente.getFirstName() + " " + utente.getLastName()));
        utenti.forEach(System.out::println);

    }

    public List<UtenteDto> sortByNameLexicographically() throws SQLException, ClassNotFoundException {

        List<UtenteBean> allUsers = utenteDao.findAll();

        List<UtenteDto> usersSorted = allUsers.stream()
                .filter(user -> !user.getFlgDel())
                .map(user -> new UtenteDto(user.getUtenteId(), user.getEmail(),  user.getNome(), user.getCognome()))
                .sorted(Comparator.comparing(UtenteDto::getFirstName)
                        .thenComparing(UtenteDto::getLastName))
                .toList();

        return usersSorted;

/*
        allUsers.sort(new Comparator<UtenteBean>() {
            @Override
            public int compare(UtenteBean o1, UtenteBean o2) {
                int firstComparison = o1.getNome().compareTo(o2.getNome());
                if (firstComparison != 0) {
                    return firstComparison;
                } else {
                    return o1.getCognome().compareTo(o2.getCognome());
                }
            }
        });


        interface EnhancedComparator<T> extends Comparator<T> {
            int secondaryCompare(T o1, T o2);
        }

        allUsers.sort(new EnhancedComparator<UtenteBean>() {
            @Override
            public int compare(UtenteBean o1, UtenteBean o2) {
                return o1.getNome().compareTo(o2.getNome());
            }

            @Override
            public int secondaryCompare(UtenteBean o1, UtenteBean o2) {
                return o1.getCognome().compareTo(o2.getCognome());
            }
        });
        */


    }

}
