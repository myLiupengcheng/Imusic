package com.suansuan.music.music.ui.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.suansuan.music.R;


/**
 *
 * Created by suansuan on 2016/10/16.
 */

/**
 * 根据不同的布局，加载显示不同的数据
 * @author suansuan
 *
 */
public abstract class LoadingLayout extends FrameLayout {

    /** 什么都没有加载的状态  */
    public static final int MODEL_NOMAL_STATE = 1;

    /** 空数据的状态  */
    public static final int MODEL_EMPTY_STATE = 2;

    /** 加载中的状态 */
    public static final int MODEL_LOADING_STATE = 3;

    /** 加载数据失败的状态  */
    public static final int MODEL_ERROR_STATE = 4;

    /** 加载成功的状态 */
    public static final int MODEL_SUCCESS_STATE = 5;

    /** 当前的状态的值 */
    public int mCurrentState = MODEL_NOMAL_STATE;

    /** 布局加载器 */
    public LayoutInflater mLayoutInflater;

    /** 所需要加载的布局 */
    private View mLoadingView;
    private View mLoadErrorView;
    private View mLoadSuccessView;
    private View mLoadEmptyView;

    private AnimationDrawable mAnimation;

    private Handler mHandler = new Handler();

    /** 构造方法 */
    public LoadingLayout(Context context) {
        this(context, null);
    }
    public LoadingLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public LoadingLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    /** 初始化操作  */
    private void init() {
        mLayoutInflater = LayoutInflater.from(getContext());
        removeAllViews();
        initLoadingView();
        initLoadErrorView();
        initLoadEmptyView();
        showRightPager();
    }

    /** 加载数据为空的布局文件 */
    private void initLoadEmptyView() {
        if(mLoadEmptyView == null){
            mLoadEmptyView = mLayoutInflater.inflate(R.layout.item_pager_empty, this, false);
        }
    }

    /** 初始化加载失败的布局 */
    private void initLoadErrorView() {
        if(mLoadErrorView == null){
            mLoadErrorView = mLayoutInflater.inflate(R.layout.item_pager_un_connectivity, this, false);
        }
//        mLoadErrorView.findViewById(R.id.btn_load).setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mCurrentState = MODEL_NOMAL_STATE;
//                showRightPager();
//                loadData();
//            }
//        });

    }

    /** 初始化加载中的布局文件 */
    private void initLoadingView() {
        if(mLoadingView == null){
            mLoadingView = mLayoutInflater.inflate(R.layout.pager_loding, this, false);
        }
        ImageView imageView = (ImageView) mLoadingView.findViewById(R.id.iv_loading);
        mAnimation = (AnimationDrawable)imageView.getBackground();

    }

    /** 显示正确的布局 */
    private void showRightPager() {
        removeAllViews();

        if(mCurrentState == MODEL_LOADING_STATE || mCurrentState == MODEL_NOMAL_STATE){
            addView(mLoadingView);
            mAnimation.start();
        }else{
            if(mAnimation != null )
                mAnimation.stop();
        }

        if(mCurrentState == MODEL_ERROR_STATE && mLoadErrorView != null){
            addView(mLoadErrorView);
        }

        if(mCurrentState == MODEL_EMPTY_STATE && mLoadEmptyView != null){
            addView(mLoadEmptyView);
        }

        if(mCurrentState == MODEL_SUCCESS_STATE && mLoadSuccessView == null){
            mLoadSuccessView = onCreateSuccess();
            if(mLoadSuccessView != null){
                addView(mLoadSuccessView);
            }
        }

    }


    /** 开始加载数据 */
    public void loadData() {
        if (mCurrentState != MODEL_LOADING_STATE) {// 如果当前没有加载, 就开始加载数据
            mCurrentState = MODEL_LOADING_STATE;
            new Thread() {
                @Override
                public void run() {
                    ResultState startLoad = startLoad();
                    mCurrentState = startLoad.getState();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            showRightPager();
                        }
                    });
                }
            }.start();
        }
    }

    /** 根据调用者去调用成功的布局 */
    public abstract View onCreateSuccess();

    /** 加载数据 */
    public abstract ResultState startLoad();


    public enum ResultState {
        MODEL_EMPTY_STATE(LoadingLayout.MODEL_EMPTY_STATE),
        MODEL_ERROR_STATE(LoadingLayout.MODEL_ERROR_STATE),
        MODEL_SUCCESS_STATE(LoadingLayout.MODEL_SUCCESS_STATE);

        private int state;

        ResultState(int state){
            this.state = state;
        }

        public int getState(){
            return this.state;
        }
    }
}
