package com.paychi.dima.paychi.KuSu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.paychi.dima.paychi.KuSu.adapter.MessagesAdapter;
import com.paychi.dima.paychi.KuSu.adapter.UserAdapter;
import com.paychi.dima.paychi.KuSu.models.Dialog;
import com.paychi.dima.paychi.KuSu.models.Message;
import com.paychi.dima.paychi.KuSu.reaponse.EmptyResponse;
import com.paychi.dima.paychi.KuSu.reaponse.MessagesResponse;
import com.paychi.dima.paychi.R;
import com.paychi.dima.paychi.RestApiClient;
import com.paychi.dima.paychi.models.User;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by KuSu on 15.12.2016.
 */

public class UserListActivity extends Activity{

    ArrayList<User> users;
    String title;
    ListView listView;
    UserAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_dialogs);
        users = (ArrayList<User>) getIntent().getSerializableExtra(Constants.USERS_DATA);
        title = getIntent().getStringExtra(Constants.TITLE_DATA);
        if (users == null)
            finish();
        setTitle(title);

        findViewById(R.id.progressBar).setVisibility(View.GONE);
        listView = (ListView) findViewById(R.id.list);
        adapter = new UserAdapter(UserListActivity.this, users);
        listView.setAdapter(adapter);
    }
}
