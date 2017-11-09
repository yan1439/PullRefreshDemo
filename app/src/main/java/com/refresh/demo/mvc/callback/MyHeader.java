package com.refresh.demo.mvc.callback;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.refresh.demo.R;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Created by zhangyanyan on 2017/9/20.
 */

public class MyHeader extends android.support.v7.widget.AppCompatImageView implements PtrUIHandler {

    private AnimationDrawable mAnimDrawable;

    public MyHeader(Context context) {
        super(context);
        initView(context);
    }

    public MyHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mAnimDrawable = (AnimationDrawable) ContextCompat.getDrawable(context, R.drawable.animation_swipe_refresh);
    }


    @Override
    public void onUIReset(PtrFrameLayout frame) {
        mAnimDrawable.stop();
        this.setImageResource(R.drawable.refresh_0);
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        if (!mAnimDrawable.isRunning()) {
            this.setImageDrawable(mAnimDrawable);
        }
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        if (mAnimDrawable != null){
            mAnimDrawable.start();
        }
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        if (mAnimDrawable != null){
            mAnimDrawable.stop();
            this.setImageResource(R.drawable.refresh_0);
        }

    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {

    }
}
