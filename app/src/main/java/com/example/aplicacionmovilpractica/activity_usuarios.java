package com.example.aplicacionmovilpractica;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class activity_usuarios extends AppCompatActivity {

    private TextInputEditText etNombres, etApellidos, etCorreo, etTelefono, etFechaNacimiento, etCiudad, etCedula;
    private RadioGroup rgGenero;
    private Button btnEnviar, btnLimpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_usuarios);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar vistas
        etNombres = findViewById(R.id.et_nombres);
        etApellidos = findViewById(R.id.et_apellidos);
        etCorreo = findViewById(R.id.et_correo);
        etTelefono = findViewById(R.id.et_telefono);
        etFechaNacimiento = findViewById(R.id.et_fecha_nacimiento);
        etCiudad = findViewById(R.id.et_ciudad);
        etCedula = findViewById(R.id.et_cedula);
        rgGenero = findViewById(R.id.rg_genero);
        btnEnviar = findViewById(R.id.btn_enviar);
        btnLimpiar = findViewById(R.id.btn_limpiar);

        // Configurar selector de fecha
        etFechaNacimiento.setOnClickListener(v -> mostrarDatePickerDialog());

        // Botón Limpiar
        btnLimpiar.setOnClickListener(v -> limpiarFormulario());

        // Botón Enviar
        btnEnviar.setOnClickListener(v -> enviarDatos());
    }

    private void mostrarDatePickerDialog() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year1, monthOfYear, dayOfMonth) -> {
                    String fecha = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1;
                    etFechaNacimiento.setText(fecha);
                }, year, month, day);
        datePickerDialog.show();
    }

    private void limpiarFormulario() {
        etNombres.setText("");
        etApellidos.setText("");
        etCorreo.setText("");
        etTelefono.setText("");
        etFechaNacimiento.setText("");
        etCiudad.setText("");
        etCedula.setText("");
        rgGenero.clearCheck();
        Toast.makeText(this, "Formulario limpiado", Toast.LENGTH_SHORT).show();
    }

    private void enviarDatos() {
        String nombres = etNombres.getText().toString().trim();
        String apellidos = etApellidos.getText().toString().trim();
        String correo = etCorreo.getText().toString().trim();
        String telefono = etTelefono.getText().toString().trim();
        String fecha = etFechaNacimiento.getText().toString().trim();
        String ciudad = etCiudad.getText().toString().trim();
        String cedula = etCedula.getText().toString().trim();

        int selectedId = rgGenero.getCheckedRadioButtonId();
        String genero = "";
        if (selectedId != -1) {
            RadioButton rb = findViewById(selectedId);
            genero = rb.getText().toString();
        }

        if (nombres.isEmpty() || correo.isEmpty() || cedula.isEmpty() || genero.isEmpty()) {
            Toast.makeText(this, "Por favor complete los campos obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear Intent para mostrar los datos
        Intent intent = new Intent(this, ActivityDetalles.class);
        intent.putExtra("NOMBRES", nombres);
        intent.putExtra("APELLIDOS", apellidos);
        intent.putExtra("CORREO", correo);
        intent.putExtra("TELEFONO", telefono);
        intent.putExtra("FECHA", fecha);
        intent.putExtra("CIUDAD", ciudad);
        intent.putExtra("CEDULA", cedula);
        intent.putExtra("GENERO", genero);
        startActivity(intent);
    }
}