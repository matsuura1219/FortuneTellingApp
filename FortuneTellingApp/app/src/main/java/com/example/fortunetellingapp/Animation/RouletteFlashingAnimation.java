package com.example.fortunetellingapp.Animation;

import android.graphics.Color;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.example.fortunetellingapp.MyView.RouletteView;

import java.util.Random;

public class RouletteFlashingAnimation extends Animation {

    RouletteView view;
    private int NUM = 0;
    int angle = 0;
    int count = 0;
    int position = 0;
    Random random;

    public RouletteFlashingAnimation(RouletteView view, int num){
        this.view = view;
        this.NUM = num;
        angle = 360 / NUM;

        random = new Random();
        count = random.nextInt(6) + 1;
    }

    @Override
    public void applyTransformation(float interpolatedTime, Transformation t){
        super.applyTransformation(interpolatedTime, t);

        count++;

        if(count % 3 == 0) {

            view.setColors(position % 6);

            position++;
        }

        view.requestLayout();

    }



}
