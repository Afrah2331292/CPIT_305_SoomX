package com.example.soomx1;

import javafx.application.Application;
import soomXDatabase.DatabaseSetup;
import soomXDatabase.ProductDAO;
import soomXDatabase.UserDAO;

public class Launcher {
    public static void main(String[] args) {

        System.out.print("/com/example/soomx1/images/Saudi_Arabia_Ai.png");


        DatabaseSetup.setupDatabase();

        ProductDAO.insertProduct("White Rare Falcon", "A rare white falcon, very valuable and majestic.", 1200);
        ProductDAO.insertProduct("Desert Camel", "A strong desert camel, perfect for long journeys and desert life.", 800);
        ProductDAO.insertProduct("Ancient Saudi Painting", "A beautiful ancient heritage painting, showcasing traditional art and history.", 1500);
        ProductDAO.insertProduct("Ancient Sharp Sword", "A medieval sharp sword, historically significant and well-preserved.", 2200);

        UserDAO.insertUser("AFRAH","AFRA","049","AFRAH",1);
        UserDAO.insertUser("HANA","SS","044","HANA",2);

        Application.launch(Main.class, args);


    }
}
