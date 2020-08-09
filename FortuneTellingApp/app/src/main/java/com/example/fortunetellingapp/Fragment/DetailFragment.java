package com.example.fortunetellingapp.Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fortunetellingapp.R;

public class DetailFragment extends Fragment {


    private View rootView;
    private String soulNumber;
    private TextView detailTitle;
    private TextView character;
    private TextView detailCharacter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //遷移前の画面で入力した生年月日を受け取る
        Bundle bundle = getArguments();
        soulNumber= bundle.getString("soulNumber");

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.activity_detail_fragment, container, false);
        detailTitle = (TextView) rootView.findViewById(R.id.detailTitle);
        character = (TextView) rootView.findViewById(R.id.character);
        detailCharacter = (TextView) rootView.findViewById(R.id.detailCharacter);

        return rootView;

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        detailTitle.setText("ソウルナンバーが" + soulNumber + "なあなたは");

        setCharacter(soulNumber);
        setDetailCharacter(soulNumber);
    }

    private void setCharacter(String soulNumber){

        switch (soulNumber){

            case "1":
                character.setText(getString(R.string.character1));
                break;
            case "2":
                character.setText(getString(R.string.character2));
                break;
            case "3":
                character.setText(getString(R.string.character3));
                break;
            case "4":
                character.setText(getString(R.string.character4));
                break;
            case "5":
                character.setText(getString(R.string.character5));
                break;
            case "6":
                character.setText(getString(R.string.character6));
                break;
            case "7":
                character.setText(getString(R.string.character7));
                break;
            case "8":
                character.setText(getString(R.string.character8));
                break;
            case "9":
                character.setText(getString(R.string.character9));
                break;
            default:
                break;

        }
    }

    private void setDetailCharacter(String soulNumber){

        switch (soulNumber){

            case "1":
                detailCharacter.setText(getString(R.string.detailcharacter1));
                break;
            case "2":
                detailCharacter.setText(getString(R.string.detailcharacter2));
                break;
            case "3":
                detailCharacter.setText(getString(R.string.detailcharacter3));
                break;
            case "4":
                detailCharacter.setText(getString(R.string.detailcharacter4));
                break;
            case "5":
                detailCharacter.setText(getString(R.string.detailcharacter5));
                break;
            case "6":
                detailCharacter.setText(getString(R.string.detailcharacter6));
                break;
            case "7":
                detailCharacter.setText(getString(R.string.detailcharacter7));
                break;
            case "8":
                detailCharacter.setText(getString(R.string.detailcharacter8));
                break;
            case "9":
                detailCharacter.setText(getString(R.string.detailcharacter9));
                break;
            default:
                break;

        }
    }
}