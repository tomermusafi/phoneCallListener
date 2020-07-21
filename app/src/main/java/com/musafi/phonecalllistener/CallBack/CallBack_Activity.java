package com.musafi.phonecalllistener.CallBack;

import com.musafi.phonecalllistener.Entity.UserInfo;

public interface CallBack_Activity {

    void goToUserPage(UserInfo userInfo);
    void goToMapPage(UserInfo userInfo);
    void goToContactListPage(UserInfo userInfo);
    void goToUsersListPage();
    void gotToMoreInfoPage(UserInfo userInfo);
}
