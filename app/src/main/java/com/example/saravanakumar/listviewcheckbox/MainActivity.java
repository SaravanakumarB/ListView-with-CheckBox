package com.example.saravanakumar.listviewcheckbox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

public class MainActivity extends Activity {
    String[] Cricket={"India","Australia","South Africa","England","New Zealand","Pakistan","Bangaladesh","Srilanka"};
    ArrayList<Product> products = new ArrayList<Product>();
    ListAdapter boxAdapter;
    SwipeRefreshLayout swipe;
    ListView lvMain;
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillData();
        boxAdapter = new ListAdapter(this, products);
        swipe=(SwipeRefreshLayout)findViewById(R.id.swipeToRefresh);
        lvMain = (ListView) findViewById(R.id.lvMain);
        swipe.setColorSchemeResources(R.color.lite_pink);
        lvMain.setAdapter(boxAdapter);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                shuffle();
                swipe.setRefreshing(false);
            }
        });
    }
    void shuffle()
    {
        Collections.shuffle(products, new Random(System.currentTimeMillis()));
        boxAdapter = new ListAdapter(this, products);
        boxAdapter.getBox().clear();
        boxAdapter.notifyDataSetChanged();
        lvMain.setAdapter(boxAdapter);
    }
    void fillData() {
        for (int i = 0; i <= 7; i++) {
            products.add(new Product(Cricket[i], false));
        }
    }

    public void showResult(View v) {
        String result = "Selected Product are :";
        for (Product p : boxAdapter.getBox()) {
            if (p.box){
                result += "\n" + p.name;
            }
        }
        Toast.makeText(this, result+"\n", Toast.LENGTH_LONG).show();
    }
}
