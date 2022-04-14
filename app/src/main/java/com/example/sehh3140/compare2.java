package com.example.sehh3140;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
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

    private ProgressBar progressBar;
    compare3 compare3 =new compare3();

    String[] test = {
            "1","2","3","4"
    };
    ListView lv;

    EditText ed;

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

        progressBar = view.findViewById(R.id.compareprice2_progressBar);
        lv = view.findViewById(R.id.compareprice2_lv);
        adapter = new ArrayAdapter<String>(getContext(), R.layout.comparelistview2 , R.id.compareprice2_tv, test);
        lv.setAdapter(adapter);

        Bundle bundle = this.getArguments();

        if(bundle != null){
            String data = bundle.getString("key");
            Log.i("mytag", data);
        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.Fragment, compare3);
                transaction.commit();

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

    public void startASycnc1() {
        new StartAsyncTask1().execute();
    }

    private  class StartAsyncTask1 extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_out));
            adapter.notifyDataSetChanged();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {

                Log.i("mytag", "hi:" );


                Document doc = Jsoup.connect("https://www.parknshop.com/zh-hk/rice-oil-noodles/rice/c/300100").get();
                String htmlString = doc.html();
                Log.i("mytag", htmlString);

                Elements number = doc.select("div[class=product-container]");

                String limitedNumberString = number.get(0).attr("data-product_list_total_items");

                int limitedNumber = Integer. valueOf(limitedNumberString);

                Log.i("mytag", "limitedNumber:" + limitedNumber);

                for (int i = 0 ; i <limitedNumber ; i++) {

                    //“椒麻雞”和它對應的圖片都在<div class="pic">中
                    Elements titleAndPic = doc.select("div[class=pic productPrimaryPic]");
                    //.select("div.product-container").select("div.item  initialized rendered").select("div.border")
                    //.select("div.padding").select("div.pic productPrimaryPic");

                    //使用Element.select(String selector)查詢元素，使用Node.attr(String key)方法取得一個屬性的值

                    String abc = titleAndPic.get(i).select("a").select("img").attr("data-original");

                    abc = "https://www.parknshop.com" + abc;



                    Log.i("mytag1", "pic:" + abc);

                    //所需連結在<div class="detail">中的<a>標籤裡面
                    Elements url = doc.select("div[class=name]").select("a");

                    Log.i("mytag", "url:" + url.get(i).select("p.text").text());
                }



                //原料在<p class="subcontent">中
                Elements burden = doc.select("p.subcontent");
                //對於一個元素中的文字，可以使用Element.text()方法
                Log.i("mytag", "burden:" + burden.get(1).text());

                Log.i("mytag", "abc:" + abc);



                return "a";

            }catch(Exception e) {
                Log.i("mytag", e.toString());
            }
        }
    }
}


