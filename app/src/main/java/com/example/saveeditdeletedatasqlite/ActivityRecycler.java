package com.example.saveeditdeletedatasqlite;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityRecycler extends AppCompatActivity implements RecyclerClickListener {

    private static final String TAG = "ActivityRecycler";

    DatabaseHelper mdatabaseHelper;
    AdapterItems adapterItems;

    @BindView(R.id.rvDbData)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        ButterKnife.bind(this);

        mdatabaseHelper = new DatabaseHelper(this);

        populateRecyclerView();
    }

    private void populateRecyclerView(){

        Cursor data = mdatabaseHelper.getData();
        List<String> stringList = new ArrayList<>();
        while (data.moveToNext()){
            stringList.add(data.getString(1));
        }

        adapterItems = new AdapterItems(this, stringList, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterItems);

    }

    @Override
    public void onItemClicked(String s) {
        Toast.makeText(this, "You clicked on " + s, Toast.LENGTH_SHORT).show();
    }
}
