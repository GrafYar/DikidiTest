package ru.dikidi.dikiditest.ui.activities;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.dikidi.dikiditest.R;
import ru.dikidi.dikiditest.data.network.resources.UserListRes;
import ru.dikidi.dikiditest.data.network.services.RetrofitService;
import ru.dikidi.dikiditest.ui.adapters.UsersAdapter;

public class UserListActivity extends AppCompatActivity {

    private CoordinatorLayout mCoordinatorLayout;
    private Toolbar mToolbar;
    private DrawerLayout mNavigationDrawer;

    RecyclerView mRecyclerView;
    List<UserListRes.New> post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        //mToolbar = findViewById(R.id.toolbar);
        // setSupportActionBar(mToolbar);

        mNavigationDrawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);

        mRecyclerView = (RecyclerView) findViewById(R.id.user_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));



        RetrofitService.getInstance()
                .getJSONApi()
                .getJson(468902)
                .enqueue(new Callback<UserListRes>() {
                    @Override
                    public void onResponse(Call<UserListRes> call, Response<UserListRes> response) {

                        try {
                            // post = (List<UserListRes.New>) response.body().getData().getBlocks().getNew();
                            post = response.body().getData().getBlocks().getNew();
                            String mText = response.body().getData().getBlocks().getNew().get(0).getId();
                            //String mTest = response.body().getData().getBlocks().getNew();
                           UsersAdapter mUserAdapter = new UsersAdapter(post);
                            mRecyclerView.setAdapter(mUserAdapter);


//                            TextView mText = findViewById(R.id.hello_world);
//                            mText.setText(post);
//                            Toast toast = Toast.makeText(getApplicationContext(),
//                                    "Тест " + post.getData().getTitle(),
//                                    Toast.LENGTH_LONG);
//                            toast.setGravity(Gravity.CENTER, 0, 0);
//                            toast.show();
                        } catch (NullPointerException e) {
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Хрен там",
                                    Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                        }


                        //textView.append(post.getId() + "\n");
                        //textView.append(post.getUserId() + "\n");
                        //textView.append(post.getTitle() + "\n");
                        //textView.append(post.getBody() + "\n");
                    }


                    @Override
                    public void onFailure(Call<UserListRes> call, Throwable t) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Провал ",
                                Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                });
    }
}
