package com.ylx.recyclerviewdemo.swipetoloadlayout;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.ylx.recyclerviewdemo.BaseActivity;
import com.ylx.recyclerviewdemo.R;

/**
 *
 *
 * 三方库SwipeToLayout的使用  实现RecycleView的上拉刷新和下拉加载
 *
 *  需要导入一下依赖
 *
 *   repositories {
 *       maven { url "https://jitpack.io" }
 *          }
 *
 *      compile 'com.github.Aspsine:SwipeToLoadLayout:1.0.3'
 *
 */
public class SwipeToLayoutActivity extends BaseActivity implements OnRefreshListener, OnLoadMoreListener {



    private RecyclerView mRecycleView;

    SwipeToLoadLayout swipeToLoadLayout;

    private HomeAdapter adapter;

    @Override
    public void initView() {
        setContentView(R.layout.activity_recycle_swipetolayout);


        swipeToLoadLayout = ((SwipeToLoadLayout) findViewById(R.id.swipeToLoadLayout));


        mRecycleView = ((RecyclerView) findViewById(R.id.swipe_target));


        adapter = new HomeAdapter();

        //设置垂直的线性布局管理器，Orientation -->   VERTICAL:垂直   HORIZONTAL:水平
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

        //添加分割线
        mRecycleView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL_LIST));

        mRecycleView.setLayoutManager(layoutManager);

        mRecycleView.setItemAnimator(new DefaultItemAnimator());

        mRecycleView.setAdapter(adapter);

        adapter.refresh();

        /**
         * 设置下拉刷新和上拉加载监听
         */
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
    }

    @Override
    public void initListener() {

    }


    @Override
    public void onRefresh() {
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.refresh();
                swipeToLoadLayout.setRefreshing(false);
            }
        },2000);
    }

    @Override
    public void onLoadMore() {
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {

                adapter.add();
                swipeToLoadLayout.setLoadingMore(false);
            }
        },2000);
    }
}
