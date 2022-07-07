package com.example.proshare;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class searchfragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    CardView androidpro, machinepro, webpro, IOSpro, IOTpro, Blockchainpro;

    private String mParam1;
    private String mParam2;

    public searchfragment() {
        // Required empty public constructor
    }


    public static searchfragment newInstance(String param1, String param2) {
        searchfragment fragment = new searchfragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_searchfragment, container, false);


        androidpro = (CardView) view.findViewById(R.id.AndroidProjects);
        webpro = (CardView) view.findViewById(R.id.WebProjects);
        machinepro = (CardView) view.findViewById(R.id.MachineLearningProjects);
        IOSpro = (CardView) view.findViewById(R.id.IOSProjects);
        IOTpro = (CardView) view.findViewById(R.id.IOTProjects);
        Blockchainpro = (CardView) view.findViewById(R.id.BlockchainProjects);

        androidpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),projectsdisplaycategory.class);
                intent.putExtra("Category", "Android App");
                startActivity(intent);
            }
        });

        webpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),projectsdisplaycategory.class);
                intent.putExtra("Category", "Web App");
                startActivity(intent);
            }
        });

        machinepro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),projectsdisplaycategory.class);
                intent.putExtra("Category", "Machine Learning");
                startActivity(intent);
            }
        });

        IOSpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),projectsdisplaycategory.class);
                intent.putExtra("Category", "IOS App");
                startActivity(intent);
            }
        });

        IOTpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),projectsdisplaycategory.class);
                intent.putExtra("Category", "IOT");
                startActivity(intent);
            }
        });

        Blockchainpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),projectsdisplaycategory.class);
                intent.putExtra("Category", "Blockchain");
                startActivity(intent);
            }
        });
        return view;
    }
}