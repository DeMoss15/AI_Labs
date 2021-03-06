package com.example.daniel.ai_labs;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.daniel.ai_labs.fragments.Lab1;
import com.example.daniel.ai_labs.fragments.Lab2;
import com.example.daniel.ai_labs.fragments.Lab3;
import com.example.daniel.ai_labs.fragments.Lab4;
import com.example.daniel.ai_labs.fragments.Lab5;
import com.example.daniel.ai_labs.fragments.Lab6;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Lab1 fragmentLab1;
    Lab2 fragmentLab2;
    Lab3 fragmentLab3;
    Lab4 fragmentLab4;
    Lab5 fragmentLab5;
    Lab6 fragmentLab6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentLab1 = new Lab1();
        fragmentLab2 = new Lab2();
        fragmentLab3 = new Lab3();
        fragmentLab4 = new Lab4();
        fragmentLab5 = new Lab5();
        fragmentLab6 = new Lab6();
        getFragmentManager().beginTransaction().replace(R.id.main_container, fragmentLab1).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentTransaction ftrans = getFragmentManager().beginTransaction();

        if (id == R.id.lab1) {
            ftrans.replace(R.id.main_container, fragmentLab1);
        } else if (id == R.id.lab2) {
            ftrans.replace(R.id.main_container, fragmentLab2);
        } else if (id == R.id.lab3) {
            ftrans.replace(R.id.main_container, fragmentLab3);
        } else if (id == R.id.lab4) {
            ftrans.replace(R.id.main_container, fragmentLab4);
        } else if (id == R.id.lab5) {
            ftrans.replace(R.id.main_container, fragmentLab5);
        } else if (id == R.id.lab6) {
            ftrans.replace(R.id.main_container, fragmentLab6);
        }

        ftrans.commit();
        item.setChecked(false);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
