package com.logic.tsg.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.logic.tsg.ui.main.ComparisionGraphActivity;
import com.logic.tsg.ui.main.ParameterGraphActivity;

public class GraphpagerAdapter extends FragmentStatePagerAdapter {
    public GraphpagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        if (position==0){
            fragment=new ParameterGraphActivity();
        }
        else {
            fragment=new ComparisionGraphActivity();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
