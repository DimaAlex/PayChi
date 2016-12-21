package com.paychi.dima.paychi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.paychi.dima.paychi.adapters.TaskItemsAdapter;
import com.paychi.dima.paychi.listeners.OnTaskItemClickListener;
import com.paychi.dima.paychi.models.TaskItem;
import com.paychi.dima.paychi.models.TaskItemWrapper;
import com.paychi.dima.paychi.models.User;
import com.paychi.dima.paychi.responses.GetTaskItemsResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskItemsActivity extends AppCompatActivity implements OnTaskItemClickListener {
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_items);

        user = User.getInstance();
        long tasklistId = (long) getIntent().getSerializableExtra("tasklistId");

        Call<GetTaskItemsResponse> callback = RestApiClient.getInstance().getPayChiService().getTaskItems(
            user.getToken(), user.getUserId(), tasklistId
        );
        callback.enqueue(new Callback<GetTaskItemsResponse>() {
            @Override
            public void onResponse(Call<GetTaskItemsResponse> call, Response<GetTaskItemsResponse> response) {
                ArrayList<TaskItemWrapper> taskItems = response.body().getData();
                ListView lvListTasks = (ListView) findViewById(R.id.lvTaskItems);
                lvListTasks.setAdapter(new TaskItemsAdapter(TaskItemsActivity.this, taskItems, TaskItemsActivity.this));
            }

            @Override
            public void onFailure(Call<GetTaskItemsResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onTaskItemClick(TaskItem task) {
        Toast.makeText(this, String.valueOf(task.getItemId()), Toast.LENGTH_SHORT).show();
    }

    public void toCreateTaskActivity(View v) {
        Intent intObj = new Intent(this, CreateTaskActivity.class);
        startActivity(intObj);
    }
}
