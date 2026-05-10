package com.example.soomx1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;



public class AuctionReport {

    private static final DateTimeFormatter FILE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static Path generateReport(String productName, int productID, List<Bid> bids) throws IOException {
        // Reports are kept in a  folder so each auction leaves behind a text summary
        Path reportsDir = Paths.get("auction-reports");
        Files.createDirectories(reportsDir);

        Path reportPath = reportsDir.resolve(
                "auction-report-product-" + productID + "-" + LocalDateTime.now().format(FILE_FORMAT) + ".txt"
        );

        // Build a simple report with winner details and the full bid history
        StringBuilder report = new StringBuilder();

        report.append("====================================================\n");
        report.append("                SOOMX AUCTION REPORT                \n");
        report.append("====================================================\n\n");

        report.append("Generated Date : ")
                .append(LocalDateTime.now().format(DISPLAY_FORMAT))
                .append("\n");

        report.append("Product ID     : ")
                .append(productID)
                .append("\n");

        report.append("Product Name   : ")
                .append(productName)
                .append("\n");

        report.append("Total Bids     : ")
                .append(bids.size())
                .append("\n\n");

        report.append("====================================================\n");
        report.append("                    WINNER DETAILS                  \n");
        report.append("====================================================\n");

        if (bids.isEmpty()) {

            report.append("No bids were placed for this auction.\n");

        } else {

            Bid winner = bids.get(bids.size() - 1);

            report.append(String.format("%-18s : %s\n",
                    "Winner Name", winner.getName()));

            report.append(String.format("%-18s : %s $\n",
                    "Winning Bid", winner.getBidPrice()));

            report.append(String.format("%-18s : %s\n",
                    "Contact Info", winner.getContactInfo()));
        }

        report.append("\n");
        report.append("====================================================\n");
        report.append("                     BID HISTORY                    \n");
        report.append("====================================================\n\n");

        if (bids.isEmpty()) {

            report.append("No bid history available.\n");

        } else {

            report.append(String.format(
                    "%-10s %-15s %-15s %-30s\n",
                    "Bid ID",
                    "User",
                    "Bid Price",
                    "Contact"
            ));

            report.append("--------------------------------------------------------------------------\n");

            for (Bid bid : bids) {

                report.append(String.format(
                        "%-10s %-15s %-15s %-30s\n",
                        bid.getBidId(),
                        bid.getName(),
                        bid.getBidPrice() + " $",
                        bid.getContactInfo()
                ));
            }
        }

        report.append("\n====================================================\n");
        report.append("                 END OF AUCTION REPORT              \n");
        report.append("====================================================\n");




        Files.writeString(reportPath, report.toString(), StandardCharsets.UTF_8);
        return reportPath;
    }
}
