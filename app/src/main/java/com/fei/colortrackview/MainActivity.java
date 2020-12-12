package com.fei.colortrackview;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    ColorTrackView colorTrackView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorTrackView = findViewById(R.id.color_track_view);
    }

    public void leftToRight(View view) {
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 1f);
        valueAnimator.setDuration(2000);
        colorTrackView.setCurrentDirection(ColorTrackView.Direction.LEFT_TO_RIGHT);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                colorTrackView.setCurrentProgress(value);
            }
        });
        valueAnimator.start();
    }

    public void rightToLeft(View view) {
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 1f);
        valueAnimator.setDuration(2000);
        colorTrackView.setCurrentDirection(ColorTrackView.Direction.RIGHT_TO_LEFT);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                colorTrackView.setCurrentProgress(value);
            }
        });
        valueAnimator.start();
    }
}