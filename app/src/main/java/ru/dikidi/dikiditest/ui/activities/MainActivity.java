package ru.dikidi.dikiditest.ui.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import ru.dikidi.dikiditest.R;
import ru.dikidi.dikiditest.ui.adapters.CatalogAdapter;
import ru.dikidi.dikiditest.ui.adapters.CategoryAdapter;
import ru.dikidi.dikiditest.ui.adapters.MainAdapter;
import ru.dikidi.dikiditest.ui.adapters.SharesAdapter;
import ru.dikidi.dikiditest.ui.fragments.AppointmentFragment;
import ru.dikidi.dikiditest.ui.fragments.CatalogItemFragment;
import ru.dikidi.dikiditest.ui.fragments.CatalogMoreFragment;
import ru.dikidi.dikiditest.ui.fragments.CategoryItemFragment;
import ru.dikidi.dikiditest.ui.fragments.LocationFragment;
import ru.dikidi.dikiditest.ui.fragments.MainFragment;
import ru.dikidi.dikiditest.ui.fragments.ShareAppFragment;
import ru.dikidi.dikiditest.ui.fragments.SharesFragment;
import ru.dikidi.dikiditest.ui.fragments.SharesItemFragment;
import ru.dikidi.dikiditest.ui.fragments.SharesMoreFragment;
import ru.dikidi.dikiditest.ui.fragments.SupportFragment;
import ru.dikidi.dikiditest.utilits.ConstantManager;
import ru.dikidi.dikiditest.utilits.NetworkStatusChecker;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        CatalogAdapter.CatalogAdapterViewHolder.CatalogItemClickListener,
        MainAdapter.CatalogViewHolder.CatalogButtonMoreClickListener,
        CategoryAdapter.CategoryAdapterViewHolder.CategoryItemClickListener,
        SharesAdapter.SharesAdapterViewHolder.SharesItemClickListener,
        MainAdapter.SharesViewHolder.SharesButtonMoreClickListener,
        View.OnClickListener {

    private static final String TAG = ConstantManager.TAG_PREFIX + " MainActivity";
    private CoordinatorLayout mCoordinatorLayout;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private AppBarLayout mAppBarLayout;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigationView;
    private Menu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mCoordinatorLayout = findViewById(R.id.coordinator_layout);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        mCollapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
        mCollapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        mCollapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);

        mAppBarLayout = findViewById(R.id.app_bar_layout);

        openMainFragment();

        mNavigationView.getMenu().getItem(0).setChecked(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mMenu = menu;
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, mMenu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_location) {
            openLocationFragment();
        } else if (id == R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
        } else if (id == R.id.nav_business) {
            openBusinessFragment();
        } else if (id == R.id.nav_support) {
            openSupportFragment();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            enableNavigationDrawer();
            unCollapseAppBar();
            unLockAppBar();
            super.onBackPressed();
        }
    }

    public void openMainFragment (){
        if(NetworkStatusChecker.isNetworkAvailable(this)) {
            unCollapseAppBar();
            unLockAppBar();

            Fragment fragment = new MainFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_main_lnrlayout, fragment).commit();
        } else {
            Snackbar.make(mCoordinatorLayout,"Нет подключения", Snackbar.LENGTH_LONG).show();
        }
    }

    public void openSharesFragment () {
        collapseAppBar();
        lockAppBar();
        setActionBarTitle(getString(R.string.shares_fragment_title));

        Fragment fragment = new SharesFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_main_lnrlayout, fragment).commit();
    }

    public void openAppointmentFragment () {
        collapseAppBar();
        lockAppBar();
        setActionBarTitle(getString(R.string.appointment_fragment_title));

        Fragment fragment = new AppointmentFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_main_lnrlayout, fragment).commit();
    }

    public void openShareAppFragment () {
        collapseAppBar();
        lockAppBar();
        setActionBarTitle(getString(R.string.share_app_fragment_title));

        Fragment fragment = new ShareAppFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_main_lnrlayout, fragment).commit();
    }

    public void openBusinessFragment () {
        collapseAppBar();
        lockAppBar();
        setActionBarTitle(getString(R.string.business_fragment_title));

        Fragment fragment = new ShareAppFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_main_lnrlayout, fragment).commit();
    }

    public void openSupportFragment () {
        collapseAppBar();
        lockAppBar();
        setActionBarTitle(getString(R.string.support_fragment_title));

        Fragment fragment = new SupportFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_main_lnrlayout, fragment).commit();
    }

    public void openCatalogItemFragment () {
        Fragment fragment = new CatalogItemFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_main_lnrlayout, fragment);
        fragmentTransaction.addToBackStack(null);

        collapseAppBar();
        lockAppBar();
        disableNavigationDrawer();
        setActionBarTitle(getString(R.string.catalog_item_fragment_title));

        mDrawerToggle.setToolbarNavigationClickListener(this);
        fragmentTransaction.commit();
    }

    public void openCatalogMoreFragment () {
        Fragment fragment = new CatalogMoreFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_main_lnrlayout, fragment);
        fragmentTransaction.addToBackStack(null);

        collapseAppBar();
        lockAppBar();
        disableNavigationDrawer();
        setActionBarTitle(getString(R.string.catalog_fragment_title));

        mDrawerToggle.setToolbarNavigationClickListener(this);
        fragmentTransaction.commit();
    }

    public void openCategoryItemFragment () {
        Fragment fragment = new CategoryItemFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_main_lnrlayout, fragment);
        fragmentTransaction.addToBackStack(null);

        collapseAppBar();
        lockAppBar();
        disableNavigationDrawer();
        setActionBarTitle(getString(R.string.category_item_fragment_title));

        mDrawerToggle.setToolbarNavigationClickListener(this);
        fragmentTransaction.commit();
    }

    public void openSharesItemFragment () {
        Fragment fragment = new SharesItemFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_main_lnrlayout, fragment);
        fragmentTransaction.addToBackStack(null);

        collapseAppBar();
        lockAppBar();
        disableNavigationDrawer();
        setActionBarTitle(getString(R.string.shares_item_fragment_title));

        mDrawerToggle.setToolbarNavigationClickListener(this);
        fragmentTransaction.commit();
    }

    public void openSharesMoreFragment () {
        Fragment fragment = new SharesMoreFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_main_lnrlayout, fragment);
        fragmentTransaction.addToBackStack(null);

        collapseAppBar();
        lockAppBar();
        disableNavigationDrawer();
        setActionBarTitle(getString(R.string.shares_fragment_title));

        mDrawerToggle.setToolbarNavigationClickListener(this);
        fragmentTransaction.commit();
    }

    public void openLocationFragment () {
        Fragment fragment = new LocationFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_main_lnrlayout, fragment);
        fragmentTransaction.addToBackStack(null);

        collapseAppBar();
        lockAppBar();
        disableNavigationDrawer();
        setActionBarTitle(getString(R.string.location_fragment_title));

        mDrawerToggle.setToolbarNavigationClickListener(this);
        fragmentTransaction.commit();
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
            mNavigationView.getMenu().getItem(4).setChecked(true);
            openBusinessFragment();
        } else {
            openCatalogItemFragment();
        }
    }

    @Override
    public void onCatalogButtonMoreClickListener(int position) {
        openCatalogMoreFragment();
    }

    @Override
    public void onCategoryItemClickListener(int position) {
        openCategoryItemFragment();
    }

    @Override
    public void onSharesItemClickListener(int position) {
        openSharesItemFragment();
    }

    @Override
    public void onSharesButtonMoreClickListener(int position) {
        openSharesMoreFragment();
    }

    @Override
    public void onClick(View v) {
        onBackPressed();
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

    private void disableNavigationDrawer() {
        // Locks NavDrawer
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        // Remove hamburger
        mDrawerToggle.setDrawerIndicatorEnabled(false);
        // Show back button
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (NullPointerException e) {
            Log.e(TAG, e.toString());
        }
        mMenu.findItem(R.id.action_location).setVisible(false);
    }

    private void enableNavigationDrawer() {
        // Unlocks NavDrawer
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        // Removes back button
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        } catch (NullPointerException e) {
            Log.e(TAG, e.toString());
        }
        // Shows hamburger
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        // Removes the/any drawer toggle listener
        mDrawerToggle.setToolbarNavigationClickListener(null);
        mMenu.findItem(R.id.action_location).setVisible(true);
    }
}
