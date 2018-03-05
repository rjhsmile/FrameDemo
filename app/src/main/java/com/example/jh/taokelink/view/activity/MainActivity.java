package com.example.jh.taokelink.view.activity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jh.taokelink.BaseActivity;
import com.example.jh.taokelink.R;
import com.example.jh.taokelink.utils.ApiContents;
import com.example.jh.taokelink.utils.CostomFragmentTabHost;
import com.example.jh.taokelink.utils.SplashView;
import com.example.jh.taokelink.utils.StringUtils;
import com.example.jh.taokelink.utils.UiUtils;
import com.example.jh.taokelink.utils.nohttps.HttpListener;
import com.example.jh.taokelink.utils.nohttps.JsonUtils;
import com.example.jh.taokelink.view.fragment.ChoiceFragment;
import com.example.jh.taokelink.view.fragment.FenleiFragment;
import com.example.jh.taokelink.view.fragment.MainFragment;
import com.example.jh.taokelink.view.fragment.PersionerFragment;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import java.text.ChoiceFormat;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity {

    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.tabhost)
    CostomFragmentTabHost tabs;

    @Override
    protected void initView() {
        //初始化Tabs
        initTabs();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    private void initTabs() {
        // 初始化tabs，设置点击Tab的时候，第三个参数用于设置Fragment显示到哪个容器中
        tabs.setup(this, getSupportFragmentManager(), R.id.content);
        // 隐藏分隔线
        tabs.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        String[] names = this.getResources().getStringArray(R.array.tabNames);
        //首页
        addTab(tabs, names[0], R.drawable.selector_tab_icon_home, MainFragment.class);
        //自选
        addTab(tabs, names[1], R.drawable.selector_tab_icon_option, ChoiceFragment.class);
        //基金
        addTab(tabs, names[2], R.drawable.selector_tab_icon_fund, FenleiFragment.class);
        //个人中心
        addTab(tabs, names[3], R.drawable.selector_tab_icon_asset, PersionerFragment.class);
    }

    public void addTab(CostomFragmentTabHost tabs, CharSequence tabName, int tabIcon, Class<?> fragmentClass) {
        // 创建一个tab标签的View
        TabHost.TabSpec tab = tabs.newTabSpec(fragmentClass.getSimpleName());
        TextView tabView = new TextView(this);
        tabView.setTextSize(10);
        tabView.setText(tabName);
        tabView.setGravity(Gravity.CENTER);
        tabView.setPadding(0, 8, 0, 8);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tabView.setTextColor(getResources().getColorStateList(R.color.selector_tab, null));
        } else {
            tabView.setTextColor(getResources().getColorStateList(R.color.selector_tab));
        }
        tabView.setCompoundDrawablesWithIntrinsicBounds(0, tabIcon, 0, 0);
        tab.setIndicator(tabView);

        // 把一个tab添加到一个tabs容器中
        tabs.addTab(tab, fragmentClass, null);
    }

}