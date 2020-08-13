package com.example.fortunetellingapp.Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.telecom.TelecomManager;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import com.example.fortunetellingapp.Animation.RouletteAnimation;
import com.example.fortunetellingapp.Animation.RouletteFlashingAnimation;
import com.example.fortunetellingapp.MyView.RouletteView;
import com.example.fortunetellingapp.R;

public class OmikujiFragment extends Fragment implements Animation.AnimationListener, View.OnTouchListener {

    /** 変数の定義 */

    //fragmentのView
    private View rootView;
    //ルーレットView
    private RouletteView rouleteView;
    //アニメーションの時間
    private final int ANIMATION_TIME = 5000;
    //ルーレットの項目数
    private final int NUM = 6;

    private TextView omikujiTitle;

    //ルーレットの結果
    private String omikujiResult = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.activity_omikuji, container, false);

        rootView.setOnTouchListener(this);
        rouleteView = (RouletteView) rootView.findViewById(R.id.roulette);
        omikujiTitle = (TextView) rootView.findViewById(R.id.omikujiTitle);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

    }



    /** アニメーションが始まったときに呼ばれるメソッド */

    @Override
    public void onAnimationStart(Animation animation) {

    }

    /** アニメーションが終わったときに呼ばれるメソッド */

    @Override
    public void onAnimationEnd(Animation animation) {
        omikujiTitle.setText(rouleteView.getResultOmikuji());
    }

    /** アニメーションが繰り返されたときに呼ばれるメソッド */

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    /** 画面がタッチされたときに呼び出されるメソッド */

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        /*
        //RouletteAnimationクラスをインスタンス化
        RouletteAnimation animation = new RouletteAnimation(rouleteView);

         */

        RouletteFlashingAnimation animation = new RouletteFlashingAnimation(rouleteView, NUM);

        //アニメーションの時間を設定
        animation.setDuration(ANIMATION_TIME);
        //アニメーションを開始
        rouleteView.startAnimation(animation);
        //アニメーションにリスナを登録
        animation.setAnimationListener(this);

        omikujiTitle.setText(getString(R.string.duringOmikuji));

        return false;
    }
}