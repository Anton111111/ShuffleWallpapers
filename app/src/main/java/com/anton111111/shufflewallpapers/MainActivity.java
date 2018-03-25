package com.anton111111.shufflewallpapers;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anton111111.shufflewallpapers.fragment.ShuffleWallpaperFragment;


public class MainActivity extends AppCompatActivity {


    private ShuffleWallpaperFragment shuffleWallpaperFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showFragment();
    }

    private void showFragment() {
        shuffleWallpaperFragment = ShuffleWallpaperFragment.newInstance();

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_holder, shuffleWallpaperFragment);
        fragmentTransaction.commit();
    }


}
