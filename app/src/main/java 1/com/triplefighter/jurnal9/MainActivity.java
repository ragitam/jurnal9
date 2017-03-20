package com.triplefighter.jurnal9;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        TextView nomor = (TextView) findViewById(R.id.hutan);
        nomor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Hutan.class);
                startActivity(intent);
            }
        });

        TextView keluarga = (TextView) findViewById(R.id.laut);
        keluarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Laut.class);
                startActivity(intent);
            }
        });

        TextView warna = (TextView) findViewById(R.id.ternak);
        warna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Ternak.class);
                startActivity(intent);
            }
        });

        TextView ungkapan = (TextView) findViewById(R.id.langka);
        ungkapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Langka.class);
                startActivity(i);
            }
        });
    }
}
