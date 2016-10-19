package teste.com.commerce;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by mainente on 13/10/16.
 */
public class ProductsRecyclerViewAdapter extends RecyclerView.Adapter<ProductsRecyclerViewAdapter.RecyclerViewHolders> {

    private Activity a;
    Context context;
    JSONArray jaProducts;

    public ProductsRecyclerViewAdapter(Activity context, JSONArray jaProducts) {
        this.jaProducts = jaProducts;
        this.context = context;
        a=context;
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
            String resource = "drawable";
            final int id = a.getResources().getIdentifier(jaProducts.getJSONObject(position).getString("image"), resource, a.getPackageName());
            holder.imageView.setImageResource(id);
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

            try {
                Intent payment = new Intent(a, PaymentActivity.class);
                payment.putExtra("joProducts", jaProducts.getJSONObject(getAdapterPosition()).toString());

                a.startActivity(payment);
            }catch (Exception e){

            }


        }
    }
}