package ru.dikidi.dikiditest.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import ru.dikidi.dikiditest.R;
import ru.dikidi.dikiditest.ui.adapters.CatalogAdapter;
import ru.dikidi.dikiditest.ui.adapters.CategoryAdapter;
import ru.dikidi.dikiditest.ui.adapters.MainAdapter;
import ru.dikidi.dikiditest.ui.adapters.SharesAdapter;
import ru.dikidi.dikiditest.ui.fragments.AppointmentFragment;
import ru.dikidi.dikiditest.ui.fragments.MainFragment;
import ru.dikidi.dikiditest.ui.fragments.ShareAppFragment;
import ru.dikidi.dikiditest.ui.fragments.SharesFragment;
import ru.dikidi.dikiditest.ui.fragments.SupportFragment;
import ru.dikidi.dikiditest.utilits.NetworkStatusChecker;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        CatalogAdapter.CatalogAdapterViewHolder.CatalogItemClickListener,
        MainAdapter.CatalogViewHolder.CatalogButtonMoreClickListener,
        CategoryAdapter.CategoryAdapterViewHolder.CategoryItemClickListener,
        SharesAdapter.SharesAdapterViewHolder.SharesItemClickListener,
        MainAdapter.SharesViewHolder.SharesButtonMoreClickListener {

    private CoordinatorLayout mCoordinatorLayout;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private AppBarLayout mAppBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mCoordinatorLayout = findViewById(R.id.coordinator_layout);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mCollapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
        mCollapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        mCollapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);

        mAppBarLayout = findViewById(R.id.app_bar_layout);

        openMainFragment();

        navigationView.getMenu().getItem(0).setChecked(true);
    }

    public void openMainFragment (){

    if(NetworkStatusChecker.isNetworkAvailable(this)) {

        Fragment fragment = new MainFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_main_lnrlayout, fragment).commit();

    } else {
        Snackbar.make(mCoordinatorLayout,"Нет подключения", Snackbar.LENGTH_LONG).show();
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
        Fragment fragment = new ShareAppFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_main_lnrlayout, fragment).commit();

    }

    public void openSupportFragment () {
        Fragment fragment = new SupportFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_main_lnrlayout, fragment).commit();
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_location) {
            Intent intent = new Intent(this, LocationActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_main) {
            openMainFragment();
            unCollapseAppBar();
            unLockAppBar();
          //  setActionBarTitle(getString(R.string.main_fragment_title));
        } else if (id == R.id.nav_shares) {
            openSharesFragment();
            collapseAppBar();
            lockAppBar();
            setActionBarTitle(getString(R.string.shares_fragment_title));
        } else if (id == R.id.nav_appointments) {
            openAppointmentFragment();
            collapseAppBar();
            lockAppBar();
            setActionBarTitle(getString(R.string.appointment_fragment_title));
        } else if (id == R.id.nav_share) {
            openShareAppFragment();
            collapseAppBar();
            lockAppBar();
            setActionBarTitle(getString(R.string.share_app_fragment_title));
        } else if (id == R.id.nav_business) {
            openBusinessFragment();
            collapseAppBar();
            lockAppBar();
            setActionBarTitle(getString(R.string.business_fragment_title));
        } else if (id == R.id.nav_support) {
            openSupportFragment();
            collapseAppBar();
            lockAppBar();
            setActionBarTitle(getString(R.string.support_fragment_title));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void collapseAppBar() {
        mAppBarLayout.setExpanded(false, true);
    }


    private void unCollapseAppBar() {
        mAppBarLayout.setExpanded(true, true);
    }


    private void lockAppBar() {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mAppBarLayout.getLayoutParams();
        AppBarLayout.Behavior behavior = (AppBarLayout.Behavior) params.getBehavior();
        behavior.setDragCallback(new AppBarLayout.Behavior.DragCallback() {
            @Override
            public boolean canDrag(AppBarLayout appBarLayout) {
                return false;
            }
        });
    }


    private void unLockAppBar() {
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

    @Override
    public void onCatalogItemClickListener(boolean isAdv) {
        if (isAdv){

            openBusinessFragment();
        } else {
            Intent catalogItemIntent = new Intent(MainActivity.this, CatalogItemActivity.class);
            startActivity(catalogItemIntent);
        }
    }

    @Override
    public void onCatalogButtonMoreClickListener(int position) {
        Intent catalogIntent = new Intent(MainActivity.this, CatalogActivity.class);
        startActivity(catalogIntent);
    }

    @Override
    public void onCategoryItemClickListener(int position) {
        Intent catalogIntent = new Intent(MainActivity.this, CategoryItemActivity.class);
        startActivity(catalogIntent);
    }

    @Override
    public void onSharesItemClickListener(int position) {
        Intent catalogIntent = new Intent(MainActivity.this, SharesItemActivity.class);
        startActivity(catalogIntent);
    }

    @Override
    public void onSharesButtonMoreClickListener(int position) {
        Intent catalogIntent = new Intent(MainActivity.this, SharesActivity.class);
        startActivity(catalogIntent);
    }

    public void setActionBarTitle(String title) {
        mCollapsingToolbarLayout.setTitle(title);
    }


    public void setActionBarImage(String url) {
        ImageView icon = findViewById(R.id.title_image);

        Picasso.with(getBaseContext())
                .load(url)
                .error(getBaseContext().getResources().getDrawable(R.drawable.placeholder))
                .into(icon);
    }
}
