package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        for (Meal meal : MealsUtil.MEALS) {
            save(meal, 1);
        }
    }

    @Override
    public Meal save(Meal meal, int userId) {
        log.info("save {}", meal);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(userId);
            repository.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        log.info("delete {}", id);
        if (repository.get(id) != null) {
            return (repository.get(id).getUserId() == userId && repository.remove(id) != null);
        } else return false;
    }

    @Override
    public Meal get(int id, int userId) {
        log.info("get {}", id);
        if (repository.get(id) != null) {
            return (repository.get(id).getUserId() == userId ? repository.get(id) : null);
        } else return null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        log.info("getAll");
        List<Meal> mealList = new ArrayList<>(repository.values());
        if (mealList.isEmpty()) {
            return Collections.emptyList();
        } else {
            return mealList.stream()
                    .filter(meal -> meal.getUserId() == userId)
                    .sorted((meal1, meal2) -> meal2.getDateTime().compareTo(meal1.getDateTime()))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<Meal> getBetween(LocalDate startDate, LocalDate endDate, int userId) {
        log.info("get between {} and {}", startDate, endDate);
        return repository.values().stream()
                .filter(meal -> meal.getUserId() == userId && DateTimeUtil.isBetweenHalfOpenDate(meal.getDate(), startDate, endDate))
                .sorted((meal1, meal2) -> meal2.getDateTime().compareTo(meal1.getDateTime()))
                .collect(Collectors.toList());
    }
}

