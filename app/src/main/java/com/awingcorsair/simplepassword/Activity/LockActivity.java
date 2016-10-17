package com.awingcorsair.simplepassword.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.awingcorsair.simplepassword.R;
import com.awingcorsair.simplepassword.Util.CloseActivityClass;
import com.awingcorsair.simplepassword.Util.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mao on 2016/9/19.
 */
public class LockActivity extends AppCompatActivity{

    @Bind(R.id.lock_fab)
    FloatingActionButton fab;
    @Bind(R.id.lock_edit)
    EditText lockPassword;
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
        //    super.onBackPressed();
        //            System.exit(0);
            CloseActivityClass.exitClient(LockActivity.this);

            //    return;
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

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);
        ButterKnife.bind(this);
        CloseActivityClass.activityList.add(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SharedPreferences sharedPreferences=getSharedPreferences("info",MODE_PRIVATE);
//                String password=sharedPreferences.getString("mainPassword","null");
                Utils util=new Utils();
                util.getPassword(LockActivity.this);
                util.setFlag(LockActivity.this,false);
            //    Log.d("pass",lockPassword.getText().toString().trim()+"=?"+password);
                if(util.getPassword(LockActivity.this).equals(lockPassword.getText().toString().trim())){
                    startActivity(new Intent(LockActivity.this,MainActivity.class));
                    finish();
                }else {
                    Toast.makeText(LockActivity.this,"请输入正确密码~",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
