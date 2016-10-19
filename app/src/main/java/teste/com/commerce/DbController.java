package teste.com.commerce;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

/**
 * Created by mainente on 29/06/16.
 */
public class DbController {

    private SQLiteDatabase db;
    private DbOpenHelper dbOpen;

    public DbController(Context context){
        dbOpen = new DbOpenHelper(context);
    }

    public Boolean insertTransaction(Double value,Integer idCard, String status){
        ContentValues valores;
        long resultado = 0;
        try {


            db = dbOpen.getWritableDatabase();


            valores=new ContentValues();

           valores.put("value", value);
            valores.put("idCardTransaction",idCard);
            valores.put("status",status);


            try {
                resultado = db.insert("Transactions", null, valores);
            }catch (Exception e){

                String teste=e.toString();
            }
            if (resultado == -1) {
                db.close();

                return false;


            } else {

                    db.close();


                    return true;



            }



        }catch (Exception e){


        }
        return false;


    }

    public Cursor listPayment() {


        SQLiteDatabase db = dbOpen.getReadableDatabase();
        String sql = "select value,status,dateTransaction from Transactions;";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {

            String teste = cursor.getString(cursor.getColumnIndex("value"));
            Log.d("t", teste);


        }


        return cursor;

    }


    public Cursor listCard() {


        SQLiteDatabase db = dbOpen.getReadableDatabase();
        String sql = "select cardNumber,cardBrand,selected from CardTransaction;";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {

            String teste = cursor.getString(cursor.getColumnIndex("cardNumber"));
            Log.d("t", teste);


        }


        return cursor;

    }

    public Cursor listCardSelected() throws Exception {


        SQLiteDatabase db = dbOpen.getReadableDatabase();
        String sql = "select * from CardTransaction where selected=1";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {

            String teste = cursor.getString(cursor.getColumnIndex("cardNumber"));
            Log.d("t", teste);


        }


        return cursor;

    }





    public Boolean insertCard(Card card) {
        ContentValues valores;
        long resultado = 0;
        try {

            unSelectedCard();


            db = dbOpen.getWritableDatabase();


            valores = new ContentValues();


            valores.put("cardHolder", card.getCard_holder());
            valores.put("cardNumber", card.getCard_number());
            valores.put("cardBrand", card.getCard_brand());
            valores.put("expiration_month", card.getExpiration_month());
            valores.put("expiration_year", card.getExpiration_year());
            valores.put("CVV", card.getCVV());
            valores.put("selected", card.getSelected());

                resultado = db.insert("CardTransaction", null, valores);

            if (resultado == -1) {
                db.close();

                return false;


            } else {

                return true;
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return false;
    }

    public Boolean unSelectedCard(){


        long result = 0;

        db = dbOpen.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("selected", 0);
        db.update("CardTransaction",values,null,null);
        if (result == -1) {
            db.close();

            return false;


        } else {
            return true;
        }




        }

    }