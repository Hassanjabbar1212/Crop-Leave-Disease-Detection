package com.example.cropdiseasedetection;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_PICK = 1;

    private ImageView imageView;
    private Button galleryButton;
    private Button submitButton;

    private String apiUrl = "http://10.112.39.85:5001/detection";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.scanimgview);
        galleryButton = findViewById(R.id.gallerybtn);
        submitButton = findViewById(R.id.submitbtn);

        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_IMAGE_PICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK && data != null) {
            // Set the selected image to the ImageView
            imageView.setImageURI(data.getData());
        }
    }

    private void uploadImage() {
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

        // Convert the bitmap to byte array
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        final byte[] imageBytes = byteArrayOutputStream.toByteArray();

        StringRequest request = new StringRequest(
                Request.Method.POST,
                apiUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // Parse the response as integer
                            int result = Integer.parseInt(response);

                            // Handle the result and redirect to different activities based on the value
                            if (result == 0) {
                                Intent intent = new Intent(MainActivity.this, Navigationbar.class);
                                startActivity(intent);
                            } else if (result == 1) {
                                Intent intent = new Intent(MainActivity.this, Response.class);
                                startActivity(intent);
                            } else {
                                // Handle other cases or display a toast message
                                Toast.makeText(MainActivity.this, "Unknown result", Toast.LENGTH_SHORT).show();
                            }
                        } catch (NumberFormatException e) {
                            Log.e("API Response", "Error parsing response: " + e.getMessage());
                            Toast.makeText(MainActivity.this, "Error parsing response", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("API Request", "Error: " + error.getMessage());
                        Toast.makeText(MainActivity.this, "Error occurred", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Set the parameters for the POST request if needed
                Map<String, String> params = new HashMap<>();
                params.put("paramName", "paramValue");
                // Add more parameters if needed
                return params;
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                // Set the image data as the body of the POST request
                return imageBytes;
            }

            @Override
            public String getBodyContentType() {
                // Set the content type as "image/jpeg"
                return "image/jpeg";
            }
        };

        // Add the request to the request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }
}
