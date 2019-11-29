package com.xtorres.almacenapp.adapters;

import android.content.Context;
<<<<<<< HEAD
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xtorres.almacenapp.R;
import com.xtorres.almacenapp.models.Producto;
import com.xtorres.almacenapp.services.ApiService;
=======
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.xtorres.almacenapp.DetailActivity;
import com.xtorres.almacenapp.R;
import com.xtorres.almacenapp.models.Producto;
import com.xtorres.almacenapp.services.ApiService;
import com.xtorres.almacenapp.services.ApiServiceGenerator;
>>>>>>> Second commit

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

<<<<<<< HEAD
=======
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

>>>>>>> Second commit
public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ViewHolder> {

    private static final String TAG = ProductosAdapter.class.getSimpleName();

    private List<Producto> productos;

    public ProductosAdapter(){
        this.productos = new ArrayList<>();
    }

    public void setProductos(List<Producto>productos){
        this.productos = productos;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView fotoImage;
        TextView nombreText;
        TextView precioText;
<<<<<<< HEAD
=======
        ImageButton menuButton;
>>>>>>> Second commit

        ViewHolder(View itemView) {
            super(itemView);
            fotoImage = itemView.findViewById(R.id.foto_image);
            nombreText = itemView.findViewById(R.id.nombre_text);
            precioText = itemView.findViewById(R.id.precio_text);
<<<<<<< HEAD
=======
            menuButton = itemView.findViewById(R.id.menu_button);
>>>>>>> Second commit
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
        return new ViewHolder(itemView);

    }

    @Override
<<<<<<< HEAD
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final Context context = viewHolder.itemView.getContext();

        Producto producto = this.productos.get(position);
=======
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        final Context context = viewHolder.itemView.getContext();

        final Producto producto = this.productos.get(position);
>>>>>>> Second commit

        viewHolder.nombreText.setText(producto.getNombre());
        viewHolder.precioText.setText(NumberFormat.getCurrencyInstance(new Locale("es", "PE")).format(producto.getPrecio()));

        String url = ApiService.API_BASE_URL + "/productos/images/" + producto.getImagen();
        Picasso.with(context).load(url).into(viewHolder.fotoImage);

<<<<<<< HEAD
=======
        viewHolder.menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(v.getContext(), v);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.remove_button:

                                ApiService service = ApiServiceGenerator.createService(ApiService.class);

                                service.destroyProducto(producto.getId()).enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                                        try {

                                            if (response.isSuccessful()) {

                                                String message = response.body();
                                                Log.d(TAG, "message: " + message);

                                                // Eliminar item del recyclerView y notificar cambios
                                                productos.remove(position);
                                                notifyItemRemoved(position);
                                                notifyItemRangeChanged(position, productos.size());

                                                Toast.makeText(context, message, Toast.LENGTH_LONG).show();

                                            } else {
                                                throw new Exception(ApiServiceGenerator.parseError(response).getMessage());
                                            }

                                        } catch (Throwable t) {
                                            Log.e(TAG, "onThrowable: " + t.getMessage(), t);
                                            Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                                        Log.e(TAG, "onFailure: " + t.getMessage(), t);
                                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
                                    }

                                });

                                break;
                        }
                        return false;
                        }
                });
                popup.show();
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("ID", producto.getId());
                context.startActivity(intent);
            }
        });

>>>>>>> Second commit
    }

    @Override
    public int getItemCount() {
        return this.productos.size();
    }
}
