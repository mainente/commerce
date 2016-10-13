package teste.com.commerce;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

/**
 * Created by mainente on 13/10/16.
 */
public class ProductsRecyclerViewAdapter extends RecyclerView.Adapter<ProductsRecyclerViewAdapter.RecyclerViewHolders> {

    private Context context;
    JSONArray jaProducts;

    public ProductsRecyclerViewAdapter(Context context, JSONArray jaProducts) {
        this.jaProducts = jaProducts;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_grid_layout, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {


        try {
            holder.nameProd.setText(jaProducts.getJSONObject(position).getString("title"));
            holder.price.setText(jaProducts.getJSONObject(position).getString("value"));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return jaProducts.length();
    }
    public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

        SquareImageView imageView;
        TextView price;
        TextView nameProd;




        public RecyclerViewHolders(View itemView) {
            super(itemView);
            price = (TextView) itemView.findViewById(R.id.text_view_order_item_product_price);
            imageView = (SquareImageView) itemView.findViewById(R.id.grid_item_image);
            nameProd=(TextView) itemView.findViewById(R.id.text_view_order_item_product_name);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Clicked Country Position = " + getPosition(), Toast.LENGTH_SHORT).show();
        }
    }
}