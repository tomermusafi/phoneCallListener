package com.musafi.phonecalllistener.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.musafi.phonecalllistener.CallBack.CallBack_Activity;
import com.musafi.phonecalllistener.Entity.UserInfo;
import com.musafi.phonecalllistener.R;



public class UserFragment extends Fragment {

    private CallBack_Activity callBack_activityList;
    private View view;

    private UserInfo userInfo;
    private Button userF_btn_map, userF_btn_contact_list, userF_btn_more_info;

    public UserFragment(){

    }

    public void setCallBack(CallBack_Activity _callBack_activityList) {
        this.callBack_activityList = _callBack_activityList;
    }

    public void setUserInfo(UserInfo userInfo){
        this.userInfo = userInfo;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);
        Log.d("pttt", "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("pttt", "onCreateView");
        if(view==null){
            view = inflater.inflate(R.layout.user_fragment, container, false);
        }

        findViews(view);


        userF_btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack_activityList.goToMapPage(userInfo);
            }
        });

        userF_btn_contact_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack_activityList.goToContactListPage(userInfo);
            }
        });

        userF_btn_more_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack_activityList.gotToMoreInfoPage(userInfo);
            }
        });


        return view;
    }

    private void findViews(View view) {
        userF_btn_map = view.findViewById(R.id.userF_btn_map);
        userF_btn_contact_list = view.findViewById(R.id.userF_btn_contact_list);
        userF_btn_more_info = view.findViewById(R.id.userF_btn_more_info);

    }



}