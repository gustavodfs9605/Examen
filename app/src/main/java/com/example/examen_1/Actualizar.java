package com.example.examen_1;

import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class Actualizar extends AppCompatActivity {

    private ArrayList<Producto> productos;
    private Button btn_buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);
        configBtn();
    }

    public void configBtn() {
        ConfiguracionDeBotones configuracionDeBotones = new ConfiguracionDeBotones();
        btn_buscar = (Button) findViewById(R.id.btn_buscar);
        btn_buscar.setOnClickListener(configuracionDeBotones);
    }

    public void obteneDatos() {
        if( getIntent().getSerializableExtra("PRODUCTOS") != null ) {
            productos = (ArrayList<Producto>) getIntent().getSerializableExtra("PRODUCTOS");
        } else {
            productos = new ArrayList<>();
        }
    }

    class ConfiguracionDeBotones implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_buscar:
                    break;

            }
        }
    }
}