package com.atguigu.interfacedemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView imageview;
    private String imageUrl = "http://www.atguigu.com/images/logo.gif";
    private MyButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (MyButton) findViewById(R.id.button);
        imageview = (ImageView) findViewById(R.id.imageview);

        button.setOnMyClickListener(new MyButton.OnMyClickListener() {
            @Override
            public void OnClick() {
                loadImage();
            }
        });

    }

    private void loadImage() {
        Utils.getInstance().loadImage(imageUrl, new Utils.RequestBitmap() {
            @Override
            public void onSuccess(final Bitmap bitmap) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageview.setImageBitmap(bitmap);
                    }
                });
            }

            @Override
            public void onError(final String error) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "" + error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
