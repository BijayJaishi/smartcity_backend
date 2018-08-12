package com.bijay.smartcity_backend;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.ToggleButton;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

public class teat extends AppCompatActivity {

   ToggleButton btn;
    ExpandableRelativeLayout expandableRelativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teat);


        btn = (ToggleButton) findViewById(R.id.radioButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableRelativeLayout = (ExpandableRelativeLayout)findViewById(R.id.btnexpand);
                expandableRelativeLayout.toggle();

            }
        });
    }
}
