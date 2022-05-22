package com.mahesh_mali_mj.pesticideassistantapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NativeActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.mahesh_mali_mj.pesticideassistantapp.ml.Model;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;

public class MainActivity2 extends AppCompatActivity {


    SurfaceView surfaceView;

    MediaPlayer mediaPlayer;
    Button button2;

    Bitmap img;

    public static final String EXTRA_NAME1   = "com.mahesh_mali_mj.pesticideassistantapp.extra.NAME1";
    String pesti_class = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        button2 = findViewById(R.id.button2);


        surfaceView = findViewById(R.id.surfaceView);
        mediaPlayer = MediaPlayer.create(this,R.raw.processing);

        surfaceView.setKeepScreenOn(true);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
                mediaPlayer.setDisplay(surfaceHolder);
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

            }
        });
        
        Intent intent = getIntent();
        String Out_name = intent.getStringExtra(MainActivity.EXTRA_NAME);
        pesti_class = Out_name;
        //Toast.makeText(this, "Path : "+Out_name, Toast.LENGTH_SHORT).show();



        //textView7.setText(Out_name);
       // Toast.makeText(this, "Processing Done. Click NEXT ", Toast.LENGTH_SHORT).show();
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity2.this,MainActivity3.class);
                intent1.putExtra(EXTRA_NAME1,pesti_class);
                startActivity(intent1);
            }
        });


    }
}