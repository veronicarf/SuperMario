package com.romerofernandez.supermario;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity que muestra la pantalla de presentación (Splash) al iniciar la aplicación.
 * Después de un breve retraso, redirige al usuario a la pantalla principal (MainActivity).
 */
public class SplashActivity extends AppCompatActivity {

    /**
     * Duración en milisegundos de la pantalla de presentación (3 segundos).
     */
    private static final int SPLASH_DURATION = 3000; // Duración en milisegundos (3 segundos)

    /**
     * Método que se ejecuta cuando se crea la actividad.
     * Muestra la pantalla de presentación por un tiempo determinado antes de redirigir a la pantalla principal.
     *
     * @param savedInstanceState El estado guardado de la actividad, si lo hay.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen); // Establece el layout de la pantalla de presentación

        // Usar un Handler para retrasar la navegación
        new Handler().postDelayed(() -> {
            // Redirigir al MainActivity
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent); // Inicia MainActivity
            finish(); // Finaliza SplashActivity para que no pueda volver atrás
        }, SPLASH_DURATION); // Se ejecuta después de SPLASH_DURATION (3000 ms)
    }
}
