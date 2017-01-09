package com.example.evan.androidviewertemplates.drawer_fragments.super_ability;

import android.support.v4.view.PagerAdapter;

import com.example.evan.androidviewertools.utils.FragmentViewPager;


/**
 * Created by colinunger on 2/4/16.
 */
public class SuperAbilityFragment extends FragmentViewPager {
    @Override
    public PagerAdapter getPagerAdapter() {
        return new SuperAbilityAdapter(getActivity(), getChildFragmentManager());
    }
}
