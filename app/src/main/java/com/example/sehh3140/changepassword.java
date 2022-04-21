package com.example.sehh3140;

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class changepassword extends Fragment {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://sehh3140database-f8582-default-rtdb.firebaseio.com/");
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    String c;
    TextView a,b,cpTv3,cpTv4;
    Button cpBtn1,cpBtn2;
    EditText cpEt1,cpEt2;

    public changepassword() {
        // Required empty public constructor
    }

    public static changepassword newInstance(String param1, String param2) {
        changepassword fragment = new changepassword();
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
        View view =inflater.inflate(R.layout.fragment_changepassword, container, false);

        personal personal=new personal();
        a=view.findViewById(R.id.cptv1);
        b=view.findViewById(R.id.cptv2);
        cpTv3=view.findViewById(R.id.cptv3);
        cpTv4=view.findViewById(R.id.cptv4);
        cpEt1=view.findViewById(R.id.cpet1);
        cpEt2=view.findViewById(R.id.cpet2);
        cpBtn1=view.findViewById(R.id.cpbtn1);
        cpBtn2=view.findViewById(R.id.cpbtn2);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        c=sharedPreferences.getString("key",null);
        if(c!=null){
            a.setText(c);
            b.setText("Account: ");
            cpTv3.setText("New password");
            cpTv4.setText("Input new password again");
            cpEt1.setVisibility(View.VISIBLE);
            cpEt2.setVisibility(View.VISIBLE);
            cpBtn1.setVisibility(View.VISIBLE);
            cpBtn2.setVisibility(View.VISIBLE);
            cpBtn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String passwordTxt=cpEt1.getText().toString();
                    String conPasswordTxt=cpEt2.getText().toString();
                    if(passwordTxt.isEmpty() || conPasswordTxt.isEmpty() ){
                        Toast.makeText(getActivity(),"請輸入所有密碼", Toast.LENGTH_SHORT).show();
                    }
                    else if(!passwordTxt.equals(conPasswordTxt)){
                        Toast.makeText(getActivity(),"兩個密碼並不相同", Toast.LENGTH_SHORT).show();
                    }else{
                        databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        databaseReference.child("users").child(c).child("password").setValue(passwordTxt);
                                        Toast.makeText(getActivity(),"更改成功", Toast.LENGTH_SHORT).show();




                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
            });
            cpBtn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.Fragment, personal);
                    transaction.commit();
                }
            });
        }



        return view;
    }
}