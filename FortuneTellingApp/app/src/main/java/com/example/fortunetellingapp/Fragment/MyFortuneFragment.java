package com.example.fortunetellingapp.Fragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fortunetellingapp.MyException.InputException;
import com.example.fortunetellingapp.R;

public class MyFortuneFragment extends Fragment implements View.OnClickListener {


    private View rootView;
    private Button resultButton;
    private EditText birthEdit;
    private TextView errorText;

    Resources res;
    int errorTextColor;
    int mainTextColor;
    int mainBackgorundColor;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initColor();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.activity_my_fortune_fragment, container, false);
        resultButton = (Button) rootView.findViewById(R.id.resultButton);
        birthEdit = (EditText) rootView.findViewById(R.id.birthEdit);
        errorText = (TextView) rootView.findViewById(R.id.errorText);
        errorText.setTextColor(mainBackgorundColor);

        return rootView;

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        resultButton.setOnClickListener(this);
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

            try{

                int birth = Integer.parseInt(birthday);

                if(inputText != 8){
                    throw new InputException("入力数が違います");
                }

                if(birthday == null){
                    errorText.setTextColor(errorTextColor);
                    errorText.setText(getString(R.string.noNumberException));
                }else {
                    errorText.setTextColor(mainBackgorundColor);
                }

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

            }catch(NumberFormatException e){
                errorText.setTextColor(errorTextColor);
                errorText.setText(getString(R.string.numberFormatException));

            }catch(InputException e){
                errorText.setTextColor(errorTextColor);
                errorText.setText(getString(R.string.inputException));

            }

        }

    }
}