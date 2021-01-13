package com.example.flowlayouttest;

import android.content.Context;
import android.util.AttributeSet;

public class ItemView extends IconView {

    private static final String TAG = "ItemView";

    public ItemView(Context context) {
        this(context, null);
    }

    public ItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setTitleGone(boolean isGone) {
        int visibility = isGone ? GONE : VISIBLE;
        getTextView().setVisibility(visibility);
    }
}
