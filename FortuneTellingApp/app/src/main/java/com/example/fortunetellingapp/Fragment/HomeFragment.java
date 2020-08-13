package com.example.fortunetellingapp.Fragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fortunetellingapp.R;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private Button myFortuneButton;
    private Button compatibilityButton;
    private Button omikujiButton;
    private View rootView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.activity_home_fragment, container, false);
        myFortuneButton = (Button) rootView.findViewById(R.id.myFortune);
        compatibilityButton = (Button) rootView.findViewById(R.id.compatibility);
        omikujiButton = (Button) rootView.findViewById(R.id.omikuji);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        myFortuneButton.setOnClickListener(this);
        compatibilityButton.setOnClickListener(this);
        omikujiButton.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.myFortune) {
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.content, new MyFortuneFragment());
            transaction.commit();

        }else if(view.getId() == R.id.compatibility) {
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.content, new CompatibilityFragment());
            transaction.commit();
        }else if(view.getId() == R.id.omikuji){
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.content, new OmikujiFragment());
            transaction.commit();
        }
    }
}