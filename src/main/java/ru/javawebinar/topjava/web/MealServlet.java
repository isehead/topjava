package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    public static final int CALORIES_THRESHOLD = 2000;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("get all meals");

        List<MealTo> mealToList = MealsUtil.filteredByStreams(MealsUtil.meals, LocalTime.MIN, LocalTime.MAX, CALORIES_THRESHOLD);
        request.setAttribute("mealToList", mealToList);
        request.getRequestDispatcher("meals.jsp").forward(request, response);
    }
}
