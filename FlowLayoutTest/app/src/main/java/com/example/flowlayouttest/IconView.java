package com.example.flowlayouttest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class IconView extends RelativeLayout implements View.OnClickListener {
    private static final String TAG = "IconView";

    private ImageView mIconView;
    private TextView mTitleView;
    private String packageName;
    private Context mContext;
    private String mTitleText;
    private int mTitleTextColor;
    private int mTitleTextSize;
    private int mIconWidth;
    private int mIconHeight;
    private int imageNormalbg;
    private int imageFocusedbg;
    private RelativeLayout mTextParent;

    public IconView(Context context) {
        this(context, null);
    }

    public IconView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IconView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.icon_view, this);
        mIconView = (ImageView) findViewById(R.id.item_icon);
        mTitleView = (TextView) findViewById(R.id.item_title);
        mTextParent=(RelativeLayout)findViewById(R.id.item_text_container);

        String tag = (String) getTag();
        if (!TextUtils.isEmpty(tag)) {
            packageName = tag;
            Log.d(TAG, "packageName =" + packageName);
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.IconView, defStyle, 0);

        int n = typedArray.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.IconView_titleText:
                    mTitleText = typedArray.getString(attr);
                    Log.d(TAG, "mTitleText =" + mTitleText);
                    break;
                case R.styleable.IconView_titleTextColor:
                    mTitleTextColor = typedArray.getColor(attr, Color.WHITE);
                    break;
                case R.styleable.IconView_titleTextSize:
                    mTitleTextSize = typedArray.getDimensionPixelSize(attr, 18);
                    Log.d(TAG, "mTitleTextSize =" + mTitleTextSize);
                case R.styleable.IconView_imageNormalbg:
                    imageNormalbg = typedArray.getResourceId(attr, -1);
                    Log.i(TAG, "imageNormalBg:" + imageNormalbg);
                    break;
                case R.styleable.IconView_imageFocusedbg:
                    imageFocusedbg = typedArray.getResourceId(attr, -1);
                    Log.i(TAG, "imageFocusedbg:" + imageFocusedbg);
                    break;
                case R.styleable.IconView_iconWidth:
                    mIconWidth = typedArray.getDimensionPixelOffset(attr, 100);
                    Log.d(TAG, "mIconWidth =" + mIconWidth);
                    break;
                case R.styleable.IconView_iconHeight:
                    mIconHeight = typedArray.getDimensionPixelOffset(attr, 100);
                    Log.d(TAG, "mIconHeight =" + mIconHeight);
                    break;
                default:
                    break;
            }

        }

        imageNormalbg = typedArray.getResourceId(R.styleable.IconView_imageNormalbg, -1);
        imageFocusedbg = typedArray.getResourceId(R.styleable.IconView_imageFocusedbg, -1);
        typedArray.recycle();
        initView();
        setFocusable(true);
        setOnClickListener(this);
    }

    private void initView() {
        mTitleView.setText(mTitleText);
        mTitleView.setTextColor(mTitleTextColor);
        mTitleView.setTextSize(mTitleTextSize);
        int resId = imageNormalbg == -1 ? 0 : imageNormalbg;
        mIconView.setImageResource(resId);
        setSize(mIconWidth, mIconHeight);
    }

    private void setSize(int width, int height) {
        LayoutParams layoutParams = (LayoutParams) mIconView.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        mIconView.setLayoutParams(layoutParams);

        LayoutParams textContainerParams = (LayoutParams) mTextParent.getLayoutParams();
        textContainerParams.width = width;
        textContainerParams.height = height;
        mTextParent.setLayoutParams(layoutParams);
    }

    @Override
    public void onClick(View v) {
    }

    public ImageView getIcon() {
        return mIconView;
    }

    public TextView getTextView() {
        return mTitleView;
    }

    public int getImageResources() {
        return imageNormalbg;
    }

    public int getImageWidth() {
        return mIconWidth;
    }

    public int getImageHeight() {
        return mIconHeight;
    }
}
