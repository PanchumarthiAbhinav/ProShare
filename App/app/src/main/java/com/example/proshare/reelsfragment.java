package com.example.proshare;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.request.transition.Transition;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class reelsfragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ViewPager2 viewPager2;
    videoadapter adapter;

    private String mParam1;
    private String mParam2;

    public reelsfragment() {
        // Required empty public constructor
    }


    public static reelsfragment newInstance(String param1, String param2) {
        reelsfragment fragment = new reelsfragment();
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

        View view = inflater.inflate(R.layout.fragment_reelsfragment, container, false);
        viewPager2 = view.findViewById(R.id.viewpager);

        FirebaseRecyclerOptions<videomodel> options =
                new FirebaseRecyclerOptions.Builder<videomodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("Reels"), videomodel.class)
                        .build();

        adapter = new videoadapter(options);
        viewPager2.setAdapter(adapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}