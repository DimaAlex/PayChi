package com.paychi.dima.paychi.KuSu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.paychi.dima.paychi.KuSu.adapter.DialogsAdapter;
import com.paychi.dima.paychi.KuSu.adapter.MessagesAdapter;
import com.paychi.dima.paychi.KuSu.models.Dialog;
import com.paychi.dima.paychi.KuSu.models.Message;
import com.paychi.dima.paychi.KuSu.reaponse.DialogsResponse;
import com.paychi.dima.paychi.KuSu.reaponse.EmptyResponse;
import com.paychi.dima.paychi.KuSu.reaponse.MessagesResponse;
import com.paychi.dima.paychi.KuSu.reaponse.UsersResponse;
import com.paychi.dima.paychi.R;
import com.paychi.dima.paychi.RestApiClient;
import com.paychi.dima.paychi.models.User;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogActivity extends Activity implements View.OnClickListener {

    User user;
    Dialog dialog;
    ProgressBar progressBar;
    ListView listView;
    MessagesAdapter adapter;

    EditText text;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_dialog);
        user = (User) getIntent().getSerializableExtra(Constants.USER_DATA);
        dialog = (Dialog) getIntent().getSerializableExtra(Constants.DIALOG_DATA);
        if (user == null)
            finish();
        if (dialog == null)
            finish();
        setTitle(dialog.getName());

        text = (EditText) findViewById(R.id.text);
        listView = (ListView) findViewById(R.id.list);
        findViewById(R.id.send).setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        RestApiClient.getInstance().getPayChiService().getFirst(user.getToken(), user.getUserId(), dialog.getDialogId()).enqueue(
                new Callback<MessagesResponse>() {
                    @Override
                    public void onResponse(Call<MessagesResponse> call, Response<MessagesResponse> response) {
                        progressBar.setVisibility(View.GONE);
                        if (response.body() != null ){
                            if (response.body().isSuccess()){
                                adapter = new MessagesAdapter(DialogActivity.this, response.body().getData(), user);
                                listView.setAdapter(adapter);
                            }else{
                                Toast.makeText(DialogActivity.this, response.body().getErrorText(), Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(DialogActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MessagesResponse> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(DialogActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        startTimer();
    }

    private void startTimer() {
        if (timer != null)
            timer.cancel();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadNext();
                    }
                });
            }
        },100,3000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dialog, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                // TODO: 15.12.2016 добавить человека
                break;
            case R.id.out:
                progressBar.setVisibility(View.VISIBLE);
                RestApiClient.getInstance().getPayChiService().outDialog(user.getToken(), user.getUserId(), dialog.getDialogId())
                        .enqueue(new Callback<EmptyResponse>() {
                            @Override
                            public void onResponse(Call<EmptyResponse> call, Response<EmptyResponse> response) {
                                if (response.body() != null ){
                                    if (response.body().isSuccess()){
                                        DialogActivity.this.finish();
                                    }else{
                                        Toast.makeText(DialogActivity.this, response.body().getErrorText(), Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Toast.makeText(DialogActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<EmptyResponse> call, Throwable t) {
                                Toast.makeText(DialogActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            case R.id.list:
                RestApiClient.getInstance().getPayChiService().getDialogsUsers(user.getToken(), user.getUserId(), dialog.getDialogId()).enqueue(
                        new Callback<UsersResponse>() {
                            @Override
                            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                                if (response.body() != null ){
                                    if (response.body().isSuccess()){
                                        if (timer != null)
                                            timer.cancel();
                                        Intent intent = new Intent(DialogActivity.this, UserListActivity.class);
                                        intent.putExtra(Constants.TITLE_DATA, dialog.getName());
                                        intent.putExtra(Constants.USERS_DATA, response.body().getData());
                                        startActivityForResult(intent, Constants.USERS_ID_ACTIVITY);
                                    }else{
                                        Toast.makeText(DialogActivity.this, response.body().getErrorText(), Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Toast.makeText(DialogActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<UsersResponse> call, Throwable t) {
                                Toast.makeText(DialogActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send:
                if (text.getText().toString().length()>0){
                    Message message = new Message();
                    message.setDialogId(dialog.getDialogId());
                    message.setText(text.getText().toString());
                    message.setType(1);
                    text.setText("");
                    progressBar.setVisibility(View.VISIBLE);
                    RestApiClient.getInstance().getPayChiService().createMessage(user.getToken(), user.getUserId(), message)
                            .enqueue(new Callback<EmptyResponse>() {
                                @Override
                                public void onResponse(Call<EmptyResponse> call, Response<EmptyResponse> response) {
                                    if (response.body() != null ){
                                        if (response.body().isSuccess()){
                                        }else{
                                            Toast.makeText(DialogActivity.this, response.body().getErrorText(), Toast.LENGTH_SHORT).show();
                                        }
                                    }else{
                                        Toast.makeText(DialogActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<EmptyResponse> call, Throwable t) {
                                    Toast.makeText(DialogActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                break;
        }
    }

    public void loadNext(){
        RestApiClient.getInstance().getPayChiService().getNext(
            user.getToken(), user.getUserId(), adapter.getLastId(), dialog.getDialogId()
        ).enqueue(
            new Callback<MessagesResponse>() {
                @Override
                public void onResponse(Call<MessagesResponse> call, Response<MessagesResponse> response) {
                    if (response.body() != null ){
                        if (response.body().isSuccess()){
                            adapter.addAll(response.body().getData());
                            adapter.notifyDataSetChanged();
                        }else{
                            Toast.makeText(DialogActivity.this, response.body().getErrorText(), Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(DialogActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<MessagesResponse> call, Throwable t) {
                    Toast.makeText(DialogActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        startTimer();
    }
}
