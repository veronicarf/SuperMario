package com.romerofernandez.supermario;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Actividad secundaria que se encarga de mostrar la información detallada de un personaje,
 * como su nombre, imagen secundaria, descripción y habilidades. Recibe estos datos a través de un Intent.
 *
 * La actividad muestra un Toast con el nombre del personaje, ajusta el color de fondo según el Intent,
 * y presenta la información detallada del personaje como su nombre, imagen, descripción y habilidades.
 * Si la imagen secundaria del personaje no está disponible, se utiliza una imagen predeterminada.
 */
public class Pantalla2 extends AppCompatActivity {

    /**
     * Método que se ejecuta cuando la actividad es creada. Configura la interfaz de usuario y
     * maneja los datos recibidos a través del Intent.
     *
     * @param savedInstanceState El estado guardado de la actividad, si está disponible.
     *                           Se utiliza para restaurar la actividad en su último estado.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla2);

        // Obtener el nombre del personaje desde el Intent
        String characterName = getIntent().getStringExtra("nombre");

        // Mostrar el Toast con el nombre del personaje
        Toast.makeText(this, getString(R.string.toast) + " " + characterName, Toast.LENGTH_SHORT).show();

        // Obtener el ConstraintLayout principal
        ConstraintLayout mainLayout = findViewById(R.id.mainLayout);

        // Obtener el color de fondo del Intent (si no se proporciona, usar el color por defecto)
        // Utilizamos ContextCompat.getColor() para obtener el color del recurso
        int fondoColorRes = getIntent().getIntExtra("fondo", R.color.fondoMario);

        // Aplicar el color de fondo a la pantalla
        mainLayout.setBackgroundColor(fondoColorRes);

        // Configurar las vistas para mostrar los datos
        TextView name2 = findViewById(R.id.name2);
        ImageView imagePantalla2 = findViewById(R.id.image_pantalla2);
        TextView descripcion = findViewById(R.id.descripcion);
        TextView habilidades = findViewById(R.id.habilidades);

        // Obtener datos del Intent
        String nombre = getIntent().getStringExtra("nombre");
        String nombreImagen = nombre.toLowerCase() + "2"; // Crear el nombre de la imagen secundaria

        // Obtener la imagen secundaria correspondiente al nombre del personaje
        int fotoSecundaria = getResources().getIdentifier(nombreImagen, "drawable", getPackageName());

        // Si la imagen secundaria no existe, usar una imagen predeterminada
        if (fotoSecundaria == 0) {
            fotoSecundaria = R.drawable.mario2;  // Asegúrate de tener una imagen predeterminada en drawable
        }

        // Obtener los detalles adicionales del personaje desde el Intent
        String desc = getIntent().getStringExtra("descripcion");
        String habs = getIntent().getStringExtra("habilidades");

        // Configurar las vistas con los datos recibidos
        name2.setText(nombre);
        imagePantalla2.setImageResource(fotoSecundaria);
        descripcion.setText(desc);
        habilidades.setText(habs);
    }
}

