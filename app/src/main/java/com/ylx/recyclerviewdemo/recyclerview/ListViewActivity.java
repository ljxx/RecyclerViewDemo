package com.ylx.recyclerviewdemo.recyclerview;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ylx.recyclerviewdemo.BaseActivity;
import com.ylx.recyclerviewdemo.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends BaseActivity {

    private TextView mBack;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayoutManager mLinearLayoutManager;

    private List<String> mDatas;
    private ListViewAdapter mAdapter;

    @Override
    public void initView() {
        setContentView(R.layout.activity_list_view);
        mBack = (TextView) findViewById(R.id.mBack);
        initData();
        mAdapter = new ListViewAdapter(ListViewActivity.this,mDatas);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.mSwipeRefreshLayout);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

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

    int page = 1;

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
         * 下拉刷新
         */
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(ListViewActivity.this, "执行刷新了 click",
                        Toast.LENGTH_SHORT).show();

                //清理数据
                page = 1;
                EndLessOnScrollListener.currentPage = 1;
                if(mDatas != null && mDatas.size() > 0){
                    mDatas.clear();
                    for (int i = 'A'; i < 'Z'; i++){
                        mDatas.add("" + (char)i);
                    }
                    mAdapter.notifyDataSetChanged();
                }


                mSwipeRefreshLayout.setRefreshing(false); //停止刷新
            }
        });

        /**
         * 上啦加载
         */
        mRecyclerView.addOnScrollListener(new EndLessOnScrollListener(mLinearLayoutManager) {
            @Override
         public void onLoadMore(int currentPage) {
                Log.i("==currentPage===","===" + currentPage);
                if(page < 5){
                    loadMoreData();
                } else {
                    Toast.makeText(ListViewActivity.this, "已经没有数据了",
                            Toast.LENGTH_SHORT).show();
                }
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

    /**
     * 加载更多数据
     */
    private void loadMoreData() {
        page ++;
        for (int i = 0; i < 10; i++){
            mDatas.add("嘿，我是“上拉加载”生出来的100" + i);
        }
        mAdapter.notifyDataSetChanged();
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
