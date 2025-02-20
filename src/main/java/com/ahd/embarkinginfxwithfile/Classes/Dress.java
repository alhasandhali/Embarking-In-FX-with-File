package com.ahd.embarkinginfxwithfile.Classes;

public class Dress {
    private String dressId;
    private String dressName;
    private String dressType;
    private String dressSize;
    private String dressColor;
    private double dressPrice;
    private String dressDetails;
    private String purchaseDate;
    private int dressQuantity;
    private String discountCode;
    private String customerGander;
    private boolean boost;
    private String dressImage;

    public Dress() {
    }

    public Dress(String dressId, String dressName, String dressType, String dressSize, String dressColor, double dressPrice, String dressDetails, String purchaseDate, int dressQuantity, String discountCode, String customerGander, boolean boost, String dressImage) {
        this.dressId = dressId;
        this.dressName = dressName;
        this.dressType = dressType;
        this.dressSize = dressSize;
        this.dressColor = dressColor;
        this.dressPrice = dressPrice;
        this.dressDetails = dressDetails;
        this.purchaseDate = purchaseDate;
        this.dressQuantity = dressQuantity;
        this.discountCode = discountCode;
        this.customerGander = customerGander;
        this.boost = boost;
        this.dressImage = dressImage;
    }

    public Dress(String dressId, String dressName, String dressType, String dressColor, double dressPrice, String purchaseDate, int dressQuantity, boolean boost) {
        this.dressId = dressId;
        this.dressName = dressName;
        this.dressType = dressType;
        this.dressColor = dressColor;
        this.dressPrice = dressPrice;
        this.purchaseDate = purchaseDate;
        this.dressQuantity = dressQuantity;
        this.boost = boost;
    }

    public String getDressId() {
        return dressId;
    }

    public void setDressId(String dressId) {
        this.dressId = dressId;
    }

    public String getDressName() {
        return dressName;
    }

    public void setDressName(String dressName) {
        this.dressName = dressName;
    }

    public String getDressType() {
        return dressType;
    }

    public void setDressType(String dressType) {
        this.dressType = dressType;
    }

    public String getDressSize() {
        return dressSize;
    }

    public void setDressSize(String dressSize) {
        this.dressSize = dressSize;
    }

    public String getDressColor() {
        return dressColor;
    }

    public void setDressColor(String dressColor) {
        this.dressColor = dressColor;
    }

    public double getDressPrice() {
        return dressPrice;
    }

    public void setDressPrice(double dressPrice) {
        this.dressPrice = dressPrice;
    }

    public String getDressDetails() {
        return dressDetails;
    }

    public void setDressDetails(String dressDetails) {
        this.dressDetails = dressDetails;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getDressQuantity() {
        return dressQuantity;
    }

    public void setDressQuantity(int dressQuantity) {
        this.dressQuantity = dressQuantity;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public String getCustomerGander() {
        return customerGander;
    }

    public void setCustomerGander(String customerGander) {
        this.customerGander = customerGander;
    }

    public boolean getBoost() {
        return boost;
    }

    public void setBoost(boolean boost) {
        this.boost = boost;
    }

    public String getDressImage() {
        return dressImage;
    }

    public void setDressImage(String dressImage) {
        this.dressImage = dressImage;
    }

    public void display() {
        System.out.println("Dress ID: " + dressId);
        System.out.println("Dress Name: " + dressName);
        System.out.println("Dress Type: " + dressType);
        System.out.println("Dress Size: " + dressSize);
        System.out.println("Dress Color: " + dressColor);
        System.out.println("Dress Price: " + dressPrice);
        System.out.println("Dress Details: " + dressDetails);
        System.out.println("Dress Date: " + purchaseDate);
        System.out.println("Dress Quantity: " + dressQuantity);
        System.out.println("Discount Code: " + discountCode);
        System.out.println("Customer Gander: " + customerGander);
        System.out.println("Boost: " + boost);
        System.out.println("Dress Image: " + dressImage);
    }
}
