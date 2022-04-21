package com.example.sehh3140;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;

public class register extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://sehh3140database-f8582-default-rtdb.firebaseio.com/");

    EditText et1,et2,et3;
    Button register1,cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et1 = findViewById(R.id.registeret1);
        et2 = findViewById(R.id.registeret2);
        et3 = findViewById(R.id.registeret3);
        register1 = findViewById(R.id.register1);

        cancel = findViewById(R.id.registercancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(com.example.sehh3140.register.this, com.example.sehh3140.login.class);
                startActivity(intent);
            }
        });
        register1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String phoneTxt = et1.getText().toString();
                 String passwordTxt = et2.getText().toString();
                 String conPasswordTxt = et3.getText().toString();

                if(phoneTxt.isEmpty() || passwordTxt.isEmpty() || conPasswordTxt.isEmpty()){
                    Toast.makeText(register.this,"請輸入所有資料", Toast.LENGTH_SHORT).show();
                }
                else if(!passwordTxt.equals(conPasswordTxt)){
                    Toast.makeText(register.this,"兩個密碼並不相同", Toast.LENGTH_SHORT).show();
                }
                else{

                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if(snapshot.hasChild(phoneTxt)){
                                String getPassword = snapshot.child(phoneTxt).child("fullname").getValue(String.class);
                                if(getPassword.equals(phoneTxt)){
                                    Toast.makeText(register.this, "用戶名稱已被註冊", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                databaseReference.child("users").child(phoneTxt).child("fullname").setValue(phoneTxt);
                                databaseReference.child("users").child(phoneTxt).child("password").setValue(passwordTxt);
                                databaseReference.child("users").child(phoneTxt).child("coupon").setValue("0");
                                Toast.makeText(register.this, "yeah", Toast.LENGTH_SHORT).show();
                                finish();
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
}



