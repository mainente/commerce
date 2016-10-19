package teste.com.commerce;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.GridView;

import org.json.JSONArray;

public class ProductsGridFragment extends Fragment {

	View v;
	JSONArray jaProducts;
	private GridLayoutManager lLayout;


	public void setJaProducts(JSONArray jaProducts){

		this.jaProducts=jaProducts;

	}




	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

         v = inflater.inflate(R.layout.fragment_products_gridview, container, false);
        return v;
    }


    
    
    @Override
    public void onActivityCreated (Bundle savedInstanceState) {
    	super.onActivityCreated(savedInstanceState);

    	try {

			Activity currentActivity = getActivity();
	     /*   gridViewAllProducts = (GridView) currentActivity.findViewById(R.id.gridViewProductsResults);
			productsGridAdapter = new ProductsGridAdapter(jaProducts);

			gridViewAllProducts.setAdapter(productsGridAdapter);
			gridViewAllProducts.setNumColumns(3);
*/
			lLayout = new GridLayoutManager(getActivity(), 2);

			RecyclerView rView = (RecyclerView)v.findViewById(R.id.cardListProducts);
			rView.setHasFixedSize(true);
			rView.setLayoutManager(lLayout);

			ProductsRecyclerViewAdapter rcAdapter = new ProductsRecyclerViewAdapter(getActivity(), jaProducts);
			rView.setAdapter(rcAdapter);






		}
    	catch (Exception exception) {
    	}


    	

    }
    




}







