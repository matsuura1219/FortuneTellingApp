package com.example.fortunetellingapp.Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.util.Printer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.fortunetellingapp.R;

public class ResultFragment extends Fragment implements View.OnClickListener {

    /** 変数の定義 */

    private View rootView;
    private TextView resultTitle;
    private TextView soulNumber;
    private TextView detail;
    private String birthday;

    private int soul = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //遷移前の画面で入力した生年月日を受け取る
        Bundle bundle = getArguments();
        birthday = bundle.getString("birthday");

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.activity_result_fragment, container, false);
        resultTitle = (TextView) rootView.findViewById(R.id.resultTitle);
        soulNumber = (TextView) rootView.findViewById(R.id.soulNumber);
        detail = (TextView) rootView.findViewById(R.id.detail);

        return rootView;

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        detail.setOnClickListener(this);
        soul = calcFortune(birthday);
        soulNumber.setText(String.valueOf(soul));

    }


    /** ソウルナンバーを計算するメソッド */

    private int calcFortune(String date){

      String sum = date;

      do{
          int addBirth = 0;

          String[] split = sum.split("");

          for(int i=1; i<split.length; i++){

              addBirth += Integer.parseInt(split[i]);
          }

          sum = String.valueOf(addBirth);

      }while(sum.length() != 1);


      return Integer.parseInt(sum);

    }


    @Override
    public void onClick(View view) {

        // データを渡す為のBundleを生成し、渡すデータを内包させる
        Bundle bundle = new Bundle();
        bundle.putString("soulNumber", String.valueOf(soul));

        // Fragmentを生成し、setArgumentsで先ほどのbundleをセットする
        Fragment fragment = new DetailFragment();
        fragment.setArguments(bundle);

        // FragmentをFragmentManagerにセットする
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.commit();

    }
}