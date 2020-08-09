package com.example.fortunetellingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.widget.TextView;

import com.example.fortunetellingapp.Animation.TextAnimation;

public class MainActivity extends AppCompatActivity {

    /** 変数の定義 */

    //activityに表示するView変数
    TextView mainTitle;
    TextView subTitle;
    TextView reference;

    //アニメーション実行時間
    int animationTime = 500;

    //遷移時間
    int stopTime = 500;

    Resources res;
    int mainBackgorundColor;
    int mainTextColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //xmlからViewをインスタンス化する処理
        initComponents();

        //Textの色の定義
        initColor();

        //TextViewの初期化
        init();

        //MainTitleのアニメーション処理
        animationMain();

    }

    @Override
    public void onResume(){
        super.onResume();

        //TextViewの初期化
        init();

        //MainTitleのアニメーション処理
        animationMain();

    }

    /** Textの色の定義をするメソッド */

    private void initColor(){

        //Resourcesクラスでは、リソースIDを引数に指定すると対応するリソースを取得できるメソッドがいくつか用意されてる
        res = getResources();
        mainBackgorundColor = res.getColor(R.color.mainBackgroundColor);
        mainTextColor = res.getColor(R.color.mainTextColor);

    }

    /** 画面の初期化メソッド */
    private void init(){

        //TextViewを非表示にする処理
        mainTitle.setTextColor(mainBackgorundColor);
        subTitle.setTextColor(mainBackgorundColor);
        reference.setTextColor(mainBackgorundColor);

    }

    /** MainTitleのアニメーション処理メソッド */

   public void animationMain(){

       mainTitle.setTextColor(mainTextColor);
       TextAnimation animation = new TextAnimation((mainTitle));
       animation.setDuration(animationTime);
       mainTitle.startAnimation(animation);
       animation.setAnimationListener(new Animation.AnimationListener() {
           @Override
           public void onAnimationStart(Animation animation) {

           }

           @Override
           public void onAnimationEnd(Animation animation) {
                animationSub();
           }

           @Override
           public void onAnimationRepeat(Animation animation) {

           }
       });

   }

    /** SubTitleのアニメーション処理メソッド */

   public void animationSub(){

       subTitle.setTextColor(mainTextColor);
       TextAnimation animation = new TextAnimation((subTitle));
       animation.setDuration(animationTime);
       subTitle.startAnimation(animation);
       animation.setAnimationListener(new Animation.AnimationListener() {
           @Override
           public void onAnimationStart(Animation animation) {

           }

           @Override
           public void onAnimationEnd(Animation animation) {
               animationRef();
           }

           @Override
           public void onAnimationRepeat(Animation animation) {

           }
       });

   }

    /** Referenceのアニメーション処理メソッド */

   public void animationRef(){

       reference.setTextColor(mainTextColor);
       TextAnimation animation = new TextAnimation((reference));
       animation.setDuration(animationTime);
       reference.startAnimation(animation);
       animation.setAnimationListener(new Animation.AnimationListener() {
           @Override
           public void onAnimationStart(Animation animation) {

           }

           @Override
           public void onAnimationEnd(Animation animation) {


               // Handlerクラスをインスタンス化し、postDelayedメソッドを呼んでいる
               new Handler().postDelayed(new Runnable() {
                   // Runnable型のインスタンス化と定義
                   @Override
                   public void run() {

                       // 遅らせて実行したい処理
                       Intent intent = new Intent(getApplication(), MenuActivity.class);
                       startActivity(intent);

                   }
               }, stopTime); // 遅らせたい時間(ミリ秒)


           }

           @Override
           public void onAnimationRepeat(Animation animation) {

           }
       });

   }


    /** xmlからViewをインスタンス化するメソッド */

    public void initComponents(){
        mainTitle = (TextView)findViewById(R.id.mainTitle);
        subTitle = (TextView)findViewById(R.id.subTitle);
        reference = (TextView)findViewById(R.id.reference);
    }


}