package com.romerofernandez.supermario;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.romerofernandez.supermario.databinding.ActivityMainBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * MainActivity es la actividad principal que gestiona la interfaz de usuario, incluyendo la navegación, la configuración de idioma y la interacción con un RecyclerView que muestra una lista de personajes.
 */
public class MainActivity extends AppCompatActivity {

    // Elementos de la interfaz de usuario
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private RecyclerView recyclerView;
    private PersonajeAdapter personajeAdapter;
    private List<Personaje> personajesList;

    /**
     * Método onCreate que inicializa la actividad, configura el idioma, los componentes de la interfaz y muestra una notificación inicial.
     *
     * @param savedInstanceState El estado de la actividad si se vuelve a crear.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflamos el layout y configuramos el toolbar
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbar);

        // Configuración de idioma y otros componentes
        configureLanguage();
        initializeRecyclerView();
        initializeDrawerAndNavigation();
        initializeFloatingActionButton();
        showWelcomeSnackbar();
    }

    /**
     * Configura el idioma de la aplicación según la preferencia guardada en SharedPreferences.
     */
    private void configureLanguage() {
        SharedPreferences prefs = getSharedPreferences("app_preferences", MODE_PRIVATE);
        String language = prefs.getString("language", "es");  // Idioma por defecto: español
        setLocale(language);
    }

    /**
     * Inicializa el RecyclerView que muestra la lista de personajes.
     */
    private void initializeRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Carga los personajes y configura el adaptador
        personajesList = loadPersonajes();
        personajeAdapter = new PersonajeAdapter(personajesList, this::openDetailScreen);
        recyclerView.setAdapter(personajeAdapter);
    }

    /**
     * Carga los personajes con su nombre, imagen, descripción, habilidades y color de fondo.
     *
     * @return Una lista de objetos Personaje.
     */
    private List<Personaje> loadPersonajes() {
        List<Personaje> list = new ArrayList<>();
        list.add(new Personaje(getString(R.string.mario_name), R.drawable.mario, getString(R.string.desc_mario), getString(R.string.hab_mario), R.drawable.mario2, ContextCompat.getColor(this, R.color.colorMario)));
        list.add(new Personaje(getString(R.string.luigi_name), R.drawable.luigi, getString(R.string.desc_luigi), getString(R.string.hab_luigi), R.drawable.luigi2, ContextCompat.getColor(this, R.color.colorLuigi)));
        list.add(new Personaje(getString(R.string.peach_name), R.drawable.peach, getString(R.string.desc_peach), getString(R.string.hab_peach), R.drawable.peach2, ContextCompat.getColor(this, R.color.colorPeach)));
        list.add(new Personaje(getString(R.string.toad_name), R.drawable.toad, getString(R.string.desc_toad), getString(R.string.hab_toad), R.drawable.toad2, ContextCompat.getColor(this, R.color.colorToad)));
        return list;
    }

    /**
     * Abre la pantalla de detalle del personaje seleccionado.
     *
     * @param personaje El personaje seleccionado.
     */
    private void openDetailScreen(Personaje personaje) {
        Intent intent = new Intent(MainActivity.this, Pantalla2.class);
        intent.putExtra("nombre", personaje.getNombre());
        intent.putExtra("descripcion", personaje.getDescripcion());
        intent.putExtra("habilidades", personaje.getHabilidades());
        intent.putExtra("fotoSecundaria", personaje.getImageSecundariaResId());
        intent.putExtra("fondo", personaje.getColorFondo());
        startActivity(intent);
    }

    /**
     * Configura el DrawerLayout y la navegación de la aplicación.
     */
    private void initializeDrawerAndNavigation() {
        drawerLayout = binding.drawerLayout;
        navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_settings, R.id.nav_language)
                .setOpenableLayout(drawerLayout)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        // Configura el listener para el menú de navegación
        navigationView.setNavigationItemSelectedListener(item -> handleNavigationItemSelected(item));
    }

    /**
     * Maneja la selección de un ítem del menú de navegación.
     *
     * @param item El ítem del menú seleccionado.
     * @return true si el ítem fue procesado correctamente.
     */
    private boolean handleNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            recreate();  // Reinicia la actividad
        } else if (id == R.id.nav_settings) {
            openSettings();
        } else if (id == R.id.nav_language) {
            toggleLanguage();  // Cambia el idioma de la aplicación
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Inicializa el botón flotante (FAB) y establece su acción.
     */
    private void initializeFloatingActionButton() {
        binding.appBarMain.fab.setOnClickListener(view ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .setAnchorView(R.id.fab)
                        .show()
        );
    }

    /**
     * Muestra un mensaje de bienvenida utilizando un Snackbar.
     */
    private void showWelcomeSnackbar() {
        Snackbar.make(findViewById(android.R.id.content), getString(R.string.bienvenidos), Snackbar.LENGTH_LONG).show();
    }

    /**
     * Cambia el idioma de la aplicación según el código de idioma proporcionado.
     *
     * @param languageCode El código de idioma (ej. "es" para español).
     */
    private void setLocale(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }

    /**
     * Muestra un cuadro de diálogo para seleccionar el idioma.
     */
    private void toggleLanguage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.select_language))
                .setItems(new CharSequence[]{"Español", "English"}, (dialog, which) -> {
                    String languageCode = (which == 0) ? "es" : "en";
                    SharedPreferences prefs = getSharedPreferences("app_preferences", MODE_PRIVATE);
                    prefs.edit().putString("language", languageCode).apply();
                    setLocale(languageCode);
                    recreate();
                })
                .create()
                .show();
    }

    /**
     * Abre la actividad de configuración.
     */
    private void openSettings() {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    /**
     * Muestra un cuadro de diálogo "Acerca de" con información de la aplicación.
     */
    private void showAboutDialog() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.about_de))
                .setMessage(getString(R.string.ace_text))
                .setIcon(R.drawable.icon)
                .setPositiveButton("Aceptar", null)
                .show();
    }

    /**
     * Infla el menú de opciones para la actividad.
     *
     * @param menu El menú que se va a inflar.
     * @return true si se infló el menú correctamente.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Maneja las selecciones de los ítems del menú de opciones.
     *
     * @param item El ítem seleccionado.
     * @return true si el ítem fue procesado correctamente.
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            showAboutDialog();  // Muestra el cuadro de diálogo "Acerca de"
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Maneja el comportamiento de la acción de navegación hacia arriba en la barra de herramientas.
     *
     * @return true si la navegación hacia arriba fue exitosa.
     */
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }
}
