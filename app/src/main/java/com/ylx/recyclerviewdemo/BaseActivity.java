package com.ylx.recyclerviewdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * ========================================
 * <p/>
 * 版 权：蓝吉星讯 版权所有 （C） 2017
 * <p/>
 * 作 者：yanglixiang
 * <p/>
 * 版 本：1.0
 * <p/>
 * 创建日期：2017/5/5  下午4:36
 * <p/>
 * 描 述：
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
    }

    public abstract void initView();

    public abstract void initListener();
}
