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
package com.refresh.demo.mvc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.refresh.demo.R;
import com.refresh.demo.mvc.model.enties.TestData;
import com.shizhefei.mvc.IDataAdapter;

import java.util.ArrayList;
import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<ViewHolder> implements IDataAdapter<List<TestData>> {
    private LayoutInflater inflater;
    private List<TestData> testDataList = new ArrayList<TestData>();

    public TestAdapter(Context context) {
        super();
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_book, parent, false)) {
        };
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        TextView textView = (TextView) holder.itemView;
        textView.setText(testDataList.get(position).getJlsj());

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "position:" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return testDataList.size();
    }

    @Override
    public void notifyDataChanged(List<TestData> data, boolean isRefresh) {
        if (isRefresh) {
            testDataList.clear();
        }
        testDataList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public List<TestData> getData() {
        return testDataList;
    }

    @Override
    public boolean isEmpty() {
        return testDataList.isEmpty();
    }

}
