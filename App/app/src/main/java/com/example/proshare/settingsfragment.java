package com.example.proshare;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class settingsfragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public settingsfragment() {
        // Required empty public constructor
    }


    public static settingsfragment newInstance(String param1, String param2) {
        settingsfragment fragment = new settingsfragment();
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
        CardView privacypolicy, communityguidelines, help;
        View view = inflater.inflate(R.layout.fragment_settingsfragment, container, false);

        privacypolicy = (CardView) view.findViewById(R.id.Privacypolicy);
        communityguidelines = (CardView) view.findViewById(R.id.communityguidelines);
        help = (CardView) view.findViewById(R.id.help);

        privacypolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PrivacyPolicy.class);
                startActivity(intent);
            }
        });

        communityguidelines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CommunityGuidelines.class);
                startActivity(intent);
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Help.class);
                startActivity(intent);
            }
        });

        return view;
    }
}