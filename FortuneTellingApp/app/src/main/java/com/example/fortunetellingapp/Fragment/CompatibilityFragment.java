package com.example.fortunetellingapp.Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.fortunetellingapp.MyException.InputException;
import com.example.fortunetellingapp.R;

public class CompatibilityFragment extends Fragment implements View.OnClickListener, View.OnTouchListener {

    private View rootView;
    private EditText myEditBirth;
    private EditText otherEditBirth;
    private TextView myErrorText;
    private TextView otherErrorText;
    private Button resultButton;
    private Button backButton;
    private ScrollView scrolView;
    //キーボードの制御をする変数
    private InputMethodManager inputMethodManager;

    private Resources res;
    private int errorTextColor;
    private int mainTextColor;
    private int mainBackgorundColor;

    //例外が発生したかどうかを判別
    private boolean isNotEception = true;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //入力イベントを制御するもの
        inputMethodManager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        initColor();

        rootView = inflater.inflate(R.layout.activity_compatibility_fragment, container, false);
        myEditBirth = (EditText) rootView.findViewById(R.id.mybirthEdit);
        otherEditBirth = (EditText) rootView.findViewById(R.id.otherbirthEdit);
        myErrorText = (TextView) rootView.findViewById(R.id.myErrorText);
        otherErrorText = (TextView) rootView.findViewById(R.id.otherErrorText);
        myErrorText.setTextColor(mainBackgorundColor);
        otherErrorText.setTextColor(mainBackgorundColor);
        resultButton = (Button) rootView.findViewById(R.id.resultButton);
        backButton = (Button) rootView.findViewById(R.id.backButton);
        scrolView = (ScrollView) rootView.findViewById(R.id.scrollView);


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


    private void setText(TextView text, String birthday, String id) {

        int input = birthday.length();

        if(input == 0){
            isNotEception = false;
            text.setTextColor(errorTextColor);
            text.setText(getString(R.string.noInputException));

        }else{
            try {
                if (input != 8) {
                    throw new InputException(getString(R.string.inputNumberError));
                }
                text.setTextColor(mainBackgorundColor);

            }catch (InputException e){
                isNotEception = false;
                text.setTextColor(errorTextColor);
                text.setText(getString(R.string.inputException));
            }catch (NumberFormatException e){
                isNotEception = false;
                text.setTextColor(errorTextColor);
                text.setText(getString(R.string.numberFormatException));
            }
        }
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.resultButton) {

            String myBirthday = myEditBirth.getText().toString();
            String otherBirthday = otherEditBirth.getText().toString();

            String id1 = "myBirthday";
            String id2 = "otherBirthday";

            setText(myErrorText, myBirthday, id1);
            setText(otherErrorText, otherBirthday, id2);

            if(isNotEception == true){

                // データを渡す為のBundleを生成し、渡すデータを内包させる
                Bundle bundle = new Bundle();
                bundle.putString(id1, myBirthday);
                bundle.putString(id2, otherBirthday);

                // Fragmentを生成し、setArgumentsで先ほどのbundleをセットする
                Fragment fragment = new CompatibilityResultFragment();
                fragment.setArguments(bundle);

                // FragmentをFragmentManagerにセットする
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.content, fragment);
                transaction.commit();

            }


            /*
            if(inputMyText == 0 || inputOtherText == 0){

                errorText.setTextColor(errorTextColor);
                errorText.setText(getString(R.string.noInputException));

            }else {

                try {

                    if (inputMyText != 8 || inputOtherText != 8) {
                        throw new InputException(getString(R.string.inputNumberError));
                    }

                    errorText.setTextColor(mainBackgorundColor);


                    // データを渡す為のBundleを生成し、渡すデータを内包させる
                    Bundle bundle = new Bundle();
                    bundle.putString("myBirthday", myBirthday);
                    bundle.putString("otherBirthday", otherBirthday);

                    // Fragmentを生成し、setArgumentsで先ほどのbundleをセットする
                    Fragment fragment = new CompatibilityResultFragment();
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

             */

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
            inputMethodManager.hideSoftInputFromWindow(scrolView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            //背景にフォーカスを移す
            scrolView.requestFocus();

        }

        return false;
    }


}