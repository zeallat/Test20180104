package com.zeallat.prndtest.base;

import android.content.Context;
import android.support.annotation.StringRes;

public class BaseActivityConfig {
    private Context mContext;
    //Toolbar 표시 여부
    private boolean isToolbarEnable = false;
    //DrawerLayout 표시 여부
    private boolean isDrawerLayoutEnable = false;
    //Toolbar 네비게이션 버튼(좌측 버튼) 종류 설정
    private BaseActivity.NavigationType mToolbarNavigationType = BaseActivity.NavigationType.NONE;
    //Toolbar Title
    private String mToolbarTitle = "";
    //Scroll type of content layout
    private ScrollType mScrollType = ScrollType.NONE;

    //scroll type enum
    public enum ScrollType {
        NONE, SCROLL, NESTED_SCROLL
    }

    public BaseActivityConfig(Context context) {
        mContext = context;
    }

    public boolean isToolbarEnable() {
        return isToolbarEnable;
    }

    public BaseActivityConfig setToolbarEnable(boolean toolbarEnable) {
        isToolbarEnable = toolbarEnable;
        return this;
    }

    public boolean isDrawerLayoutEnable() {
        return isDrawerLayoutEnable;
    }

    public BaseActivityConfig setDrawerLayoutEnable(boolean drawerLayoutEnable) {
        isDrawerLayoutEnable = drawerLayoutEnable;
        return this;
    }

    public BaseActivity.NavigationType getToolbarNavigationType() {
        return mToolbarNavigationType;
    }

    public BaseActivityConfig setToolbarNavigationType(BaseActivity.NavigationType toolbarNavigationType) {
        mToolbarNavigationType = toolbarNavigationType;
        return this;
    }

    public String getToolbarTitle() {
        return mToolbarTitle;
    }

    public BaseActivityConfig setToolbarTitle(String toolbarTitle) {
        mToolbarTitle = toolbarTitle;
        return this;
    }

    public BaseActivityConfig setToolbarTitle(@StringRes int toolbarTitleResId) {
        mToolbarTitle = mContext.getString(toolbarTitleResId);
        return this;
    }

    public ScrollType getScrollType() {
        return mScrollType;
    }

    public BaseActivityConfig setScrollType(ScrollType scrollType) {
        mScrollType = scrollType;
        return this;
    }
}