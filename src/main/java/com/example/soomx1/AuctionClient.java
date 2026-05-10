package com.example.soomx1;

import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class AuctionClient {

    public static String sendBid(String username, int productID, double bidPrice) {

        try (

                // Hana:
                // The client connects to the AuctionServer using localhost and port 8189.
                Socket client = new Socket("127.0.0.1", 8189);

                // Hana:
                // Scanner is used to receive messages from the server.
                Scanner in = new Scanner(client.getInputStream(), StandardCharsets.UTF_8);

                // Hana:
                // PrintWriter is used to send bid data to the server.
                PrintWriter out = new PrintWriter(client.getOutputStream(), true, StandardCharsets.UTF_8)

        ) {

            // Hana:
            // The client sends username, product ID, and bid price
            // in one message separated by "|".
            out.println(username + "|" + productID + "|" + bidPrice);

            // Hana:
            // Wait for the server response (ACCEPT or ERROR).
            return in.nextLine();

        } catch (Exception e) {
            e.printStackTrace();

            // Hana:
            // Return ERROR if connection or communication fails.
            return "ERROR";
        }
    }
}