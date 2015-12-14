package com.example.cj.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by CJ on 2015-12-10.
 */
public class FinalActivity extends AppCompatActivity implements View.OnClickListener {
    Button exit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        Intent intent = getIntent();
        int Stage = intent.getExtras().getInt("stage");

        TextView textview = (TextView)findViewById(R.id.text);
        textview.setText(" " + Stage);

        exit_btn = (Button)findViewById(R.id.exit_btn);
        exit_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        finish();
        System.exit(0);
    }
}
