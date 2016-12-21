package com.paychi.dima.paychi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.paychi.dima.paychi.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateTaskActivity extends AppCompatActivity {
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
    }

    protected void сreateTaskItem(View v) {
        EditText nameInput = (EditText) findViewById(R.id.et_name);
        String name = nameInput.getText().toString();

        EditText descInput = (EditText) findViewById(R.id.et_description);
        String desc = descInput.getText().toString();

        RadioGroup visibilityRG = (RadioGroup) findViewById(R.id.rg_visibility);
        RadioButton visibilityRB = (RadioButton) findViewById(visibilityRG.getCheckedRadioButtonId());
        long visibility = getVisibilityByValue(visibilityRB.getText().toString());

        user = User.getInstance();
//        TaskList taskList = new TaskList(name, user.getUserId(), visibility, desc);
//
//        Call<CreateTasksListResponse> callback = RestApiClient.getInstance().getPayChiService().createTaskList(
//            user.getToken(), user.getUserId(), taskList
//        );
//        callback.enqueue(new Callback<>() {
//            @Override
//            public void onResponse(Call<CreateTasksListResponse> call, Response<CreateTasksListResponse> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<CreateTasksListResponse> call, Throwable t) {
//
//            }
//        });
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
