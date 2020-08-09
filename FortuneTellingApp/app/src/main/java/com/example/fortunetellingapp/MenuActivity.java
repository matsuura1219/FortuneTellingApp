package com.example.fortunetellingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.fortunetellingapp.Fragment.AccountFragment;
import com.example.fortunetellingapp.Fragment.HomeFragment;
import com.example.fortunetellingapp.Fragment.SNSFragment;
import com.example.fortunetellingapp.Fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuActivity extends AppCompatActivity{

    /** 変数の定義 */

    private BottomNavigationView navigation;
    private Fragment fragment;
    private FragmentManager fragmentManager;


    /** コールバックメソッドの処理 */

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        //ナビゲーションアイコンが選択されたときに呼び出されるメソッド
        //@NotNullとはNullチェックを自動で行ってくれる。Nullの場合、例外が発生する

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
            transaction.replace(R.id.content, fragment).commit();
            //return文により、ここで処理終了
            return true;
        }
    };

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //xmlファイルからViewのインスタンス化処理
        initComponents();

        //フラグメントマネジャーのインスタンス化
        //フラグメントマネジャーとはフラグメントを操作するもの
        fragmentManager = getSupportFragmentManager();

        //ホーム画面のフラグメントをインスタンス化
        fragment = new HomeFragment();
        //トランザクションをインスタンス化
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        //実行したいトランザクションを保存
        transaction.replace(R.id.content, fragment).commit();

        //リスナーの登録
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }


    /** xmlからViewをインスタンス化するメソッド */

    private void initComponents(){

        navigation = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

    }
}