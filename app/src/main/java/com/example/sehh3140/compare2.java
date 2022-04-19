package com.example.sehh3140;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
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
    SharedPreferences sharedpreferences;
    public static final String selected = "selected";
    public static final String data = "data";
    String word;

    //final static  String DATA_RECEIVE = "data_receive";


    compare3 compare3 =new compare3();


    String[][] snack = {{"1","2","3","4"}
            ,{}
            ,{}
            ,{}};


    String[][] noodle = {{"日清合味道 海鮮味 大杯麵 ","日清合味道 香辣海鮮味 大杯麵","3","4"},
             {"https://www.parknshop.com/zh-hk/big-cup-noodle-seafood/p/BP_369506","https://www.parknshop.com/zh-hk/big-cup-noodle-spicy-seafood/p/BP_369507"},
            {"https://www.jhceshop.com/zh/Products/Detail?itemid=4897878630011","https://www.jhceshop.com/zh/Products/Detail?itemid=4897878630028"} ,
             {"https://www.ztore.com/tc/product/big-cup-noodle-seafood-6003457#query=%E6%97%A5%E6%B8%85%E5%90%88%E5%91%B3%E9%81%93%20%E6%B5%B7%E9%AE%AE%E5%91%B3%20%E5%A4%A7%E6%9D%AF%E9%BA%B5","https://www.ztore.com/tc/product/big-cup-noodle-spicy-seafood-6003456#query=%E6%97%A5%E6%B8%85%E5%90%88%E5%91%B3%E9%81%93%E9%A6%99%E8%BE%A3%E6%B5%B7%E9%AE%AE%E5%91%B3%E5%A4%A7%E6%9D%AF%E9%BA%B5"}};

    String[][] can = {{"can","2","3","4"},
            {"https://www.parknshop.com/zh-hk/inst-noodle-sesame-oil-case/p/BP_157892"},
            {""},
            {"https://www.ztore.com/tc/product/instant-noodle-sesame-oil-8000117-c5058",""}};

    String[][] drink = {{"drink","2","3","4"},
            {"https://www.parknshop.com/zh-hk/inst-noodle-sesame-oil-case/p/BP_157892"},
            {""},
            {"https://www.ztore.com/tc/product/instant-noodle-sesame-oil-8000117-c5058",""}};
    ListView lv;

    EditText ed;
    String url;

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
        sharedpreferences = getActivity().getSharedPreferences(selected, //create a sharedpreferences
                Context.MODE_PRIVATE);

        word = sharedpreferences.getString(data , "");
        Log.i("mytag1", word);
        lv = view.findViewById(R.id.compareprice2_lv);


            switch (word) {
                case "零食" :
                    adapter = new ArrayAdapter<String>(getContext(), R.layout.comparelistview2 , R.id.compareprice2_tv, snack[0]);
                    lv.setAdapter(adapter);
                    break;
                case "麵類" :
                    adapter = new ArrayAdapter<String>(getContext(), R.layout.comparelistview2 , R.id.compareprice2_tv, noodle[0]);
                    lv.setAdapter(adapter);
                    break;
                case "罐頭" :
                    adapter = new ArrayAdapter<String>(getContext(), R.layout.comparelistview2 , R.id.compareprice2_tv, can[0]);
                    lv.setAdapter(adapter);
                    break;
                case "飲品" :
                    adapter = new ArrayAdapter<String>(getContext(), R.layout.comparelistview2 , R.id.compareprice2_tv, drink[0]);
                    lv.setAdapter(adapter);
                    break;
            }





        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                switch (word) {
                    case "零食" :
                        url = noodle[1][position] +";" + noodle[2][position] +";" +noodle[3][position];
                        editor.putString(data, url);
                        editor.commit();

                        transaction.replace(R.id.Fragment, compare3);
                        transaction.commit();
                        break;
                    case "麵類" :
                        url = noodle[1][position] +";" + noodle[2][position] +";" +noodle[3][position];
                        editor.putString(data, url);
                        editor.commit();

                        transaction.replace(R.id.Fragment, compare3);
                        transaction.commit();
                        break;
                    case "罐頭" :
                        url = noodle[1][position] +";" + noodle[2][position] +";" +noodle[3][position];
                        editor.putString(data, url);
                        editor.commit();

                        transaction.replace(R.id.Fragment, compare3);
                        transaction.commit();
                        break;
                    case "飲品" :
                        String url = noodle[1][position] +";" + noodle[2][position] +";" +noodle[3][position];
                        editor.putString(data, url);
                        editor.commit();

                        transaction.replace(R.id.Fragment, compare3);
                        transaction.commit();
                        break;
                }
                /*
                if(word=="零食"){

                }else if(word=="麵類"){
                    Bundle bundle = new Bundle();
                    bundle.putString("Website1",noodle[1][position]);
                    bundle.putString("Website2",noodle[2][position]);
                    bundle.putString("Website3",noodle[3][position]);

                    compare3 fragment = new compare3();
                    fragment.setArguments(bundle);

                    transaction.replace(R.id.Fragment, compare3);
                    transaction.commit();
                }else if(word=="罐頭"){
                    Bundle bundle = new Bundle();
                    bundle.putString("Website1",can[1][position]);
                    bundle.putString("Website2",can[2][position]);
                    bundle.putString("Website3",can[3][position]);

                    compare3 fragment = new compare3();
                    fragment.setArguments(bundle);

                }else if(word=="飲品"){
                    Bundle bundle = new Bundle();
                    bundle.putString("Website1",drink[1][position]);
                    bundle.putString("Website2",drink[2][position]);
                    bundle.putString("Website3",drink[3][position]);

                    compare3 fragment = new compare3();
                    fragment.setArguments(bundle);

                }*/


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
        });
        return view;

    }


}


