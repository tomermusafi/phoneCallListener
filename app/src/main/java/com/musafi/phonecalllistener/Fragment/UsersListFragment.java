package com.musafi.phonecalllistener.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.musafi.phonecalllistener.Adapter.Adapter_UserModel;
import com.musafi.phonecalllistener.CallBack.CallBack_Activity;
import com.musafi.phonecalllistener.CallBack.CallBack_UsersReady;
import com.musafi.phonecalllistener.Entity.UserInfo;
import com.musafi.phonecalllistener.MyFirebase;
import com.musafi.phonecalllistener.R;

import java.util.ArrayList;

public class UsersListFragment extends Fragment {

    private CallBack_Activity callBack_activityList;

    private View view;

    ArrayList<UserInfo> myUsers;
    private RecyclerView main_rv_users;
    private Adapter_UserModel adapter_userModel;
    Context context;
    public UsersListFragment(Context context){
        this.context = context;
    }

    public void setCallBack(CallBack_Activity _callBack_activityList) {
        this.callBack_activityList = _callBack_activityList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);
        Log.d("pttt", "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("pttt", "onCreateView");
        if(view==null){
            view = inflater.inflate(R.layout.users_list_fragment, container, false);
        }

        findViews(view);

        MyFirebase.getUsers(new CallBack_UsersReady() {
            @Override
            public void usersReady(ArrayList<UserInfo> users) {
                refreshList(users);
                Log.d("pttt", "C - Number of users: " + users.size());
            }

            @Override
            public void error() {
                // TODO: 2020-01-08 handle errors
            }

        });

        return view;
    }

    private void findViews(View view) {
        main_rv_users = view.findViewById(R.id.main_rv_users);

    }
    private void refreshList(ArrayList<UserInfo> users) {
        myUsers = new ArrayList<>(users);
        for(UserInfo user : myUsers){
            Log.d("pttt", "name: " + user.getUserName());
        }
        initList();
    }

    private void initList() {
        adapter_userModel = new Adapter_UserModel(context, myUsers);
        main_rv_users.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        main_rv_users.setLayoutManager(layoutManager);
        main_rv_users.setAdapter(adapter_userModel);

        adapter_userModel.SetOnItemClickListener(new Adapter_UserModel.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position, UserInfo user) {
                openScore(user);
                callBack_activityList.goToUserPage(user);

            }
        });
    }
    private void openScore(UserInfo user) {
        Toast.makeText(context, "" +user.getUserName(), Toast.LENGTH_SHORT).show();
    }


}