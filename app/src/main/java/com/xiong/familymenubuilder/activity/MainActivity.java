package com.xiong.familymenubuilder.activity;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.xiong.familymenubuilder.R;

public class MainActivity extends BaseActivity {

    private DrawerLayout dl_main_drawerlayout;
    private Toolbar tb_main_toolbar;

    @Override
    public int initResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        tb_main_toolbar = (Toolbar)findViewById(R.id.tb_main_toolbar);
        dl_main_drawerlayout = (DrawerLayout)findViewById(R.id.dl_main_drawerlayout);
        setSupportActionBar(tb_main_toolbar);
        ActionBar supportActionBar = getSupportActionBar();

        if (supportActionBar!=null){
            supportActionBar.setHomeButtonEnabled(true);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        super.initListener();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl_main_drawerlayout, tb_main_toolbar, R.string.open, R.string.close) {
            //菜单打开
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            // 菜单关闭
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        toggle.syncState();
        dl_main_drawerlayout.addDrawerListener(toggle);
    }

}
