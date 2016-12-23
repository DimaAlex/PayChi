package com.paychi.dima.paychi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.paychi.dima.paychi.models.TaskItem;
import com.paychi.dima.paychi.models.User;
import com.paychi.dima.paychi.responses.CreateTaskItemResponse;

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
        EditText titleInput = (EditText) findViewById(R.id.et_title);
        String title = titleInput.getText().toString();

        EditText descInput = (EditText) findViewById(R.id.et_description);
        String desc = descInput.getText().toString();

        EditText costInput = (EditText) findViewById(R.id.et_cost);
        String cost = costInput.getText().toString();

        RadioGroup targetRG = (RadioGroup) findViewById(R.id.rg_target);
        RadioButton targetRB = (RadioButton) findViewById(targetRG.getCheckedRadioButtonId());
        int target = getTargetByValue(targetRB.getText().toString());

        user = User.getInstance();
        long tasklistId = (long) getIntent().getSerializableExtra("tasklistId");
        TaskItem task = new TaskItem(title, desc, cost, tasklistId, 1, target);

        Call<CreateTaskItemResponse> callback = RestApiClient.getInstance().getPayChiService().createTaskItem(
            user.getToken(), user.getUserId(), task
        );
        callback.enqueue(new Callback<CreateTaskItemResponse>() {
            @Override
            public void onResponse(Call<CreateTaskItemResponse> call, Response<CreateTaskItemResponse> response) {
                CreateTaskItemResponse body = response.body();
                if (body.getErrorCode() == 0) {
                    Intent intent = new Intent(CreateTaskActivity.this, TaskItemsActivity.class);
                    intent.putExtra("tasklistId", body.getData().getListId());
                    startActivity(intent);
                }
                else {
                    Toast.makeText(CreateTaskActivity.this, body.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CreateTaskItemResponse> call, Throwable t) {

            }
        });
    }

    private int getTargetByValue(String value) {
        switch (value) {
            case "Публичный":
                return 1;
            case "Приватный":
                return 2;
        }
        return 0;
    }
}
