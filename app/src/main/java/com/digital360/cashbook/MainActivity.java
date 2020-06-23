package com.digital360.cashbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView mNavigationView;

    Database database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mNavigationView = findViewById(R.id.navigationId);
        mNavigationView.setNavigationItemSelectedListener(MainActivity.this);

        //database
        database = new Database(MainActivity.this);

        if (savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new Dashboard()).commit();
            setTitle("ডিজিটাল ক্যাশ বুক");
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.dashboard:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new Dashboard()).commit();
                setTitle("ডিজিটাল ক্যাশ বুক");
                break;
           /* case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new Profile()).commit();
                setTitle("Profile");
                break;*/
            case R.id.income:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new Income()).commit();
                setTitle("ইনকাম");
                break;
            case R.id.expense:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new Expense()).commit();
                setTitle("খরচ");
                break;
            case R.id.loan:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new Loan()).commit();
                setTitle("ঋন");
                break;
            case R.id.report:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new Report()).commit();
                setTitle("সকল তথ্য");
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
