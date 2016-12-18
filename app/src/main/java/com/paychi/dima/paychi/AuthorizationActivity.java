package com.paychi.dima.paychi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.paychi.dima.paychi.responses.UserResponse;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

import com.paychi.dima.paychi.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthorizationActivity extends AppCompatActivity {
    Button btnVK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        btnVK = (Button) findViewById(R.id.btn_vk);
    }

    public void onBtnVkClick(View view) {
        VKSdk.login(this, new String[]{"email"});
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                Log.d("vk", res.toString());
                String token = VKSdk.getAccessToken().accessToken;
                int userId = Integer.parseInt(VKSdk.getAccessToken().userId);
                // int type = Integer.parseInt(VKSdk.getAccessToken().type);

                Call<UserResponse> callback = RestApiClient.getInstance().getPayChiService().getUser(token, userId, 1);
                callback.enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        User currentUser;
                        if (response.body().error_code == 0)
                            currentUser = response.body().getData();
                        else
                            Toast.makeText(AuthorizationActivity.this, response.body().error_message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onError(VKError error) {
                Log.d("vk", error.toString());
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
