package com.romerofernandez.supermario;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity que gestiona la pantalla de configuración de la aplicación.
 * Proporciona opciones para gestionar el usuario, el tema y cerrar sesión.
 */
public class SettingsActivity extends AppCompatActivity {

    /**
     * Método que se ejecuta al crear la actividad.
     * Aquí se inicializan las vistas y se configuran los listeners de los botones.
     *
     * @param savedInstanceState El estado guardado de la actividad, si lo hay.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_setting); // Crea un layout para esta actividad

        // Inicializamos los botones de configuración
        Button btnUser = findViewById(R.id.btnUser);
        Button btnTheme = findViewById(R.id.btnTheme);
        Button btnLogout = findViewById(R.id.btnLogout);

        // Configuramos el OnClickListener para el botón de usuario
        btnUser.setOnClickListener(v -> {
            // Acción al hacer clic en "Usuario"
            openUserSettings();
        });

        // Configuramos el OnClickListener para el botón de tema
        btnTheme.setOnClickListener(v -> {
            // Acción al hacer clic en "Temas"
            openThemeSettings();
        });

        // Configuramos el OnClickListener para el botón de logout
        btnLogout.setOnClickListener(v -> {
            // Acción al hacer clic en "Salir"
            logout();
        });
    }

    /**
     * Método que se ejecuta al hacer clic en el botón "Usuario".
     * Muestra un mensaje de configuración de usuario (puede abrir un fragmento o actividad para gestionar el usuario).
     */
    private void openUserSettings() {
        //  agregar un fragmento o actividad para cambiar el usuario.
        Toast.makeText(this, getString(R.string.ajustes_de_usuario), Toast.LENGTH_SHORT).show();
    }

    /**
     * Método que se ejecuta al hacer clic en el botón "Temas".
     * Muestra un mensaje de configuración de tema (puede abrir un fragmento o actividad para cambiar el tema de la aplicación).
     */
    private void openThemeSettings() {
        //cambiar el tema de la aplicación.
        Toast.makeText(this, getString(R.string.ajustes_de_tema), Toast.LENGTH_SHORT).show();
    }

    /**
     * Método que se ejecuta al hacer clic en el botón "Salir".
     * Cierra la sesión del usuario y regresa a la pantalla principal de la aplicación.
     */
    private void logout() {
        //manejar el cierre de sesión y regresar a la pantalla principal.
        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        startActivity(intent); // Volver a la pantalla principal
        finish(); // Finalizar la actividad de ajustes
    }
}
