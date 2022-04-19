package com.example.sehh3140;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

    private TextView jhceshop_productNametv;
    private TextView jhceshop_productDetailtv;
    private TextView jhceshop_pricetv;
    private TextView jhceshop_Linktv;

    private TextView zstore_productNametv;
    private TextView zstore_productDetailtv;
    private TextView zstore_pricetv;
    private TextView zstore_Linktv;

    private ProgressBar progressBar;
    String website1;
    String website2;
    String website3;

    String[] zstoredata;
    String[] jhcedata;

    String parknshop_name;
    String parknshop_product;
    String parknshop_price;

    String zstore_name;
    String zstore_product;
    String zstore_price;

    String jhceshop_name;
    String jhceshop_product;
    String jhceshop_price;


    SharedPreferences sharedpreferences;
    public static final String selected = "selected";
    public static final String data = "data";
    String word;

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

        jhceshop_productNametv = view.findViewById(R.id.jhceshop_productName);
        jhceshop_productDetailtv = view.findViewById(R.id.jhceshop_productDetail);
        jhceshop_pricetv = view.findViewById(R.id.jhceshop_price);
        jhceshop_Linktv = view.findViewById(R.id.jhceshop_Link);

        zstore_productNametv = view.findViewById(R.id.zstore_productName);
        zstore_productDetailtv = view.findViewById(R.id.zstore_productDetail);
        zstore_pricetv = view.findViewById(R.id.zstore_price);
        zstore_Linktv = view.findViewById(R.id.zstore_Link);
        sharedpreferences = getActivity().getSharedPreferences(selected, //create a sharedpreferences
                Context.MODE_PRIVATE);

        word = sharedpreferences.getString(data , "");

        String[] website = word.split(";");
            website1 = website[0];
            website2 = website[1];
            website3 = website[2];

            parknshop_Linktv.setText(website1);
            jhceshop_Linktv.setText(website2);
            zstore_Linktv.setText(website3);

            startASycnc1(website1,website2,website3);





        return view;
    }

    public void taskFinish( String result1 ,String result2 ,String result3 ,String result4 ,String result5 ,String result6 ,String result7 ,String result8, String result9 )
    {
        parknshop_productNametv.setText(result1);
        parknshop_productDetailtv.setText(result2);
        parknshop_pricetv.setText(result3);

        jhceshop_productNametv.setText(result7);
        jhceshop_productDetailtv.setText(result8);
        jhceshop_pricetv.setText(result9);

        zstore_productNametv.setText(result4);
        zstore_productDetailtv.setText(result5);
        zstore_pricetv.setText(result6);


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
            taskFinish(parknshop_name, parknshop_product,parknshop_price,zstore_name,zstore_product,zstore_price,jhceshop_name,jhceshop_product,jhceshop_price);

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
                Elements parknshop_unit = doc.select("span[class=sizeUnitColor]");
                Elements parknshop_priceWB = doc.select(  "span[itemprop=price]");

                Elements jhceshop_data = doc1.select("div[class=col-sm-12 col-md-7]").select("div[class=desc]");
                String text1 =jhceshop_data.first().html();
                text1 = text1.replaceAll("<br>", ";");
                jhcedata = text1.split(";");
                Elements jhceshop_priceWB = doc1.select("div[class=col-sm-12 col-md-7]").select("div[class=price-box]").select("span[class=product-price]");

                Elements zstore_productname=doc2.select("div[class=jsx-1852479493 name]").select("h2[class=jsx-1852479493]");
                Elements zstore_brandname=doc2.select("div[class=jsx-1852479493 brand]").select("a");
                Elements zstore_priceWB = doc2.select("div[class=jsx-1852479493]").select( "span[class=jsx-1852479493 promotion]");
                String text2 =zstore_productname.text();
                text2 = text2.replaceAll(" ", ";");
                zstoredata = text2.split(";");

                Log.i("TAG", "doInBackground: zstore_name"  + "//" +zstoredata[0] + " aaa " + zstoredata[1]);




                 parknshop_name =parknshop_brandname.text()+" "+parknshop_productname.text() ;
                 parknshop_product = parknshop_unit.text();
                 parknshop_price = parknshop_priceWB.text();

                 zstore_name = zstore_brandname.text() + " " +zstoredata[0];
                 zstore_product = zstoredata[1];
                 zstore_price =zstore_priceWB.text();

                 jhceshop_name = jhcedata[0];
                 jhceshop_product = jhcedata[1];
                 jhceshop_price =jhceshop_priceWB.text();
















            }catch(Exception e) {
                Log.i("mytag", e.toString());
            }
            return null;
        }




    }


}

