package com.example.sehh3140;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseError;

public class couponview extends Fragment {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://sehh3140database-f8582-default-rtdb.firebaseio.com/");
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

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        c=sharedPreferences.getString("key",null);

        personal personal=new personal();
        a=view.findViewById(R.id.cvtv1);
        b=view.findViewById(R.id.cvtv2);
        cvbtn1=view.findViewById(R.id.cvbtn1);
        cvTv3=view.findViewById(R.id.cvtv3);
        cvTv4=view.findViewById(R.id.cvtv4);
        cvTv5=view.findViewById(R.id.cvtv5);

        if(c!=null){
            b.setVisibility(View.VISIBLE);
            a.setVisibility(View.VISIBLE);
            cvTv3.setVisibility(View.VISIBLE);
            cvTv4.setVisibility(View.VISIBLE);
            cvTv5.setVisibility(View.VISIBLE);
            b.setText("帳戶: ");
            cvTv3.setText("優惠券代碼: ");
            cvTv4.setText("(最後限期: 31/12/2022)");
        databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.hasChild(c)) {
                    String getNewCoupon;
                    a.setText(c);
                    getNewCoupon = snapshot.child(c).child("coupon").getValue(String.class);
                    cvTv4.setText(getNewCoupon);
                } }@Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

            });}


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