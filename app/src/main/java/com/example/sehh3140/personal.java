package com.example.sehh3140;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class personal extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    userinfo userinfo =new userinfo();
    changepassword changepassword = new changepassword();
    couponview couponview = new couponview();
    String[] a = {
            "個人資料","修改密碼","查看優惠券"
    };
    ListView lv1 ;




    public personal() {
        // Required empty public constructor
    }



    public static personal newInstance(String param1, String param2) {
        personal fragment = new personal();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_personal, container, false);

        lv1 = view.findViewById(R.id.lv1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.listview,R.id.tv,a);
        lv1.setAdapter(adapter);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.Fragment, userinfo);
                    transaction.commit();
                } else if(position==1){
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.Fragment, changepassword);
                    transaction.commit();
                }else if(position==2){
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.Fragment, couponview);
                    transaction.commit();
                }
            }
        });

        return view;
    }
 }
