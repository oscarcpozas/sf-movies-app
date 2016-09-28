package com.oscar.pozas.github.sf.movies.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.oscar.pozas.github.sf.movies.R;
import com.oscar.pozas.github.sf.movies.ui.fragment.MainFragment;
import com.oscar.pozas.github.sf.movies.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        ButterKnife.bind(this);

        // Set up the toolbar
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        // actionBar.setDisplayHomeAsUpEnabled(false);

        final FragmentManager fm = getSupportFragmentManager();
        MainFragment fragment = MainFragment.newInstance();
        ActivityUtils.addFragmentToActivity(fm, fragment, R.id.contentFragment);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
