package com.example.mvcapp.view;

import android.view.View;
import android.widget.TextView;

import com.example.mvcapp.R;
import com.example.mvcapp.base.BaseActivity;
import com.example.mvcapp.bean.CalendarInfo;
import com.example.mvcapp.http.ApiUtil;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.observers.DefaultObserver;

public class MainActivity extends BaseActivity {


    @BindView(R.id.tv_data)
    TextView tvData;

    @OnClick(R.id.btn_getData)
    public void onViewClicked() {
        showLoadingDialog();//加载等待框
        //处理获取的数据
        ApiUtil.calendarDetails(mProvider, "ceshi", "1462377600", "CD78D9012F1C063E54C640EA27952F80", new DefaultObserver<CalendarInfo>() {
            @Override
            public void onNext(CalendarInfo calendarInfo) {
                showCalendarInfo(calendarInfo);
            }

            @Override
            public void onError(Throwable e) {
                requestOnError(e);
            }

            @Override
            public void onComplete() {
                hideLoadingDialog();
            }
        });
    }

    protected int setContentLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        View actionBar = setActionBar("首页");
    }

    @Override
    protected void initData() {

    }

    //展示日历信息
    private void showCalendarInfo(CalendarInfo calendarInfo) {
        tvData.setText(calendarInfo.toString());
    }
}
