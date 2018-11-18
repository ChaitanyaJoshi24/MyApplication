package com.chetz.myapplication.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chetz.myapplication.R;
import com.chetz.myapplication.fragments.DetailFragment;
import com.chetz.myapplication.fragments.MapFragment;
import com.chetz.myapplication.models.Product;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailProductActivity extends AppCompatActivity {

    Product product;

    @BindView(R.id.vpPager)
    ViewPager viewPager;

    @BindView(R.id.pager_header)
    PagerTabStrip pagerTabStrip;

    public static final String KEY_PRODUCTMODEL = "product_model";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        ButterKnife.bind(this);
        product = getIntent().getParcelableExtra(KEY_PRODUCTMODEL);

        FragmentAdapter adapterViewPager = new FragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);
    }

    public class FragmentAdapter extends FragmentPagerAdapter {

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {

            if(i == 0){
                return DetailFragment.newInstance(product);
            }
            else if(i == 1){
                return MapFragment.newInstance(product);
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            if(position == 0){

                return "Details ";
            }
            else if(position == 1){

                return "Map";
            }
            return null;

        }

    }
}

