package com.example.fortunetellingapp.Fragment;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.fortunetellingapp.MyException.InputException;
import com.example.fortunetellingapp.R;

public class MyFortuneFragment extends Fragment implements View.OnClickListener, View.OnTouchListener {


    private View rootView;
    private Button resultButton;
    private Button backButton;
    private EditText birthEdit;
    private TextView errorText;

    private Resources res;
    private int errorTextColor;
    private int mainTextColor;
    private int mainBackgorundColor;

    private ScrollView scrolView;
    //キーボードの制御をする変数
    private InputMethodManager inputMethodManager;

    ConstraintLayout constraintLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initColor();

        //入力イベントを制御するもの
        inputMethodManager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.activity_my_fortune_fragment, container, false);
        resultButton = (Button) rootView.findViewById(R.id.resultButton);
        backButton = (Button) rootView.findViewById(R.id.backButton);
        birthEdit = (EditText) rootView.findViewById(R.id.birthEdit);
        errorText = (TextView) rootView.findViewById(R.id.errorText);
        errorText.setTextColor(mainBackgorundColor);
        scrolView = (ScrollView) rootView.findViewById(R.id.scrollView);

        constraintLayout = rootView.findViewById(R.id.main_constraint);

        return rootView;

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        resultButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
        scrolView.setOnTouchListener(this);
    }


    /** Textの色の定義をするメソッド */

    private void initColor(){

        //Resourcesクラスでは、リソースIDを引数に指定すると対応するリソースを取得できるメソッドがいくつか用意されてる
        res = getResources();
        errorTextColor = res.getColor(R.color.errorTextColor);
        mainTextColor = res.getColor(R.color.mainTextColor);
        mainBackgorundColor = res.getColor(R.color.mainBackgroundColor);

    }


    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.resultButton) {

            String birthday = null;
            birthday = birthEdit.getText().toString();
            int inputText = birthday.length();

            if(inputText == 0){
                errorText.setTextColor(errorTextColor);
                errorText.setText(getString(R.string.noInputException));

            }else {

                try {

                    if (inputText != 8) {
                        throw new InputException("入力数が違います");
                    }

                    errorText.setTextColor(mainBackgorundColor);

                    // データを渡す為のBundleを生成し、渡すデータを内包させる
                    Bundle bundle = new Bundle();
                    bundle.putString("birthday", birthday);

                    // Fragmentを生成し、setArgumentsで先ほどのbundleをセットする
                    Fragment fragment = new ResultFragment();
                    fragment.setArguments(bundle);

                    // FragmentをFragmentManagerにセットする
                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.content, fragment);
                    transaction.commit();

                } catch (NumberFormatException e) {
                    errorText.setTextColor(errorTextColor);
                    errorText.setText(getString(R.string.numberFormatException));

                } catch (InputException e) {
                    errorText.setTextColor(errorTextColor);
                    errorText.setText(getString(R.string.inputException));

                }
            }

        }else if(view.getId() == R.id.backButton){

            Fragment fragment = new HomeFragment();

            // FragmentをFragmentManagerにセットする
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.content, fragment);
            transaction.commit();

        }

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if(view.getId() == R.id.scrollView){

            //キーボードを隠す
            inputMethodManager.hideSoftInputFromWindow(constraintLayout.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            //背景にフォーカスを移す
            constraintLayout.requestFocus();

        }
        return false;
    }
}