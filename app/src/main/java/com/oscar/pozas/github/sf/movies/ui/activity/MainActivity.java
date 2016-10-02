package com.oscar.pozas.github.sf.movies.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.oscar.pozas.github.sf.movies.Injection;
import com.oscar.pozas.github.sf.movies.R;
import com.oscar.pozas.github.sf.movies.ui.fragment.MainFragment;
import com.oscar.pozas.github.sf.movies.ui.presenter.MainPresenter;
import com.oscar.pozas.github.sf.movies.utils.ActivityUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // Set up the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);

        // Create fragment
        final FragmentManager fm = getSupportFragmentManager();
        MainFragment fragment = MainFragment.newInstance();
        ActivityUtils.addFragmentToActivity(fm, fragment, R.id.contentFragment);

        // Create presenter
        new MainPresenter(fragment, Injection.provideUseCaseHandler(),
                Injection.provideGetFilms(this));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
