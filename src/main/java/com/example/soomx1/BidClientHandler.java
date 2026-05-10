package com.example.soomx1;



import soomXDatabase.BidDAO;

import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class BidClientHandler implements Runnable {

    private Socket incoming;

    public BidClientHandler(Socket incomingSocket) {
        incoming = incomingSocket;
    }

    @Override
    public void run() {

        try (
                Scanner in = new Scanner(incoming.getInputStream(), StandardCharsets.UTF_8);
                PrintWriter out = new PrintWriter(incoming.getOutputStream(), true, StandardCharsets.UTF_8)
        ) {
            String line = in.nextLine();

            String[] parts = line.split("\\|");

            String username = parts[0];
            int productID = Integer.parseInt(parts[1]);
            double bidPrice = Double.parseDouble(parts[2]);

            // Hana:
            // Print the thread name to show that each client is handled by a different thread.
            System.out.println("START " + Thread.currentThread().getName()
                    + " | User: " + username
                    + " | Product ID: " + productID
                    + " | Bid: " + bidPrice);

            boolean success = BidDAO.insertBid(bidPrice, username, productID);

            // Hana:
            // Sleep is used here only to make the multithreading behavior visible in the console,
            // similar to the thread examples in the slides.

            if (success) {
                out.println("ACCEPT");

                System.out.println("END " + Thread.currentThread().getName()
                        + " | Bid accepted for " + username);
            } else {
                out.println("REJECT");

                System.out.println("END " + Thread.currentThread().getName()
                        + " | Bid rejected for " + username);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }}