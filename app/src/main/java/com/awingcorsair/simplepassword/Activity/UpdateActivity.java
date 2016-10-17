package com.awingcorsair.simplepassword.Activity;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.awingcorsair.simplepassword.Database.DatabaseHelper;
import com.awingcorsair.simplepassword.Model.Record;

/**
 * Created by Mao on 2016/10/14.
 */
public class UpdateActivity extends AddActivity {
    private DatabaseHelper helper;
    @Override
    public void onFabClicked() {
        if(!input_website.getText().toString().isEmpty()&&!input_userId.getText().toString().isEmpty()&&!input_userPass.getText().toString().isEmpty()&&!input_note.getText().toString().isEmpty()){
            helper=new DatabaseHelper(this);
            int id=helper.getRecordCounts();
            Record newRecord=new Record(id,input_website.getText().toString(),input_userId.getText().toString(),input_userPass.getText().toString(),input_note.getText().toString());
            //Log.d("fab"," "+id);
            helper.updateRecord(newRecord);
            Intent intent=new Intent(this,MainActivity.class);
            //    intent.putExtra("activity_value","isFromAdd");
            util.setFlag(UpdateActivity.this,false);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this,"请输入完整信息",Toast.LENGTH_SHORT).show();
        }
    }
}
