package com.refresh.demo;

import android.app.Application;

import com.refresh.demo.mvc.callback.MyLoadViewFactory;
import com.shizhefei.mvc.MVCHelper;

/**
 * Created by zhangyanyan on 2017/9/20.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        // 设置LoadView的factory，用于创建使用者自定义的加载失败，加载中，加载更多等布局,写法参照DeFaultLoadViewFactory
        MVCHelper.setLoadViewFractory(new MyLoadViewFactory());
    }
}
