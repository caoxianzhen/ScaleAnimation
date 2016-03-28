package com.example.jiashuaishuai.myapplicationscaleanimation;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        button = (Button) findViewById(R.id.button);

        int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        tv.measure(w, h);
        int height =tv.getMeasuredHeight();
        final int width =tv.getMeasuredWidth();
//        textView.append("\n"+height+","+width);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                ObjectAnimator.ofInt(new ViewWrapper(tv),
                        "width", width,0).setDuration(3000).start();
            }
        });
    }

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
