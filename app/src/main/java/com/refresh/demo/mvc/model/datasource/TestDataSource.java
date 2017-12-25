/*
Copyright 2015 shizhefei（LuckyJayce）

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package com.refresh.demo.mvc.model.datasource;


import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.refresh.demo.mvc.adapter.TestResultData;
import com.refresh.demo.mvc.model.enties.TestData;
import com.shizhefei.mvc.IDataCacheLoader;
import com.shizhefei.mvc.IDataSource;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

public class TestDataSource implements IDataSource<List<TestData>>, IDataCacheLoader<List<TestData>> {
    private int currentPage = 1;
    private int currentPageSize = 0;
    private int maxPage = 10;
    private String testUrl = "http://www.bdkol.net:8133/webs/app_jk/sq/txb/sbjl_lb.jsp";

    /**
     * 加载缓存<br>
     * 注意这个方法执行于UI线程，不要做太过耗时的操作<br>
     * 每次刷新的时候触发该方法，该方法在DataSource refresh之前执行<br>
     *
     * @param isEmpty adapter是否有数据，这个值是adapter.isEmpty()决定
     * @return 加载的数据，返回后会执行adapter.notifyDataChanged(data, true)<br>
     * 相当于refresh执行后adapter.notifyDataChanged(data, true)
     */
    @Override
    public List<TestData> loadCache(boolean isEmpty) {
//		if (isEmpty) {
//			List<TestData> dataList = new ArrayList<TestData>();
//			for (int i = 0; i < 10; i++) {
//				dataList.add(new TestData("cache  page 1  Java编程思想 " + i, 108.00d));
//			}
//			return dataList;
//		}
        return null;
    }

    @Override
    public List<TestData> refresh() throws Exception {
        return loadTestData(1);
    }

    @Override
    public List<TestData> loadMore() throws Exception {
        return loadTestData(currentPage + 1);
    }

    private List<TestData> loadTestData(int page) throws Exception {
        List<TestData> testDataList = new ArrayList<TestData>();
        currentPageSize = 0;
        Response response = OkGo.get(testUrl)
                .tag(this)
                .params("userid", "8170")
                .params("pagenum", String.valueOf(page)) //当前页
                .params("myhs", String.valueOf(maxPage)) // 当前页最大记录数
                .execute();
        if (response == null)
            return testDataList;
        String resultStr = response.body().string().trim();

        if (TextUtils.isEmpty(resultStr) || resultStr.equals("false")) {
            return testDataList;
        }
        Gson gson = new Gson();
        TestResultData testResultData = gson.fromJson(resultStr, TestResultData.class);
        if (testResultData == null) {
            return testDataList;
        }
        List<TestData> testResultDataList = testResultData.getData();
        if (testResultDataList == null || testResultDataList.size() <= 0) {
            return testDataList;
        }
        testDataList.addAll(testResultDataList);
        currentPage = page;
        currentPageSize = testDataList.size();
        Log.i("TestDataSource", currentPageSize + "");
        return testDataList;
    }

    @Override
    public boolean hasMore() {
        return currentPageSize == maxPage;
    }
}
