package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@UtilityClass
public class DataGenerator {

    public static String generateCity() {
        String[] cities  = {"Йошкар-Ола", "Ижевск", "Чебоксары", "Барнаул", "Чита", "Краснодар", "Красноярск", "Пермь",
                "Владивосток", "Хабаровск", "Архангельск", "Астрахань", "Волгоград", "Воронеж", "Иркутск", "Кемерово",
                "Киров", "Курск", "Липецк", "Магадан", "Мурманск", "Новосибирск", "Омск", "Оренбург", "Псков",
                "Самара", "Екатеринбург", "Тюмень", "Москва", "Санкт-Петербург"};
        Random random = new Random();
        int index = random.nextInt(cities.length);
        return cities[index];
    }

    public static String generateDate(int step) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        final String date = formatter.format(today.plusDays(step));
        return date;
    }

    public static String generateName(String locale) {
        final Faker faker = new Faker(new Locale(locale));
        String name = faker.name().fullName();
        return name;
    }

    public static String generatePhone(String locale) {
        final Faker faker = new Faker(new Locale(locale));
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }
}




