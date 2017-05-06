package com.ylx.recyclerviewdemo;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends BaseActivity {

    private TextView mBack;
    private RecyclerView mRecyclerView;

    private List<String> mDatas;
    private ListViewAdapter mAdapter;

    @Override
    public void initView() {
        setContentView(R.layout.activity_list_view);
        mBack = (TextView) findViewById(R.id.mBack);
        initData();
        mAdapter = new ListViewAdapter(ListViewActivity.this,mDatas);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //添加默认分割线：高度为2px，颜色为灰色
        mRecyclerView.addItemDecoration(new RecycleViewDividerLine(ListViewActivity.this,LinearLayoutManager.HORIZONTAL));

        //添加自定义分割线：可自定义分割线drawable
        /*mRecyclerView.addItemDecoration(new RecycleViewDivider(
                mContext, LinearLayoutManager.VERTICAL, R.drawable.divider_mileage));*/
        //添加自定义分割线：可自定义分割线高度和颜色
        /*mRecyclerView.addItemDecoration(new RecycleViewDivider(
                mContext, LinearLayoutManager.VERTICAL, 10, getResources().getColor(R.color.divide_gray_color)));*/

        mRecyclerView.setAdapter(mAdapter);

        setHeaderView(mRecyclerView);
        setFooterView(mRecyclerView);
    }

    @Override
    public void initListener() {
        /**
         * 返回
         */
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /**
         * 点击事件
         */
        mAdapter.setOnItemClickListener(new ListViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int positin) {
                Toast.makeText(ListViewActivity.this, positin + " click",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(ListViewActivity.this, position + " long click",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData(){
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'Z'; i++){
            mDatas.add("" + (char)i);
        }
    }

    private void setHeaderView(RecyclerView view){
        View header = LayoutInflater.from(this).inflate(R.layout.recycler_view_header,view,false);
        mAdapter.setHeaderView(header);
    }

    private void setFooterView(RecyclerView view){
        View footer = LayoutInflater.from(this).inflate(R.layout.recycler_view_footer,view,false);
        mAdapter.setFooterView(footer);
    }
}
