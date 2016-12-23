package com.paychi.dima.paychi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.paychi.dima.paychi.adapters.UserListAdapter;
import com.paychi.dima.paychi.models.TaskList;
import com.paychi.dima.paychi.models.User;
import com.paychi.dima.paychi.responses.CreateTasksListResponse;
import com.paychi.dima.paychi.responses.getUsersResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateListTasksActivity extends AppCompatActivity {
    User user;
    List<User> users;
    UserListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list_tasks);

        user = User.getInstance();
        Call<getUsersResponse> callback = RestApiClient.getInstance().getPayChiService().getUsers(
            user.getToken(), user.getUserId()
        );
        callback.enqueue(new Callback<getUsersResponse>() {
            @Override
            public void onResponse(Call<getUsersResponse> call, Response<getUsersResponse> response) {
                users = response.body().getData();
            }

            @Override
            public void onFailure(Call<getUsersResponse> call, Throwable t) {

            }
        });
    }

    public void initList(List<User> usrs) {
        ListView lvUserList = (ListView) findViewById(R.id.lv_user_list);
        lvUserList.setVisibility(View.VISIBLE);
        adapter = new UserListAdapter(this, usrs);
        lvUserList.setAdapter(adapter);
    }

    public void deInitList() {
        ListView lvUserList = (ListView) findViewById(R.id.lv_user_list);
        lvUserList.setVisibility(View.GONE);
        adapter = null;
        lvUserList.setAdapter(null);
    }

    protected void сreateListTasks(View v) {
        EditText nameInput = (EditText) findViewById(R.id.et_name);
        String name = nameInput.getText().toString();

        EditText descInput = (EditText) findViewById(R.id.et_description);
        String desc = descInput.getText().toString();

        RadioGroup visibilityRG = (RadioGroup) findViewById(R.id.rg_visibility);
        RadioButton visibilityRB = (RadioButton) findViewById(visibilityRG.getCheckedRadioButtonId());
        long visibility = getVisibilityByValue(visibilityRB.getText().toString());

        user = User.getInstance();
        TaskList taskList = new TaskList(name, user.getUserId(), visibility, desc);

        Call<CreateTasksListResponse> callback = RestApiClient.getInstance().getPayChiService().createTaskList(
            user.getToken(), user.getUserId(), taskList
        );
        callback.enqueue(new Callback<CreateTasksListResponse>() {
            @Override
            public void onResponse(Call<CreateTasksListResponse> call, Response<CreateTasksListResponse> response) {
                CreateTasksListResponse body = response.body();
                if (body.getErrorCode() == 0) {
                    Intent intent = new Intent(CreateListTasksActivity.this, ListTasksActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(CreateListTasksActivity.this, body.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CreateTasksListResponse> call, Throwable t) {

            }
        });
    }

    protected void OnRBChooseClick(View v) {
        initList(users);
    }

    protected void OnRBAllOrNobodyClick(View v) {
        deInitList();
    }

    private int getVisibilityByValue(String value) {
        switch (value) {
            case "Все":
                return 1;
            case "Никто":
                return 2;
            case "Выбрать":
                return 3;
        }
        return 0;
    }
}
