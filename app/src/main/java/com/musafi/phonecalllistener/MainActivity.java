package com.musafi.phonecalllistener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.musafi.phonecalllistener.CallBack.CallBack_Activity;
import com.musafi.phonecalllistener.Entity.UserInfo;
import com.musafi.phonecalllistener.Fragment.ContactListFragment;
import com.musafi.phonecalllistener.Fragment.MapFragment;
import com.musafi.phonecalllistener.Fragment.MoreInfoFragment;
import com.musafi.phonecalllistener.Fragment.UserFragment;
import com.musafi.phonecalllistener.Fragment.UsersListFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<UserInfo> myUsers;
    private UserFragment userFragment;
    private UsersListFragment usersListFragment;
    private ContactListFragment contactListFragment;
    private MapFragment mapFragment;
    private MoreInfoFragment moreInfoFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myUsers = new ArrayList<>();

        userFragment = new UserFragment();
        usersListFragment = new UsersListFragment(this);
        contactListFragment = new ContactListFragment(this);
        mapFragment = new MapFragment();
        moreInfoFragment = new MoreInfoFragment();

        userFragment.setCallBack(myCallBack);
        usersListFragment.setCallBack(myCallBack);
        contactListFragment.setCallBack(myCallBack);
        mapFragment.setCallBack(myCallBack);
        moreInfoFragment.setCallBack(myCallBack);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fl, usersListFragment);
        transaction.commit();

    }

    CallBack_Activity myCallBack = new CallBack_Activity() {
        @Override
        public void goToUserPage(UserInfo userInfo) {
            userFragment.setUserInfo(userInfo);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_fl, userFragment);
            transaction.commit();
        }

        @Override
        public void goToMapPage(UserInfo userInfo) {
            mapFragment.setUserInfo(userInfo);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_fl, mapFragment);
            transaction.commit();
        }

        @Override
        public void goToContactListPage(UserInfo userInfo) {
            contactListFragment.setUserInfo(userInfo);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_fl, contactListFragment);
            transaction.commit();
        }

        @Override
        public void goToUsersListPage() {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_fl, usersListFragment);
            transaction.commit();
        }

        @Override
        public void gotToMoreInfoPage(UserInfo userInfo) {
            moreInfoFragment.setUserInfo(userInfo);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_fl, moreInfoFragment);
            transaction.commit();
        }
    };

    @Override
    public void onBackPressed() {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fl, usersListFragment);
        transaction.commit();
    }
}
