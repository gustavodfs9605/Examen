package com.example.examen_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class AdaptadorProducto extends BaseAdapter {

    private ArrayList<Producto> productos;
    private Context context;

    private TextView txt_v_marca, txt_v_precio, txt_v_descuento, txt_v_tipo;

    public AdaptadorProducto(Context context, ArrayList<Producto> productos) {
        this.context = context;
        this.productos = productos;
    }

    @Override
    public int getCount() {
        return productos.size();
    }

    @Override
    public Object getItem(int position) {
        return productos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if(view == null)
            view = LayoutInflater.from(context).inflate(R.layout.producto_item,parent, false);
        Producto producto = (Producto) getItem(position);

        txt_v_marca    = (TextView) view.findViewById(R.id.txt_v_marca);
        txt_v_descuento   = (TextView) view.findViewById(R.id.txt_v_descuento);
        txt_v_precio      = (TextView) view.findViewById(R.id.txt_v_precio);
        txt_v_tipo        = (TextView) view.findViewById(R.id.txt_v_tipo);

        txt_v_marca.setText(producto.getMarca());
        txt_v_precio.setText(""+producto.getPrecio());
        txt_v_descuento.setText(""+producto.getDescuento());
        txt_v_tipo.setText(producto.getTipo());

        return view;
    }
}
