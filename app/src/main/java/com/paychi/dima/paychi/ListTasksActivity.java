package com.paychi.dima.paychi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.paychi.dima.paychi.adapters.ListTasksAdapter;
import com.paychi.dima.paychi.listeners.OnTaskListClickListener;
import com.paychi.dima.paychi.models.TaskList;
import com.paychi.dima.paychi.models.TaskListWrapper;
import com.paychi.dima.paychi.models.User;
import com.paychi.dima.paychi.responses.TaskListResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListTasksActivity extends AppCompatActivity implements OnTaskListClickListener {
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tasks);

        user = User.getInstance();

        Call<TaskListResponse> callback = RestApiClient.getInstance().getPayChiService().getTaskList(
            user.getToken(), user.getUserId()
        );
        callback.enqueue(new Callback<TaskListResponse>() {
            @Override
            public void onResponse(Call<TaskListResponse> call, Response<TaskListResponse> response) {
                ArrayList<TaskListWrapper> taskLists = response.body().getData();
                ListView lvListTasks = (ListView) findViewById(R.id.lvListTasks);
                lvListTasks.setAdapter(new ListTasksAdapter(ListTasksActivity.this, taskLists, ListTasksActivity.this));
            }

            @Override
            public void onFailure(Call<TaskListResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onTaskListClick(TaskList list) {
//        Toast.makeText(this, String.valueOf(list.getListId()), Toast.LENGTH_SHORT).show();

        Intent intObj = new Intent(this, TaskItemsActivity.class);
        intObj.putExtra("tasklistId", list.getListId());
        startActivity(intObj);
    }

    public void toCreateListTasksActivity(View v) {
        Intent intObj = new Intent(this, CreateListTasksActivity.class);
        startActivity(intObj);
    }
}
