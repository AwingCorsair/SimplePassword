package com.awingcorsair.simplepassword.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.awingcorsair.simplepassword.Activity.MainActivity;
import com.awingcorsair.simplepassword.Activity.UpdateActivity;
import com.awingcorsair.simplepassword.Database.DatabaseHelper;
import com.awingcorsair.simplepassword.Model.Record;
import com.awingcorsair.simplepassword.R;
import com.awingcorsair.simplepassword.Util.Utils;
import com.ramotion.foldingcell.FoldingCell;

import java.util.List;

/**
 * Created by Mao on 2016/9/17.
 */
public class RecordAdapter extends BaseAdapter {
    private List<Record> records;
    private Context context;
    private Utils util=new Utils();

    public RecordAdapter(Context context, List<Record> records) {
        super();
        this.records = records;
        this.context = context;
    }

    @Override
    public int getCount() {
        return records.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public Object getItem(int position) {
        return records.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //    Activity activity=(Activity)context;
        //    ButterKnife.bind(activity);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }
        final int pos=position;
        final TextView showWebsite = (TextView) convertView.findViewById(R.id.show_website);
        final TextView showNote = (TextView) convertView.findViewById(R.id.show_note);
        final TextView unshowWebsite = (TextView) convertView.findViewById(R.id.unfolded_show_website);
        TextView unshowUsername = (TextView) convertView.findViewById(R.id.unfolded_show_username);
        TextView unshowPassword = (TextView) convertView.findViewById(R.id.unfolded_show_password);
        Button copyUsername = (Button) convertView.findViewById(R.id.unfolded_show_copyUsername);
        Button copyPassword = (Button) convertView.findViewById(R.id.unfolded_show_copyPassword);
        Button update=(Button)convertView.findViewById(R.id.unfolded_show_update);
        Button delete=(Button)convertView.findViewById(R.id.unfolded_show_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context).setTitle("确认").setMessage("数据一经删除不可恢复，确定删除吗？")
                        .setPositiveButton("我意已决", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DatabaseHelper helper=new DatabaseHelper(context);
                                //Record delRecord=new Record(id,input_website.getText().toString(),input_userId.getText().toString(),input_userPass.getText().toString(),input_note.getText().toString());
                                helper.deleteRecord(records.get(position).getId());
                                Toast.makeText(context,"删除完成",Toast.LENGTH_SHORT).show();
                                notifyDataSetChanged();
                                //notifyDataSetInvalidated();
                                context.startActivity(new Intent(context, MainActivity.class));
                            }
                        }).setNegativeButton("我再想想", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, UpdateActivity.class));
            }
        });
        copyUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                util.copy(records.get(pos).getUsername(),context);
                Toast.makeText(context,"用户名已复制到剪贴板",Toast.LENGTH_SHORT).show();
            }
        });
        copyPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                util.copy(records.get(pos).getPassword(),context);
                Toast.makeText(context,"密码已复制到剪贴板",Toast.LENGTH_SHORT).show();
            }
        });

        final FoldingCell fc = (FoldingCell) convertView.findViewById(R.id.folding_cell);
        //if not set , every six different view with share the same animation
        fc.fold(true);
        fc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fc.toggle(false);
            }
        });

        showWebsite.setText(records.get(position).getWebsite());
        //    showWebsite.setText("Text");

        showNote.setText(records.get(position).getNote());
        unshowWebsite.setText(records.get(position).getWebsite());
        unshowUsername.setText("用户名："+records.get(position).getUsername());
        unshowPassword.setText("密码："+records.get(position).getPassword());
        return convertView;
    }


}
