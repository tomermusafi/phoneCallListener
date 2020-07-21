package com.musafi.phonecalllistener.CallBack;

import com.musafi.phonecalllistener.Entity.CallInfo;

import java.util.ArrayList;

public interface CallBack_callReady {
    void usersReady(ArrayList<CallInfo> calls);
    void error();
}