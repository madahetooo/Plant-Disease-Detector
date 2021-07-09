package com.apps.smartgreenhouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.navigation.NavigationView;

public class DrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new SetupActivity()).commit();
            navigationView.setCheckedItem(R.id.setUpWindow);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.setUpWindow:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SetupActivity()).commit();
                break;
            case R.id.CheckSensorsValues:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MainActivity()).commit();
                break;
            case R.id.ControlPanel:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ControlPanel()).commit();
                break;
            case R.id.CheckPlantHealth:

                Intent intent = new Intent(getApplicationContext(), ImageClassificationActivity.class);
                startActivity(intent);
                break;
            case R.id.checkPlantColor:

                Intent intent2 = new Intent(getApplicationContext(), ColorClassification.class);
                startActivity(intent2);
                break;
            case R.id.diseases_guide_tap:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new DiseaseGuide()).commit();
                break;


            case R.id.exit:
                //return to login Activity
                finish();
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (doubleBackToExitPressedOnce) {
                finish();
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }
}