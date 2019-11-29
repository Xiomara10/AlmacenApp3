package com.xtorres.almacenapp.services;

import com.xtorres.almacenapp.models.Producto;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
<<<<<<< HEAD
import retrofit2.http.GET;
=======
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
>>>>>>> Second commit

public interface ApiService {

    String API_BASE_URL = "http://192.168.1.8:8085";

    @GET("/productos") //http://10.0.2.2:8085/productos
    Call<List<Producto>> findAll();

    @FormUrlEncoded
    @POST("/productos")
    Call<Producto> createProducto(@Field("nombre") String nombre,
                                  @Field("precio") String precio,
                                  @Field("detalles") String detalles);
    @Multipart
    @POST("/productos")
    Call<Producto> createProducto(@Part("nombre") RequestBody nombre,
                                  @Part("precio") RequestBody precio,
                                  @Part("detalles") RequestBody detalles
    );

<<<<<<< HEAD
=======
    @DELETE("/productos/{id}")
    Call<String> destroyProducto(@Path("id") Long id);


    @GET("/productos/{id}")
    Call<Producto> showProducto(@Path("id") Long id);

>>>>>>> Second commit

}
