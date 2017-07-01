package com.sda.model;

import com.sda.model.Address;
import com.sda.model.User;

import javax.jws.soap.SOAPBinding;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by RENT on 2017-07-01.
 */
public class Application {

    public static void main(String[] args) {
        Address address = Address.builder()
                .city("Poznan")
                .street("Dabrowskiego")
                .flatNumber(22)
                .postalCode("12-456")
                .build();

        Address address2 = Address.builder()
                .city("Pila")
                .street("Lesna")
                .flatNumber(30)
                .postalCode("12-987")
                .build();

        Address address3 = Address.builder()
                .city("Warszawa")
                .street("Parkowa")
                .flatNumber(44)
                .flatNumber(12)
                .postalCode("07-250")
                .build();

        User user = User.builder()
                .firstName("Szymon")
                .lastName("Nowak")
                .age(23).phoneNumber("123456789")
                .address(address)
                .build();

        User user2 = User.builder()
                .firstName("Jan")
                .lastName("Kowalski")
                .age(40).phoneNumber("989865432")
                .address(address2)
                .build();

        User user3 = User.builder()
                .firstName("Janina")
                .lastName("Kowalska")
                .age(36).phoneNumber("445727312")
                .address(address2)
                .build();

        User user4 = User.builder()
                .firstName("Tomasz")
                .lastName("Borowski")
                .age(31).phoneNumber("322120664")
                .address(address3)
                .build();

        List<User> users = Arrays.asList(user, user2, user3, user4);

        //1. Wyswietlic wszystkich userow
        users.stream()
                .map(e -> e.toString())
                .forEach(e -> System.out.println(e));

        //2. Wyswietlic tylko userow ktorych wiek jest powyzej 35
        users.stream()
                .filter(e -> e.getAge() > 35)
                .forEach(e -> System.out.println(e));

        //3. Wyswietlic wszystkich userow z Poznania
        users.stream()
                .filter(e -> e.getAddress().getCity().equals("Poznan"))
                .forEach(e -> System.out.println(e));

        //4. Wyswietlic wszystkich userow ktorych kod pocztowy zaczyna sie na 12 i zeby to była Piła
        users.stream()
                .filter(e -> e.getAddress().getPostalCode().startsWith("12"))
                .filter(e -> e.getAddress().getCity().equals("Pila"))
                .forEach(e -> System.out.println(e));

        //5. Zliczyc wystapienia nazwiska Kowalski
        System.out.println(users.stream()
                .filter(e -> e.getLastName().equals("Kowalski"))
                .count());

        //5a. Zliczyc ilosc adresow
        System.out.println(users.stream()
                .map(e -> e.getAddress())
                .distinct()
                .count());

        //6.Wyswietlic osoby ktore mieszkaja pod adresem address2
        users.stream()
                .filter(e -> e.getAddress().equals(address2))
                .forEach(e -> System.out.println(e));

        //7. Utworzyc liste userow ktorych imie zaczyna sie na J
        List<User> usersWithFirstNameStartingWithJ = users.stream()
                .filter(e -> e.getFirstName().startsWith("J"))
                .collect(Collectors.toList());

        //8. Sprawdz czy w kolekcji istnieje ktokolwiek z miasta Kalisz
        System.out.println(users.stream()
                .map(e -> e.getAddress())
                .distinct()
                .map(e -> e.getCity())
                .anyMatch(e -> "Kalisz".equals(e)));

        //9. Sprawdzic czy wszyscy userzy o nazwisku Kowalski mieszkaja w Pozaniu
        System.out.println(users.stream()
                .filter(e -> "Kowalski".equals(e.getLastName()))
                .map(e -> e.getAddress())
                .map(e -> e.getCity())
                .distinct()
                .allMatch(e -> "Poznan".equals(e)));

        //10. Obliczyc srednia wiekow userow
        System.out.println(users.stream()
                .mapToInt(e -> e.getAge())
                .average());

        //11. Wyswietlic hashe wszystkich userow
        System.out.println("11. Wyswietlic hashe wszystkich userow");
        users.stream()
                .mapToInt(e -> e.hashCode())
                .forEach(e -> System.out.println(e));
        System.out.println();

        Map<String, List<User>> usersByDistrict = new HashMap<>();
        usersByDistrict.put("Wielkopolskie", Arrays.asList(user, user2, user3));
        usersByDistrict.put("Mazowieckie", Arrays.asList(user4));
        System.out.println();

        //1. Zrobic z tego liste userow
        System.out.println("1. Zrobic z tego liste userow");
        usersByDistrict.entrySet().stream()
                .map(e -> e.getValue())
                .flatMap(e -> e.stream())
                .forEach(e -> System.out.println(e));

    }

}
