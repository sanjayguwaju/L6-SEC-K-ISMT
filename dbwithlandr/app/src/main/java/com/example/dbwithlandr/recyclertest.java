package com.example.dbwithlandr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class recyclertest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler);

        database db = new database(this);

        RecyclerView recyl = findViewById(R.id.recy_view);

        recyl.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<datamodel> alldata = db.fetchdata();





        Recycleradapter adapter = new Recycleradapter(this, alldata);
        recyl.setAdapter(adapter);


    }
}