package com.example.proshare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class HomePage extends AppCompatActivity {

    BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        navigationView = findViewById(R.id.bottom_navigation);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportFragmentManager().beginTransaction().replace(R.id.body_container,new homefragment()).commit();


        navigationView.setSelectedItemId(R.id.nav_home);


        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        fragment = new homefragment();
                        break;
                    case R.id.nav_search:
                        fragment = new searchfragment();
                        break;
                    case R.id.nav_reels:
                        fragment = new reelsfragment();
                        break;
                    case R.id.nav_settings:
                        fragment = new settingsfragment();
                        break;
                    case R.id.nav_profile:
                        fragment = new profilefragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.body_container, fragment).commit();
                return true;
            }
        });
    }

    
}