package com.example.sehh3140;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

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

                    startActivity(new Intent(getActivity(), compare2.class));
                }else if(position==1){

                    startActivity(new Intent(getActivity(), compare2.class));
                }else if(position==2){

                    startActivity(new Intent(getActivity(), compare2.class));
                }else if(position==3){

                    startActivity(new Intent(getActivity(), compare2.class));
                }

            }
        });


        return view;
    }
}