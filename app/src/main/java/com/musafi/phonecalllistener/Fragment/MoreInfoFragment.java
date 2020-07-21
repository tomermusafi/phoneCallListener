package com.musafi.phonecalllistener.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.musafi.phonecalllistener.CallBack.CallBack_Activity;
import com.musafi.phonecalllistener.Entity.CallInfo;
import com.musafi.phonecalllistener.Entity.UserInfo;
import com.musafi.phonecalllistener.R;

public class MoreInfoFragment extends Fragment {
    private CallBack_Activity callBack_activityList;
    private View view;

    private UserInfo userInfo;
    private TextView more_info_txv_info;

    public MoreInfoFragment(){

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
            view = inflater.inflate(R.layout.more_info_fragment, container, false);
        }

        findViews(view);

        int incomingCalls = 0;
        int outgoingCalls = 0;
        for (CallInfo c : userInfo.getUserCallsInfo().values()){
            if(c.getCallStatus().equals("OUTGOING_CALL"))
                outgoingCalls+=1;
            if(c.getCallStatus().equals("INCOMING_CALL"))
                incomingCalls+=1;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Name: "+userInfo.getUserName()+"\n")
                .append("Phone Number: "+userInfo.getUserPhoneNumber()+"\n")
                .append("Total calls: " + userInfo.getUserCallsInfo().size()+"\n")
                .append("Number Of Incoming Calls: " + incomingCalls+"\n")
                .append("Number Of Outgoing Calls: "+ outgoingCalls+"\n");

        more_info_txv_info.setText(sb.toString());

        return view;
    }

    private void findViews(View view) {
        more_info_txv_info = view.findViewById(R.id.more_info_txv_info);


    }


}
