package com.xiong.familymenubuilder.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.xiong.familymenubuilder.R;


/**
 * Created by xsw on 2016/11/4 0004.
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    public static BaseActivity context;
    public FragmentManager fm;
    public static float xMultiple;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //强制竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        context = this;
        fm = getSupportFragmentManager();
        setContentView(initResId());
        initView();
        initData();
        initListener();

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        xMultiple = point.x / 750f;
    }

    public abstract int initResId();

    protected abstract void initView();

    protected abstract void initData();

    protected void initListener() {

    }


    //点击别处隐藏软件盘
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v != null && getCurrentFocus().getWindowToken() != null) {
                if (isShouldHideInput(v, ev)) {

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                }
            }

            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        return getWindow().superDispatchTouchEvent(ev) || onTouchEvent(ev);

    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v instanceof EditText) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }

        return false;
    }

    @Override
    public void onBackPressed() {
        onBackStack();
    }

    public void onBackStack() {
        if (fm.getBackStackEntryCount() <= 1) {
            finish();
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.rl_back:
//                onBackPressed();
//                break;
        }
    }

    @Override
    public void startActivity(Intent intent) {
        if (intent != null) {
            super.startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        }
    }

    public void jumpToMain(BaseActivity activity, int index) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.putExtra("index", index);
        activity.startActivity(intent);
    }


}
