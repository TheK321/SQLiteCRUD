package com.example.sqlitecrud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TareasArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public TareasArrayAdapter(Context context, String[] values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.tareaview, parent, false);
        TextView rowid = (TextView) rowView.findViewById(R.id.tareaview_id);
        TextView rownombre = (TextView) rowView.findViewById(R.id.tareaview_nombre);
        TextView rowdescripcion = (TextView) rowView.findViewById(R.id.tareaview_descripcion);
        TextView rowfechas = (TextView) rowView.findViewById(R.id.tareaview_fechas);
        TextView rowdificultad = (TextView) rowView.findViewById(R.id.tareaview_dificultad);
        rowid.setText(values[position]);
        // change the icon for Windows and iPhone
        String s = values[position];
        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i]);
        }
        return rowView;
    }
}