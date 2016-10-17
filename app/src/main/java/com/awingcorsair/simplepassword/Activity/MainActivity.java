package com.awingcorsair.simplepassword.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.awingcorsair.simplepassword.Adapter.RecordAdapter;
import com.awingcorsair.simplepassword.Database.DatabaseHelper;
import com.awingcorsair.simplepassword.Model.Record;
import com.awingcorsair.simplepassword.R;
import com.awingcorsair.simplepassword.Util.CloseActivityClass;
import com.awingcorsair.simplepassword.Util.NavigitionInit;
import com.awingcorsair.simplepassword.Util.Utils;
import com.jakewharton.rxbinding.view.RxView;


import net.sqlcipher.database.SQLiteDatabase;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.nav_view)
    NavigationView navigationView;
    @Bind(R.id.list_main)
    ListView records;
//    Button update=(Button)findViewById(R.id.unfolded_show_update);
//    @Bind(R.id.unfolded_show_update)
//    Button update;
//    @Bind(R.id.unfolded_show_delete)
//    Button delete;
    private RecordAdapter adapter;
    private DatabaseHelper databaseHelper;
    private List<Record> recordList;
    private SQLiteDatabase db;
    boolean doubleBackToExitPressedOnce = false;
    Utils util=new Utils();
//    final FoldingCell fc = (FoldingCell) findViewById(R.id.folding_cell);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        CloseActivityClass.activityList.add(this);


        //  Declare a new thread to do a preference check
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //  Initialize SharedPreferences
                SharedPreferences getPrefs = PreferenceManager
                        .getDefaultSharedPreferences(getBaseContext());

                //  Create a new boolean and preference and set it to true
                boolean isFirstStart = getPrefs.getBoolean("firstStart", true);

                //  If the activity has never started before...
                if (isFirstStart) {

                    //  Launch app intro
                    Intent i = new Intent(MainActivity.this, IntroActivity.class);
                    startActivity(i);

                    //  Make a new preferences editor
                    SharedPreferences.Editor e = getPrefs.edit();

                    //  Edit preference to make it false because we don't want this to run again
                    e.putBoolean("firstStart", false);

                    //  Apply changes
                    e.apply();
                }
            }
        });

        // Start the thread
        t.start();

        if(util.getFlag(this)){
            startActivity(new Intent(this, LockActivity.class));
        }
        initiUI();
        SQLiteDatabase.loadLibs(this);
        databaseHelper = new DatabaseHelper(this);
        recordList = databaseHelper.getAllRecord();
        adapter = new RecordAdapter(this, recordList);
        records.setAdapter(adapter);

        //    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        //drawer.setDrawerListener(toggle);


    }

    public void initiUI() {
        setSupportActionBar(toolbar);
        RxView.clicks(fab).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                onFabClicked();
            }
        });
        // attach click listener to folding cell
        //fc.initialize(1000, Color.GRAY, 1);


        //    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
//        RxView.clicks(update).subscribe(new Action1<Void>() {
//            @Override
//            public void call(Void aVoid) {
//
//            }
//        });
//        RxView.clicks(delete).subscribe(new Action1<Void>() {
//            @Override
//            public void call(Void aVoid) {
//                Log.d("delete",delete.getParent().toString());
//               // recordList.get()
//            }
//        });
      //  update.setOnClickListener(this);
    }

//    public void updateRecord(){
//
//    }

    public void onFabClicked() {
        startActivity(new Intent(this, AddActivity.class));
        finish();
    }

    //    @Override
//    public void onBackPressed() {
//        //    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            util.setFlag(MainActivity.this,true);
            CloseActivityClass.exitClient(MainActivity.this);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    //    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_database) {
//
//        } else if (id == R.id.nav_setting) {
//            Toast.makeText(this,"1",Toast.LENGTH_SHORT).show();
//        } else if (id == R.id.nav_feedback) {
//
//        } else if (id == R.id.nav_star) {
//
//        } else if (id == R.id.nav_about) {
//
//        } else if (id == R.id.nav_quit) {
//         //   System.exit(0);
//        }
//
//   //     DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        NavigitionInit init = new NavigitionInit();
        init.setMenu(MainActivity.this, item, drawer);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        util.setFlag(MainActivity.this,true);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        Bundle bundle=this.getIntent().getExtras();
//        String result=bundle.getString("activity_value");
        if(util.getFlag(this)){
            startActivity(new Intent(this, LockActivity.class));
        }
//        if(!util.getFlag(this)&&result.equals("isFromAdd")){
//            startActivity(new Intent(this, LockActivity.class));
//        }
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.unfolded_show_update:
//                updateRecord();
//                break;
//        }
//    }
}
