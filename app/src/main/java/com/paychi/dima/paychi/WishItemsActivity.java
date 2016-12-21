package com.paychi.dima.paychi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.paychi.dima.paychi.adapters.WishItemsAdapter;
import com.paychi.dima.paychi.listeners.OnWishItemClickListener;
import com.paychi.dima.paychi.models.User;
import com.paychi.dima.paychi.models.WishItem;
import com.paychi.dima.paychi.responses.GetWishItemsResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WishItemsActivity extends AppCompatActivity implements OnWishItemClickListener {
    User user;
    long wishlistId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_items);
        user = User.getInstance();
        wishlistId = (long) getIntent().getSerializableExtra("wishlistId");

        Call<GetWishItemsResponse> callback = RestApiClient.getInstance().getPayChiService().getWishItems(
            user.getToken(), user.getUserId(), wishlistId
        );
        callback.enqueue(new Callback<GetWishItemsResponse>() {
            @Override
            public void onResponse(Call<GetWishItemsResponse> call, Response<GetWishItemsResponse> response) {
                ArrayList<WishItem> wishItems = response.body().getData();
                ListView lvWishItems = (ListView) findViewById(R.id.lvWishItems);
                lvWishItems.setAdapter(new WishItemsAdapter(WishItemsActivity.this, wishItems, WishItemsActivity.this));
            }

            @Override
            public void onFailure(Call<GetWishItemsResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onWishItemClick(WishItem wish) {
        Toast.makeText(this, String.valueOf(wish.getItemId()), Toast.LENGTH_SHORT).show();
    }

    public void toCreateWishActivity(View v) {
        Intent intObj = new Intent(this, CreateWishActivity.class);
        intObj.putExtra("wishlistId", wishlistId);
        startActivity(intObj);
    }
}
