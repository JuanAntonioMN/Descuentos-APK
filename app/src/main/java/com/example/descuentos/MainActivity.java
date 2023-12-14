package com.example.descuentos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response; // Asegúrate de importar la clase Response correcta

public class MainActivity extends AppCompatActivity {
    private Spinner spinnerCategorias;
    private RecyclerView recyclerViewProductos;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categorias);
        spinnerCategorias = findViewById(R.id.spinnerCategorias);
        recyclerViewProductos = findViewById(R.id.recyclerViewProductos);

         apiService = ApiDescuentos.createApiService();

        List<String> categorias = obtenerCategoriasDesdeTuFuenteDeDatos();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categorias);
        adapter.setDropDownViewResource(R.layout.items);

        spinnerCategorias.setAdapter(adapter);

        // Configurar el listener del Spinner
        spinnerCategorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Obtener la categoría seleccionada
                String categoriaSeleccionada = (String) parentView.getSelectedItem();

                // Realizar la solicitud a la API para obtener productos de la categoría seleccionada
                obtenerProductosPorCategoria(categoriaSeleccionada);
                // Cambiar el color del texto seleccionado
                ((TextView) parentView.getChildAt(0)).setTextColor(Color.WHITE);

                // Cambiar el tamaño del texto seleccionado
                ((TextView) parentView.getChildAt(0)).setTextSize(20);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // No hacer nada cuando no se selecciona ninguna categoría
            }
        });
    }


    private List<String> obtenerCategoriasDesdeTuFuenteDeDatos() {
        // Lógica para obtener las categorías desde tu fuente de datos (puede ser estática o dinámica)
        List<String> categorias = new ArrayList<>();
        categorias.add("ropa");
        categorias.add("zapatos");
        categorias.add("tenis");
        categorias.add("peliculas");
        categorias.add("joyas");

        categorias.add("libros");
        categorias.add("Motos");
        categorias.add("Belleza");
        categorias.add("Camaras");
        categorias.add("Celulares");
        categorias.add("Deportes");
        categorias.add("Instrumentos");
        categorias.add("Salud");
        categorias.add("Fiestas");
        categorias.add("Servicios");





        return categorias;
    }

    private void obtenerProductosPorCategoria(String categoria) {
        // Realiza la solicitud a la API para obtener productos de la categoría seleccionada
        apiService.obtenerProductosPorCategoria(categoria).enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if (response.isSuccessful()) {
                    List<Producto> productos = response.body();
                    // Actualiza el RecyclerView con los nuevos productos
                    actualizarRecyclerView(productos);
                } else {
                    // Manejar errores de la respuesta
                    Log.e("Retrofit", "Error en la respuesta: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                // Manejar errores de la solicitud
                Log.e("Retrofit", "Error en la solicitud: " + t.getMessage());
            }
        });
    }

    private void actualizarRecyclerView(List<Producto> productos) {
        if (productos != null) {
            // Crear un nuevo adaptador con la lista de productos
            ProductosAdapter adapter = new ProductosAdapter(productos);
            // Configurar el RecyclerView con el nuevo adaptador
            recyclerViewProductos.setAdapter(adapter);
            // Puedes configurar un LayoutManager según tus necesidades
            recyclerViewProductos.setLayoutManager(new LinearLayoutManager(this));
        }
    }

}
