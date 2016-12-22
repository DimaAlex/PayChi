package com.paychi.dima.paychi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    RadioGroup radioGroup;
    RadioButton radioParent;
    RadioButton radioChild;
    int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        btnVK = (Button) findViewById(R.id.btn_vk);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioParent = (RadioButton) findViewById(R.id.radio_parent);
        radioParent.setOnClickListener(radioButtonClickListener);
        radioChild = (RadioButton) findViewById(R.id.radio_child);
        radioChild.setOnClickListener(radioButtonClickListener);
        radioGroup.setEnabled(false);
        radioParent.setEnabled(false);
        radioChild.setEnabled(false);
    }

    View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RadioButton rb = (RadioButton)v;
            switch (rb.getId()) {
                case R.id.radio_parent:
                    type = 1;
                    break;
                case R.id.radio_child:
                    type = 2;
                    break;
                default:break;
            }
        }
    };

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
                Log.d("vk", token);
                int userId = Integer.parseInt(VKSdk.getAccessToken().userId);
                // int type = Integer.parseInt(VKSdk.getAccessToken().type);
                Log.d("vk", Integer.toString(userId));
                Call<UserResponse> callback = RestApiClient.getInstance().getPayChiService().getUser(token, userId);
                callback.enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        User currentUser;
                        UserResponse userResponse = response.body();
                        if (userResponse.error_code == 0) {
                            currentUser = userResponse.getData();
                            Log.d("vk_user", currentUser.toString());

                            Intent intent = new Intent(getParent(), MainActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(AuthorizationActivity.this, response.body().error_message, Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        Log.d("vk_callback", "Throwable is " + t);
                    }
                });
                radioGroup.setEnabled(true);
                radioParent.setEnabled(true);
                radioChild.setEnabled(true);
                callback = RestApiClient.getInstance().getPayChiService().getUser(token, userId, type);
                callback.enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        User currentUser;
                        UserResponse userResponse = response.body();
                        if (userResponse.error_code == 0) {
                            currentUser = userResponse.getData();
                            Log.d("vk_user_registered", currentUser.toString());

                            Intent intent = new Intent(getParent(), MainActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(AuthorizationActivity.this, response.body().error_message, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        Log.d("vk_callback_register", "Throwable is " + t);
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
