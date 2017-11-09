package com.refresh.demo.mvc.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.refresh.demo.R;
import com.refresh.demo.mvc.adapter.ReBooksAdapter;
import com.refresh.demo.mvc.callback.MyHeader;
import com.refresh.demo.mvc.model.datasource.BooksDataSource;
import com.refresh.demo.mvc.model.enties.Book;
import com.shizhefei.mvc.MVCHelper;
import com.shizhefei.mvc.MVCUltraHelper;

import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.MaterialHeader;

/**
 * Created by zhangyanyan on 2017/7/19.
 * 下拉刷新，底部自动加载：通用布局
 * MVCHelper和android-Ultra-Pull-To-Refresh 共同使用
 */

public class CommonMVCHelperActivity extends Activity {
    private MVCHelper<List<Book>> mvcHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc_helper_common);
        initView();
    }

    private void initView() {
        PtrClassicFrameLayout mPtrFrameLayout = (PtrClassicFrameLayout) findViewById(R.id.rotate_header_list_view_frame);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        final MaterialHeader header = new MaterialHeader(this);
//        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
//        header.setPtrFrameLayout(mPtrFrameLayout);
//        mPtrFrameLayout.setLoadingMinTime(800);
//        mPtrFrameLayout.setDurationToCloseHeader(800);
//        mPtrFrameLayout.setHeaderView(header);
//        mPtrFrameLayout.addPtrUIHandler(header);
        MyHeader header = new MyHeader(this);
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        mPtrFrameLayout.setLoadingMinTime(800);
        mPtrFrameLayout.setDurationToCloseHeader(800);
        mPtrFrameLayout.setHeaderView(header);
        mPtrFrameLayout.addPtrUIHandler(header);
        mPtrFrameLayout.setKeepHeaderWhenRefresh(true);//刷新时保持头部的显示，默认为true
        //mPtrFrame.disableWhenHorizontalMove(true);//如果是ViewPager，设置为true，会解决ViewPager滑动冲突问题。


        mvcHelper = new MVCUltraHelper<List<Book>>(mPtrFrameLayout);
        // 设置数据源
        mvcHelper.setDataSource(new BooksDataSource());
        // 设置适配器
        mvcHelper.setAdapter(new ReBooksAdapter(this));

        // 加载数据
        mvcHelper.refresh();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 释放资源
        mvcHelper.destory();
    }

}
