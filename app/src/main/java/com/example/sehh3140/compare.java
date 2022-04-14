package com.example.sehh3140;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class compare extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    String[] b = {
            "零食","麵類","罐頭","飲品"
    };
    ListView lv2;

    compare2 compare2 =new compare2();

    public compare() {
        // Required empty public constructor
    }

    public static compare newInstance(String param1, String param2) {
        compare fragment = new compare();
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
        View view = inflater.inflate(R.layout.fragment_compare, container, false);

        lv2 = view.findViewById(R.id.lv2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.comparelistview,R.id.tv2,b);
        lv2.setAdapter(adapter);

        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Bundle bundle = new Bundle();
                    bundle.putString("key",b[position]);
                    Log.i("mytag", b[position]);
                    compare2 fragment = new compare2();
                    fragment.setArguments(bundle);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.Fragment, compare2);
                    transaction.commit();
                }else if(position==1){
                    Bundle bundle = new Bundle();
                    bundle.putString("key",b[position]);
                    Log.i("mytag", b[position]);
                    compare2 fragment = new compare2();
                    fragment.setArguments(bundle);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.Fragment, compare2);
                    transaction.commit();
                }else if(position==2){
                    Bundle bundle = new Bundle();
                    bundle.putString("key",b[position]);
                    Log.i("mytag", b[position]);
                    compare2 fragment = new compare2();
                    fragment.setArguments(bundle);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.Fragment, compare2);
                    transaction.commit();
                }else if(position==3){
                    Bundle bundle = new Bundle();
                    bundle.putString("key",b[position]);
                    Log.i("mytag", b[position]);
                    compare2 fragment = new compare2();
                    fragment.setArguments(bundle);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.Fragment, compare2);
                    transaction.commit();
                }

            }
        });


        return view;
    }
}