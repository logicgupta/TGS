package com.logic.tsg.ui;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.logic.tsg.Adapter.GraphpagerAdapter;
import com.logic.tsg.Model.HId;
import com.logic.tsg.Model.HardwareId;
import com.logic.tsg.Model.Header;
import com.logic.tsg.R;
import com.logic.tsg.ViewModelProvidersFactory;
import com.logic.tsg.viewmodel.MainViewModel;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ViewPager viewPager;
    WormDotsIndicator indicator;
    GraphpagerAdapter adapter;
    List<String> hardwareIdList=new ArrayList<>();
    HId hId;

    @Inject
    ViewModelProvidersFactory factory;
    MainViewModel viewModel;
    TextView textViewBill,textViewEmisssion,textViewLoad,textViewConsumption,textViewReading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        viewModel= ViewModelProviders.of(this,factory).get(MainViewModel.class);
        hId=new HId("702ff36c6c16daad5cbb65f457644f56ca9b844f");
        hardwareIdList.add("702ff36c6c16daad5cbb65f457644f56ca9b844f");
        HardwareId hardwareId=new HardwareId(hardwareIdList);
        viewModel.header(hardwareId);

        viewModel.getHeaderLivedata().observe(this, new Observer<Header>() {
            @Override
            public void onChanged(Header header) {

                String y1=String.format("%.2f",header.getCurrentbilling());
                String y2=String.format("%.2f",header.getCarbonEmission());
                String y3=String.format("%.2f",header.getElectricityConsumption());
                String y4=String.format("%.2f",header.getCurrentLoad());
                String y5=String.format("%.2f",header.getCurrentMeterReading());
                textViewBill.setText("₹ "+y1);
                textViewEmisssion.setText(""+y2+" Kg");
                textViewConsumption.setText(""+y3+" KwH");
                textViewLoad.setText(""+y4+" Kw/hr");
                textViewReading.setText(""+y5+" KwH");
            }
        });

        init();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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
        if (id == R.id.action_notify) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_wallet) {

        } else if (id == R.id.nav_logout) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void init(){
        viewPager=findViewById(R.id.viewPager);
        indicator=findViewById(R.id.dots_indicator);
        adapter=new GraphpagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
        textViewBill=findViewById(R.id.bill);
        textViewConsumption=findViewById(R.id.consumption);
        textViewEmisssion=findViewById(R.id.carbon);
        textViewLoad=findViewById(R.id.load);
        textViewReading=findViewById(R.id.reading);

    }

}
