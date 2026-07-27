package com.example.classification;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.classification.ml.Model;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;


public class detection extends AppCompatActivity {


    ImageView camera, gallery;
    ImageView imageView;
    TextView result,textdata;
    int imageSize = 32;
    String MY_PREFS_NAME = "MyPrefsFile";
    String address , weather;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detection);
        gallery = findViewById(R.id.gallery);

        result = findViewById(R.id.result);
        textdata = findViewById(R.id.data);


        imageView = findViewById(R.id.imageView);

        camera = findViewById(R.id.camera);


        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if(checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                        Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraintent,3);

                    }else
                    {
                        requestPermissions(new String[]{android.Manifest.permission.CAMERA},100);

                    }
                }
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(getApplicationContext(),links.class);
               startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 3) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            int dimension = Math.min(image.getWidth(), image.getHeight());
            image = ThumbnailUtils.extractThumbnail(image, dimension, dimension);
            imageView.setImageBitmap(image);

            image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false);
            classifyImage(image);
        } else {
            Uri dat = data.getData();
            Bitmap image = null;
            try {
                image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), dat);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageView.setImageBitmap(image);

            image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false);
            classifyImage(image);
        }
    }

    private void classifyImage(Bitmap image) {


        try {
            Model model = Model.newInstance(getApplicationContext());

            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 32, 32, 3}, DataType.FLOAT32);


            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3);
            byteBuffer.order(ByteOrder.nativeOrder());

            int[] intValues = new int[imageSize * imageSize];
            image.getPixels(intValues, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());
            int pixel = 0;
            //iterate over each pixel and extract R, G, and B values. Add those values individually to the byte buffer.
            for (int i = 0; i < imageSize; i++) {
                for (int j = 0; j < imageSize; j++) {
                    int val = intValues[pixel++]; // RGB
                    byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 1));
                    byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 1));
                    byteBuffer.putFloat((val & 0xFF) * (1.f / 1));
                }
            }

            inputFeature0.loadBuffer(byteBuffer);

            // Runs model inference and gets result.
            Model.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

            float[] confidences = outputFeature0.getFloatArray();
            // find the index of the class with the biggest confidence.
            int maxPos = 0;
            float maxConfidence = 0;
            for (int i = 0; i < confidences.length; i++) {
                if (confidences[i] > maxConfidence) {
                    maxConfidence = confidences[i];
                    maxPos = i;
                }
            }
           String[] classes = {"Aphids – मावा","Army worm - लष्करी अळी","Bacterial Blight - जिवाणूजन्य करपा","Healthy – निरोगी","Powdery Mildew - पावडरी बुरशी","Target spot - लक्ष्य स्थान"};
            result.setText(classes[maxPos]);

            if(classes[maxPos] == "Aphids – मावा")
            {

                textdata.setText("मावा- Use neem oil spray (निंबोळी अर्क फवारणी करा) \n" +
                        "- Avoid excess nitrogen (नायट्रोजन खताचा अति वापर टाळा) \n" +
                        "- Promote natural predators like ladybugs (प्राकृतिक शत्रूंना प्रोत्साहन द्या)");
            }else if(classes[maxPos] == "Army worm - लष्करी अळी")
            {
                textdata.setText("लष्करी अळी- Regular crop monitoring (पिकांचे नियमित निरीक्षण) \n" +
                        "- Light traps to catch moths (प्रकाश सापळ्यांचा वापर) \n" +
                        "- Deep ploughing after harvest (शेती नांगरून अळी नष्ट करा)");
            }else if(classes[maxPos] == "Bacterial Blight - जिवाणूजन्य करपा"){
                textdata.setText("जिवाणूजन्य करपा- Use resistant varieties (प्रतिरोधक वाण वापरा) \n" +
                        "- Avoid overhead irrigation (वरून पाणी देणे टाळा) \n" +
                        "- Remove and destroy infected plants (संसर्गित झाडे नष्ट करा)");
            }else if(classes[maxPos] == "Powdery Mildew - पावडरी बुरशी")
            {
                textdata.setText("पावडरी बुरशी- Ensure good air circulation (हवा खेळती राहील याची काळजी घ्या) \n" +
                        "- Avoid waterlogging (पाण्याचा साठा टाळा) \n" +
                        "- Use sulphur-based fungicides (गंधक आधारित बुरशीनाशके वापरा)");
            }
            // Releases model resources if no longer used.
            model.close();
        } catch (IOException e) {
            // TODO Handle the exception
        }
    }
}