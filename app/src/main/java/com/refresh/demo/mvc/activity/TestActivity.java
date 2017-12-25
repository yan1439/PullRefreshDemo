package com.refresh.demo.mvc.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.refresh.demo.R;
import com.refresh.demo.mvc.adapter.ReBooksAdapter;
import com.refresh.demo.mvc.adapter.TestAdapter;
import com.refresh.demo.mvc.callback.MyHeader;
import com.refresh.demo.mvc.model.datasource.BooksDataSource;
import com.refresh.demo.mvc.model.datasource.TestDataSource;
import com.refresh.demo.mvc.model.enties.Book;
import com.refresh.demo.mvc.model.enties.TestData;
import com.shizhefei.mvc.MVCHelper;
import com.shizhefei.mvc.MVCUltraHelper;

import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by zhangyanyan on 2017/12/25.
 */

public class TestActivity extends Activity {
    private MVCHelper<List<TestData>> mvcHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
    }

    private void initView() {
        PtrClassicFrameLayout mPtrFrameLayout = (PtrClassicFrameLayout) findViewById(R.id.test_fl);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.test_rl);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MyHeader header = new MyHeader(this);
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        mPtrFrameLayout.setLoadingMinTime(800);
        mPtrFrameLayout.setDurationToCloseHeader(800);
        mPtrFrameLayout.setHeaderView(header);
        mPtrFrameLayout.addPtrUIHandler(header);
        //mPtrFrame.disableWhenHorizontalMove(true);//如果是ViewPager，设置为true，会解决ViewPager滑动冲突问题。


        mvcHelper = new MVCUltraHelper<List<TestData>>(mPtrFrameLayout);
        // 设置数据源
        mvcHelper.setDataSource(new TestDataSource());
        // 设置适配器
        mvcHelper.setAdapter(new TestAdapter(this));

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
