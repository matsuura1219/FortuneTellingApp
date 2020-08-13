package com.example.fortunetellingapp.Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fortunetellingapp.R;

public class CompatibilityResultFragment extends Fragment implements View.OnClickListener {


    private View rootView;
    private String myBirthday = null;
    private String otherBirthday = null;

    private TextView mySoul;
    private TextView otherSoul;
    private TextView compatibilityDetail;

    private int mySoulNumber = 0;
    private int otherSoulNumber = 0;

    private Button backButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //遷移前の画面で入力した生年月日を受け取る
        Bundle bundle = getArguments();
        myBirthday = bundle.getString("myBirthday");
        otherBirthday = bundle.getString("otherBirthday");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        rootView = inflater.inflate(R.layout.activity_compatibility_result_fragment, container, false);
        mySoul = (TextView) rootView.findViewById(R.id.mySoulNumber);
        otherSoul = (TextView) rootView.findViewById(R.id.otherSoulNumber);
        compatibilityDetail = (TextView) rootView.findViewById(R.id.compatibilityDetail);
        backButton = (Button) rootView.findViewById(R.id.backButton);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        compatibilityDetail.setOnClickListener(this);
        backButton.setOnClickListener(this);

        mySoulNumber = calcFortune(myBirthday);
        otherSoulNumber = calcFortune(otherBirthday);

        mySoul.setText(String.valueOf(mySoulNumber));
        otherSoul.setText(String.valueOf(otherSoulNumber));
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.compatibilityDetail) {
            // データを渡す為のBundleを生成し、渡すデータを内包させる
            Bundle bundle = new Bundle();
            bundle.putString("mySoulNumber", String.valueOf(mySoulNumber));
            bundle.putString("otherSoulNumber", String.valueOf(otherSoulNumber));

            // Fragmentを生成し、setArgumentsで先ほどのbundleをセットする
            Fragment fragment = new CompatibilityDetailFragment();
            fragment.setArguments(bundle);

            // FragmentをFragmentManagerにセットする
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.content, fragment);
            transaction.commit();

        }else if(view.getId() == R.id.backButton){

            // Fragmentを生成し、setArgumentsで先ほどのbundleをセットする
            Fragment fragment = new CompatibilityFragment();

            // FragmentをFragmentManagerにセットする
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.content, fragment);
            transaction.commit();

        }
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
}