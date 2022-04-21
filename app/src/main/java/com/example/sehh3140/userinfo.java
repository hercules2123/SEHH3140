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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class userinfo extends Fragment {



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    Button userinfoBtn;
    TextView a,b;
    String c;

    public userinfo() {
        // Required empty public constructor
    }


    public static userinfo newInstance(String param1, String param2) {
        userinfo fragment = new userinfo();
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
        View view =inflater.inflate(R.layout.fragment_userinfo, container, false);

        personal personal=new personal();
        a=view.findViewById(R.id.a);
        b=view.findViewById(R.id.b);
        userinfoBtn=view.findViewById(R.id.userinfobtn);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        c=sharedPreferences.getString("key",null);
        if(c!=null){
            a.setText(c);
            b.setText("帳戶: ");
            userinfoBtn.setVisibility(View.VISIBLE);
        }
         userinfoBtn.setOnClickListener(new View.OnClickListener() {
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