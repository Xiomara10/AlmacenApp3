package com.xtorres.almacenapp;

<<<<<<< HEAD
=======
import android.content.Intent;
import android.support.annotation.NonNull;
>>>>>>> Second commit
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
<<<<<<< HEAD
=======
import android.view.View;
>>>>>>> Second commit
import android.widget.Toast;

import com.xtorres.almacenapp.adapters.ProductosAdapter;
import com.xtorres.almacenapp.models.ApiError;
import com.xtorres.almacenapp.models.Producto;
import com.xtorres.almacenapp.services.ApiService;
import com.xtorres.almacenapp.services.ApiServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView productosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //testRest();

        productosList = findViewById(R.id.reciclerview);
        productosList.setLayoutManager(new LinearLayoutManager(this));

        productosList.setAdapter(new ProductosAdapter());

        initialize();

    }

    private void testRest(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        Call<List<Producto>> call = service.findAll();

        call.enqueue(new Callback<List<Producto>>() {
            @Override
<<<<<<< HEAD
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
=======
            public void onResponse(@NonNull Call<List<Producto>> call, Response<List<Producto>> response) {
>>>>>>> Second commit

                if(response.isSuccessful()){

                    List<Producto> productos = response.body();
                    Log.d("MainActivity", "productos: " +productos );

                }else{
                    Toast.makeText(MainActivity.this, "Error" +response.code(), Toast.LENGTH_SHORT);
                }

            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Error critico" + t.getMessage(), Toast.LENGTH_SHORT);

            }
        });

    }

    private void initialize(){

         ApiService service = ApiServiceGenerator.createService(ApiService.class);

         service.findAll().enqueue(new Callback<List<Producto>>() {
             @Override
<<<<<<< HEAD
             public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                 if(response.isSuccessful()){
                     List<Producto> productos = response.body();
                     Log.d(TAG, "productos: " + productos);
=======
             public void onResponse(@NonNull Call<List<Producto>> call, @NonNull Response<List<Producto>> response) {
                 if(response.isSuccessful()){
                     List<Producto> productos = response.body();
                     Log.d(TAG, "productos: " + productos);

                     ProductosAdapter adapter = (ProductosAdapter) productosList.getAdapter();
                     adapter.setProductos(productos);
                     adapter.notifyDataSetChanged();


>>>>>>> Second commit
                 }else{
                     ApiError error = ApiServiceGenerator.parseError(response);
                     Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                 }
             }

             @Override
             public void onFailure(Call<List<Producto>> call, Throwable t) {
                 Toast.makeText(MainActivity.this, "ERROR: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
             }
         });

    }

<<<<<<< HEAD
=======
    private static final int REQUEST_REGISTER_FORM = 100;

    public void showRegister(View view){
        startActivityForResult(new Intent(this, RegisterActivity.class), REQUEST_REGISTER_FORM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_REGISTER_FORM) {
            initialize();   // refresh data from rest service
        }
    }

>>>>>>> Second commit
}
