package com.example.fortunetellingapp.Animation;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.TextView;

import com.example.fortunetellingapp.MyView.RouletteView;

public class RouletteAnimation extends Animation {

    /** 変数の定義 */

    RouletteView view;
    int count = 0;
    int base = 10;

    /** コンストラクタ */

    public RouletteAnimation(RouletteView view){
        this.view = view;
    }

    /** アニメーションの実行 */

    @Override
    public void applyTransformation(float interpolatedTime, Transformation t){
        super.applyTransformation(interpolatedTime, t);

        count++;

        int move = base - (count / 25);

        if(move < 0){
            move = 0;
        }

        /*

        view.addPos(move);

         */
        view.requestLayout();

        if(move == 0){
            cancel();
        }
    }

}
