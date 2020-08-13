package com.example.fortunetellingapp.Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.proto.ProtoOutputStream;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.fortunetellingapp.AccountActivity;
import com.example.fortunetellingapp.MenuActivity;
import com.example.fortunetellingapp.MyInterface.CameraInterface;
import com.example.fortunetellingapp.R;

import static android.app.Activity.RESULT_OK;

public class AccountFragment extends Fragment implements View.OnClickListener {

    private View rootView;
    private ImageView userImg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.activity_account_fragment, container, false);

        userImg = (ImageView) rootView.findViewById(R.id.userImg);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        userImg.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(getActivity(), AccountActivity.class);
        startActivity(intent);

    }

}