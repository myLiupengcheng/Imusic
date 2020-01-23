package com.suansuan.music.Base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.suansuan.music.R;
import com.suansuan.music.utils.UIUtils;

/**
 * Activity的一个基类
 * Created by suansuan on 2016/9/30.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    protected String Tag = getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onAttach();
        setContentView(getResId());
        View rootView = findViewById(android.R.id.content);
        UIUtils.findButtonSetOnClickListener(this, rootView);
        initData();
    }

    @Override
    protected void onStart() {
        initView();
        initListener();
        super.onStart();
    }

    @Override
    protected void onResume() {
        initAdapter();
        super.onResume();
    }

    /**
     * 提供了一个开启新的Activity的方法
     */
    public void startNewAticity(Class clazz, boolean isfinsh) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        if (isfinsh) {
            finish();
        }
    }

    @SuppressWarnings("all")
    /** 子类查找控件，不使用强转 */
    public <T> T findView(int id) {
        T view = (T) super.findViewById(id);
        return view;
    }

    /**
     * 提供常用的Toast的方法
     */
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /** 最先执行，根据自己的需要进行是否重写 */
    protected void onAttach() {}

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_black:
                finish();
                break;
            default:
                onClick(v, v.getId());
                break;
        }
    }

    /**
     * 获取子类的所要加载布局的ID
     */
    protected abstract int getResId();

    /**
     * 初始化控件View
     */
    protected abstract void initView();

    /**
     * 舒适化数据适配器
     */
    protected abstract void initAdapter();

    /**
     * 初始化监听器
     */
    protected abstract void initListener();

    /**
     * 初始化数据，-----> 这个方法以后要和网络框架所关联
     */
    protected abstract void initData();

    /**
     * 在这里对没个页面索要使用到的资源统一管理
     */
    protected abstract void onClick(View v, int id);
}
