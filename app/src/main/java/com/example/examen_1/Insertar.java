package com.example.examen_1;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class Insertar extends AppCompatActivity {

    private Spinner sp_tipos;
    private Button btn_guardar, btn_act_main;
    private EditText ed_txt_precio, ed_txt_descuento, ed_txt_marca;
    private TextView txt_v_contador;
    private ArrayList<Producto> productos;
    private ConfiguracionDeBotones configuracionDeBotones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);
        configRecursos();
        obteneDatos();
        configBtn();
    }

    public void obteneDatos() {
        if( getIntent().getSerializableExtra("PRODUCTOS") != null ) {
            productos = (ArrayList<Producto>) getIntent().getSerializableExtra("PRODUCTOS");
            txt_v_contador.setText(""+productos.size());
        } else {
            productos = new ArrayList<>();
        }
    }

    public void configRecursos() {
        ed_txt_marca     = (EditText) findViewById(R.id.ed_txt_marca);
        ed_txt_precio    = (EditText) findViewById(R.id.ed_txt_precio);
        ed_txt_descuento = (EditText) findViewById(R.id.ed_txt_descuento);
        txt_v_contador   = (TextView) findViewById(R.id.txt_v_contador);
        configSpinner();
    }

    public void configBtn() {
        configuracionDeBotones = new ConfiguracionDeBotones();
        btn_guardar = (Button) findViewById(R.id.btn_guardar);
        btn_act_main = (Button) findViewById(R.id.btn_act_main);
        btn_guardar.setOnClickListener(configuracionDeBotones);
        btn_act_main.setOnClickListener(configuracionDeBotones);
    }

    public void configSpinner() {
        sp_tipos = (Spinner) findViewById(R.id.sp_tipos);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.list_tipos, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_tipos.setAdapter(adapter);
    }

    class ConfiguracionDeBotones implements View.OnClickListener {
        Intent intent;
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_act_main:
                    regresar(view);
                    break;
                case R.id.btn_guardar:
                    guardar(view);
                    break;
            }
        }

        public void guardar( View view ){
            Producto producto = new Producto();
            producto.setMarca(ed_txt_marca.getText().toString());
            producto.setPrecio(Float.parseFloat(ed_txt_precio.getText().toString()));
            producto.setDescuento(Float.parseFloat(ed_txt_descuento.getText().toString()));
            producto.setTipo(sp_tipos.getSelectedItem().toString());
            productos.add(producto);
            txt_v_contador.setText(""+productos.size());
            ed_txt_marca.setText("");
            ed_txt_descuento.setText("");
            ed_txt_precio.setText("");
            Toast.makeText(view.getContext(),"Producto agregado", Toast.LENGTH_SHORT).show();
        }

        public void regresar(View v) {
            intent = new Intent(v.getContext(), MainActivity.class);
            intent.putExtra("PRODUCTOS", productos);
            startActivity(intent);
        }
    }
}