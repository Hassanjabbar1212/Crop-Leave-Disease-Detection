package com.example.cropdiseasedetection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class Navigationbar extends AppCompatActivity {
DrawerLayout drawerLayout;
NavigationView navigationView;
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigationbar);

        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navigationview);
        toolbar = findViewById(R.id.toolbar);

        // step 1

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.OpenDrawer, R.string.CloseDrawer
        );
       drawerLayout.addDrawerListener(toggle);

       toggle.syncState();

       navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               int id = item.getItemId();

               if (id==R.id.contact){

               } else if (id==R.id.About) {

               } else {

               }

               return true;
           }
       });
    }
}