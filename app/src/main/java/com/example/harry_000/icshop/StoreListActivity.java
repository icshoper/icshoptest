package com.example.harry_000.icshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Weirun on 2015/11/17.
 */
public class StoreListActivity  extends AppCompatActivity {

    private List<Store> Store = new ArrayList<Store>();
    private MyDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_list);

        db = new MyDatabase(this);
        Store = db.getAllBusiness(); // you would not typically call this on the main thread
        ArrayAdapter<Store> adapter = new ArrayAdapter<Store>(this,
                android.R.layout.simple_list_item_1, Store);

        ListView list = (ListView) findViewById(android.R.id.list);
        list.setAdapter(adapter);

        //listening to single list item on click
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // selected item
                Store clickedObject = (Store) parent.getAdapter().getItem(position);

                String strid = String.valueOf(clickedObject.getID());
                String strStore = clickedObject.getName();
                // Launching new Activity on selecting single List Item
                Intent i = new Intent(getApplicationContext(), StoreDetailActivity.class);
                // sending data to new activity
                i.putExtra("storeID", strid);
                i.putExtra("storeName", strStore);
                startActivity(i);

            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }


}