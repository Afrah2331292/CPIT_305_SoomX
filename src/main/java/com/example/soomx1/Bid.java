package com.example.soomx1;

public class Bid {

    private String bidId;
    private String name;
    private String bidPrice;
    private String contactInfo;

    public Bid(String bidId, String name, String bidPrice, String contactInfo) {
        this.bidId = bidId;
        this.name = name;
        this.bidPrice = bidPrice;
        this.contactInfo = contactInfo;
    }

    public String getBidId() { return bidId; }
    public String getName() { return name; }
    public String getBidPrice() { return bidPrice; }
    public String getContactInfo() { return contactInfo; }
}
