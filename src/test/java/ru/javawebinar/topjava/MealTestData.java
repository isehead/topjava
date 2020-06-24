package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;

import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int NOT_FOUND = 10;
    public static final int MEAL_ID = START_SEQ;

    public static final Meal MEAL1 = new Meal(START_SEQ, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
    public static final Meal MEAL2 = new Meal(START_SEQ + 1, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000);
    public static final Meal MEAL3 = new Meal(START_SEQ + 2, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500);
    public static final Meal MEAL4 = new Meal(START_SEQ + 3, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100);
    public static final Meal MEAL5 = new Meal(START_SEQ + 4, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000);
    public static final Meal MEAL6 = new Meal(START_SEQ + 5, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500);
    public static final Meal MEAL7 = new Meal(START_SEQ + 6, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410);

/*  TODO to be refactored

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", 1555, false, new Date(), Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        User updated = new User(USER);
        updated.setName("UpdatedName");
        updated.setCaloriesPerDay(330);
        return updated;
    }

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "roles");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "roles").isEqualTo(expected);
    }*/
}
