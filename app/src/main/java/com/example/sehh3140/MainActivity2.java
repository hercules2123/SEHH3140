package com.example.sehh3140;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity2 extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView menu;
    game game = new game();
    compare compare = new compare();
    personal personal = new personal();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        menu = findViewById(R.id.menu);
        menu.setOnNavigationItemSelectedListener(this);
        menu.setSelectedItemId(R.id.compare);
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {



        switch (item.getItemId()) {
            case R.id.compare:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.Fragment, compare)
                        .commit();
                return true;

            case R.id.game:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.Fragment, game)
                        .commit();
                return true;

            case R.id.personal:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.Fragment, personal)
                        .commit();
                return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(MainActivity2.this)
                .setTitle("提示")
                .setMessage("是否登出")
                .setPositiveButton("登出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent();
                        intent.setClass(MainActivity2.this, login.class);
                        startActivity(intent);
                    }

                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).show();

    }


}






