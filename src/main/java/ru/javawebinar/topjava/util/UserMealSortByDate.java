package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;

import java.util.Comparator;

public class UserMealSortByDate implements Comparator<UserMeal> {

    @Override
    public int compare(UserMeal o1, UserMeal o2) {
        if (o1.getDateTime().toLocalDate().isBefore(o2.getDateTime().toLocalDate())) {
            return -1;
        } else if (o1.getDateTime().toLocalDate().isAfter(o2.getDateTime().toLocalDate())) {
            return 1;
        } else return 0;
    }
}
