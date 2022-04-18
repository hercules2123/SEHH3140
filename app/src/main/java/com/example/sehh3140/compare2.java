package com.example.sehh3140;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;



import java.util.ArrayList;
import java.util.HashMap;

public class compare2 extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    final static  String DATA_RECEIVE = "data_receive";


    compare3 compare3 =new compare3();


    String[][] snack1 = {{"1","2","3","4"}
            ,{}};
    String[] snack = {"1","2","3","4"};
    String[][] noodle = {{"麻油味即食麵 原箱 30包 ","2","3","4"},{"https://www.parknshop.com/zh-hk/inst-noodle-sesame-oil-case/p/BP_157892"},
            {"https://www.hktvmall.com/hktv/zh/main/%E6%97%A5%E6%B8%85%E9%A3%9F%E5%93%81%28%E9%A6%99%E6%B8%AF%29/s/H1061001/%E8%B6%85%E7%B4%9A%E5%B7%BF%E5%A0%B4/%E8%B6%85%E7%B4%9A%E5%B8%82%E5%A0%B4/%E5%8D%B3%E9%A3%9F%E9%BA%B5-%E9%BA%B5-%E6%84%8F%E7%B2%89/%E5%8D%B3%E9%A3%9F%E9%BA%B5/%E9%BA%B5/%E5%8E%9F%E7%AE%B1-%E9%BA%BB%E6%B2%B9%E5%91%B3%E9%BA%B5/p/H0888001_S_P10004812"},
            {"https://www.ztore.com/tc/product/instant-noodle-sesame-oil-8000117-c5058",""}};
    String[][] can = {{"1","2","3","4"}
            ,{}};
    String[][] drink = {{"1","2","3","4"}
            ,{}};
    ListView lv;

    EditText ed;

    String data;
    ArrayAdapter<String> adapter;

    ArrayList<HashMap<String, String>> productList;




    public compare2() {
        // Required empty public constructor
    }

    public static compare2 newInstance(String param1, String param2) {
        compare2 fragment = new compare2();
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
        View view = inflater.inflate(R.layout.fragment_compare2, container, false);

        lv = view.findViewById(R.id.compareprice2_lv);
        adapter = new ArrayAdapter<String>(getContext(), R.layout.comparelistview2 , R.id.compareprice2_tv, snack);
        lv.setAdapter(adapter);

        Bundle args = this.getArguments();

            data = args.getString(DATA_RECEIVE);
            Log.i("mytag1",""+data);
        return view;


            /*
            switch (data) {
                case "1" :
                    adapter = new ArrayAdapter<String>(getContext(), R.layout.comparelistview2 , R.id.compareprice2_tv, snack[0]);
                    lv.setAdapter(adapter);
                case "2" :
                    adapter = new ArrayAdapter<String>(getContext(), R.layout.comparelistview2 , R.id.compareprice2_tv, noodle[0]);
                    lv.setAdapter(adapter);
                case "3" :
                    adapter = new ArrayAdapter<String>(getContext(), R.layout.comparelistview2 , R.id.compareprice2_tv, can[0]);
                    lv.setAdapter(adapter);
                case "4" :
                    adapter = new ArrayAdapter<String>(getContext(), R.layout.comparelistview2 , R.id.compareprice2_tv, drink[0]);
                    lv.setAdapter(adapter);
            }












        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(data=="1"){
                    Bundle bundle = new Bundle();
                    bundle.putString("Website1",snack1[1][position]);
                    bundle.putString("Website2",snack1[2][position]);
                    bundle.putString("Website3",snack1[3][position]);
                    compare3 fragment = new compare3();
                    fragment.setArguments(bundle);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.Fragment, compare3);
                    transaction.commit();
                }else if(data=="2"){
                    Bundle bundle = new Bundle();
                    bundle.putString("Website1",noodle[1][position]);
                    bundle.putString("Website2",noodle[2][position]);
                    bundle.putString("Website3",noodle[3][position]);

                    compare3 fragment = new compare3();
                    fragment.setArguments(bundle);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.Fragment, compare3);
                    transaction.commit();
                }else if(data=="3"){
                    Bundle bundle = new Bundle();
                    bundle.putString("Website1",can[1][position]);
                    bundle.putString("Website2",can[2][position]);
                    bundle.putString("Website3",can[3][position]);

                    compare3 fragment = new compare3();
                    fragment.setArguments(bundle);

                }else if(data=="4"){
                    Bundle bundle = new Bundle();
                    bundle.putString("Website1",drink[1][position]);
                    bundle.putString("Website2",drink[2][position]);
                    bundle.putString("Website3",drink[3][position]);

                    compare3 fragment = new compare3();
                    fragment.setArguments(bundle);

                }


            }});



        ed = view.findViewById(R.id.compareprice2_et);

        ed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                compare2.this.adapter.getFilter().filter(s);
            }
        });*/

    }


}


