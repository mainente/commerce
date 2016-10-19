package teste.com.commerce;

/**
 * Created by mainente on 15/10/16.
 */
import com.google.gson.JsonElement;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RequestInterface {
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })

    @GET("listproducts")
    Call<JsonElement> getJSON();



    @POST("sendtransaction")
    Call<JsonElement> sendTransaction(@Body Transaction transaction);

}