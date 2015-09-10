package com.szefo.controllers;

import com.szefo.models.Gender;
import com.szefo.models.Passenger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/AddPassenger")
public class AddPassenger extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("first_name", "");
        req.setAttribute("last_name", "");
        req.setAttribute("date_birth", "");
        RequestDispatcher view = req.getRequestDispatcher("WEB-INF/views/add_passenger.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("errors", false);

        Passenger passenger = new Passenger();

        String firstName = req.getParameter("first_name");
        if (firstName.length() == 0) {
            req.setAttribute("errors", true);
            req.setAttribute("firstName_error", true);
            req.setAttribute("first_name", "");
        } else {
            passenger.setFirstName(firstName);
            req.setAttribute("first_name", firstName);
        }

        String lastName = req.getParameter("last_name");
        if (lastName.length() == 0) {
            req.setAttribute("errors", true);
            req.setAttribute("lastName_error", true);
            req.setAttribute("last_name", "");
        } else {
            passenger.setLastName(lastName);
            req.setAttribute("last_name", lastName);
        }

        String dateBirth = req.getParameter("date_birth");
        String dateBirthArray[] = dateBirth.split("\\/");

        String pattern = "^\\d{1,2}\\/\\d{1,2}\\/\\d{4}$";
        Pattern patternClass = Pattern.compile(pattern);
        Matcher matcher = patternClass.matcher(dateBirth);
        if (matcher.find()) {
            String day = dateBirthArray[0];
            String month = dateBirthArray[1];
            String year = dateBirthArray[2];

            Calendar cal = Calendar.getInstance();

            cal.set(Calendar.YEAR, Integer.parseInt(year));
            cal.set(Calendar.MONTH, Integer.parseInt(month));
            cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));

            Date date = cal.getTime();

            passenger.setDateBirth(date);
            req.setAttribute("date_birth", dateBirth);
        } else {
            System.out.println("Invalid day of birth");
            req.setAttribute("errors", true);
            req.setAttribute("date_format_error", true);
            if (dateBirth.length() != 0)
                req.setAttribute("date_birth", dateBirth);
            else
                req.setAttribute("date_birth", "");
        }

        String gender = req.getParameter("gender");
        passenger.setGender(Gender.valueOf(gender));

        if ((Boolean) req.getAttribute("errors")) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/views/add_passenger.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            ServletContext servletContext = this.getServletContext();
            synchronized (this) {
                ArrayList<Passenger> passengerList =
                        (ArrayList<Passenger>) servletContext.getAttribute("passengers");
                passengerList.add(passenger);
                servletContext.setAttribute("passengers", passengerList);
                resp.sendRedirect("");
            }
        }
    }

}
