package com.musafi.phonecalllistener.CallBack;

import com.musafi.phonecalllistener.Entity.UserInfo;

import java.util.ArrayList;

public interface CallBack_UsersReady {
    void usersReady(ArrayList<UserInfo> users);
    void error();
}