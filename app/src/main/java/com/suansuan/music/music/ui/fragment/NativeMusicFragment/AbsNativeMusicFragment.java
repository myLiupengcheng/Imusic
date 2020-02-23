package com.suansuan.music.music.ui.fragment.NativeMusicFragment;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;
import com.suansuan.music.base.BaseFragment;
import com.suansuan.music.R;
import com.suansuan.music.engine.NativeEngine;
import com.suansuan.music.music.ui.view.IndexQueryView;

import java.util.List;

import static android.view.View.inflate;


/**
 *
 * Created by suansuan on 2016/10/14.
 */

public abstract class AbsNativeMusicFragment extends BaseFragment {

    public View mViewRoot;
    protected ListView mListView;
    private TextView mCurrentWord;
    private IndexQueryView mQuickIndexBar;

    protected NativeEngine mMusicEngine;

    private boolean isScale = false;
    public Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            initAdapter();
        }
    };

    @Override
    protected void findData() {
        mMusicEngine = NativeEngine.newInstance(mActivity);
        initNativeMusicData();
        mHandler.sendEmptyMessage(0);
    }

    @Override
    protected View initView() {
        mViewRoot = inflate(mActivity, R.layout.fragment_music, null);
        mListView = (ListView) mViewRoot.findViewById(R.id.native_music_lv);
        mQuickIndexBar = (IndexQueryView) mViewRoot.findViewById(R.id.quickIndexBar);
        mQuickIndexBar.setVisibility(View.GONE);
        mCurrentWord = (TextView) mViewRoot.findViewById(R.id.currentWord);
        ViewHelper.setScaleX(mCurrentWord, 0);
        ViewHelper.setScaleY(mCurrentWord, 0);
        initNativeMusicView();
        return mViewRoot;
    }



    @Override
    protected void initListener() {
        mQuickIndexBar.addOnClickIndexQueryListener(new IndexQueryView.OnClickIndexQueryListener() {
            @Override
            public void onClickIndexQuery(String letter) {
                setListLocaltion(letter);
                showCurrentWord(letter);
            }
        });

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if(scrollState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL|| scrollState == AbsListView.OnScrollListener.SCROLL_STATE_FLING){
                    mQuickIndexBar.setVisibility(View.VISIBLE);
                }else{
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mQuickIndexBar.setVisibility(View.GONE);
                        }
                    }, 2000);
                }
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    protected void showCurrentWord(String letter) {
        mCurrentWord.setText(letter);
        if(!isScale){
            isScale = true;
            ViewPropertyAnimator.animate(mCurrentWord).scaleX(1f).setInterpolator(new OvershootInterpolator()).setDuration(450).start();
            ViewPropertyAnimator.animate(mCurrentWord).scaleY(1f).setInterpolator(new OvershootInterpolator()).setDuration(450).start();
        }
        //延时隐藏currentWord
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ViewPropertyAnimator.animate(mCurrentWord).scaleX(0f).setDuration(450).start();
                ViewPropertyAnimator.animate(mCurrentWord).scaleY(0f).setDuration(450).start();
                isScale = false;
            }
        }, 1500);
    }

    abstract class  NativeAdapter<T> extends BaseAdapter {

        private List<T> mList;

        public NativeAdapter(List<T> list ){
            this.mList = list;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public T getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return adapterGetView(position,convertView,parent);
        }

        /** ListView的getView方法 */
        protected abstract View adapterGetView(int position, View convertView, ViewGroup parent);

    }
    /** 点击IndexQueryView的事件回调 */
    protected abstract void setListLocaltion(String l);

    /** 初始化布局 */
    protected abstract void initNativeMusicView();

    /** 初始化数据 */
    protected abstract void initNativeMusicData();

    /** 初始化数据适配器 */
    protected abstract void initAdapter();



}
