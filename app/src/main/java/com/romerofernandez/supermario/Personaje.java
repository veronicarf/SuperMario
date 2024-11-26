package com.romerofernandez.supermario;

/**
 * Clase que representa un personaje con información detallada como su nombre, imagen principal,
 * descripción, habilidades, imagen secundaria y color de fondo.
 *
 * Esta clase proporciona los datos necesarios para representar a un personaje en la aplicación y
 * se utiliza para pasar la información entre actividades.
 */
public class Personaje {

    private String nombre;
    private int imageResId; // Foto principal
    private String descripcion; // Descripción para la segunda pantalla
    private String habilidades; // Habilidades para la segunda pantalla
    private int imageSecundariaResId; // Foto secundaria (mario2, luigi2, etc.)
    private int colorFondo; // Color de fondo

    /**
     * Constructor de la clase Personaje.
     *
     * @param nombre El nombre del personaje.
     * @param imageResId El ID del recurso de la imagen principal del personaje.
     * @param descripcion La descripción del personaje para la segunda pantalla.
     * @param habilidades Las habilidades del personaje para la segunda pantalla.
     * @param imageSecundariaResId El ID del recurso de la imagen secundaria del personaje.
     * @param colorFondo El color de fondo que se aplicará en la segunda pantalla.
     */
    public Personaje(String nombre, int imageResId, String descripcion, String habilidades, int imageSecundariaResId, int colorFondo) {
        this.nombre = nombre;
        this.imageResId = imageResId;
        this.descripcion = descripcion;
        this.habilidades = habilidades;
        this.imageSecundariaResId = imageSecundariaResId;
        this.colorFondo = colorFondo;
    }

    /**
     * Obtiene el nombre del personaje.
     *
     * @return El nombre del personaje.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el ID del recurso de la imagen principal del personaje.
     *
     * @return El ID de la imagen principal.
     */
    public int getImageResId() {
        return imageResId;
    }

    /**
     * Obtiene la descripción del personaje para la segunda pantalla.
     *
     * @return La descripción del personaje.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Obtiene las habilidades del personaje para la segunda pantalla.
     *
     * @return Las habilidades del personaje.
     */
    public String getHabilidades() {
        return habilidades;
    }

    /**
     * Obtiene el ID del recurso de la imagen secundaria del personaje.
     *
     * @return El ID de la imagen secundaria.
     */
    public int getImageSecundariaResId() {
        return imageSecundariaResId;
    }

    /**
     * Obtiene el color de fondo que se aplicará en la segunda pantalla.
     *
     * @return El color de fondo.
     */
    public int getColorFondo() {
        return colorFondo;
    }
}
