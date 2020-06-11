package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDAO {

    public List<Meal> getMeals();

    public Meal getMeal(int theId);

    public void saveMeal(Meal meal);

    public void deleteMeal(int theId);
}
