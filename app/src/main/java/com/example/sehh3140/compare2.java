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
import android.widget.Button;
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
    Button compare2btn;

    //final static  String DATA_RECEIVE = "data_receive";




    String[][] snack = {{"LAYS原味薯片","LAYS洋蔥乳酪味薯片","LAYS車打芝士味薯片","BLUE DIAMOND蜜糖焗杏仁","固力果蕃茄百力滋餅乾條","固力果沙律百力滋餅乾條","康力施洛 蘋果葡萄口味蒟蒻啫哩","SEALECT美式吞拿魚餐","金安記 蜜汁豬肉乾","金安記 黑胡椒牛肉乾","金安記 香辣豬肉絲"}
            ,{"https://www.parknshop.com/zh-hk/potato-chips-regular/p/BP_188086","https://www.parknshop.com/zh-hk/potato-chips-sour-cream-onion/p/BP_188084","https://www.parknshop.com/zh-hk/cheddar-sour-cream/p/BP_380603","https://www.parknshop.com/zh-hk/honey-roasted-almonds-can/p/BP_113851","https://www.parknshop.com/zh-hk/pretz-tomato/p/BP_359939","https://www.parknshop.com/zh-hk/pretz-salad/p/BP_103131","https://www.parknshop.com/zh-hk/konnyaku-jelly-apple-grape/p/BP_459028","https://www.parknshop.com/zh-hk/tuna-snackit-american-style/p/BP_178216?search=SEALECT","https://www.parknshop.com/zh-hk/sliced-pork-jerky/p/BP_317394","https://www.parknshop.com/zh-hk/black-pepper-sliced-beef/p/BP_317398","https://www.parknshop.com/zh-hk/spicy-pork-floss/p/BP_317396"}
            ,{"https://www.jhceshop.com/zh/Products/Detail?itemid=028400055093","https://www.jhceshop.com/zh/Products/Detail?itemid=028400055154","https://www.jhceshop.com/zh/Products/Detail?itemid=028400055116","https://www.jhceshop.com/zh/Products/Detail?itemid=041570072561","https://www.jhceshop.com/zh/Products/Detail?itemid=4901005520820","https://www.jhceshop.com/zh/Products/Detail?itemid=4901005520813","https://www.jhceshop.com/zh/Products/Detail?itemid=4571157255408","https://www.jhceshop.com/zh/Products/Detail?itemid=4891329540171","https://www.jhceshop.com/zh/Products/Detail?itemid=4711883040293","https://www.jhceshop.com/zh/Products/Detail?itemid=4711883040255","https://www.jhceshop.com/zh/Products/Detail?itemid=4711883040279"}
            ,{"https://www.ztore.com/tc/product/rich-1011491#query=lays","https://www.ztore.com/tc/product/dahongpao-oolong-tea-full-case-1011480#query=lays","https://www.ztore.com/tc/product/cheddar-sour-cream-potato-chips-1016730#query=lays","https://www.ztore.com/tc/product/honey-roasted-almonds-1004671#query=BLUE%20DIAMOND","https://www.ztore.com/tc/product/pretz-tomato-1000673#query=GLICO","https://www.ztore.com/tc/product/pretz-salad-flavour-1012260#query=GLICO","https://www.ztore.com/tc/product/apple-jelly-1015100#query=ORIHIRO","https://www.ztore.com/tc/product/tuna-snackit-american-style-1000889","https://www.ztore.com/tc/product/pork-jerky-honey-1051597","https://www.ztore.com/tc/product/beef-jerky-plain-1051591","https://www.ztore.com/tc/product/pork-silk-garlic-armatic-1051595"}};


    String[][] noodle = {{"日清合味道 海鮮味 大杯麵 ","日清合味道 香辣海鮮味 大杯麵","農心 辛拉麵","三養 韓式牛骨湯麵","百味雅 意大利粉","百味雅 直紋通粉","百味雅 通心粉","SAN REMO 扁意粉"},
            {"https://www.parknshop.com/zh-hk/big-cup-noodle-seafood/p/BP_369506","https://www.parknshop.com/zh-hk/big-cup-noodle-spicy-seafood/p/BP_369507","https://www.parknshop.com/zh-hk/noodle-shin-ramyun-5p/p/BP_192496","https://www.parknshop.com/zh-hk/korean-beef-bone-noodle-soup-multi/p/BP_321183","https://www.parknshop.com/zh-hk/spaghetti-7/p/BP_118240","https://www.parknshop.com/zh-hk/penne-rigate/p/BP_402789","https://www.parknshop.com/zh-hk/chifferi-n-41/p/BP_404420","https://www.parknshop.com/zh-hk/linguine/p/BP_458621"},
            {"https://www.jhceshop.com/zh/Products/Detail?itemid=4897878630011","https://www.jhceshop.com/zh/Products/Detail?itemid=4897878630028","https://www.jhceshop.com/zh/Products/Detail?itemid=8801043014830","https://www.jhceshop.com/zh/Products/Detail?itemid=8801073142145","https://www.jhceshop.com/zh/Products/Detail?itemid=8076800195057","https://www.jhceshop.com/zh/Products/Detail?itemid=8076802085738","https://www.jhceshop.com/zh/Products/Detail?itemid=8076808050440","https://www.jhceshop.com/zh/Products/Detail?itemid=8006222600075"} ,
            {"https://www.ztore.com/tc/product/big-cup-noodle-seafood-6003457","https://www.ztore.com/tc/product/big-cup-noodle-spicy-seafood-6003456","https://www.ztore.com/tc/product/shin-ramen---shrimp-flavor-1039426","https://www.ztore.com/tc/product/beef-bone-white-soup-ramen-1025382","https://www.ztore.com/tc/product/spaghetti-5-1049653","https://www.ztore.com/tc/product/penne-rigate-73-1049652","https://www.ztore.com/tc/product/chifferi-1002009","https://www.ztore.com/tc/product/linguine-1051888"}};

    String[][] can = {{"s三祖 午餐肉","地捫 日式辣油沙甸魚","地捫 茄汁沙甸魚","特別好 忌廉粟米蓉","特別好 原粒粟米","美味牌 葵花籽油浸吞拿魚","美味牌 礦泉水浸吞拿魚","天龍牌 紅燒瑤柱鮑魚","官燕棧 紅燒鮑魚４頭","官燕棧 紅燒鮑魚８—１０頭"},
            {"https://www.parknshop.com/zh-hk/luncheon-meat/p/BP_387188","https://www.parknshop.com/zh-hk/sardines-in-chilli-oil/p/BP_416341","https://www.parknshop.com/zh-hk/sardines-in-tomato-sauce/p/BP_103162","https://www.parknshop.com/zh-hk/cream-style-corn/p/BP_101451","https://www.parknshop.com/zh-hk/whole-kernel-corn/p/BP_101452","https://www.parknshop.com/zh-hk/tuna-in-sunflower-oil/p/BP_115295","https://www.parknshop.com/zh-hk/tuna-in-spring-water/p/BP_112976","https://www.parknshop.com/zh-hk/aba-in-brown-sauce-w-dried-scallop6p/p/BP_413070","https://www.parknshop.com/zh-hk/abalone-in-braised-sauce-4-pcs/p/BP_497438","https://www.parknshop.com/zh-hk/abalone-in-braised-sauce-8-10-pcs/p/BP_497446"},
            {"https://www.jhceshop.com/zh/Products/Detail?itemid=8801047181293","https://www.jhceshop.com/zh/Products/Detail?itemid=748485200071","https://www.jhceshop.com/zh/Products/Detail?itemid=748485200064","https://www.jhceshop.com/zh/Products/Detail?itemid=011194296452","https://www.jhceshop.com/zh/Products/Detail?itemid=011194301224","https://www.jhceshop.com/zh/Products/Detail?itemid=4891329331021","https://www.jhceshop.com/zh/Products/Detail?itemid=4891329331014","https://www.jhceshop.com/zh/Products/Detail?itemid=4895158008017","https://www.jhceshop.com/zh/Products/Detail?itemid=4895158008024","https://www.jhceshop.com/zh/Products/Detail?itemid=4895158008017"},
            {"https://www.ztore.com/tc/product/luncheon-meat-korean-version-1013845#query=午餐肉","https://www.ztore.com/tc/product/japan-style-sardines-in-chilli-oil-1046674","https://www.ztore.com/tc/product/sardines-in-tomato-sauce-1000288","https://www.ztore.com/tc/product/cream-style-corn-1000337","https://www.ztore.com/tc/product/whole-kernel-corn-1005241","https://www.ztore.com/tc/product/tuna-in-sunflower-oil-1000621","https://www.ztore.com/tc/product/tuna-in-spring-water-1000630","https://www.ztore.com/tc/product/abalone-in-brown-sauce-with-dried-scallop-6-pcs-1049703","https://www.ztore.com/tc/product/life-concept-abalone-in-braised-sauce-1024696#query=%E5%AE%98%E7%87%95%E6%A3%A7%E7%B4%85%E7%87%92%E9%AE%91%E9%AD%9A","https://www.ztore.com/tc/product/life-concept-abalone-in-braised-sauce-1024695#query=%E5%AE%98%E7%87%95%E6%A3%A7%E7%B4%85%E7%87%92%E9%AE%91%E9%AD%9A"}};
    String[][] drink = {{"可口可樂高罐","可口可樂 無糖可口可樂汽水高罐裝","葡萄適 葡萄糖能量飲品","玉泉 忌廉","雪碧 檸檬青檸味","伊藤園 日式綠茶","維他奶 原味豆奶","維他奶 麥精豆奶","芬達 橙味","雀巢 香滑咖啡"},
            {"https://www.parknshop.com/zh-hk/tall-can/p/BP_406250","https://www.parknshop.com/zh-hk/coke-no-sugar-tall-can/p/BP_406251","https://www.parknshop.com/zh-hk/energy-drink-orange-4-s-pack/p/BP_184117","https://www.parknshop.com/zh-hk/cream-soda/p/BP_188329","https://www.parknshop.com/zh-hk/lemon-lime-flavoured-soda/p/BP_185888","https://www.parknshop.com/zh-hk/japan-green-tea/p/BP_194101","https://www.parknshop.com/zh-hk/soya-bean-milk-6-s/p/BP_185709","https://www.parknshop.com/zh-hk/malted-soya-bean-milk-6-s/p/BP_185710","https://www.parknshop.com/zh-hk/orange-drink/p/BP_119612","https://www.parknshop.com/zh-hk/milk-sugar-coffee/p/BP_151271"},
            {"https://www.jhceshop.com/zh/Products/Detail?itemid=4890008893713","https://www.jhceshop.com/zh/Products/Detail?itemid=4890008109807","https://www.jhceshop.com/zh/Products/Detail?itemid=8885012290210","https://www.jhceshop.com/zh/Products/Detail?itemid=4890008400805","https://www.jhceshop.com/zh/Products/Detail?itemid=4890008110803","https://www.jhceshop.com/zh/Products/Detail?itemid=04901085192016","https://www.jhceshop.com/zh/Products/Detail?itemid=4894768122502","https://www.jhceshop.com/zh/Products/Detail?itemid=4894768122519","https://www.jhceshop.com/zh/Products/Detail?itemid=4890008120246","https://www.jhceshop.com/zh/Products/Detail?itemid=4890008501311"},
            {"https://www.ztore.com/tc/product/coke-tall-can-1045055","https://www.ztore.com/tc/product/coke-zero-tall-can-1045054","https://www.ztore.com/tc/product/energy-orange-6002970","https://www.ztore.com/tc/product/cream-soda-1000170","https://www.ztore.com/tc/product/lemon-lime-flavoured-soda-1000160","https://www.ztore.com/tc/product/japan-green-tea-6003817","https://www.ztore.com/tc/product/soya-bean-milk-1000001","https://www.ztore.com/tc/product/malted-soya-bean-milk-1000003","https://www.ztore.com/tc/product/orange-drink-1000161","https://www.ztore.com/tc/product/rtd-coffee-with-milk-sugar-1000202"}};
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
        compare3 compare3 = new compare3();
        compare compare= new compare();

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
                        url = snack[1][position] +";" + snack[2][position] +";" +snack[3][position];
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
                        url = can[1][position] +";" + can[2][position] +";" +can[3][position];
                        editor.putString(data, url);
                        editor.commit();

                        transaction.replace(R.id.Fragment, compare3);
                        transaction.commit();
                        break;
                    case "飲品" :
                        String url = drink[1][position] +";" + drink[2][position] +";" +drink[3][position];
                        editor.putString(data, url);
                        editor.commit();

                        transaction.replace(R.id.Fragment, compare3);
                        transaction.commit();
                        break;
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
        });

        compare2btn =view.findViewById(R.id.compare2_returnbutton);
        compare2btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.Fragment, compare);
                transaction.commit();
            }
        });

        return view;

    }


}


