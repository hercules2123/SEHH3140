package com.example.sehh3140;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class couponview extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    String c;
    TextView a,b,cvTv3,cvTv4,cvTv5;
    Button cvbtn1;
    public couponview() {
        // Required empty public constructor
    }

    public static couponview newInstance(String param1, String param2) {
        couponview fragment = new couponview();
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
        View view = inflater.inflate(R.layout.fragment_couponview, container, false);

        personal personal=new personal();
        a=view.findViewById(R.id.cvtv1);
        b=view.findViewById(R.id.cvtv2);
        cvbtn1=view.findViewById(R.id.cvbtn1);
        cvTv3=view.findViewById(R.id.cvtv3);
        cvTv4=view.findViewById(R.id.cvtv4);
        cvTv5=view.findViewById(R.id.cvtv5);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        c=sharedPreferences.getString("key",null);
        if(c!=null){
            a.setText(c);
            b.setText("Account: ");
            cvTv3.setText("Discount Code: ");
            cvTv4.setText("");
            cvTv5.setText("(Due date: 31/12/2022");
            cvbtn1.setVisibility(View.VISIBLE);
        }
        cvbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.Fragment, personal);
                transaction.commit();
            }
        });

        return view;
    }
}