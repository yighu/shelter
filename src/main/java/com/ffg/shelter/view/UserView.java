package com.ffg.shelter.view;

/**
 * Created with IntelliJ IDEA.
 * User: N060974
 * Date: 7/7/13
 * Time: 4:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserView {

    private Long userId;
    private String userEmail;
    private String userRole;
    private String userExpiryDate;
    private String userExpiryDay;
    private String userExpiryMonth;
    private String userExpiryYear;
    private String userFirstName;
    private String userLastName;

    public String getUserExpiryDay() {
        return userExpiryDay;
    }

    public void setUserExpiryDay(String userExpiryDay) {
        this.userExpiryDay = userExpiryDay;
    }

    public String getUserExpiryMonth() {
        return userExpiryMonth;
    }

    public void setUserExpiryMonth(String userExpiryMonth) {
        this.userExpiryMonth = userExpiryMonth;
    }

    public String getUserExpiryYear() {
        return userExpiryYear;
    }

    public void setUserExpiryYear(String userExpiryYear) {
        this.userExpiryYear = userExpiryYear;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserExpiryDate() {
        return userExpiryDate;
    }

    public void setUserExpiryDate(String userExpiryDate) {
        this.userExpiryDate = userExpiryDate;
    }
}
