package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

//    {
//        for (Meal MEAL : MealsUtil.MEALS) {
//            save(MEAL, SecurityUtil.authUserId());
//        }
//    }


    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        return repository.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        return repository.get(id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        List<Meal> mealList = repository.values().stream()
                .sorted(Comparator.comparing(Meal::getDate))
                .collect(Collectors.toList());
        return MealsUtil.filterByUserId(mealList, userId);
    }

    @Override
    public List<Meal> getBetween(LocalDate startDate, LocalDate endDate, int userId) {
        List<Meal> mealList = new ArrayList<>();
        for (Meal meal : repository.values()) {
            if (meal.getUserId() == userId) {
                if (DateTimeUtil.isBetweenHalfOpenDate(meal.getDate(), startDate, endDate))
                    mealList.add(meal);
            }
        }
        return mealList;
    }
}

