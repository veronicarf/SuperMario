package com.romerofernandez.supermario;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adapter para el RecyclerView que gestiona una lista de personajes.
 * Se encarga de mostrar cada personaje en un item del RecyclerView,
 * configurando su nombre, imagen y color, y gestionando los clics sobre los items.
 *
 * La clase también implementa un patrón de diseño Listener para manejar los clics en los elementos.
 */
public class PersonajeAdapter extends RecyclerView.Adapter<PersonajeAdapter.PersonajeViewHolder> {

    private List<Personaje> personajesList;
    private OnItemClickListener onItemClickListener;
    int[] colores;

    /**
     * Interfaz para manejar los clics en los elementos del RecyclerView.
     *
     * @see PersonajeAdapter
     */
    public interface OnItemClickListener {
        /**
         * Método que se llama cuando un item del RecyclerView es clicado.
         *
         * @param personaje El personaje que fue clicado.
         */
        void onItemClick(Personaje personaje);
    }

    /**
     * Constructor del Adapter.
     *
     * @param personajesList La lista de personajes que se mostrarán en el RecyclerView.
     * @param onItemClickListener El listener para manejar los clics en los elementos.
     */
    public PersonajeAdapter(List<Personaje> personajesList, OnItemClickListener onItemClickListener) {
        this.personajesList = personajesList;
        this.onItemClickListener = onItemClickListener;

        // Inicializamos el array de colores desde los recursos
        this.colores = new int[]{
                R.color.colorMario,
                R.color.colorLuigi,
                R.color.colorPeach,
                R.color.colorToad
        };
    }

    /**
     * Crea un nuevo ViewHolder para un item del RecyclerView.
     *
     * @param parent El ViewGroup que contiene los items del RecyclerView.
     * @param viewType El tipo de vista (usado para manejar múltiples tipos de vistas si es necesario).
     * @return Un nuevo ViewHolder para el item.
     */
    @Override
    public PersonajeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflamos el layout del item del RecyclerView
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_personajes, parent, false);
        return new PersonajeViewHolder(itemView);
    }

    /**
     * Vincula un personaje a un item del RecyclerView.
     *
     * @param holder El ViewHolder que mantiene las vistas del item.
     * @param position La posición del personaje en la lista de personajes.
     */
    @Override
    public void onBindViewHolder(PersonajeViewHolder holder, int position) {
        // Obtenemos el personaje en la posición actual
        Personaje personaje = personajesList.get(position);

        // Configuramos el nombre del personaje
        holder.nameTextView.setText(personaje.getNombre());

        // Selección cíclica de colores (usando posición % colores.length)
        int colorIndex = position % colores.length;

        // Obtenemos el color desde los recursos usando ContextCompat
        int color = ContextCompat.getColor(holder.itemView.getContext(), colores[colorIndex]);

        // Asignamos el color al texto del TextView
        holder.nameTextView.setTextColor(color);

        // Configuramos la imagen del personaje
        holder.characterImageView.setImageResource(personaje.getImageResId());

        // Configuramos el OnClickListener para el item
        holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(personaje));
    }

    /**
     * Obtiene el número de items en el RecyclerView.
     *
     * @return El número de elementos en la lista de personajes.
     */
    @Override
    public int getItemCount() {
        return personajesList.size();
    }

    /**
     * ViewHolder que mantiene las referencias a las vistas de cada item del RecyclerView.
     */
    public static class PersonajeViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public ImageView characterImageView;

        /**
         * Constructor del ViewHolder. Inicializa las vistas de cada item.
         *
         * @param itemView La vista del item que contiene los componentes del personaje.
         */
        public PersonajeViewHolder(View itemView) {
            super(itemView);
            // Inicializamos las vistas
            nameTextView = itemView.findViewById(R.id.characterName);
            characterImageView = itemView.findViewById(R.id.characterImage);
        }
    }
}
