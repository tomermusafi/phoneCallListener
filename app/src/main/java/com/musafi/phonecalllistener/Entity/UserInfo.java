package com.musafi.phonecalllistener.Entity;


import java.util.Map;

public class UserInfo {
    private String userId;
    private String userPhoneNumber;
    private Map<String,String> userContacts;
    private Map<String, CallInfo> userCallsInfo;
    private String userName;


    public UserInfo(String userId, String userPhoneNumber, Map<String, String> userContacts, Map<String, CallInfo> userCallsInfo, String userName) {
        this.userPhoneNumber = userPhoneNumber;
        this.userContacts = userContacts;
        this.userCallsInfo = userCallsInfo;
        this.userName = userName;
        this.userId = userId;
    }

    public UserInfo() {
    }

    public String getUserId() {
        return userId;
    }

    public UserInfo setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public UserInfo setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
        return this;
    }

    public Map<String, String> getUserContacts() {
        return userContacts;
    }

    public UserInfo setUserContacts(Map<String, String> userContacts) {
        this.userContacts = userContacts;
        return this;
    }

    public Map<String, CallInfo> getUserCallsInfo() {
        return userCallsInfo;
    }

    public UserInfo setUserCallsInfo(Map<String, CallInfo> userCallsInfo) {
        this.userCallsInfo = userCallsInfo;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public UserInfo setUserName(String userName) {
        this.userName = userName;
        return this;
    }
}
