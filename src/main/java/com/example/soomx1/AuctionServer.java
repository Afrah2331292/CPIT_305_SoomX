package com.example.soomx1;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class AuctionServer {

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(8189)) {

            // Hana:
            // This server listens for incoming bid requests from users.
            // Port 8189 is used for communication between the client and the server.

            int i = 1;

            System.out.println("SoomX Server is running...");

            // Hana:
            // The server keeps running to accept multiple users continuously.
            while (true) {

                // Hana:
                // accept() waits until a client connects to the server.
                Socket incoming = server.accept();

                System.out.println("Spawning client: " + i);

                // Hana:
                // Each connected client gets its own Runnable handler.
                // This allows multiple users to place bids at the same time.
                Runnable r = new BidClientHandler(incoming);

                // Hana:
                // A separate thread is created for every client request.
                Thread t = new Thread(r);

                // Hana:
                // start() runs the thread concurrently.
                t.start();

                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}