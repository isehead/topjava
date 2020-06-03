package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

//        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        // sum calories by date
        Map<LocalDate, Integer> tempMap = new HashMap<>();
        for (UserMeal userMeal : meals) {
            int caloriesCounter = tempMap.getOrDefault(userMeal.getDateTime().toLocalDate(), 0);
            caloriesCounter = caloriesCounter + userMeal.getCalories();
            tempMap.put(userMeal.getDateTime().toLocalDate(), caloriesCounter);
        }

        // define excess flag and filter meals by time
        List<UserMealWithExcess> userMealWithExcessList = new ArrayList<>();
        for (UserMeal tempMeal : meals) {
            boolean isExcess = tempMap.get(tempMeal.getDateTime().toLocalDate()) > caloriesPerDay;
            if (TimeUtil.isBetweenHalfOpen(tempMeal.getDateTime().toLocalTime(), startTime, endTime)) {
                UserMealWithExcess tempUserMealWithExcess = new UserMealWithExcess(tempMeal.getDateTime(),
                        tempMeal.getDescription(), tempMeal.getCalories(), isExcess);
                userMealWithExcessList.add(tempUserMealWithExcess);
            }
        }
        return userMealWithExcessList;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO Implement by streams
        return null;
    }
}
