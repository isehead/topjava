package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repository.MealInMemoryDAOImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static final String INSERT_OR_EDIT = "meal.jsp";
    private static final String LIST_MEAL = "listMeal.jsp";
    public static final int CALORIES_THRESHOLD = 2000;
    private MealInMemoryDAOImpl mealInMemoryDAO;

    public MealServlet() {
        mealInMemoryDAO = new MealInMemoryDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("get all meals");

//        String forward = "";
//        String action = request.getParameter("action");
//
//        if (action.equalsIgnoreCase("delete")) {
//            int userId = Integer.parseInt(request.getParameter("userId"));
//            mealInMemoryDAO.deleteMeal(userId);
//            forward = LIST_MEAL;
//            request.setAttribute("meals", mealInMemoryDAO.getMeals());
//        } else if (action.equalsIgnoreCase("edit")) {
//            forward = INSERT_OR_EDIT;
//            int userId = Integer.parseInt(request.getParameter("userId"));
//            Meal meal = mealInMemoryDAO.getMeal(userId);
//            request.setAttribute("meal", meal);
//        } else if (action.equalsIgnoreCase("listMeal")) {
//            forward = LIST_MEAL;
//            request.setAttribute("meals", mealInMemoryDAO.getMeals());
//        } else {
//            forward = INSERT_OR_EDIT;
//        }
//
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher(forward);
//        requestDispatcher.forward(request, response);

        List<MealTo> mealToList = MealsUtil.filteredByStreams(MealsUtil.meals, LocalTime.MIN, LocalTime.MAX, CALORIES_THRESHOLD);
        request.setAttribute("mealToList", mealToList);
        request.getRequestDispatcher("meals.jsp").forward(request, response);
    }
}
