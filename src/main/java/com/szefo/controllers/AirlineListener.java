package com.szefo.controllers;

import com.szefo.models.Passenger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;

@WebListener
public class AirlineListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        ArrayList<Passenger> passengerList =
                (ArrayList<Passenger>) servletContext.getAttribute("passenger");

        if (passengerList == null) {
            System.out.println("no passenger list created yet. Let's create the list here...");
            passengerList = new ArrayList<Passenger>();
            servletContext.setAttribute("passengers", passengerList);
        }
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
