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

import com.musafi.phonecalllistener.Adapter.Adapter_ContactListModel;
import com.musafi.phonecalllistener.CallBack.CallBack_Activity;
import com.musafi.phonecalllistener.Entity.Contact;
import com.musafi.phonecalllistener.Entity.UserInfo;
import com.musafi.phonecalllistener.R;

import java.util.ArrayList;
import java.util.TreeMap;

public class ContactListFragment extends Fragment {

    private CallBack_Activity callBack_activityList;

    private View view;

    private RecyclerView contact_rv_contact_list;
    private Adapter_ContactListModel adapter_contactListModel;
    UserInfo userInfo;
    Context context;
    ArrayList<Contact> contacts;
    public ContactListFragment(Context context){
        this.context = context;
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
            view = inflater.inflate(R.layout.contact_list_fragment, container, false);
        }

        findViews(view);
        TreeMap<String,String> sortedContact = new TreeMap<>(userInfo.getUserContacts());
        ArrayList<String> keys = new ArrayList<>(sortedContact.keySet());
        ArrayList<String> values = new ArrayList<>(sortedContact.values());
        contacts = new ArrayList<>();
        for(int i = 0; i < keys.size(); i++){
            contacts.add(new Contact(values.get(i), keys.get(i)));
        }
        initList();
        return view;
    }

    private void findViews(View view) {
        contact_rv_contact_list = view.findViewById(R.id.contact_rv_contact_list);

    }


    private void initList() {
        adapter_contactListModel = new Adapter_ContactListModel(context, contacts);
        contact_rv_contact_list.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        contact_rv_contact_list.setLayoutManager(layoutManager);
        contact_rv_contact_list.setAdapter(adapter_contactListModel);

        adapter_contactListModel.SetOnItemClickListener(new Adapter_ContactListModel.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position, Contact user) {
                openScore(user);
               // callBack_activityList.goToUserPage(user);

            }
        });
    }
    private void openScore(Contact user) {
        Toast.makeText(context, "" +user.getName(), Toast.LENGTH_SHORT).show();
    }


}