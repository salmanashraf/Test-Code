package com.sa.icarasia.adapters;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sa.icarasia.ui.fragments.LoginFragment;
import com.sa.icarasia.ui.fragments.SignupFragment;


public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments;

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<Fragment>();
        fragments.add(new LoginFragment());
        fragments.add(new SignupFragment());
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:

                return new LoginFragment();

            case 1:
                return new SignupFragment();


        }
        return new LoginFragment();
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}