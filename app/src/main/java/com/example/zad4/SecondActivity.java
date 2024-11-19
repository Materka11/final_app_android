package com.example.zad4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    int i = 0;
    int index = 0;
    int forwardClickCount = 0;
    int backwardClickCount = 0;
    int rgb2gClickCount = 0;
    int[] Zdj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        Zdj = getIntent().getIntArrayExtra("imageResources");
        int selectedPosition = getIntent().getIntExtra("selectedPosition", 0);
        i = selectedPosition;

        Button back = (Button) findViewById(R.id.button);
        Button rgb2g = (Button) findViewById(R.id.button2);
        Button forward = (Button) findViewById(R.id.button3);
        Button quit = (Button) findViewById(R.id.button4);

        updateImage();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i==0)
                    i=Zdj.length - 1;
                else
                    --i;
                backwardClickCount++;
                updateImage();
            }
        });

        rgb2g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rgb2gClickCount++;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        index = i;
                        Bitmap foto = BitmapFactory.decodeResource(getResources(), Zdj[Math.abs(index%Zdj.length)]);
                        Bitmap foto1 = Bitmap.createScaledBitmap(foto, 640, 480, false);
                        int maxHeight = foto1.getHeight();
                        int maxWidth = foto1.getWidth();

                        for (int j=0; j<5; j++) {
                            for (int x=0; x<maxWidth; x++) {
                                for (int y=0; y<maxHeight; y++){
                                    int pixel = foto1.getPixel(x, y);
                                    int A = Color.alpha(pixel);
                                    int R = Color.red(pixel);
                                    int G = Color.green(pixel);
                                    int B = Color.blue(pixel);

                                    R=G=B= (int)(0.299*R+0.587*G+0.114*B);

                                    foto1.setPixel(x,y,Color.argb(A, R, G, B));
                                }
                            }
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ImageView okno1 = (ImageView) findViewById(R.id.imageView);
                                okno1.setImageBitmap(foto1);
                            }
                        });

                    }
                }).start();
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i==Zdj.length - 1)
                    i=0;
                else
                    ++i;
                forwardClickCount++;
                updateImage();
            }
        });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = "Przód: " + forwardClickCount + " razy\n" +
                        "Tył: " + backwardClickCount + " razy\n" +
                        "RGB2G: " + rgb2gClickCount + " razy";
                Toast.makeText(SecondActivity.this, message, Toast.LENGTH_LONG).show();

                finish();
            }
        });

    }

    private void updateImage() {
        Bitmap foto = BitmapFactory.decodeResource(getResources(), Zdj[Math.abs(i % Zdj.length)]);
        Bitmap foto1 = Bitmap.createScaledBitmap(foto, 640, 480, false);
        ImageView okno = findViewById(R.id.imageView);
        okno.setImageBitmap(foto1);
    }
}
