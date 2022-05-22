package com.mahesh_mali_mj.pesticideassistantapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {
    TextView texView3;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    public static final String EXTRA_NAME2   = "com.mahesh_mali_mj.pesticideassistantapp.extra.NAME2";
    String pesti_choose;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        texView3 = findViewById(R.id.textView3);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);

        Intent intent1 = getIntent();

        String Out_name = intent1.getStringExtra(MainActivity2.EXTRA_NAME1);
        //texView3.setText(Out_name);
        if(Out_name.equals("0")){
            imageView3.setImageResource(R.drawable.dudex);
            texView3.setText("Name : Dudex");

        }
        else if(Out_name.equals("1")){
            imageView3.setImageResource(R.drawable.effect);
            texView3.setText("Name : Effect");
        }
        else if(Out_name.equals("2")){
            imageView3.setImageResource(R.drawable.weedsuper);
            texView3.setText("Name : Weed Super");
        }
        else{
            texView3.setText("Pesticide not found");
        }

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Out_name.equals("0")){
                    pesti_choose = "grape0";
                }
                else if(Out_name.equals("1")){
                    pesti_choose = "grape1";
                }
                else{
                    pesti_choose = "grape2";
                }
                Intent intent2 = new Intent(MainActivity3.this,MainActivity4.class);
                intent2.putExtra(EXTRA_NAME2,pesti_choose);
                startActivity(intent2);
            }
        });

        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Out_name.equals("0")){
                    pesti_choose = "ber0";
                }
                else if(Out_name.equals("1")){
                    pesti_choose = "ber1";
                }
                else{
                    pesti_choose = "ber2";
                }
                Intent intent2 = new Intent(MainActivity3.this,MainActivity4.class);
                intent2.putExtra(EXTRA_NAME2,pesti_choose);
                startActivity(intent2);
            }
        });
    }
}