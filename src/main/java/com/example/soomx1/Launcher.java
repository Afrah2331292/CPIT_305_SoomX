package com.example.soomx1;

import javafx.application.Application;
import soomXDatabase.DatabaseSetup;
import soomXDatabase.ProductDAO;
import soomXDatabase.UserDAO;

import java.time.LocalDate;

public class Launcher {
    public static void main(String[] args) {

        System.out.print("/com/example/soomx1/images/Saudi_Arabia_Ai.png");


        DatabaseSetup.setupDatabase();
        ProductDAO.insertProduct(
                "White Rare Falcon",
                "A rare white falcon, very valuable and majestic.",
                1200,
                LocalDate.of(2026, 5, 10),
                "1:15 PM"
        );

        ProductDAO.insertProduct(
                "Desert Camel",
                "A strong desert camel, perfect for long journeys and desert life.",
                800,
                LocalDate.of(2026, 5, 15),
                "2:00 PM"
        );

        ProductDAO.insertProduct(
                "Ancient Saudi Painting",
                "A beautiful ancient heritage painting, showcasing traditional art and history.",
                1500,
                LocalDate.of(2026, 5, 20),
                "8:30 AM"
        );

        ProductDAO.insertProduct(
                "Ancient Sharp Sword",
                "A medieval sharp sword, historically significant and well-preserved.",
                2200,
                LocalDate.of(2026, 5, 25),
                "10:15 AM"
        );

        UserDAO.insertUser("Admin","Admin@gmail.com","0559900045","0000",1);
        UserDAO.insertUser("Fake_User","FakeUser@gmail.com","0559900045","0000",2);

        Application.launch(Main.class, args);


    }
}
