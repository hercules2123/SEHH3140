package com.example.sehh3140;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

//import com.squareup.picasso.Callback;
//import com.squareup.picasso.Picasso;
public class MainActivity extends AppCompatActivity {

    private  static String abc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(() -> {
            abc = run();
            //Log.i("mytag", "Thread abc:" + abc);
            //Log.i("mytag", "Thread run:" + run());
        }).start();
        /*try {
            //從一個URL載入一個Document物件。
            Document doc = Jsoup.connect("https://www.parknshop.com/zh-hk/rice-oil-noodles/rice/c/300100").get();
            //選擇“美食天下”所在節點
            Elements elements = doc.select("div.plp-filter-list-container remove-padding-plp-filter");
            //列印 <a>標籤裡面的title
            Log.i("mytag",elements.select("item  initialized rendered").attr("title"));
        }catch(Exception e) {
            Log.i("mytag", e.toString());
        }*/

    }

    public String run() {


        try {

            Log.i("mytag", "hi:" );

            // WebClient webClient=new WebClient(BrowserVersion.FIREFOX);

            //HtmlPage page= webClient.getPage("https://www.parknshop.com/zh-hk/rice-oil-noodles/rice/c/300100");
            //String a =page.asXml();


            //HtmlInput button=(HtmlInput) page.getByXPath("//a[@class='button highlight']");
            //h3[@class='company_name']/a
            //a[@class='button highlight']
            //HtmlPage page2=button.click();

            //String pageXml = page2.asXml();

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

        return "a";
    };
}