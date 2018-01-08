package com.zeallat.prndtest.util;

import android.graphics.Color;
import android.os.Build;
import android.os.SystemClock;
import android.view.View;

import com.zeallat.prndtest.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static android.text.TextUtils.isEmpty;
import static com.google.common.base.MoreObjects.firstNonNull;

/**
 * ViewUtil.java
 *
 * @author HoJunLee
 * @description comment here
 * Created by HoJunLee on 2017-07-10.
 */
public class ViewUtil {
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    /**
     * Generate a value suitable for use in {@link #(int)}.
     * This value will not collide with ID values generated at build time by aapt for R.id.
     *
     * @return a generated ID value
     */
    public static int generateViewId() {
        if (Build.VERSION.SDK_INT < 17) {
            for (; ; ) {
                final int result = sNextGeneratedId.get();
                // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
                int newValue = result + 1;
                if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
                if (sNextGeneratedId.compareAndSet(result, newValue)) {
                    return result;
                }
            }
        } else {
            return View.generateViewId();
        }
    }

    public static int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    private static Map<String, Long> mLastClickedTimeMap = new HashMap<>();
    private static final int CLICK_DELAY_MIN = 600;

    /**
     * 중복 클릭 체크 메서드
     *
     * @param view
     * @return
     */
    public static boolean isRecentlyClicked(View view) {
        String viewIdTag = (String) firstNonNull(view.getTag(R.id.key_tag_uuid), "");
        long currentTime = SystemClock.elapsedRealtime();
        if (isEmpty(viewIdTag)) {
            viewIdTag = UUID.randomUUID().toString();
            view.setTag(R.id.key_tag_uuid, viewIdTag);
            mLastClickedTimeMap.put(viewIdTag, currentTime);
            return false;
        } else {
            long lastClickedTime = mLastClickedTimeMap.get(viewIdTag);
            if (lastClickedTime + CLICK_DELAY_MIN < currentTime) {//Click is allowed
                mLastClickedTimeMap.put(viewIdTag, currentTime);
                return false;
            } else {//Click is not allowed
                return true;
            }
        }
    }
}
