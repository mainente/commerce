package teste.com.commerce;


import android.app.Activity;
import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;

public class HistoricFragment extends Fragment {

	View v;
	JSONArray jaProducts;
	private GridLayoutManager lLayout;


	Cursor c;






	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



         v = inflater.inflate(R.layout.fragment_item_list, container, false);
        return v;
    }


    
    
    @Override
    public void onActivityCreated (Bundle savedInstanceState) {
    	super.onActivityCreated(savedInstanceState);

    	try {

			Activity currentActivity = getActivity();
			DbController db=new DbController(getActivity());
			c=db.listPayment();
			TransactionsAdapter transactionsAdapter = new TransactionsAdapter(getActivity(), c);
			AbsListView mListView = (AbsListView) v.findViewById(android.R.id.list);
			TextView infolist=(TextView)v.findViewById(R.id.tInfoTransaction);

			mListView.setAdapter(transactionsAdapter);
			if(!c.moveToFirst()){
				mListView.setVisibility(View.GONE);
				infolist.setVisibility(View.VISIBLE);
				infolist.setText("Nenhuma venda foi realizada");


			}else {

				infolist.setVisibility(View.GONE);
				mListView.setVisibility(View.VISIBLE);


			}




		}
    	catch (Exception exception) {
    	}


    	

    }
    




}







