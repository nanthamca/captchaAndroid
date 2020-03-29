package com.game.nanthabalane.captcha;

import android.content.Context;

public class Utils {

    public static int dpToPx(Context context, float px) {
        return (int) (px * context.getResources().getDisplayMetrics().density);
    }

    public static int sptoPx(Context context, float px) {
        return (int) (px * context.getResources().getDisplayMetrics().scaledDensity);
    }
}
