package com.refresh.demo.mvc.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.refresh.demo.R;
import com.refresh.demo.mvc.adapter.MovieDetailAdapter;
import com.refresh.demo.mvc.model.datasource.MovieDetailDataSource;
import com.refresh.demo.mvc.model.enties.Discuss;
import com.refresh.demo.mvc.model.enties.Movie;
import com.shizhefei.mvc.MVCHelper;
import com.shizhefei.mvc.MVCUltraHelper;
import com.shizhefei.mvc.data.Data3;

import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * Created by zhangyanyan on 2017/9/20.
 * 下拉刷新，底部自动加载：复杂布局
 * MVCHelper和android-Ultra-Pull-To-Refresh 共同使用
 */

public class ComplexMVCHelperActivity extends Activity {

    private MVCHelper<Data3<Movie, List<Discuss>, List<Movie>>> mvcHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc_helper_complex);
        initView();
    }

    private void initView() {
        PtrClassicFrameLayout contentLayout = (PtrClassicFrameLayout) findViewById(R.id.rotate_header_list_view_frame);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mvcHelper = new MVCUltraHelper<Data3<Movie, List<Discuss>, List<Movie>>>(contentLayout);
        // 设置数据源
        mvcHelper.setDataSource(new MovieDetailDataSource());
        // 设置适配器
        mvcHelper.setAdapter(new MovieDetailAdapter(this));

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
