package com.example.evan.androidviewertools.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.evan.androidviewertools.R;


public abstract class FragmentViewPager extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.second_pick_view_pager, container, false);
        ViewPager pager = (ViewPager) result.findViewById(R.id.secondPickAbilityPager);

        pager.setAdapter(getPagerAdapter());

        return (result);
    }

    public abstract PagerAdapter getPagerAdapter();
}
