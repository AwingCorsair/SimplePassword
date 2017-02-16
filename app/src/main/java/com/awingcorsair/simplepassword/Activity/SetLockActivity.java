package com.awingcorsair.simplepassword.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
public class SetLockActivity extends AppCompatActivity{

    @Bind(R.id.setlock_fab)
    FloatingActionButton fab;
    @Bind(R.id.setlock_edit)
    EditText lockPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setlock);
        ButterKnife.bind(this);
        CloseActivityClass.activityList.add(this);
    //    setSupportActionBar(toolbar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lockPassword.getText().length()>5){
                    Utils util=new Utils();
                    util.setPassword(SetLockActivity.this,lockPassword.getText().toString().trim());
                    util.setFlag(SetLockActivity.this,false);
                    Log.d("pass",lockPassword.getText().toString().trim());
                    startActivity(new Intent(SetLockActivity.this,MainActivity.class));
                    finish();
                }else {
                    Toast.makeText(SetLockActivity.this,"请至少输入六个字符！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
