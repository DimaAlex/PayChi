package com.paychi.dima.paychi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.paychi.dima.paychi.adapters.WishListAdapter;
import com.paychi.dima.paychi.listeners.OnWishListClickListener;
import com.paychi.dima.paychi.models.User;
import com.paychi.dima.paychi.models.WishList;
import com.paychi.dima.paychi.models.WishListWrapper;
import com.paychi.dima.paychi.responses.WishListResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WishListsActivity extends AppCompatActivity implements OnWishListClickListener {
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_lists);

        user = User.getInstance();
        if (user.getType() == 1) {
            Button btn = (Button) findViewById(R.id.btn_addTasksList);
            btn.setVisibility(View.GONE);
        }

        Call<WishListResponse> callback = RestApiClient.getInstance().getPayChiService().getWishList(
            user.getToken(), user.getUserId()
        );
        callback.enqueue(new Callback<WishListResponse>() {
            @Override
            public void onResponse(Call<WishListResponse> call, Response<WishListResponse> response) {
                ArrayList<WishListWrapper> wishLists = response.body().getData();
                ListView lvWishList = (ListView) findViewById(R.id.lvWishList);
                lvWishList.setAdapter(new WishListAdapter(WishListsActivity.this, wishLists, WishListsActivity.this));
            }

            @Override
            public void onFailure(Call<WishListResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onWishListClick(WishList list) {
        Intent intObj = new Intent(this, WishItemsActivity.class);
        intObj.putExtra("wishlistId", list.getListId());
        startActivity(intObj);
    }

    public void toCreateWishListActivity(View v) {
        Intent intObj = new Intent(this, CreateWishListActivity.class);
        startActivity(intObj);
    }
}
