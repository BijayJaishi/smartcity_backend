package com.bijay.smartcity_backend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class homepage extends AppCompatActivity {


    ImageView mhome;
    ImageView muser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        mhome=findViewById(R.id.catehome);
        muser=findViewById(R.id.profile);

        mhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(homepage.this,categories.class);
                startActivity(i);
            }
        });

        muser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j=new Intent(homepage.this,teat.class);
                startActivity(j);
            }
        });


    }
}
