package com.example.proshare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class CommunityGuidelines extends AppCompatActivity {

    TextView communityguidelines;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_guidelines);

        communityguidelines = findViewById(R.id.communityguidelinestext);

        communityguidelines.setText("The objective for the use of social media is not just to disseminate information but also to\n" +
                "undertake public engagement for a meaningful public participation for formulation of public\n" +
                "policy. Government organisations are exploring the use of social media for public\n" +
                "engagements for disseminating information, policy making, recruitment, generating\n" +
                "awareness, education etc. about public services. Therefore, Social Media may be used for:\n" +
                "• Seeking feedback from citizens\n" +
                "• Re-pronouncement of Public Policy\n" +
                "• Issue based as well as Generic interaction\n" +
                "• Brand Building or Public Relations\n" +
                "• Generating Awareness and education on National Action Plans and implementation\n" +
                "strategies\n" +
                "In the Indian context, care must be taken so that people can communicate in their own\n" +
                "language, and due cognizance of the views expressed in local languages is taken. ");
    }
}