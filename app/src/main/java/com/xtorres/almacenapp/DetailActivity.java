package com.xtorres.almacenapp;

import android.icu.util.IslamicCalendar;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.xtorres.almacenapp.models.Producto;
import com.xtorres.almacenapp.services.ApiService;
import com.xtorres.almacenapp.services.ApiServiceGenerator;

import java.text.NumberFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();

    private Long id;

    private ImageView fotoImage;
    private TextView nombreText;
    private TextView detallesText;
    private TextView precioText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        fotoImage = findViewById(R.id.foto_image);
        nombreText = findViewById(R.id.nombre_text);
        detallesText = findViewById(R.id.detalles_text);
        precioText = findViewById(R.id.precio_text);

        id = getIntent().getExtras().getLong("ID");
        Log.e(TAG, "id" + id);

        initialize();

    }

    private void initialize(){
        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<Producto> call = service.showProducto(id);

        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                try{
                    if(response.isSuccessful()){
                        Producto producto = response.body();
                        Log.d(TAG, "producto: "+producto);

                        nombreText.setText(producto.getNombre());
                        detallesText.setText(producto.getDetalles());
                        precioText.setText(String.format("Precio; %s", NumberFormat.getCurrencyInstance(new Locale("es", "PE")).format(producto.getPrecio())));

                        String url = ApiService.API_BASE_URL+"/productos/images/"+ producto.getImagen();
                        Picasso.with(DetailActivity.this).load(url).into(fotoImage);
                    }else {
                        throw new Exception(ApiServiceGenerator.parseError(response).getMessage());
                    }
                }catch (Throwable t){
                    Log.e(TAG, "onThrowable: " + t.getMessage(), t);
                    Toast.makeText(DetailActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(@NonNull Call<Producto> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage(),t);
                Toast.makeText(DetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
