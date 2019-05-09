package com.cinemacar.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.cinemacar.R;
import com.cinemacar.fragments.InfoFragment;


public class MainActivity extends AppCompatActivity {

  InfoFragment infoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragments();
    }

    @Override
    protected void onStart() {
        super.onStart();
        goToFragment(infoFragment);
    }

    public void goToFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void initFragments() {
        infoFragment = new InfoFragment();

    }

    @Override
    public void onBackPressed() {
        int stackCount = getSupportFragmentManager().getBackStackEntryCount();
        if (stackCount == 1) finish();
        else super.onBackPressed();
    }

}
