package teste.com.commerce;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.uncopt.android.widget.text.justify.JustifiedTextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mainente on 15/10/16.
 */
public class PaymentActivity extends AppCompatActivity {

    int idCard;
    BigDecimal bValue;
    Toolbar mToolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_activity);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        //Action bar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle intent = getIntent().getExtras();

        try {



        String sProducts=intent.getString("joProducts");
        String resource = "drawable";


        JSONObject joProducts=new JSONObject(sProducts);

        final int id = getResources().getIdentifier(joProducts.getString("image"), resource, getPackageName());

        ImageView imgProduct=(ImageView)findViewById(R.id.imgProducts);
        TextView nameProduct=(TextView)findViewById(R.id.nameProduct);
        TextView price=(TextView)findViewById(R.id.price);
        JustifiedTextView detail=(JustifiedTextView)findViewById(R.id.txtdetail);
        imgProduct.setImageResource(id);
        nameProduct.setText(joProducts.getString("title"));
        price.setText(joProducts.getString("value"));
        detail.setText(joProducts.getString("details"));

        Button btnBuy=(Button)findViewById(R.id.btnBuy);

            btnBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final ProgressDialog progressDialog = new ProgressDialog(PaymentActivity.this,
                            R.style.AppTheme_Dark_Dialog);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Finalizando Compra...");
                    progressDialog.show();


                    String url="https://private-b3620-desafio.apiary-mock.com/commerce/";
                        Gson gson = new GsonBuilder()
                                .setLenient()
                                .create();
                        OkHttpClient client = new OkHttpClient();

                    Transaction transaction=new Transaction();

                    final DbController dbOpenHelper=new DbController(PaymentActivity.this);

                    Cursor cursor= null;
                    try {


                        cursor = dbOpenHelper.listCardSelected();


                    final TextView txtValue=(TextView)findViewById(R.id.price);


                    if (cursor.moveToFirst()) {

                        bValue = Extras.getInstance().getBigDecimalFromDecimalString(txtValue.getText().toString().replace("R$", ""));

                        transaction.setCard_brand(cursor.getString(cursor.getColumnIndex("cardBrand")));
                        transaction.setCard_holder(cursor.getString(cursor.getColumnIndex("cardHolder")));
                        transaction.setCard_number(cursor.getString(cursor.getColumnIndex("cardNumber")));
                        transaction.setCVV(cursor.getString(cursor.getColumnIndex("CVV")));
                        transaction.setExpiration_month(cursor.getInt(cursor.getColumnIndex("expiration_month")));
                        transaction.setExpiration_year(cursor.getInt(cursor.getColumnIndex("expiration_year")));
                        transaction.setValue(bValue.doubleValue());
                        idCard = cursor.getInt(cursor.getColumnIndex("id"));


                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(url)
                                .client(client)
                                .addConverterFactory(GsonConverterFactory.create(gson))
                                .build();
                        RequestInterface request = retrofit.create(RequestInterface.class);
                        final Call<JsonElement> call = request.sendTransaction(transaction);


                        call.enqueue(new Callback<JsonElement>() {
                            @Override
                            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                                try {

                                    JsonElement jsonResponse = response.body();

                                    JSONObject joResponse = new JSONObject(jsonResponse.toString());

                                    dbOpenHelper.insertTransaction(bValue.doubleValue(), idCard, joResponse.getString("status"));

                                    progressDialog.dismiss();

                                        Toast.makeText(PaymentActivity.this, "Compra realizada", Toast.LENGTH_LONG).show();
                                        finish();



                                } catch (Exception e) {

                                    e.printStackTrace();

                                }

                            }

                            @Override
                            public void onFailure(Call<JsonElement> call, Throwable t) {
                                Log.d("Error", t.getMessage());

                                Toast.makeText(PaymentActivity.this, "Erro ao realizar pagamento", Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                            }
                        });

                    }else {
                        Toast.makeText(PaymentActivity.this,"Nenhum cartão foi selecionado para pagamento",Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }

                    }catch (Exception e){

                        Toast.makeText(PaymentActivity.this,"Erro ao realizar pagamento",Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();



                    }







                }
            });

        }catch (Exception e){
            Toast.makeText(PaymentActivity.this,"Erro ao realizar pagamento",Toast.LENGTH_LONG).show();



        }








    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.R.id.home == item.getItemId()) {
            //	NavUtils.navigateUpTo(this, new Intent(this, ChargeActivity.class));
            finish();
            return true;
            //Intent i = new Intent();
            //i.setClass(ZCLMenuWithHomeButtonActivity.this, ChargeActivity.class);
            //i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            //startActivity(i);
            //return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
//	    return false;
    }


}
