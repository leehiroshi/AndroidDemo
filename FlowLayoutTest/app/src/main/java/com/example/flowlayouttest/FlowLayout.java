package com.example.flowlayouttest;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import java.util.ArrayList;
import java.util.List;

/**
 * Main view flow layout
 */
public class FlowLayout extends FrameLayout implements OnClickListener {
    private static final String TAG = "FlowLayout";
    private static final int MSG_RUNNING = 1;
    private static final int MSG_LEFT_Z = 2;
    private static final int MSG_RIGHT_Z = 3;
    private static final int MSG_SHOW_SHADE = 4;
    private List<ItemView> listItemView;
    private ImgData[] mDatas;
    private boolean isRunning;
    private boolean isInit;
    private ItemView redItemView;
    private ItemView blueItemView;
    private ItemView greenItemView;
    private Context mContext;
    private ImageView mOutlineView;
    private int mWidth;
    private int mHeight;

    private float mDefaultScaleFg = 1.25f;
    private float mDefaultScaleBg = 1f;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SHOW_SHADE:
                    break;
                case MSG_RUNNING:
                    isRunning = false;
                    break;
                case MSG_LEFT_Z:
                    listItemView.get(2).setTitleGone(false);
                    listItemView.get(0).setTitleGone(true);
                    listItemView.get(1).setTitleGone(true);
                    listItemView.get(2).bringToFront();

                    listItemView.get(1).setTitleGone(false);
                    listItemView.get(0).setTitleGone(true);
                    listItemView.get(2).setTitleGone(true);
                    listItemView.get(1).bringToFront();

                    listItemView.get(0).setTitleGone(false);
                    listItemView.get(1).setTitleGone(true);
                    listItemView.get(2).setTitleGone(true);
                    listItemView.get(0).bringToFront();
                    listItemView.add(0, listItemView.remove(2));
                    invalidate();
                    break;
                case MSG_RIGHT_Z:
                    listItemView.get(1).setTitleGone(false);
                    listItemView.get(0).setTitleGone(true);
                    listItemView.get(2).setTitleGone(true);
                    listItemView.get(1).bringToFront();

                    listItemView.get(0).setTitleGone(false);
                    listItemView.get(2).setTitleGone(true);
                    listItemView.get(1).setTitleGone(true);
                    listItemView.get(0).bringToFront();

                    listItemView.get(2).setTitleGone(false);
                    listItemView.get(0).setTitleGone(true);
                    listItemView.get(1).setTitleGone(true);
                    listItemView.get(2).bringToFront();
                    listItemView.add(listItemView.remove(0));
                    invalidate();
                    break;
                default:
                    break;
            }
        }
    };

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
        setFocusable(true);
        setClickable(true);
        setOnClickListener(this);
        initDefaultScale();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.flow_layout, this);
        redItemView = (ItemView) findViewById(R.id.flowlayout_red);
        blueItemView = (ItemView) findViewById(R.id.flowlayout_blue);
        greenItemView = (ItemView) findViewById(R.id.flowlayout_green);
        mOutlineView = (ImageView) findViewById(R.id.flowlayout_outline);
        listItemView = new ArrayList<ItemView>();
        listItemView.add(redItemView);
        listItemView.add(blueItemView);
        listItemView.add(greenItemView);
        redItemView.setTitleGone(true);
        blueItemView.setTitleGone(false);
        greenItemView.setTitleGone(true);

        int[] position = new int[2];
        listItemView.get(1).getIcon().getLocationInWindow(position);
        float scale = mContext.getResources().getDisplayMetrics().density;
        mWidth = listItemView.get(1).getIcon().getLayoutParams().width;
        mHeight = listItemView.get(1).getIcon().getLayoutParams().height;
        Log.d(TAG, "width = " + mWidth + ",height =" + mHeight);
    }

    private void initDefaultScale() {
        listItemView.get(1).animate().scaleX(mDefaultScaleFg).scaleY(mDefaultScaleFg).
                setDuration(0).start();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (!isInit) {
            mDatas = new ImgData[listItemView.size()];
            for (int i = 0; i < listItemView.size(); i++) {
                mDatas[i] = new ImgData(listItemView.get(i).getX(), listItemView.get(i).getWidth(),
                        listItemView.get(i).getHeight());
            }
            isInit = true;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_LEFT:
                if (!isRunning) {
                    isRunning = true;
                    mHandler.sendEmptyMessageDelayed(MSG_RUNNING, 400);
                    left();
                }
                return true;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                if (!isRunning) {
                    isRunning = true;
                    mHandler.sendEmptyMessageDelayed(MSG_RUNNING, 400);
                    right();
                }
                return true;
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        Log.i(TAG, "onFocusChanged:" + gainFocus);
        int visibility = gainFocus ? VISIBLE : INVISIBLE;
        mOutlineView.setVisibility(visibility);
    }

    @Override
    public void onClick(View view) {
        String packageName = (String) listItemView.get(1).getTag();
        Log.i(TAG, "onClick package name:" + packageName);
    }

    private void left() {
        anim(listItemView.get(0), mDatas[1], mDefaultScaleFg);
        anim(listItemView.get(1), mDatas[2], mDefaultScaleBg);
        anim(listItemView.get(2), mDatas[0], mDefaultScaleBg);
        mHandler.sendEmptyMessageDelayed(MSG_LEFT_Z, 150);
    }

    private void right() {
        anim(listItemView.get(0), mDatas[2], mDefaultScaleBg);
        anim(listItemView.get(1), mDatas[0], mDefaultScaleBg);
        anim(listItemView.get(2), mDatas[1], mDefaultScaleFg);
        mHandler.sendEmptyMessageDelayed(MSG_RIGHT_Z, 150);

    }

    private void anim(ItemView from, ImgData to, float scale) {
        from.animate().x(to.x - (from.getWidth() - to.w) / 2).scaleX(scale)
                .scaleY(scale).setDuration(300).start();
    }

    private class ImgData {
        private float x;
        private float w;
        private float h;

        public ImgData(float x, int w, int h) {
            this.x = x;
            this.w = (float) w;
            this.h = (float) h;
        }
    }
}
