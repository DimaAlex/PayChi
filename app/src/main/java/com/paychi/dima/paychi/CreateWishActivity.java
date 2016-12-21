package com.paychi.dima.paychi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.paychi.dima.paychi.models.User;
import com.paychi.dima.paychi.models.WishItem;
import com.paychi.dima.paychi.responses.CreateWishItemResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateWishActivity extends AppCompatActivity {
    Spinner spinner;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_wish);
        spinner = (Spinner) findViewById(R.id.sp_rate);
        Integer[] items = new Integer[]{1,2,3,4,5};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, items);
        spinner.setAdapter(adapter);
        spinner.setSelection(1);
    }

    protected void сreateWishItem(View v) {
        EditText titleInput = (EditText) findViewById(R.id.et_title);
        String title = titleInput.getText().toString();

        EditText commentInput = (EditText) findViewById(R.id.et_description);
        String comment = commentInput.getText().toString();

        EditText linkInput = (EditText) findViewById(R.id.et_link);
        String link = linkInput.getText().toString();

        EditText costInput = (EditText) findViewById(R.id.et_cost);
        long cost = Integer.parseInt(costInput.getText().toString());

        long wantRate = Integer.parseInt(spinner.getSelectedItem().toString());

        RadioGroup visibilityRG = (RadioGroup) findViewById(R.id.rg_visibility);
        RadioButton visibilityRB = (RadioButton) findViewById(visibilityRG.getCheckedRadioButtonId());
        int visibility = getvisibilityByValue(visibilityRB.getText().toString());

        user = User.getInstance();
        long wishlistId = (long) getIntent().getSerializableExtra("wishlistId");
        WishItem wish = new WishItem(title, comment, link, cost, wantRate, visibility, wishlistId);

        Call<CreateWishItemResponse> callback = RestApiClient.getInstance().getPayChiService().createWishItem(
            user.getToken(), user.getUserId(), wish, wishlistId
        );
        callback.enqueue(new Callback<CreateWishItemResponse>() {
            @Override
            public void onResponse(Call<CreateWishItemResponse> call, Response<CreateWishItemResponse> response) {

            }

            @Override
            public void onFailure(Call<CreateWishItemResponse> call, Throwable t) {

            }
        });
    }

    private int getvisibilityByValue(String value) {
        switch (value) {
            case "Публичный":
                return 1;
            case "Приватный":
                return 2;
        }
        return 0;
    }
}
