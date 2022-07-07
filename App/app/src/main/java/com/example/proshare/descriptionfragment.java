package com.example.proshare;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class descriptionfragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    String Title, Category, ImageUrl, Description;

    public descriptionfragment(String Title, String Category, String ImageUrl, String Description){
        this.Title = Title;
        this.Category = Category;
        this.ImageUrl = ImageUrl;
        this.Description = Description;
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

        View view = inflater.inflate(R.layout.fragment_descriptionfragment, container, false);

        ImageView icondescription;
        TextView titledescription, categorydescription, descriptiontext;

        icondescription = view.findViewById(R.id.icondescription);
        titledescription = view.findViewById(R.id.titledescription);
        categorydescription = view.findViewById(R.id.categorydescription);
        descriptiontext = view.findViewById(R.id.descriptiontext);

        titledescription.setText(Title);
        categorydescription.setText(Category);
        descriptiontext.setText(Description);
        Glide.with(getContext()).load(ImageUrl).into(icondescription);
        return view;
    }

    public void onBackPressed(){
        AppCompatActivity activity = (AppCompatActivity) getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.body_container,new homefragment()).addToBackStack(null).commit();
    }
}