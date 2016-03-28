package com.example.jiashuaishuai.myapplicationscaleanimation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by jiashuaishuai on 2016/3/16.
 */
public class ShrinkTextView extends LinearLayout {
    private TextView tetview;
    private CheckBox button;
    private int height;
    private int width;
    private LinearLayout back;

    public ShrinkTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.content_main, this, true);
        button = (CheckBox) findViewById(R.id.button);
        tetview = (TextView) findViewById(R.id.tv);
        back = (LinearLayout) findViewById(R.id.back);
        measureTextViewWhit();
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button.isChecked()) {
                    zoom();
                } else {
                    expand();
                }
            }
        });
    }

    /**
     * 缩放
     */
    private void zoom() {
        ObjectAnimator animator = ObjectAnimator.ofInt(new ViewWrapper(tetview),
                "width", width, 0);
        animator.setDuration(800).start();
        /**
         * 添加监听，执行完成后替换图片
         */
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                button.setBackgroundResource(R.drawable.information_off);
                back.setBackgroundColor(Color.parseColor("#00000000"));
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    /**
     * 扩张
     */
    private void expand() {
        button.setBackgroundResource(R.drawable.close_pop_off);
        back.setBackgroundResource(R.drawable.sh);
        ObjectAnimator.ofInt(new ViewWrapper(tetview),
                "width", width).setDuration(800).start();
    }

    /**
     * 测量控件宽度
     */
    private void measureTextViewWhit() {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        tetview.measure(w, h);
        height = tetview.getMeasuredHeight();
        width = tetview.getMeasuredWidth();
    }

    /**
     * 具体不太懂
     */
    private class ViewWrapper {
        private View mTarget;

        public ViewWrapper(View mTarget) {
            this.mTarget = mTarget;
        }

        public int getWidth() {
            return mTarget.getLayoutParams().width;

        }


        public void setWidth(int width) {

            mTarget.getLayoutParams().width = width;

            mTarget.requestLayout();

        }

    }

}
