package com.zeallat.prndtest.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.zeallat.prndtest.BuildConfig;
import com.zeallat.prndtest.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.zeallat.prndtest.Config.IS_SCREEN_ORIENTATION_PORTRAIT_REQUIRED;

/**
 * BaseActivity.java
 *
 * @author HoJunLee
 * @description 베이스 activity.
 * Created by HoJunLee on 2017-07-05.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder mUnbinder;
    private Handler mHandler;
    private RequestManager mRequestManager;

    private boolean isNewActivity = true;

    public static final String EXTRA_IS_TRANSITION_OVERRIDE_ENABLE = "08b63731-e729-40c6-b465-bd27d834f38e";

    private boolean isTransitionOverrideEnable = true;

    /**
     * 드로어 레이아웃 & 네비게이션 뷰 선언
     */
    private DrawerLayout mBaseDrawerLayout = null;
    private NavigationView mNavigationView = null;

    /**
     * BaseActivityConfig
     */
    private BaseActivityConfig mConfig = new BaseActivityConfig(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //화면 세로 고정 처리
        if (IS_SCREEN_ORIENTATION_PORTRAIT_REQUIRED)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if (getIntent().getExtras() != null) {
            isTransitionOverrideEnable = getIntent().getExtras().getBoolean(EXTRA_IS_TRANSITION_OVERRIDE_ENABLE, true);
        }

        mHandler = new Handler(Looper.getMainLooper());
        mRequestManager = Glide.with(this);
        if (BuildConfig.DEBUG)
            Log.d("BaseActivity", String.format("==========================\nActivity has create : %s\n==========================",
                    getLocalClassName()));
        //액티비티 애니메이션 처리
        isNewActivity = true;
        if (savedInstanceState == null) {
            if (isTransitionOverrideEnable)
                this.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left_slow);
        } else {
            isNewActivity = false;
        }
    }

    /**
     * 레이아웃 타입 확인해서 레이아웃 설정
     *
     * @param layoutResID
     */
    @Override
    public void setContentView(int layoutResID) {
        /**
         * config 초기화
         */
        setConfig(mConfig);

        View rootView;//root View
        LinearLayout viewGroupContent;//ViewGroup that contain real content
        View contentView = getLayoutInflater().inflate(layoutResID, null);
        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        //set drawer layout
        if (mConfig.isDrawerLayoutEnable()) {
            mBaseDrawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.base_layout_drawer, null);
            rootView = mBaseDrawerLayout;
            mNavigationView = mBaseDrawerLayout.findViewById(R.id.navigationView);
            viewGroupContent = mBaseDrawerLayout.findViewById(R.id.viewGroupContent);
            /**
             * set navigation view menu action here.
             */
            mNavigationView.setNavigationItemSelectedListener(item -> {
                switch (item.getItemId()) {
                    case R.id.navigationMenuDummy:
                        break;
                }
                mBaseDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            });
        } else {
            viewGroupContent = (LinearLayout) getLayoutInflater().inflate(R.layout.base_layout, null);
            rootView = viewGroupContent;
        }

        //set toolbar
        setToolbarLayout(viewGroupContent);

        //set scrollable
        contentView = setLayoutScroll(contentView);

        //set content
        viewGroupContent.addView(contentView);

        super.setContentView(rootView);

    }

    /**
     * set scrollable depend config
     */
    private View setLayoutScroll(View contentView) {
        if (mConfig.getScrollType() == BaseActivityConfig.ScrollType.SCROLL) {
            ScrollView scrollView = new ScrollView(this);
            ScrollView.LayoutParams layoutParams =
                    new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            scrollView.setFocusableInTouchMode(true);
            scrollView.setFillViewport(true);
            scrollView.setLayoutParams(layoutParams);
            scrollView.addView(contentView);
            return scrollView;
        } else if (mConfig.getScrollType() == BaseActivityConfig.ScrollType.NESTED_SCROLL) {
            NestedScrollView nestedScrollView = new NestedScrollView(this);
            NestedScrollView.LayoutParams layoutParams =
                    new NestedScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            nestedScrollView.setFocusableInTouchMode(true);
            nestedScrollView.setFillViewport(true);
            nestedScrollView.setLayoutParams(layoutParams);
            nestedScrollView.addView(contentView);
            return nestedScrollView;
        }
        return contentView;
    }

    /**
     * inflate content
     *
     * @param viewGroupContent
     */
    private void setToolbarLayout(LinearLayout viewGroupContent) {
        if (mConfig.isToolbarEnable()) {
            AppBarLayout appBarLayout = (AppBarLayout) getLayoutInflater().inflate(R.layout.view_toolbar, viewGroupContent, false);
            Toolbar toolbar = appBarLayout.findViewById(R.id.toolbar);
            toolbar.setTitle(mConfig.getToolbarTitle());
            setSupportActionBar(toolbar);
            if (mConfig.getToolbarNavigationType() != null) {
                switch (mConfig.getToolbarNavigationType()) {
                    case NAVIGATION:
                        if (!mConfig.isDrawerLayoutEnable()) break;
                        toolbar.setNavigationIcon(R.drawable.ic_menu_white_24px);
                        toolbar.setNavigationOnClickListener(view -> {
                            if (mBaseDrawerLayout.isDrawerOpen(GravityCompat.START))
                                mBaseDrawerLayout.closeDrawer(GravityCompat.START);
                            else mBaseDrawerLayout.openDrawer(GravityCompat.START);
                        });
                        break;
                    case BACK:
                        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24px);
                        toolbar.setNavigationOnClickListener(view -> onBackPressed());
                        break;
                    case NONE:
                        break;
                }
            }
            viewGroupContent.addView(appBarLayout);
        }

    }

    /**
     * navigation button type
     */
    protected enum NavigationType {
        NAVIGATION, BACK, NONE
    }

    /**
     * Base Activity BaseActivityConfig 설정.
     */
    protected abstract void setConfig(BaseActivityConfig config);

    protected RequestManager getGlide() {
        return mRequestManager;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        mUnbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (BuildConfig.DEBUG) Log.d("BaseActivity", "onDestroy");
        mUnbinder.unbind();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //액티비티 애니메이션 처리
        if (isNewActivity) {
            isNewActivity = false;
        } else {
            this.overridePendingTransition(R.anim.slide_in_right_slow, R.anim.slide_out_right);
        }

        try {
            //regist eventbus
            EventBus.getDefault().register(this);
        } catch (Throwable t) {
//            t.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            //unregist eventbus
            EventBus.getDefault().unregister(this);
        } catch (Throwable t) {
//            t.printStackTrace();
        }
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        //드로어 레이아웃 예외처리
        if (mBaseDrawerLayout != null && mBaseDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mBaseDrawerLayout.closeDrawer(GravityCompat.START);
            return;
        }

        if (isTaskRoot()) {//마지막 액티비티일 경우 실행
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "\'뒤로\' 버튼을 한 번 더 누르면 앱이 종료됩니다", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 키보드 숨김
     */
    protected void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 키보드 노출
     */
    protected void showKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            /**
             * EditText 외부를 클릭했을경우, SoftKeyboard hide처리 및 EditText의 포커스 해제
             */
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            } else {
                hideKeyboard();
            }
        }
        return super.dispatchTouchEvent(event);
    }
}
