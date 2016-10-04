package com.oscar.pozas.github.sf.movies.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.oscar.pozas.github.sf.movies.Injection;
import com.oscar.pozas.github.sf.movies.R;
import com.oscar.pozas.github.sf.movies.ui.fragment.MainFragment;
import com.oscar.pozas.github.sf.movies.ui.presenter.MainPresenter;
import com.oscar.pozas.github.sf.movies.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements
        MainFragment.LoadingIndicatorCallback {

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

        // Set up the toolbar.
        setSupportActionBar(mToolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);

        // Create fragment.
        final FragmentManager fm = getSupportFragmentManager();
        MainFragment fragment = MainFragment.newInstance();
        ActivityUtils.addFragmentToActivity(fm, fragment, R.id.contentFragment);

        // Create presenter.
        new MainPresenter(fragment, Injection.provideUseCaseHandler(),
                Injection.provideGetFilms(this));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search_filter:
                startActivity(new Intent(this, SearchActivity.class));
                break;
        }
        return true;
    }

    @Override
    public void onVisibilityChange(boolean visible) {
        mProgressBar.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    }

}
