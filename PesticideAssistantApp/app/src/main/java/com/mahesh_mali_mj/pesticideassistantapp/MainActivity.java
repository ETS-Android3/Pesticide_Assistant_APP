package com.mahesh_mali_mj.pesticideassistantapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.mahesh_mali_mj.pesticideassistantapp.ml.Model;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ImageView imageView2;
    ImageView  imageView;
    TextView textView2;
    TextView textView;
    Button button;
    Uri vid;
    //private Bitmap bitmap;
    public Bitmap imageBitmap;
    String currentPhotoPath;
    private String Pesti_Class = "3";
     static final int REQUEST_VIDEO_CAPTURE = 1;
    static  final  int REQUEST_IMAGE_CAPTURE = 1;


    public static final String EXTRA_NAME   = "com.mahesh_mali_mj.pesticideassistantapp.extra.NAME";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView2 = findViewById(R.id.imageView2);
        button = findViewById(R.id.button);


        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                try {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                } catch (ActivityNotFoundException e) {
                    // display error state to the user
                }

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageBitmap = Bitmap.createScaledBitmap(imageBitmap,224,224,true);
                try {
                    Model model = Model.newInstance(getApplicationContext());

                    // Creates inputs for reference.
                    TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.UINT8);
                    TensorImage tensorImage = new TensorImage(DataType.UINT8);
                    tensorImage.load(imageBitmap);
                    ByteBuffer byteBuffer = tensorImage.getBuffer();

                    inputFeature0.loadBuffer(byteBuffer);

                    // Runs model inference and gets result.
                    Model.Outputs outputs = model.process(inputFeature0);
                    TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

                    // Releases model resources if no longer used.
                    model.close();
                    //Toast.makeText(MainActivity.this, "output : "+outputFeature0.getFloatArray()[0], Toast.LENGTH_SHORT).show();
                    if(outputFeature0.getFloatArray()[0] >= 0.7){
                        Pesti_Class = "0";
                    }
                    else if(outputFeature0.getFloatArray()[1] >= 0.7){
                        Pesti_Class = "1";
                    }
                    else if(outputFeature0.getFloatArray()[2]>=0.7){
                        Pesti_Class = "2";
                    }
                    else{
                        Pesti_Class = "3";
                    }

                } catch (IOException e) {
                    // TODO Handle the exception
                }
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra(EXTRA_NAME,Pesti_Class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       // textView = findViewById(R.id.textView);
        //imageView = findViewById(R.id.imageView);
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            //vid = data.getData(); --> original
            //Toast.makeText(this, "Image capturing done.", Toast.LENGTH_SHORT).show();
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");



        }
    }
    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

}