package com.example.proshare;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;


public class profilefragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public profilefragment() {
        // Required empty public constructor
    }


    public static profilefragment newInstance(String param1, String param2) {
        profilefragment fragment = new profilefragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profilefragment,
                container, false);

        CardView uploadproject, editproject, uploadreel, logout;
        TextView username, usermail;

        uploadproject = (CardView) view.findViewById(R.id.UploadProject);
        editproject = (CardView) view.findViewById(R.id.EditProject);
        uploadreel = (CardView) view.findViewById(R.id.Uploadreel);
        logout = (CardView) view.findViewById(R.id.LogOut);
        username = view.findViewById(R.id.CustomerName);
        usermail = view.findViewById(R.id.CustomerMail);

        uploadproject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UploadProject.class);
                startActivity(intent);
            }
        });

        editproject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditProject.class);
                startActivity(intent);
            }
        });

        uploadreel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), reelupload.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });

        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        String userid=user.getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Student");
        reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot datas: snapshot.getChildren()){
                    String firstname = datas.child("firstname").getValue().toString();
                    String lastname = datas.child("lastname").getValue().toString();
                    String email = datas.child("email").getValue().toString();
                    username.setText(firstname+" "+lastname);
                    usermail.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return view;
    }
}