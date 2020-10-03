package com.example.examen_1;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btn_insertar, btn_visualizar, btn_eliminar, btn_actualizar;
    private ConfiguracionDeBotones configuracionDeBotones;
    private ArrayList<Producto> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        obtenerDatos();
        configBtn();
    }

    public void obtenerDatos() {
        productos = (ArrayList<Producto>) (( getIntent().getSerializableExtra("PRODUCTOS") != null )?
                        getIntent().getSerializableExtra("PRODUCTOS"): new ArrayList<>());
    }

    public void configBtn() {
        configuracionDeBotones = new ConfiguracionDeBotones();
        btn_insertar    = (Button) findViewById(R.id.btn_insertar);
        btn_visualizar  = (Button) findViewById(R.id.btn_visualizar);
        btn_eliminar    = (Button) findViewById(R.id.btn_eliminar);
        btn_actualizar  = (Button) findViewById(R.id.btn_actualizar);

        btn_insertar.setOnClickListener(configuracionDeBotones);
        btn_visualizar.setOnClickListener(configuracionDeBotones);
        btn_eliminar.setOnClickListener(configuracionDeBotones);
        btn_actualizar.setOnClickListener(configuracionDeBotones);
    }

    class ConfiguracionDeBotones implements View.OnClickListener {
        Intent intent;
        @Override
        public void onClick(View view) {
            intent = getIntent(view);
            intent.putExtra("PRODUCTOS", productos);
            startActivity(intent);
        }

        public Intent getIntent(View v) {
            int id = v.getId();
            return (id == R.id.btn_insertar)? new Intent(v.getContext(),Insertar.class):
                    (id == R.id.btn_visualizar)? new Intent(v.getContext(),Visualizar.class):
                    (id == R.id.btn_eliminar)? new Intent(v.getContext(), Eliminar.class):
                    (id == R.id.btn_actualizar)? new Intent(v.getContext(),Actualizar.class): null;
        }
    }
}