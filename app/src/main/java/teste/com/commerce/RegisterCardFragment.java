package teste.com.commerce;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;

public class RegisterCardFragment extends Fragment {

	View v;
	FragmentManager fm;







	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

         v = inflater.inflate(R.layout.fragment_new_card, container, false);
        return v;
    }





	EditText eCard_holder;
	EditText eCard_number;
	EditText eCard_brand;
	EditText eExpiration_month;
	EditText eExpiration_year;
	EditText eCVV;
    @Override
    public void onActivityCreated (Bundle savedInstanceState) {
    	super.onActivityCreated(savedInstanceState);


		 eCard_holder=(EditText)v.findViewById(R.id.card_holder);
		 eCard_number=(EditText)v.findViewById(R.id.card_number);
		 eCard_brand=(EditText)v.findViewById(R.id.card_brand);
		 eExpiration_month=(EditText)v.findViewById(R.id.expiration_month);
		 eExpiration_year=(EditText)v.findViewById(R.id.expiration_year);
		 eCVV=(EditText)v.findViewById(R.id.CVV);

    	try {


			Button btn_newCard=(Button) getActivity().findViewById(R.id.register_card_button);
			btn_newCard.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {

					String card_holder=eCard_holder.getText().toString();
					String card_number=eCard_number.getText().toString();
					String card_brand=eCard_brand.getText().toString();
					int expiration_month= Integer.parseInt(eExpiration_month.getText().toString());
					int expiration_year= Integer.parseInt(eExpiration_year.getText().toString());
					String CVV=eCVV.getText().toString();
					Boolean selected=true;
					Card card=new Card(card_holder,card_number,card_brand,expiration_month,expiration_year,CVV,selected);

					DbController dbController=new DbController(getActivity());

					if(dbController.insertCard(card)){

						fm = getActivity().getFragmentManager();
						fm.popBackStack();

					}else {

						Toast.makeText(getActivity(),"Não foi possível salvar o cartão",Toast.LENGTH_LONG).show();
					}







				}
			});


		}
    	catch (Exception exception) {

			exception.printStackTrace();


    	}


    	

    }
    




}







