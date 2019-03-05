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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.dikidi.dikiditest.R;
import ru.dikidi.dikiditest.data.network.resources.AbstractListRes;
import ru.dikidi.dikiditest.data.network.resources.CatalogListRes;
import ru.dikidi.dikiditest.data.network.resources.ItemList;
import ru.dikidi.dikiditest.data.network.resources.MainListRes;
import ru.dikidi.dikiditest.data.network.resources.SharesListRes;
import ru.dikidi.dikiditest.data.network.services.RetrofitService;
import ru.dikidi.dikiditest.ui.adapters.MainAdapter;
import ru.dikidi.dikiditest.ui.adapters.MainCatalogAdapter;
import ru.dikidi.dikiditest.ui.fragments.AppointmentFragment;
import ru.dikidi.dikiditest.ui.fragments.MainFragment;
import ru.dikidi.dikiditest.ui.fragments.ShareAppFragment;
import ru.dikidi.dikiditest.ui.fragments.SharesFragment;
import ru.dikidi.dikiditest.ui.fragments.SupportFragment;
import ru.dikidi.dikiditest.utilits.NetworkStatusChecker;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Integer mCityId = 468902;
    RecyclerView mRecyclerView;
    Fragment mFrag;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    List<SharesListRes.ListShares> post;
    List<MainListRes.Category> post2;
    List<CatalogListRes> post3;
    ArrayList<ArrayList<ItemList>> mList = new ArrayList();
    ArrayList<ItemList> mShares = new ArrayList();
    ArrayList<ItemList> mCategory = new ArrayList();
    ArrayList<ItemList> mCatalog = new ArrayList();


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

    }

    public void openMainFragment (){

    if(NetworkStatusChecker.isNetworkAvailable(this)) {

    mFrag = new MainFragment();
    mFragmentManager = getSupportFragmentManager();
    mFragmentTransaction = mFragmentManager.beginTransaction();
    mFragmentTransaction.replace(R.id.content_main_lnrlayout, mFrag);
    mFragmentTransaction.commit();

    loadData();

    } else {
        Toast toast = Toast.makeText(getApplicationContext(),
                "Нет подключения",
                Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    }

    public void openSharesFragment () {

        if (NetworkStatusChecker.isNetworkAvailable(this)) {
            mFrag = new SharesFragment();
            mFragmentManager = getSupportFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.content_main_lnrlayout, mFrag);
            mFragmentTransaction.commit();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Нет подключения",
                    Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    public void openAppointmentFragment () {
        if (NetworkStatusChecker.isNetworkAvailable(this)) {
            mFrag = new AppointmentFragment();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.content_main_lnrlayout, mFrag);
            mFragmentTransaction.commit();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Нет подключения",
                    Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    public void openShareAppFragment () {
        if (NetworkStatusChecker.isNetworkAvailable(this)) {
            mFrag = new ShareAppFragment();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.content_main_lnrlayout, mFrag);
            mFragmentTransaction.commit();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Нет подключения",
                    Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    public void openSupportFragment () {
        if (NetworkStatusChecker.isNetworkAvailable(this)) {
            mFrag = new SupportFragment();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.content_main_lnrlayout, mFrag);
            mFragmentTransaction.commit();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Нет подключения",
                    Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    public void loadData() {
        RetrofitService.getInstance()
                .getJSONApi()
                .getMainJson(mCityId)
                .enqueue(new Callback<MainListRes>() {
                    @Override
                    public void onResponse(Call<MainListRes> call, Response<MainListRes> response) {

                        try {
                            mCategory.addAll(response.body().getData().getBlocks().getCategories());

                            mShares.addAll(response.body().getData().getBlocks().getShares().getList());
                            //post = response.body().Blocks();


                            //post2.getType();

                            post3 = response.body().getData().getBlocks().getCatalog();

                          //  mShares.addAll(post);
                          //  mCategory.addAll(post2);

                            //  mShares

                            mList.add(mShares);
                            mList.add(mCategory);
                            //                     mList.addAll(post2);
                            // mList.addAll(post3);

//                            mList2.addAll( post2);


                            //  mCatalog.addAll(post3);

                            mRecyclerView = findViewById(R.id.user_list);
//                            LinearLayoutManager layoutManager
//                                    = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
                            LinearLayoutManager layoutManager
                                    = new LinearLayoutManager(getBaseContext());
                            mRecyclerView.setLayoutManager(layoutManager);

                            MainAdapter mUserAdapter = new MainAdapter(getBaseContext(), mList, mShares, mCategory);
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
                                "Провал",
                                Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                });
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
