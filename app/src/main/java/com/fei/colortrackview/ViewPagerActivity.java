package com.fei.colortrackview;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * @ClassName: ViewPagerActivity
 * @Description: java类作用描述
 * @Author: Fei
 * @CreateDate: 2020-12-12 10:04
 * @UpdateUser: 更新者
 * @UpdateDate: 2020-12-12 10:04
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ViewPagerActivity extends AppCompatActivity {

    private String[] mTitles = {"新闻", "爱看", "电影", "综艺", "少儿"};
    private ViewPager mViewPager;
    private LinearLayout mIndicatorContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        mViewPager = findViewById(R.id.view_pager);
        mIndicatorContainer = findViewById(R.id.ll_indicate);

        initIndicator();
        initViewPager();
    }

    /**
     * 初始化viewpager
     */
    private void initViewPager() {
        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e("tag", "position = " + position + " positionOffset = " + positionOffset);
                //左边
                ColorTrackView leftView = (ColorTrackView) mIndicatorContainer.getChildAt(position);
                leftView.setCurrentDirection(ColorTrackView.Direction.RIGHT_TO_LEFT);
                leftView.setCurrentProgress(1.0f - positionOffset);

                if (position + 1 == mIndicatorContainer.getChildCount()) return;
                //右边
                ColorTrackView rightView = (ColorTrackView) mIndicatorContainer.getChildAt(position + 1);
                rightView.setCurrentDirection(ColorTrackView.Direction.LEFT_TO_RIGHT);
                rightView.setCurrentProgress(positionOffset);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return ItemFragment.newInstance(mTitles[position]);
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }
    }

    /**
     * 添加头部
     */
    private void initIndicator() {
        for (String mTitle : mTitles) {
            ColorTrackView colorTrackView = new ColorTrackView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.weight = 1;
            layoutParams.topMargin = 10;
            layoutParams.bottomMargin = 10;
            colorTrackView.setLayoutParams(layoutParams);
            colorTrackView.setTextSize(20);
            colorTrackView.setOriginaColor(getResources().getColor(R.color.colorPrimaryDark));
            colorTrackView.setChangeColor(getResources().getColor(R.color.colorAccent));
            colorTrackView.setText(mTitle);
            mIndicatorContainer.addView(colorTrackView);
        }
    }
}
