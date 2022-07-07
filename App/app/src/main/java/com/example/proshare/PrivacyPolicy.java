package com.example.proshare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PrivacyPolicy extends AppCompatActivity {

    TextView privacypolicy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        privacypolicy = findViewById(R.id.privacypolicytext);
        privacypolicy.setText("ProShare has created this privacy statement to demonstrate our firm’s commitment to privacy. We understand you trust us with your personal information hence this site has security measures in place to protect the loss, misuse, alteration of information provided. ProShare is committed to providing privacy to our visitors. This policy is to bring before you the information that we collect, why we collect it and what we do with it; further ensuring that ProShare is compliant with the General Data Protection Regulation (GDPR) standards.\n" +
                "\n" +
                "To give relevant content based on your preferences, we may need to ask your personal information:\n" +
                "\n" +
                "Full name\n" +
                "Email ID\n" +
                "Telephone number\n" +
                "Country\n" +
                "Digital identification viz. Internet Service Provider (ISP), Internet Protocol (IP) address, browser type, OS, device details\n" +
                "Metadata viz. location, cookies, time spent on pages, the information being surfed, access timing details etc.\n" +
                "In case you do not provide your personal information, we may not be able to serve your request. Please be rest assured that ProShare does not utilize personal information to take automatic decisions");
    }
}