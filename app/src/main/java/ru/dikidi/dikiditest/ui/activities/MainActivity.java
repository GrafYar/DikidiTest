package ru.dikidi.dikiditest.ui.activities;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.dikidi.dikiditest.R;
import ru.dikidi.dikiditest.data.network.resources.ItemList;
import ru.dikidi.dikiditest.data.network.resources.MainListRes;
import ru.dikidi.dikiditest.data.network.services.RetrofitService;
import ru.dikidi.dikiditest.ui.adapters.CategoryAdapter;
import ru.dikidi.dikiditest.ui.adapters.MainAdapter;
import ru.dikidi.dikiditest.ui.adapters.CatalogAdapter;
import ru.dikidi.dikiditest.ui.adapters.SharesAdapter;
import ru.dikidi.dikiditest.ui.adapters.CatalogAdapter.CatalogAdapterViewHolder.CatalogItemClickListener;
import ru.dikidi.dikiditest.ui.adapters.MainAdapter.CatalogViewHolder.CatalogButtonMoreClickListener;
import ru.dikidi.dikiditest.ui.adapters.CategoryAdapter.CategoryAdapterViewHolder.CategoryItemClickListener;
import ru.dikidi.dikiditest.ui.adapters.SharesAdapter.SharesAdapterViewHolder.SharesItemClickListener;
import ru.dikidi.dikiditest.ui.adapters.MainAdapter.SharesViewHolder.SharesButtonMoreClickListener;
import ru.dikidi.dikiditest.ui.fragments.AppointmentFragment;
import ru.dikidi.dikiditest.ui.fragments.BusinessFragment;
import ru.dikidi.dikiditest.ui.fragments.MainFragment;
import ru.dikidi.dikiditest.ui.fragments.ShareAppFragment;
import ru.dikidi.dikiditest.ui.fragments.SharesFragment;
import ru.dikidi.dikiditest.ui.fragments.SupportFragment;
import ru.dikidi.dikiditest.utilits.NetworkStatusChecker;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Integer mCityId = 468902;
    RecyclerView mRecyclerView;
    CoordinatorLayout mCoordinatorLayout;
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    AppBarLayout mAppBarLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mRecyclerView = findViewById(R.id.data_list);

        mCollapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
        mCollapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        mCollapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);

        mAppBarLayout = findViewById(R.id.app_bar_layout);

        setTitle(getString(R.string.app_title));

        openMainFragment();

        navigationView.getMenu().getItem(0).setChecked(true);
    }

    public void openMainFragment (){

    if(NetworkStatusChecker.isNetworkAvailable(this)) {

    Fragment fragment = new MainFragment();
    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
    fragmentTransaction.replace(R.id.content_main_lnrlayout, fragment).commit();

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

        Fragment fragment = new SharesFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_main_lnrlayout, fragment).commit();
    }

    public void openAppointmentFragment () {

        Fragment fragment = new AppointmentFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_main_lnrlayout, fragment).commit();

    }

    public void openShareAppFragment () {

        Fragment fragment = new ShareAppFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_main_lnrlayout, fragment).commit();

    }

    public void openBusinessFragment () {
        Fragment fragment = new BusinessFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_main_lnrlayout, fragment).commit();

    }

    public void openSupportFragment () {
        Fragment fragment = new SupportFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //mFragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_main_lnrlayout, fragment).commit();
    }

    public void loadData() {
        RetrofitService.getInstance()
                .getJSONApi()
                .getMainJson(mCityId)
                .enqueue(new Callback<MainListRes>() {
                    @Override
                    public void onResponse(Call<MainListRes> call, Response<MainListRes> response) {

                        try {

                            ArrayList<ArrayList<ItemList>> listAll = new ArrayList<>();
                            ArrayList<ItemList> shares = new ArrayList<>();
                            ArrayList<ItemList> category = new ArrayList<>();
                            ArrayList<ItemList> catalog = new ArrayList<>();

                            shares.addAll(response.body().getData().getBlocks().getShares().getList());
                            category.addAll(response.body().getData().getBlocks().getCategories());
                            catalog.addAll(response.body().getData().getBlocks().getCatalog());
                            String catalogCount = response.body().getData().getCatalogCount();

                            listAll.add(shares);
                            listAll.add(category);
                            listAll.add(catalog);

                            mRecyclerView = findViewById(R.id.data_list);
                            LinearLayoutManager layoutManager
                                    = new LinearLayoutManager(getBaseContext());
                            mRecyclerView.setLayoutManager(layoutManager);

                            MainAdapter mainAdapter = new MainAdapter(getBaseContext(), listAll, shares, category, catalog, catalogCount,
                                    new CatalogItemClickListener() {
                                        @Override
                                        public void onCatalogItemClickListener(boolean isAdv) {
                                            if (isAdv){

                                                openBusinessFragment();
                                            } else {
                                                Intent catalogItemIntent = new Intent(MainActivity.this, CatalogItemActivity.class);
                                                startActivity(catalogItemIntent);
                                            }
                                        }
                                    },
                                    new CatalogButtonMoreClickListener() {
                                        @Override
                                        public void onCatalogButtonMoreClickListener(int position) {
                                            Intent catalogIntent = new Intent(MainActivity.this, CatalogActivity.class);
                                            startActivity(catalogIntent);
                                        }
                                    },
                                    new CategoryItemClickListener() {
                                        @Override
                                        public void onCategoryItemClickListener(int position) {
                                            Intent catalogIntent = new Intent(MainActivity.this, CategoryItemActivity.class);
                                            startActivity(catalogIntent);
                                        }
                                    },
                                    new SharesItemClickListener() {
                                        @Override
                                        public void onSharesItemClickListener(int position) {
                                            Intent catalogIntent = new Intent(MainActivity.this, SharesItemActivity.class);
                                            startActivity(catalogIntent);
                                        }
                                    },
                                    new SharesButtonMoreClickListener() {
                                        @Override
                                        public void onSharesButtonMoreClickListener(int position) {
                                            Intent catalogIntent = new Intent(MainActivity.this, SharesActivity.class);
                                            startActivity(catalogIntent);
                                        }
                                    });


                            mRecyclerView.setAdapter(mainAdapter);


                        } catch (NullPointerException e) {
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Ошибка",
                                    Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MainListRes> call, Throwable t) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Ошибка запроса",
                                Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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
        if (id == R.id.action_location) {

            Intent intent = new Intent(this, LocationActivity.class);
            startActivity(intent);
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
            unCollapseAppBar();
            unLockAppBar();
        } else if (id == R.id.nav_shares) {
            openSharesFragment();
            collapseAppBar();
            lockAppBar();
        } else if (id == R.id.nav_appointments) {
            openAppointmentFragment();
            collapseAppBar();
            lockAppBar();
        } else if (id == R.id.nav_share) {
            openShareAppFragment();
            collapseAppBar();
            lockAppBar();
        } else if (id == R.id.nav_business) {
            openShareAppFragment();
        } else if (id == R.id.nav_support) {
            openSupportFragment();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Скрывает AppBar
    private void collapseAppBar() {
        mAppBarLayout.setExpanded(false, true);
    }

    // Раскрывает AppBar
    private void unCollapseAppBar() {
        mAppBarLayout.setExpanded(true, true);
    }

    //Блокирует раскрываение AppBar
    private void lockAppBar() {
    //Блокируем AppBar от раскрывания
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mAppBarLayout.getLayoutParams();
        AppBarLayout.Behavior behavior = (AppBarLayout.Behavior) params.getBehavior();
        behavior.setDragCallback(new AppBarLayout.Behavior.DragCallback() {
            @Override
            public boolean canDrag(AppBarLayout appBarLayout) {
                return false;
            }
        });
    }

    //Разблокирует раскрываение AppBar
    private void unLockAppBar() {
        //Разблокируем AppBar от раскрывания
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mAppBarLayout.getLayoutParams();
        AppBarLayout.Behavior behavior = (AppBarLayout.Behavior) params.getBehavior();
        if (behavior != null) {
            behavior.setDragCallback(new AppBarLayout.Behavior.DragCallback() {
                @Override
                public boolean canDrag(AppBarLayout appBarLayout) {
                    return true;
                }
            });
        }
    }
}
