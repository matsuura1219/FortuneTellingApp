package com.example.fortunetellingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import com.example.fortunetellingapp.Fragment.AccountFragment;
import com.example.fortunetellingapp.Fragment.HomeFragment;
import com.example.fortunetellingapp.Fragment.SNSFragment;
import com.example.fortunetellingapp.Fragment.SearchFragment;
import com.example.fortunetellingapp.MyInterface.CameraInterface;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    /** 変数の定義 */

    private BottomNavigationView navigation;
    private Fragment fragment;
    private FragmentManager fragmentManager;
    //キーボードの制御をする変数
    private InputMethodManager inputMethodManager;
    private ConstraintLayout mainLayout;
    private FrameLayout content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //xmlファイルからViewのインスタンス化処理
        initComponents();

        //UIの画面の初期化処理
        init();

        //リスナー登録
        navigation.setOnNavigationItemSelectedListener(this);
        //入力イベントを制御するもの
        inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

    }


    /** xmlからViewをインスタンス化するメソッド */

    private void initComponents(){

        navigation = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        mainLayout = (ConstraintLayout) findViewById(R.id.mainLayout);
        content = (FrameLayout) findViewById(R.id.content);

    }

    /** FrameLayoutに最初に占いフラグメントを表示させる初期化処理 */

    private void init(){

        //フラグメントマネジャーのインスタンス化
        //フラグメントマネジャーとはフラグメントを操作するもの
        fragmentManager = getSupportFragmentManager();

        //ホーム画面のフラグメントをインスタンス化
        fragment = new HomeFragment();
        //トランザクションをインスタンス化
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        //実行したいトランザクションを保存
        transaction.replace(R.id.content, fragment).commit();

    }


    /** ナビゲーションタブを押下したときに呼ばれるコールバックメソッド */

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;
            case R.id.navigation_search:
                fragment = new SearchFragment();
                break;
            case R.id.navigation_sns:
                fragment = new SNSFragment();
                break;
            case R.id.navigation_account:
                fragment = new AccountFragment();
                break;
        }

        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        //FrameLayoutにfragmentを表示させる
        transaction.replace(R.id.content, fragment).commit();
        //return文により、ここで処理終了
        return true;
    }

    /** 画面を押下したときに呼ばれるコールバックメソッド */

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //キーボードを隠す
        inputMethodManager.hideSoftInputFromWindow(mainLayout.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        //背景にフォーカスを移す
        mainLayout.requestFocus();

        //キーボードを隠す
        inputMethodManager.hideSoftInputFromWindow(content.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        //背景にフォーカスを移す
        content.requestFocus();

        return false;
    }


    /** Backキーを無効 */

    //キーイベントをハンドリングするメソッド
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction()==KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
                //バックキーが押されたとき
                case KeyEvent.KEYCODE_BACK:
                    // ダイアログ表示など特定の処理を行いたい場合はここに記述
                    // 親クラスのdispatchKeyEvent()を呼び出さずにtrueを返す
                    return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }



}