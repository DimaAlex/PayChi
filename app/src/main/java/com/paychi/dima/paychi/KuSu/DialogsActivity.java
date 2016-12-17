package com.paychi.dima.paychi.KuSu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.paychi.dima.paychi.KuSu.adapter.DialogsAdapter;
import com.paychi.dima.paychi.KuSu.models.Dialog;
import com.paychi.dima.paychi.KuSu.reaponse.DialogsResponse;
import com.paychi.dima.paychi.R;
import com.paychi.dima.paychi.RestApiClient;
import com.paychi.dima.paychi.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogsActivity extends Activity implements AdapterView.OnItemClickListener {

    User user;
    ProgressBar progressBar;
    ListView listView;
    DialogsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_dialogs);
        user = (User) getIntent().getSerializableExtra(Constants.USER_DATA);
        if (user == null)
            finish();
        setTitle("Диалоги");

        listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        load();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dialogs, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                // TODO: 15.12.2016 создать диалог
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Dialog dialog = adapter.getItem(position);
        Intent dia = new Intent(this, DialogActivity.class);
        dia.putExtra(Constants.DIALOG_DATA, dialog);
        dia.putExtra(Constants.USER_DATA, user);
        startActivityForResult(dia, Constants.DIALOG_ID_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            default:
            case Constants.DIALOG_ID_ACTIVITY:
                load();
                break;
        }
    }

    public void load(){
        progressBar.setVisibility(View.VISIBLE);
        RestApiClient.getInstance().getPayChiService().getDialogs(user.getToken(), user.getUserId()).enqueue(
                new Callback<DialogsResponse>() {
                    @Override
                    public void onResponse(Call<DialogsResponse> call, Response<DialogsResponse> response) {
                        progressBar.setVisibility(View.GONE);
                        if (response.body() != null ){
                            if (response.body().isSuccess()){
                                adapter = new DialogsAdapter(DialogsActivity.this, response.body().getData());
                                listView.setAdapter(adapter);
                            }else{
                                Toast.makeText(DialogsActivity.this, response.body().getErrorText(), Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(DialogsActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<DialogsResponse> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(DialogsActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}
