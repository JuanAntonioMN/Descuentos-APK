package com.example.descuentos;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("/productos-{categoria}")
    Call<List<Producto>> obtenerProductosPorCategoria(@Path("categoria") String categoria);
}
