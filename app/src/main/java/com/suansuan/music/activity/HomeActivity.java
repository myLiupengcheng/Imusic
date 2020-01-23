package com.suansuan.music.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.suansuan.music.Base.BaseActivity;
import com.suansuan.music.R;
import com.suansuan.music.focator.FragmentFactory;

public class HomeActivity extends BaseActivity {

    private LinearLayout mLinearLayout;
    private LinearLayout mTitleLayout;
    private ImageButton mLeftImageButton, mRightImageButton;
    private ViewPager mViewPager;

    private HomePagerAdapter mAdapter;
    private TextView mTitleTextView;

    private int count;

    @Override
    protected void onAttach() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    protected int getResId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        initTitleBar();
        initContentView();
    }

    /**
     * 初始化内容View
     */
    private void initContentView() {
        mViewPager = findView(R.id.content_pager);
        mLinearLayout = findView(R.id.ll_buttom_layout);
//        mLinearLayout.removeViewAt(2);
        count = mLinearLayout.getChildCount() - 1;
        setButtonEnable(0);
    }


    /**
     * 初始化initTitleBar
     */
    private void initTitleBar() {
        mTitleLayout = findView(R.id.rl_titleBar);

        mLeftImageButton = findView(R.id.left_icon);
        mRightImageButton = findView(R.id.right_icon);

        mTitleTextView = findView(R.id.title);
    }

    /** 初始化数据适配器 */
    @Override
    protected void initAdapter() {
        if(mAdapter == null){
            mAdapter = new HomePagerAdapter(getSupportFragmentManager());
        }
        mViewPager.setAdapter(mAdapter);
    }

    /** 初始化监听器 */
    @Override
    protected void initListener() {
        //ViewPager的事件监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setButtonEnable(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /** 初始化数据 */
    @Override
    protected void initData() {

    }

    /** 设置底部菜单栏选中状态 */
    private void setButtonEnable(int pos){
        Log.i("suansuan","-----------------------------------------------");
        for(int i = 0; i < count; i++ ){
            if(i > 1){
                i = i + 1;
            }
            View childAt = mLinearLayout.getChildAt(i);
            if(i > 1){
                i = i - 1;
            }
            childAt.setEnabled((pos != i));
            Log.i("suansuan","---->>pos = " + pos + " i = " + i + " i+1 = " + (i+1));
        }
    }

    /** 设置标题栏是否显示 */
    public void setTitleVisibility(boolean isShow){
//        mTitleLayout.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    /** 通过底部菜单栏去切换ViewPager页面 */
    private void setCurrentItem(int position){
        if(mViewPager == null){
            return;
        }
        mViewPager.setCurrentItem(position);
    }

    /** 单击事件的处理 */
    @Override
    protected void onClick(View v, int id) {
        switch (id) {
            case R.id.left_icon:
                Toast.makeText(this, "left_icon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.right_icon:
                Toast.makeText(this, "right_icon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_home:
                setCurrentItem(0);
                break;
            case R.id.iv_find:
                setCurrentItem(1);
                break;
            case R.id.iv_v:
                setCurrentItem(2);
                break;
            case R.id.iv_like:
                setCurrentItem(3);
                break;

        }
    }

    /** ViewPager页面的数据适配器 */
    class HomePagerAdapter extends FragmentPagerAdapter {

        HomePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentFactory.newInstance(position);
        }

        @Override
        public int getCount() {
            return count;
        }
    }
}
