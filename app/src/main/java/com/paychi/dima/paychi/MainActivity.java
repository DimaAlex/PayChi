package com.paychi.dima.paychi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.paychi.dima.paychi.KuSu.Constants;
import com.paychi.dima.paychi.KuSu.DialogsActivity;
import com.paychi.dima.paychi.models.User;

public class MainActivity extends AppCompatActivity {

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String token = "2580123";
        long userId = 6;
        user = new User(userId, token);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    public void toAuthorizationActivity(View v) {
        Intent intObj = new Intent(this, AuthorizationActivity.class);
        startActivity(intObj);
    }

    public void toListTasksActivity(View v) {
        Intent intObj = new Intent(this, ListTasksActivity.class);
        startActivity(intObj);
    }

    public void toDialogsActivity(View v) {
        Intent dialogs = new Intent(this, DialogsActivity.class);
        dialogs.putExtra(Constants.USER_DATA, user);
        startActivity(dialogs);
    }

    public void toWishListsActivity(View v) {
        Intent wl = new Intent(this, WishListsActivity.class);
        startActivity(wl);
    }
}
