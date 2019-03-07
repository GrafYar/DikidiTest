package ru.dikidi.dikiditest.ui.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toolbar;
import android.view.MenuItem;
import ru.dikidi.dikiditest.R;

public class ItemCatalogActivity extends AppCompatActivity {

    private Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_catalog);
        setTitle(getString(R.string.catalog_activity_main));

        mToolBar = findViewById(R.id.toolbar);
        setupToolBar();
    }

    private void setupToolBar() {
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();

        return true;
    }



}
