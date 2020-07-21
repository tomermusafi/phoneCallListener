package com.musafi.phonecalllistener;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.musafi.phonecalllistener.CallBack.CallBack_UsersReady;
import com.musafi.phonecalllistener.CallBack.CallBack_callReady;
import com.musafi.phonecalllistener.Entity.CallInfo;
import com.musafi.phonecalllistener.Entity.UserInfo;

import java.util.ArrayList;

public class MyFirebase {

    public static void getCalls(final CallBack_callReady callBack_callReady, final UserInfo userInfo) {
        final ArrayList<CallInfo> calls = new ArrayList<>();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("usersInfo");

        myRef.child("Users").child(userInfo.getUserId()).child("userCallsInfo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot == null)
                    callBack_callReady.error();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    CallInfo call = ds.getValue(CallInfo.class);

                    calls.add(call);
                }

                callBack_callReady.usersReady(calls);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callBack_callReady.error();
            }
        });
    }

    public static void getUsers(final CallBack_UsersReady callBack_usersReady) {
        final ArrayList<UserInfo> users2 = new ArrayList<>();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("usersInfo");

        myRef.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot == null)
                    callBack_usersReady.error();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    UserInfo user = ds.getValue(UserInfo.class);

                    users2.add(user);
                }

                callBack_usersReady.usersReady(users2);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callBack_usersReady.error();
            }
        });
    }
}