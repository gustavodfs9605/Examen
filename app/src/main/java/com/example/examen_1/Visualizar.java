package com.example.examen_1;

import android.view.View;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class Visualizar extends AppCompatActivity {

    private ArrayList<Producto> productos;
    private ListView list_productos;
    private Button btn_act_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar);
        obteneDatos();
        configBtn();
    }

    public void obteneDatos() {
        if( getIntent().getSerializableExtra("PRODUCTOS") != null ) {
            productos = (ArrayList<Producto>) getIntent().getSerializableExtra("PRODUCTOS");
            AdaptadorProducto ad = new AdaptadorProducto(this, productos);
            list_productos = (ListView) findViewById(R.id.list_productos);
            list_productos.setAdapter(ad);
        } else {
            productos = new ArrayList<>();
        }
    }

    public void configBtn() {
        btn_act_main = (Button) findViewById(R.id.btn_act_main);
        btn_act_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class );
                intent.putExtra("PRODUCTOS", productos);
                startActivityForResult(intent,0);
            }
        });
    }
}