package com.example.workshop2.ModelClass;

public class UserListModel {
    private String userId, userName, userPhone,userEmail, userCity;

    public userListModel(String userId, String userName, String userPhone, String userEmail, String userCity) {
        this.userId=userId;
        this.userName=userName;
        this.userPhone=userPhone;
        this.userEmail=userEmail;
        this.userCity=userCity;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId=userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName=userName;
    }
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail=userEmail;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity=userCity;
    }
    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone=userPhone;
    }
}
