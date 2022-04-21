package com.example.sehh3140;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;

import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://sehh3140database-f8582-default-rtdb.firebaseio.com/");
    SharedPreferences sharedpreferences;
    EditText et1,et2 ;
    Button register,login,guest;
    String user,phoneTxt;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 =findViewById(R.id.loginet1);
        et2 =findViewById(R.id.loginet2);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);
        guest = findViewById(R.id.Guest);
        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pass2();
                Intent intent = new Intent();
                intent.setClass(com.example.sehh3140.login.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(com.example.sehh3140.login.this, com.example.sehh3140.register.class);
                startActivity(intent);
            }
        });
        et1 = findViewById(R.id.loginet1);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                phoneTxt = et1.getText().toString();
                 String passwordTxt = et2.getText().toString();
                sharedpreferences = getSharedPreferences(phoneTxt,Context.MODE_PRIVATE);
                if(phoneTxt.isEmpty() || passwordTxt.isEmpty()){
                    Toast.makeText(login.this,"請輸入您的使用者名稱", Toast.LENGTH_SHORT).show();
                }
                else{

                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if(snapshot.hasChild(phoneTxt)){

                                final String getPassword = snapshot.child(phoneTxt).child("password").getValue(String.class);

                                if(getPassword.equals(passwordTxt)){

                                    Toast.makeText(login.this, "登入成功", Toast.LENGTH_SHORT).show();

                                    startActivity(new Intent(login.this, MainActivity2.class));
                                     pass1();
                                    finish();
                                }
                                else{
                                    Toast.makeText(login.this, "用戶不存在", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(login.this, "密碼錯誤", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
        });

    }

    public void pass1(){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("key", phoneTxt);
        editor.commit();
    }
    public void pass2(){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("key", null);
        editor.commit();
    }



}


