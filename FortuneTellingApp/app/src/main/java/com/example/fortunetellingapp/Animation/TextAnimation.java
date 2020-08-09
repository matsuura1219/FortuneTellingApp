package com.example.fortunetellingapp.Animation;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.TextView;

public class TextAnimation extends Animation {

    /** 変数の定義 */

    TextView text;

    /** コンストラクタ */

    public TextAnimation(TextView text){
        this.text = text;
    }

    /** アニメーションの実行 */

    @Override
    public void applyTransformation(float interpolatedTime, Transformation t){
        super.applyTransformation(interpolatedTime, t);

        //textをフェードインするための処理
        text.setAlpha(interpolatedTime);
        //UIに反映する処理
        text.requestLayout();
    }
}
