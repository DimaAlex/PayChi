package com.paychi.dima.paychi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.paychi.dima.paychi.models.User;
import com.paychi.dima.paychi.models.WishList;
import com.paychi.dima.paychi.responses.CreateWishListResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateWishListActivity extends AppCompatActivity {
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_wish_list);
    }

    protected void сreateWishList(View v) {
        EditText nameInput = (EditText) findViewById(R.id.et_name);
        String name = nameInput.getText().toString();

        EditText descInput = (EditText) findViewById(R.id.et_description);
        String desc = descInput.getText().toString();

        RadioGroup visibilityRG = (RadioGroup) findViewById(R.id.rg_visibility);
        RadioButton visibilityRB = (RadioButton) findViewById(visibilityRG.getCheckedRadioButtonId());
        long visibility = getVisibilityByValue(visibilityRB.getText().toString());

        user = User.getInstance();
        WishList wishList = new WishList(name, visibility, desc);

        Call<CreateWishListResponse> callback = RestApiClient.getInstance().getPayChiService().createWishList(
            user.getToken(), user.getUserId(), wishList
        );
        callback.enqueue(new Callback<CreateWishListResponse>() {
            @Override
            public void onResponse(Call<CreateWishListResponse> call, Response<CreateWishListResponse> response) {
                CreateWishListResponse body = response.body();
                if (body.getErrorCode() == 0) {
                    Intent intent = new Intent(CreateWishListActivity.this, WishListsActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(CreateWishListActivity.this, body.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CreateWishListResponse> call, Throwable t) {

            }
        });
    }

    private int getVisibilityByValue(String value) {
        switch (value) {
            case "Публичный":
                return 1;
            case "Приватный":
                return 2;
        }
        return 0;
    }
}
