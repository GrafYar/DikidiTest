package ru.dikidi.dikiditest.ui.activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.dikidi.dikiditest.R;
import ru.dikidi.dikiditest.data.network.resources.MainListRes;
import ru.dikidi.dikiditest.data.network.services.RetrofitService;
import ru.dikidi.dikiditest.ui.adapters.UsersAdapter;
import ru.dikidi.dikiditest.ui.fragments.AppointmentFragment;
import ru.dikidi.dikiditest.ui.fragments.MainFragment;
import ru.dikidi.dikiditest.ui.fragments.ShareAppFragment;
import ru.dikidi.dikiditest.ui.fragments.SharesFragment;
import ru.dikidi.dikiditest.ui.fragments.SupportFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView mRecyclerView;
    Fragment mFrag;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    List<MainListRes.Category> post;
    FragmentTransaction fTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();

        openMainFragment();


//        Fragment frag1 = new CategoriesFragment();
//
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.lnrlay, frag1);
//        fragmentTransaction.commit();
//
//
//
//
//        RetrofitService.getInstance()
//                .getJSONApi()
//                .getMainJson(468902)
//                .enqueue(new Callback<MainListRes>() {
//                    @Override
//                    public void onResponse(Call<MainListRes> call, Response<MainListRes> response) {
//
//                        try {
//                            // post = (List<UserListRes.New>) response.body().getData().getBlocks().getNew();
//                            post = response.body().getData().getBlocks().getCategories();
//                            //String mText = response.body().getData().getBlocks().getNew().get(0).getId();
//
//                            mRecyclerView = (RecyclerView) findViewById(R.id.user_list);
//                            LinearLayoutManager layoutManager
//                                    = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
//                            mRecyclerView.setLayoutManager(layoutManager);
//
//                            UsersAdapter mUserAdapter = new UsersAdapter(post);
//                            mRecyclerView.setAdapter(mUserAdapter);
//
//
//
//                        } catch (NullPointerException e) {
//                            Toast toast = Toast.makeText(getApplicationContext(),
//                                    "Хрен там",
//                                    Toast.LENGTH_LONG);
//                            toast.setGravity(Gravity.CENTER, 0, 0);
//                            toast.show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<MainListRes> call, Throwable t) {
//                        Toast toast = Toast.makeText(getApplicationContext(),
//                                "Провал ",
//                                Toast.LENGTH_LONG);
//                        toast.setGravity(Gravity.CENTER, 0, 0);
//                        toast.show();
//                    }
//                });








//        RetrofitService.getInstance()
//                .getJSONApi()
//                .getJson(468902)
//                .enqueue(new Callback<UserListRes>() {
//                    @Override
//                    public void onResponse(Call<UserListRes> call, Response<UserListRes> response) {
//
//                        try {
//                            // post = (List<UserListRes.New>) response.body().getData().getBlocks().getNew();
//                            post = response.body().getData().getBlocks().getNew();
//                            String mText = response.body().getData().getBlocks().getNew().get(0).getId();
//
//                            mRecyclerView = (RecyclerView) findViewById(R.id.user_list);
//                            LinearLayoutManager layoutManager
//                                    = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL, false);
//                            mRecyclerView.setLayoutManager(layoutManager);
//
//                            UsersAdapter mUserAdapter = new UsersAdapter(post);
//                            mRecyclerView.setAdapter(mUserAdapter);
//                             //String mTest = response.body().getData().getBlocks().getNew();
////                           UsersAdapter mUserAdapter = new UsersAdapter(post);
////                            mRecyclerView.setAdapter(mUserAdapter);
//
//
////                            TextView mText = findViewById(R.id.hello_world);
////                            mText.setText(post);
////                            Toast toast = Toast.makeText(getApplicationContext(),
////                                    "Тест " + post.getData().getTitle(),
////                                    Toast.LENGTH_LONG);
////                            toast.setGravity(Gravity.CENTER, 0, 0);
////                            toast.show();
//                        } catch (NullPointerException e) {
//                            Toast toast = Toast.makeText(getApplicationContext(),
//                                    "Хрен там",
//                                    Toast.LENGTH_LONG);
//                            toast.setGravity(Gravity.CENTER, 0, 0);
//                            toast.show();
//                        }
//
//
//                        //textView.append(post.getId() + "\n");
//                        //textView.append(post.getUserId() + "\n");
//                        //textView.append(post.getTitle() + "\n");
//                        //textView.append(post.getBody() + "\n");
//                    }
//
//                    @Override
//                    public void onFailure(Call<UserListRes> call, Throwable t) {
//                        Toast toast = Toast.makeText(getApplicationContext(),
//                                "Провал ",
//                                Toast.LENGTH_LONG);
//                        toast.setGravity(Gravity.CENTER, 0, 0);
//                        toast.show();
//                    }
//                });













    }

    public void openMainFragment (){

       mFrag = new MainFragment();
       mFragmentTransaction = mFragmentManager.beginTransaction();
       mFragmentTransaction.replace(R.id.lnrlay, mFrag);
       mFragmentTransaction.commit();





        RetrofitService.getInstance()
                .getJSONApi()
                .getMainJson(468902)
                .enqueue(new Callback<MainListRes>() {
                    @Override
                    public void onResponse(Call<MainListRes> call, Response<MainListRes> response) {

                        try {
                            // post = (List<UserListRes.New>) response.body().getData().getBlocks().getNew();
                            post = response.body().getData().getBlocks().getCategories();
                            //String mText = response.body().getData().getBlocks().getNew().get(0).getId();

                            mRecyclerView = (RecyclerView) findViewById(R.id.user_list);
                            LinearLayoutManager layoutManager
                                    = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
                            mRecyclerView.setLayoutManager(layoutManager);

                            UsersAdapter mUserAdapter = new UsersAdapter(post);
                            mRecyclerView.setAdapter(mUserAdapter);



                        } catch (NullPointerException e) {
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Хрен там",
                                    Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MainListRes> call, Throwable t) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Провал ",
                                Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                });

    }

    public void openSharesFragment () {

        mFrag = new SharesFragment();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.lnrlay, mFrag);
        mFragmentTransaction.commit();

    }

    public void openAppointmentFragment () {

        mFrag = new AppointmentFragment();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.lnrlay, mFrag);
        mFragmentTransaction.commit();

    }

    public void openShareAppFragment () {

        mFrag = new ShareAppFragment();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.lnrlay, mFrag);
        mFragmentTransaction.commit();

    }

    public void openSupportFragment () {

        mFrag = new SupportFragment();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.lnrlay, mFrag);
        mFragmentTransaction.commit();

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_main) {

            openMainFragment();

        } else if (id == R.id.nav_shares) {

            openSharesFragment();

        } else if (id == R.id.nav_appointments) {

            openAppointmentFragment();

        } else if (id == R.id.nav_share) {

            openShareAppFragment();

        } else if (id == R.id.nav_support) {

            openSupportFragment();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
