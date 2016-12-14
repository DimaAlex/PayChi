package com.paychi.dima.paychi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.paychi.dima.paychi.models.TaskList;
import com.paychi.dima.paychi.models.TaskListWrapper;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateListTasksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list_tasks);
    }

    protected void сreateListTasks(View v) {
        EditText nameInput = (EditText) findViewById(R.id.et_name);
        String name = nameInput.getText().toString();

        EditText descInput = (EditText) findViewById(R.id.et_description);
        String desc = descInput.getText().toString();

        RadioGroup visibilityRG = (RadioGroup) findViewById(R.id.rg_visibility);
        RadioButton visibilityRB = (RadioButton) findViewById(visibilityRG.getCheckedRadioButtonId());
        long visibility = getVisibilityByValue(visibilityRB.getText().toString());

        String token = "2580123";
        long userId = 6;

        TaskList taskList = new TaskList(name, userId, visibility,desc);

        Call<TasksListItemResponse> callback = RestApiClient.getInstance().getPayChiService().createTaskList(token, userId, taskList);
        callback.enqueue(new Callback<TasksListItemResponse>() {
            @Override
            public void onResponse(Call<TasksListItemResponse> call, Response<TasksListItemResponse> response) {

            }

            @Override
            public void onFailure(Call<TasksListItemResponse> call, Throwable t) {

            }
        });
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
