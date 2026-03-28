package com.example.foodloop;

public class RecyclerRowObject {
    private String name, street, city, province, country, postal, phone, email, password;
    private String status, itemName, recipient;

    // DEFAULT
    public RecyclerRowObject() {
    }

    // FOR ACCOUNTS
    public RecyclerRowObject(String name, String street, String city, String province, String country,
                               String postal, String phone, String email, String password) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.province = province;
        this.country = country;
        this.postal = postal;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    // FOR DONATION HISTORY
    public RecyclerRowObject(String status, String itemname, String recipient) {
        this.status = status;
        this.itemName = itemname;
        this.recipient = recipient;
    }

    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getCountry() {
        return country;
    }

    public String getPostal() {
        return postal;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }

    public String getItemName() {
        return itemName;
    }

    public String getRecipient() {
        return recipient;
    }
}
