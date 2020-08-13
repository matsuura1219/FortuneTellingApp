package com.example.fortunetellingapp.MyView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.fortunetellingapp.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RouletteView extends View {

    Paint paint;
    Paint textPaint;

    private int angle = 0;
    //ルーレットの項目数
    private final int NUM = 6;
    private int init = 0;

    // Canvas 中心点
    private float xc = 0.0f;
    private float yc = 0.0f;
    private final int TEXT_SIZE = 40;

    private final int backColor = getResources().getColor(R.color.mainBackgroundColor);
    private final int selectedRoulette = getResources().getColor(R.color.selectedRoulette);
    private final int nonSelectedRoulette = getResources().getColor(R.color.nonSelectedRoulette);
    private final int rouletteText = getResources().getColor(R.color.rouletteText);
    private final int rouletteStrole = getResources().getColor(R.color.rouletteStroke);

    private int position = 0;
    private String resultOmikuji = null;
    /*

    int pos = 0;

     */


    private RectF rectF = null;


    private int[] colors = {
            selectedRoulette,
            nonSelectedRoulette,
            nonSelectedRoulette,
            nonSelectedRoulette,
            nonSelectedRoulette,
            nonSelectedRoulette
    };


    List<String> result = new ArrayList<String>();

    private String[] testStrings = {
            "大吉",
            "中吉",
            "小吉",
            "末吉",
            "凶",
            "大凶",
    };

    /** コンストラクタ */

    public RouletteView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //ルーレットを描画するためのPaintクラス
        paint = new Paint();

        //ルーレット内の文字を描画するPaintクラス
        //文字の色をColor.DKGRAYに設定
        textPaint = new Paint(rouletteText);
        //文字サイズ
        textPaint.setTextSize(TEXT_SIZE);

        //1つあたりの項目の角度
        angle = 360 / NUM;

        //ArrayListにおみくじに表示する文字を格納
        for(int i=0; i<NUM; i++){
            result.add(testStrings[i]);
        }

        //ArrayListの並び順をシャッフル
        Collections.shuffle(result);

    }


    @Override
    protected void onDraw(Canvas canvas) {

        // 背景
        canvas.drawColor(backColor);

        //描画範囲を指定
        if(init == 0) {
            // Canvas 中心点
            if(xc == 0.0f) xc = canvas.getWidth() / 2;
            if(yc == 0.0f) yc = canvas.getHeight() / 2;

            // 画面の中心から横幅に合わせた正方形を作る
            if(rectF == null) rectF = new RectF(0.0f + 20, yc - xc + 20, canvas.getWidth() - 20, yc + xc - 20);

            init = 1;
        }


        //パネルの描画
        for (int i = 0; i < NUM; i++) {

            /*

            paint.setColor(colors[i]);
            canvas.drawArc(rectF, (i * angle) + pos, angle, true, paint);

             */

            //円弧の線の処理
            //枠線の色
            paint.setColor(rouletteStrole);
            //枠線の幅
            paint.setStrokeWidth(5);
            //Paintクラスのスタイル
            paint.setStyle(Paint.Style.STROKE);
            //ふちを滑らかにする処理
            paint.setAntiAlias(true);
            canvas.drawArc(rectF, (i * angle), angle, true, paint);

            //円弧の塗りつぶしの処理
            paint.setColor(colors[i]);
            paint.setStyle(Paint.Style.FILL);
            //ふちを滑らかにする処理
            paint.setAntiAlias(true);
            canvas.drawArc(rectF, (i * angle), angle, true, paint);

        }

        //テキストの描画
        for (int j = 0; j < NUM; j++) {
            int intTextAngle;
            if(j == 0) {

                /*

                intTextAngle = angle / 2 + pos;

                 */

                intTextAngle = angle / 2;

            } else{
                intTextAngle = angle;
            }
            canvas.rotate(intTextAngle, xc, yc);

            //ふちを滑らかにする処理
            textPaint.setAntiAlias(true);

            canvas.drawText(result.get(j), xc + 100, yc + 20, textPaint);


        }
    }



    /** アニメーションを実行するためのセッター */

    /*

    public void addPos(int pos){
        this.pos += pos;
    }

     */

    public void setColors(int num){

        for(int i=0; i<NUM; i++){

            if(i == num){
                colors[i] =  selectedRoulette;
                position = num;
            }else{
                colors[i] = nonSelectedRoulette;
            }

        }

    }


    public String getResultOmikuji(){
        return result.get(position);
    }




}
