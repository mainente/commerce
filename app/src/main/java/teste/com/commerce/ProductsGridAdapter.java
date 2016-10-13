package teste.com.commerce;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;



import org.json.JSONArray;
import org.json.JSONException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Vector;

@SuppressLint("NewApi")
public class ProductsGridAdapter extends BaseAdapter{
	private Context context;

	public static ProductsGridAdapter instance = null;
	JSONArray jaProducts;



    public ProductsGridAdapter(JSONArray jaProducts) {
		super();


		try {
			this.jaProducts=jaProducts;
		}catch (Exception e){

		}
	}



	@SuppressLint("NewApi")
	public View getView(final int position, View convertView, final ViewGroup parent) {
 
//		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = null;

		final ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.
					from(parent.getContext()).
					inflate(R.layout.custom_grid_layout, parent,false);
			holder = new ViewHolder();
			holder.price = (TextView) convertView.findViewById(R.id.text_view_order_item_product_price);
			holder.imageView = (SquareImageView) convertView.findViewById(R.id.grid_item_image);
			holder.nameProd=(TextView) convertView.findViewById(R.id.text_view_order_item_product_name);

			convertView.setTag(holder);






		} else {

			holder = (ViewHolder) convertView.getTag();


		}


		try {
			holder.nameProd.setText(jaProducts.getJSONObject(position).getString("title"));
			holder.price.setText(jaProducts.getJSONObject(position).getString("value"));
		} catch (Exception e) {
			e.printStackTrace();
		}


		return convertView;








	}
	






	@Override
	public int getCount() {
		return jaProducts.length();

	}
 
	@Override
	public Object getItem(int position) {
		return null;
	}
 
	@Override
	public long getItemId(int position) {
		return 0;
	}



	static class ViewHolder {
		SquareImageView imageView;
		TextView price;
		TextView nameProd;


	}
}
