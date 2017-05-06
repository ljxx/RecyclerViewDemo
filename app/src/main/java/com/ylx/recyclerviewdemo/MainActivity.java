package com.ylx.recyclerviewdemo;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

    private TextView mListView,mGridView,mListAndGrid;

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        mListView = (TextView) findViewById(R.id.mListView);
        mGridView = (TextView) findViewById(R.id.mGridView);
        mListAndGrid = (TextView) findViewById(R.id.mListAndGrid);
    }

    @Override
    public void initListener() {
        mListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ListViewActivity.class));
            }
        });

    }
}
