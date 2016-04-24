package com.example.huangxiangyu.pm25onlinedetectsystemdemo.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.huangxiangyu.pm25onlinedetectsystemdemo.R;
import com.example.huangxiangyu.pm25onlinedetectsystemdemo.RecyclerViewAdapter;
import com.example.huangxiangyu.pm25onlinedetectsystemdemo.model.WeatherData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangxiangyu on 16/4/19.
 * In Pm25OnlineDetectSystemDemo
 */
public class FrontActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private RecyclerView recyclerView;
    private List<WeatherData> weatherDataList;
    private RecyclerViewAdapter recyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);

        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("首页");
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
//                    case R.id.action_search:
//                        Intent intent = new Intent(FrontActivity.this, SearchActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);
//                        break;
                    case R.id.action_about:
                        Toast.makeText(FrontActivity.this, "!!!!", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;

                }
                return false;
            }
        });
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        // Navigation drawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
        navigationView.setItemIconTintList(null);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        initWeatherData();
        recyclerViewAdapter = new RecyclerViewAdapter(weatherDataList, FrontActivity.this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void initWeatherData() {
        // TODO:获取基本数据信息
        weatherDataList = new ArrayList<>();

//        weatherDataList.add(new WeatherData("郑州", "19:00", 177, 56, 40, 4, 3, 3, 4, "空气质量中", 23, 50));
//        weatherDataList.add(new WeatherData("苏州", "19:00", 177, 56, 40, 4, 3, 3, 4, "空气质量中", 23, 50));
//        weatherDataList.add(new WeatherData("温州", "19:00", 177, 56, 40, 4, 3, 3, 4, "空气质量中", 23, 50));
//        weatherDataList.add(new WeatherData("杭州", "19:00", 177, 56, 40, 4, 3, 3, 4, "空气质量中", 23, 50));
//        weatherDataList.add(new WeatherData("南京", "19:00", 177, 56, 40, 4, 3, 3, 4, "空气质量中", 23, 50));
//        weatherDataList.add(new WeatherData("泰州", "19:00", 177, 56, 40, 4, 3, 3, 4, "空气质量中", 23, 50));
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.front_navigation_menu_item:
                        // 已经在该页面中
                        break;
                    case R.id.list_navigation_menu_item:
                        Intent intentl = new Intent(FrontActivity.this, ListActivity.class);
                        intentl.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intentl);
                        break;
                    case R.id.map_navigation_menu_item:
                        Intent intentm = new Intent(FrontActivity.this, MapActivity.class);
                        intentm.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intentm);
                        break;
                    case R.id.community_navigation_menu_item:
                        Intent intentc = new Intent(FrontActivity.this, CommunityActivity.class);
                        intentc.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intentc);
                        break;
                    case R.id.setting_navigation_menu_item:
                        Intent intents = new Intent(FrontActivity.this, SettingActivity.class);
                        intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intents);
                        break;
                    default:
                        break;
                }
                // Navigation View在item被选中后关闭
                item.setChecked(true);
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;

    }

}
