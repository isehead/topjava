package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsGenerator;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.filteredByStreams;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");

//        List<MealTo> mealToList = MealsUtil.filteredByStreams
//                (MealsGenerator.generateHardcodedMeals(), LocalTime.MIN, LocalTime.MAX, 2000);
//        List<MealTo> mealToList = Arrays.asList(
//                new MealTo(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500, true),
//                new MealTo(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000, false));
        List<MealTo> mealToList = new ArrayList<>();
        mealToList.add(new MealTo(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500, true));
        mealToList.add(new MealTo(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000, false));

        request.setAttribute("mealToList", mealToList);
//        response.sendRedirect("meals");
        request.getRequestDispatcher("meals.jsp").forward(request, response);
    }


}
