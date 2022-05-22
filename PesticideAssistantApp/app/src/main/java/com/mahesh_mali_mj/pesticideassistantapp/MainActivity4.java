package com.mahesh_mali_mj.pesticideassistantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity4<var, water> extends AppCompatActivity {
//    private progr = 0
    TextView textView4;
    TextView textView5 ;
    TextView textView6;
    ImageView imageView7;
    ProgressBar progressBar;
    ProgressBar progressBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        imageView7 = findViewById(R.id.imageView7);
        progressBar = findViewById(R.id.progressBar);
        progressBar2 = findViewById(R.id.progressBar2);

        progressBar.setProgress(100);
        textView5.setText("1 Litre");
        Intent intent2 = getIntent();
        String Pesticide = intent2.getStringExtra(MainActivity3.EXTRA_NAME2);
        if(Pesticide.equals("grape0")){
            textView4.setText("Crop : Grape");
            imageView7.setImageResource(R.drawable.dudex);
            progressBar2.setProgress(10);
            textView6.setText("1 milli L");

        }
        else if(Pesticide.equals("grape1")){
            textView4.setText("Crop : Grape");
            imageView7.setImageResource(R.drawable.effect);
            progressBar2.setProgress(10);
            textView6.setText("10 milli L");

        }
        else if(Pesticide.equals("grape2")){
            textView4.setText("Crop : Grape");
            imageView7.setImageResource(R.drawable.weedsuper);
            progressBar2.setProgress(10);
            textView6.setText("10 milli L");
        }
        else if(Pesticide.equals("ber0")){
            textView4.setText("Crop : Apple Ber");
            imageView7.setImageResource(R.drawable.dudex);
            progressBar2.setProgress(20);
            textView6.setText("2 milli L");
        }
        else if(Pesticide.equals("ber1")){
            textView4.setText("Crop : Apple Ber");
            imageView7.setImageResource(R.drawable.effect);
            progressBar2.setProgress(18);
            textView6.setText("15 milli L");
        }
        else{
            textView4.setText("Crop : Apple Ber");
            imageView7.setImageResource(R.drawable.weedsuper);
            progressBar2.setProgress(14);
            textView6.setText("14 milli L ");
        }



    }


}