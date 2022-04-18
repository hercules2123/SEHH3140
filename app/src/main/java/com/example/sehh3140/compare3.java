package com.example.sehh3140;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.URL;


public class compare3 extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private TextView parknshop_productNametv;
    private TextView parknshop_productDetailtv;
    private TextView parknshop_pricetv;
    private TextView parknshop_Linktv;
    private ProgressBar progressBar;
    String website1;
    String website2;
    String website3;

    public compare3() {
        // Required empty public constructor
    }

    public static compare3 newInstance(String param1, String param2) {
        compare3 fragment = new compare3();
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
        View view = inflater.inflate(R.layout.fragment_compare3, container, false);
        progressBar = view.findViewById(R.id.compareprice2_progressBar);
        parknshop_productNametv = view.findViewById(R.id.parknshop_productName);
        parknshop_productDetailtv = view.findViewById(R.id.parknshop_productDetail);
        parknshop_pricetv = view.findViewById(R.id.parknshop_price);
        parknshop_Linktv = view.findViewById(R.id.parknshop_Link);
        Bundle bundle = this.getArguments();
        if(bundle != null){
            website1 = bundle.getString("website1");
            website2 = bundle.getString("website2");
            website3 = bundle.getString("website3");

            parknshop_Linktv.setText(website1);

            startASycnc1(website1,website2,website3);
        }


        return view;
    }

    public void startASycnc1(String website1 ,String website2,String website3 ) {
        Log.i("mytag", "hi1" );
        new StartAsyncTask1().execute(website1,website2,website3);
        Log.i("mytag", "hi2" );
    }

    private  class StartAsyncTask1 extends AsyncTask<String, Void, Void> {


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
        }

        @Override
        protected Void doInBackground(String... strings) {
            try {

                Log.i("mytag", "hi:" );


                Document doc = Jsoup.connect(strings[0]).get();
                Document doc1 = Jsoup.connect(strings[1]).get();
                Document doc2 = Jsoup.connect(strings[2]).get();

                Elements parknshop_brandname = doc.select("a").select("span[class=brandName]");
                Elements parknshop_productname=doc.select("h1[class=productName productNameForH1]");
                Elements parknshop_unit = doc.select("a").select("span[class=sizeUnitColor]");
                Elements parknshop_price = doc.select("span[itemprop=price]");

                parknshop_productNametv.setText(parknshop_brandname.text()+" "+parknshop_productname.text());
                parknshop_productDetailtv.setText(parknshop_unit.text());
                parknshop_pricetv.setText(parknshop_price.text());










            }catch(Exception e) {
                Log.i("mytag", e.toString());
            }
            return null;
        }


    }
}