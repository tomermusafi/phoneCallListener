package com.musafi.phonecalllistener.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.musafi.phonecalllistener.CallBack.CallBack_Activity;
import com.musafi.phonecalllistener.Entity.CallInfo;
import com.musafi.phonecalllistener.Entity.UserInfo;
import com.musafi.phonecalllistener.R;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private static MapFragment INSTANCE = null;
    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";
    private CallBack_Activity callBack_activityList;
    View view;
    UserInfo userInfo;
    GoogleMap map;
    MapView mapView;

    public MapFragment(){

    }
    public void setCallBack(CallBack_Activity _callBack_activityList) {
        this.callBack_activityList = _callBack_activityList;
    }
    public void setUserInfo(UserInfo userInfo){
        this.userInfo = userInfo;
    }

    public static MapFragment getINSTANCE(){
        if(INSTANCE == null)
            INSTANCE = new MapFragment();
        return INSTANCE;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.map_fragment, container, false);
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }
        mapView = view.findViewById(R.id.mapView);

//        MyFirebase.getCalls(new CallBack_callReady() {
//                                @Override
//                                public void usersReady(ArrayList<CallInfo> calls) {
//                                    refreshList(calls);
//                                }
//
//                                @Override
//                                public void error() {
//
//                                }
//                            }, userInfo);


                mapView.onCreate(mapViewBundle);

        mapView.getMapAsync(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        mapView = view.findViewById(R.id.mapView);
////
////        if(mapView != null){
////            mapView.onCreate(null);
////            mapView.onResume();
////            mapView.getMapAsync(this);
////        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        map = googleMap;
        placeMarkers();
//        map.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
//        map.addMarker(new MarkerOptions().position(new LatLng(30, 30)).title("Marker"));


//        if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
//        != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
//        != PackageManager.PERMISSION_GRANTED){
//            return;
//        }
        //map.setMyLocationEnabled(true);
    }

    private void placeMarkers(){
        for(CallInfo call : userInfo.getUserCallsInfo().values()){
            StringBuilder sb = new StringBuilder();
            sb.append("Call Status: "+call.getCallStatus()+"\n")
                    .append("Call Duration: "+call.getCallDuration()+" s\n")
                    .append("Call Date: "+call.getDate()+"\n")
                    .append("Call Location: "+call.getmLatitude()+":"+call.getmLongitude()+"\n")
                    .append("Phone Number: "+call.getOtherPhoneNumber()+"\n")
                    .append("Name: "+call.getOtherName()+"\n");

            if(call.getmLatitude() > 0 && call.getmLongitude() > 0){

                map.addMarker(new MarkerOptions().position(new LatLng(call.getmLatitude()
                        , call.getmLongitude())).title(sb.toString()));
            }
        }
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                showAlert(marker.getTitle(), marker);
                return false;
            }
        });
    }

    private void showAlert(String s, final Marker marker){
        new AlertDialog.Builder(getContext())
                .setTitle("Call Info")
                .setMessage(s)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        map.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(),18),5000,null);
                    }
                })
                .show();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
//    private void refreshList(ArrayList<CallInfo> calls) {
//        myCalls = new ArrayList<>(calls);
//        placeMarkers();
//
//    }

}

