package com.example.examen_1;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class Eliminar extends AppCompatActivity {

    private ArrayList<Producto> productos;
    private int indice = -1;

    private Button btn_buscar,btn_act_main, btn_eliminar;
    private LinearLayout formulario;
    private EditText ed_txt_marca,ed_txt_serie,ed_txt_precio,ed_txt_descuento, ed_txt_serieEd;
    private Spinner sp_tipos;
    private ArrayAdapter<CharSequence> adapter;
    private TextView txt_v_contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);
        configRecursos();
        obteneDatos();
    }

    public void configRecursos() {
        ed_txt_serie = (EditText) findViewById(R.id.ed_txt_serie);
        configBtn();
        configForm();
        configSpinner();
    }

    public void configSpinner() {
        sp_tipos = (Spinner) findViewById(R.id.sp_tipos);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.list_tipos, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_tipos.setAdapter(adapter);
        sp_tipos.setEnabled(false);
        sp_tipos.setFocusable(false);
    }

    public void configForm() {
        formulario = (LinearLayout) findViewById(R.id.formulario);
        ed_txt_marca     = (EditText) findViewById(R.id.ed_txt_marca);
        ed_txt_serie     = (EditText) findViewById(R.id.ed_txt_serie);
        ed_txt_precio    = (EditText) findViewById(R.id.ed_txt_precio);
        ed_txt_descuento = (EditText) findViewById(R.id.ed_txt_descuento);
        txt_v_contador   = (TextView) findViewById(R.id.txt_v_contador);
        ed_txt_serieEd   = (EditText) findViewById(R.id.ed_txt_serieEd);


        ed_txt_marca.setEnabled(false);
        ed_txt_marca.setFocusable(false);
        ed_txt_precio.setEnabled(false);
        ed_txt_precio.setFocusable(false);
        ed_txt_descuento.setEnabled(false);
        ed_txt_descuento.setFocusable(false);
        ed_txt_serieEd.setEnabled(false);
        ed_txt_serieEd.setFocusable(false);


        formulario.setVisibility(View.INVISIBLE);
    }

    public void configBtn() {
        ConfiguracionDeBotones configuracionDeBotones = new ConfiguracionDeBotones();
        btn_buscar      = (Button) findViewById(R.id.btn_buscar);
        btn_act_main    = (Button) findViewById(R.id.btn_act_main);
        btn_eliminar    = (Button) findViewById(R.id.btn_eliminar);
        btn_buscar.setOnClickListener(configuracionDeBotones);
        btn_act_main.setOnClickListener(configuracionDeBotones);
        btn_eliminar.setOnClickListener(configuracionDeBotones);
    }

    public void obteneDatos() {
        if( getIntent().getSerializableExtra("PRODUCTOS") != null ) {
            productos = (ArrayList<Producto>) getIntent().getSerializableExtra("PRODUCTOS");
        } else {
            productos = new ArrayList<>();
        }
    }
    class ConfiguracionDeBotones implements View.OnClickListener {

        private Intent intent;

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_buscar:
                    obtenerProducto(v);
                    break;
                case R.id.btn_eliminar:
                    eliminar(v);
                    break;
                case R.id.btn_act_main:
                    regresar(v);
                    break;

            }
        }

        public void eliminar(View v) {
            if(indice != -1 && (productos.size()>-1) ) {
                productos.remove(indice);
                btn_eliminar.setClickable(false);
                formulario.setVisibility(View.INVISIBLE);
                Toast.makeText(v.getContext(),"Producto eliminado",Toast.LENGTH_SHORT).show();
            }
        }

        public void regresar(View v) {
            intent = new Intent(v.getContext(), MainActivity.class);
            intent.putExtra("PRODUCTOS", productos);
            startActivity(intent);
        }

        public void obtenerProducto(View v) {
            if(productos.isEmpty()) {Toast.makeText(v.getContext(),"No hay productos", Toast.LENGTH_SHORT).show(); return;}
            indice = buscarProducto();
            if( indice > -1) {
                Toast.makeText(v.getContext(),"Producto encontrado!",Toast.LENGTH_SHORT).show();
                Producto p = productos.get(indice);
                ed_txt_marca.setText(p.getMarca());
                ed_txt_serieEd.setText(p.getSerie());
                ed_txt_precio.setText("" + p.getPrecio());
                ed_txt_descuento.setText(""+p.getDescuento());
                sp_tipos.setSelection(adapter.getPosition(p.getTipo()));
                btn_eliminar.setClickable(true);
                formulario.setVisibility(View.VISIBLE);
            } else {
                btn_eliminar.setClickable(false);
                formulario.setVisibility(View.INVISIBLE);
                Toast.makeText(v.getContext(),"Producto no encontrado!",Toast.LENGTH_SHORT).show();
            }
        }

        public int buscarProducto() {
            int aux = -1;
            String txt_serie = ed_txt_serie.getText().toString();
            for (int i=0; i<productos.size();i++) {
                if(txt_serie.equals(productos.get(i).getSerie())) {
                    aux = i;
                }
            }
            return aux;
        }
    }
}