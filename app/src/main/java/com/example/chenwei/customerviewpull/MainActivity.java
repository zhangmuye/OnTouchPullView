package com.example.chenwei.customerviewpull;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.example.chenwei.customerviewpull.view.ViewPull;

public class MainActivity extends AppCompatActivity {

    ViewPull viewPull;

    private static final int TOUCH_MOVE_MAX_y = 600;
    private float mTouchMoveStartY;
    private float progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPull = findViewById(R.id.viewPull);

        findViewById(R.id.main_id).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //获得意图
                int action = motionEvent.getActionMasked();
                switch (action){
                    //按下屏幕
                    case MotionEvent.ACTION_DOWN:
                        mTouchMoveStartY = motionEvent.getY();
                        return true;
                    //移动时
                    case MotionEvent.ACTION_MOVE:
                        float y = motionEvent.getY();
                        if (y >= mTouchMoveStartY){
                            float moveSize = y - mTouchMoveStartY;
                            progress = moveSize >= TOUCH_MOVE_MAX_y?1:moveSize / TOUCH_MOVE_MAX_y;
                            viewPull.setProgress(progress);

                            return true;
                        }

                        break;
                    case MotionEvent.ACTION_UP:
                        viewPull.release();
                        break;
                }
                return true;
            }
        });

    }
}
