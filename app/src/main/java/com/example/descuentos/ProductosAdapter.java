package com.example.descuentos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ProductoViewHolder> {

    private List<Producto> productos;

    public ProductosAdapter(List<Producto> productos) {
        this.productos = productos;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main, parent, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        Producto producto = productos.get(position);
        // Configurar la vista del producto con la información correspondiente
        holder.bind(producto);
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    static class ProductoViewHolder extends RecyclerView.ViewHolder {
        // Configura las vistas para mostrar la información del producto (nombre, precio, etc.)
        private final TextView nombreTextView;
        private final TextView precioTextView;
        private final TextView precioAnteriorTextView;
        private final TextView DescuentoTextView;
        private final ImageView Imagen;
        ProductoViewHolder(View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.textViewNombre);
            precioTextView = itemView.findViewById(R.id.textViewPrecio);
            precioAnteriorTextView=itemView.findViewById(R.id.textViewPrecioAnterior);
            DescuentoTextView=itemView.findViewById(R.id.textViewDescuento);
            Imagen=itemView.findViewById(R.id.ImageViewProducto);
        }

        void bind(Producto producto) {
            // Asigna los valores del producto a las vistas correspondientes

            if (producto != null) {
                nombreTextView.setText(producto.getTitle());
                precioTextView.setText("Precio actual: $"+ producto.getPrice() +"MX");
                precioAnteriorTextView.setText("Precio anterior: $"+producto.original_price+ "MX");
                DescuentoTextView.setText(String.valueOf("Descuentos: "+producto.getDiscount_percentage()+"%"));
                Picasso.get().load(String.valueOf(producto.getThumbnail())).into(Imagen);
            }
            else{
                nombreTextView.setText(" ");
                precioTextView.setText(" ");
                precioAnteriorTextView.setText(" ");
                DescuentoTextView.setText(" ");
            }

        }
    }
}

