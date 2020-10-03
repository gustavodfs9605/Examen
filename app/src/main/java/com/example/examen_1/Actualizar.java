package com.example.examen_1;

import android.content.Intent;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class Actualizar extends AppCompatActivity {

    private ArrayList<Producto> productos;
    private int indice = -1;

    private Button btn_buscar,btn_act_main, btn_guardar;
    private LinearLayout formulario;
    private EditText ed_txt_marca,ed_txt_serie,ed_txt_precio,ed_txt_descuento, ed_txt_serieEd;
    private Spinner sp_tipos;
    private ArrayAdapter<CharSequence> adapter;
    private TextView txt_v_contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);
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
    }

    public void configForm() {
        formulario = (LinearLayout) findViewById(R.id.formulario);
        ed_txt_marca     = (EditText) findViewById(R.id.ed_txt_marca);
        ed_txt_serie     = (EditText) findViewById(R.id.ed_txt_serie);
        ed_txt_precio    = (EditText) findViewById(R.id.ed_txt_precio);
        ed_txt_descuento = (EditText) findViewById(R.id.ed_txt_descuento);
        txt_v_contador   = (TextView) findViewById(R.id.txt_v_contador);
        ed_txt_serieEd   = (EditText) findViewById(R.id.ed_txt_serieEd);
        formulario.setVisibility(View.INVISIBLE);
    }

    public void configBtn() {
        ConfiguracionDeBotones configuracionDeBotones = new ConfiguracionDeBotones();
        btn_buscar      = (Button) findViewById(R.id.btn_buscar);
        btn_act_main    = (Button) findViewById(R.id.btn_act_main);
        btn_guardar     = (Button) findViewById(R.id.btn_guardar);
        btn_buscar.setOnClickListener(configuracionDeBotones);
        btn_act_main.setOnClickListener(configuracionDeBotones);
        btn_guardar.setOnClickListener(configuracionDeBotones);
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
                case R.id.btn_guardar:
                    guardar(v);
                    break;
                case R.id.btn_act_main:
                    regresar(v);
                    break;

            }
        }

        public boolean validarDatos(){
            return  ((ExpReg.validarString(ed_txt_marca.getText().toString())) &&
                    (ExpReg.validarString(ed_txt_serieEd.getText().toString())) &&
                    (ExpReg.validarFloat(ed_txt_precio.getText().toString())) &&
                    (ExpReg.validarFloat(ed_txt_descuento.getText().toString())))? true : false;
        }

        public void guardar(View v) {
            if(validarDatos()) {
                productos.get(indice).setMarca(ed_txt_marca.getText().toString());
                productos.get(indice).setSerie(ed_txt_serieEd.getText().toString());
                productos.get(indice).setTipo(sp_tipos.getSelectedItem().toString());
                productos.get(indice).setPrecio(Float.parseFloat(ed_txt_precio.getText().toString()));
                productos.get(indice).setDescuento(Float.parseFloat(ed_txt_descuento.getText().toString()));
                btn_guardar.setClickable(false);
                formulario.setVisibility(View.INVISIBLE);
                Toast.makeText(v.getContext(),"El producto fue modificado!",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(v.getContext(),"Datos incorrectos!",Toast.LENGTH_SHORT).show();
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
                btn_guardar.setClickable(true);
                formulario.setVisibility(View.VISIBLE);
            } else {
                btn_guardar.setClickable(false);
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