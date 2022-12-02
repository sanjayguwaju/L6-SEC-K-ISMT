package com.example.dbwithlandr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Homepage extends AppCompatActivity {


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    RecyclerView recyl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        database db = new database(this);  // database connectivity

        //connect id for navigation view
        drawerLayout = findViewById(R.id.homes);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.ttoolbar);
        setSupportActionBar(toolbar);    //remove default toolbar and adding custom toolbar
        //create actionbar object to open and close navigation bar
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.OpenDrawer, R.string.CloseDrawer);
       //connect drawerlayout with toogle
        drawerLayout.addDrawerListener(toggle);
        //give status of navigation(open or close)
        toggle.syncState();
        //create onclick for menu item
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if(id == R.id.mlogout){
                    Toast.makeText(Homepage.this, "Log out", Toast.LENGTH_SHORT).show();

                }

                else if (id == R.id.mcategory){
                    loadfragment(new AFragment());
                }

                drawerLayout.closeDrawer((GravityCompat.START));
                return true;
            }
        });

        //
        //Recycler View Content




    }
    public void loadfragment(Fragment fragment){
        FragmentManager fm  = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.fraga, fragment);
        ft.commit();

    }





}