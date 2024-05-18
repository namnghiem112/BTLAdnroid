package com.example.shopcafe.model;

public class UserDetail {

    private String password;
    private String name;
    private String email;
    private String contact;
    private String birthdate;

    public UserDetail() {
    }

    public UserDetail(String password, String name, String email, String contact, String birthdate) {
        this.password = password;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.birthdate = birthdate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}
