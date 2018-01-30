package com.sa.icarasia.ui.activities;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;

import com.sa.icarasia.R;
import com.sa.icarasia.adapters.SectionsPagerAdapter;
import com.sa.icarasia.model.UserModel;
import com.sa.icarasia.ui.activities.base.BaseActivity;
import com.sa.icarasia.ui.fragments.SignupFragment;

import io.realm.RealmList;

public class MainActivity extends BaseActivity implements SignupFragment.OnAddUserClickListener{

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }

    public void showUsers(RealmList<UserModel> user) {

        for (int i=0; i <user.size() ; i++){
            Log.d("email", user.get(i).getEmail());
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onAddUserClickListener(UserModel user) {

    }
}
