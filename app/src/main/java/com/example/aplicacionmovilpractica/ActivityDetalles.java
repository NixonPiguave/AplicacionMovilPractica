package com.example.aplicacionmovilpractica;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityDetalles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        TextView tvNombres = findViewById(R.id.tv_resultado_nombres);
        TextView tvApellidos = findViewById(R.id.tv_resultado_apellidos);
        TextView tvCorreo = findViewById(R.id.tv_resultado_correo);
        TextView tvTelefono = findViewById(R.id.tv_resultado_telefono);
        TextView tvFecha = findViewById(R.id.tv_resultado_fecha);
        TextView tvCiudad = findViewById(R.id.tv_resultado_ciudad);
        TextView tvCedula = findViewById(R.id.tv_resultado_cedula);
        TextView tvGenero = findViewById(R.id.tv_resultado_genero);
        Button btnVolver = findViewById(R.id.btn_volver);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            tvNombres.setText("Nombres: " + extras.getString("NOMBRES"));
            tvApellidos.setText("Apellidos: " + extras.getString("APELLIDOS"));
            tvCorreo.setText("Correo: " + extras.getString("CORREO"));
            tvTelefono.setText("Teléfono: " + extras.getString("TELEFONO"));
            tvFecha.setText("Fecha Nacimiento: " + extras.getString("FECHA"));
            tvCiudad.setText("Ciudad: " + extras.getString("CIUDAD"));
            tvCedula.setText("Cédula: " + extras.getString("CEDULA"));
            tvGenero.setText("Género: " + extras.getString("GENERO"));
        }

        btnVolver.setOnClickListener(v -> finish());
    }
}