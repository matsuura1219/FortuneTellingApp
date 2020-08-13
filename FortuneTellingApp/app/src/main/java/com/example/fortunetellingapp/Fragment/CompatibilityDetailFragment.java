package com.example.fortunetellingapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.fortunetellingapp.R;


public class CompatibilityDetailFragment extends Fragment {


    private View rootView;
    private String mySoulNumber = null;
    private String otherSoulNumber = null;

    private TextView myDetailTitle;
    private TextView otherDetailTitle;
    private TextView myCharacter;
    private TextView myDetailCharacter;
    private TextView otherCharacter;
    private TextView otherDetailCharacter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //遷移前の画面で入力した生年月日を受け取る
        Bundle bundle = getArguments();
        mySoulNumber = bundle.getString("mySoulNumber");
        otherSoulNumber = bundle.getString("otherSoulNumber");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        rootView = inflater.inflate(R.layout.activity_compatibility_detail_fragment, container, false);

        myDetailTitle = (TextView) rootView.findViewById(R.id.myDetailTitle);
        otherDetailTitle = (TextView) rootView.findViewById(R.id.otherDetailTitle);
        myCharacter = (TextView) rootView.findViewById(R.id.myCharacter);
        otherCharacter = (TextView) rootView.findViewById(R.id.otherCharacter);
        myDetailCharacter = (TextView) rootView.findViewById(R.id.myDetailCharacter);
        otherDetailCharacter = (TextView) rootView.findViewById(R.id.otherDetailCharacter);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        myDetailTitle.setText("ソウルナンバーが" + mySoulNumber + "なあなたは");
        otherDetailTitle.setText("ソウルナンバーが" + otherSoulNumber + "な相手は");

        setCharacter(myCharacter, mySoulNumber);
        setCharacter(otherCharacter, otherSoulNumber);

        setDetailCharacter(myDetailCharacter, mySoulNumber);
        setDetailCharacter(otherDetailCharacter, otherSoulNumber);

    }

    private void setCharacter(TextView text, String soulNumber){
        switch (soulNumber){

            case "1":
                text.setText(getString(R.string.character1));
                break;
            case "2":
                text.setText(getString(R.string.character2));
                break;
            case "3":
                text.setText(getString(R.string.character3));
                break;
            case "4":
                text.setText(getString(R.string.character4));
                break;
            case "5":
                text.setText(getString(R.string.character5));
                break;
            case "6":
                text.setText(getString(R.string.character6));
                break;
            case "7":
                text.setText(getString(R.string.character7));
                break;
            case "8":
                text.setText(getString(R.string.character8));
                break;
            case "9":
                text.setText(getString(R.string.character9));
                break;
            default:
                break;

        }
    }

    private void setDetailCharacter(TextView text, String soulNumber){

        switch (soulNumber){

            case "1":
                text.setText(getString(R.string.detailcharacter1));
                break;
            case "2":
                text.setText(getString(R.string.detailcharacter2));
                break;
            case "3":
                text.setText(getString(R.string.detailcharacter3));
                break;
            case "4":
                text.setText(getString(R.string.detailcharacter4));
                break;
            case "5":
                text.setText(getString(R.string.detailcharacter5));
                break;
            case "6":
                text.setText(getString(R.string.detailcharacter6));
                break;
            case "7":
                text.setText(getString(R.string.detailcharacter7));
                break;
            case "8":
                text.setText(getString(R.string.detailcharacter8));
                break;
            case "9":
                text.setText(getString(R.string.detailcharacter9));
                break;
            default:
                break;

        }
    }
}