package com.awingcorsair.simplepassword.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.awingcorsair.simplepassword.Database.DatabaseHelper;
import com.awingcorsair.simplepassword.Model.Record;
import com.awingcorsair.simplepassword.R;

import com.awingcorsair.simplepassword.Util.navigitionInit;
import com.awingcorsair.simplepassword.Util.passwordGenerator;
import com.jakewharton.rxbinding.support.v7.widget.RxToolbar;
import com.jakewharton.rxbinding.view.RxView;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.techery.progresshint.addition.widget.SeekBar;
import rx.functions.Action1;

/**
 * Created by Mao on 2016/9/12.
 */
public class AddActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.add_toolbar)
    Toolbar toolbar;
    @Bind(R.id.add_fab)
    FloatingActionButton fab;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.nav_view)
    NavigationView navigationView;
    @Bind(R.id.add_input_website)
    EditText input_website;
    @Bind(R.id.add_input_userId)
    EditText input_userId;
    @Bind(R.id.add_input_userPass)
    EditText input_userPass;
    @Bind(R.id.add_input_note)
    EditText input_note;
    AlertDialog dialog;
    @Bind(R.id.seekbar)
    SeekBar seekBar;
    @Bind(R.id.generate_pass)
    Button generate;
    private static int id=0;
    private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);

        initUI();
        helper=new DatabaseHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_select_website) {
            setupWebsiteSelector();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setupWebsiteSelector() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
        dialog = builder
                .setView(R.layout.alert_add)
                .create();
        dialog.show();
//        SimpleImageArrayAdapter adapter = new SimpleImageArrayAdapter(AddActivity.this,
//                new Integer[]{R.drawable.logo_baidu, R.drawable.logo_bilibili, R.drawable.logo_facebook, R.drawable.logo_github, R.drawable.logo_google});
//        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(AddActivity.this, " " + which, Toast.LENGTH_SHORT).show();
//            }
//        });
//        builder.show();
//        builder.setView(R.layout.alert_add);

        //设置窗口的大小
        //dialog.getWindow().setLayout(1000,1500);
    }

    public void initUI() {
        setSupportActionBar(toolbar);
        RxView.clicks(fab).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                onFabClicked();
            }
        });

        //    navigationView.setNavigationItemSelectedListener(this);

        //    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        //            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //    toggle.syncState();
        //    ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,)
        //toolbar.setNavigationIcon(R.drawable.arrow_back_black_18x18);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RxView.clicks(generate).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                passwordGenerator generator = new passwordGenerator();
                input_userPass.setText(generator.randomString(seekBar.getProgress()));
            }
        });

        RxToolbar.navigationClicks(toolbar).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                onBackPressed();
            }
        });
        seekBar.setMax(16);
        seekBar.setProgress(6);
        seekBar.setOnSeekBarChangeListener(new android.widget.SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(android.widget.SeekBar seekBar, int progress, boolean fromUser) {
                //Toast.makeText(AddActivity.this, " " + progress, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(android.widget.SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(android.widget.SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        //    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void onFabClicked() {
        if(!input_website.getText().toString().isEmpty()&&!input_userId.getText().toString().isEmpty()&&!input_userPass.getText().toString().isEmpty()&&!input_note.getText().toString().isEmpty()){
            int id=helper.getRecordCounts();
            Record newRecord=new Record(id,input_website.getText().toString(),input_userId.getText().toString(),input_userPass.getText().toString(),input_note.getText().toString());
            Log.d("fab"," "+id);
            helper.addRecord(newRecord);
            Intent intent=new Intent(this,MainActivity.class);
            intent.putExtra("activity_value","add_trans");
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this,"请输入完整信息",Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        navigitionInit init = new navigitionInit();
        init.setMenu(AddActivity.this, item, drawer);
        return true;
    }

    public void onWebsiteSelected(View view) {
        switch (view.getId()) {
            case R.id.logo_baidu:
                input_website.setText("www.baidu.com");
                dialog.dismiss();
                break;
            case R.id.logo_bilibili:
                input_website.setText("www.bilibili.com");
                dialog.dismiss();

                break;
            case R.id.logo_facebook:
                input_website.setText("www.facebook.com");
                dialog.dismiss();

                break;
            case R.id.logo_github:
                input_website.setText("github.com");
                dialog.dismiss();

                break;
            case R.id.logo_instagram:
                input_website.setText("www.instagram.com");
                dialog.dismiss();

                break;
            case R.id.logo_google:
                input_website.setText("www.google.com");
                dialog.dismiss();

                break;
            case R.id.logo_netease:
                input_website.setText("www.netease.com");
                dialog.dismiss();

                break;
            case R.id.logo_steam:
                input_website.setText("www.steam.com");
                dialog.dismiss();

                break;
            case R.id.logo_tencent:
                input_website.setText("www.tencent.com");
                dialog.dismiss();

                break;
            case R.id.logo_twitter:
                input_website.setText("www.twitter.com");
                dialog.dismiss();

                break;
            default:
                //  Toast.makeText(this, " " + view.getId(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
