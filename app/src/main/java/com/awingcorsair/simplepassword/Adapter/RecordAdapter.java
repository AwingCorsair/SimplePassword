package com.awingcorsair.simplepassword.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.awingcorsair.simplepassword.Model.Record;
import com.awingcorsair.simplepassword.R;
import com.awingcorsair.simplepassword.Util.utils;
import com.jakewharton.rxbinding.view.RxView;
import com.ramotion.foldingcell.FoldingCell;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.functions.Action1;

/**
 * Created by Mao on 2016/9/17.
 */
public class RecordAdapter extends BaseAdapter {
    //    @Bind(R.id.show_logo)
//    ImageView showLogo;
//    @Bind(R.id.show_website)
//    TextView showWebsite;
//    @Bind(R.id.show_note)
//    TextView showNote;
//    @Bind(R.id.unfolded_show_logo)
//    ImageView unshowLogo;
//    @Bind(R.id.unfolded_show_website)
//    TextView unshowWebsite;
//    @Bind(R.id.unfolded_show_username)
//    TextView unshowUsername;
//    @Bind(R.id.unfolded_show_password)
//    TextView unshowPassword;
//    @Bind(R.id.unfolded_show_copyUsername)
//    Button copyUsername;
//    @Bind(R.id.unfolded_show_copyPassword)
//    Button copyPassword;
    private List<Record> records;
    private Context context;

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
    public View getView(int position, View convertView, ViewGroup parent) {
        //    Activity activity=(Activity)context;
        //    ButterKnife.bind(activity);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);

        }
        final int pos=position;
        TextView showWebsite = (TextView) convertView.findViewById(R.id.show_website);
        TextView showNote = (TextView) convertView.findViewById(R.id.show_note);
        TextView unshowWebsite = (TextView) convertView.findViewById(R.id.unfolded_show_website);
        TextView unshowUsername = (TextView) convertView.findViewById(R.id.unfolded_show_username);
        TextView unshowPassword = (TextView) convertView.findViewById(R.id.unfolded_show_password);
        Button copyUsername = (Button) convertView.findViewById(R.id.unfolded_show_copyUsername);
        copyUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utils util=new utils();
                util.copy(records.get(pos).getUsername(),context);
                Toast.makeText(context,"用户名已复制到剪贴板",Toast.LENGTH_SHORT).show();
            }
        });
        Button copyPassword = (Button) convertView.findViewById(R.id.unfolded_show_copyPassword);
        copyPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utils util=new utils();
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
//        RxView.clicks(copyUsername).subscribe(new Action1<Void>() {
//            @Override
//            public void call(Void aVoid) {
//                Toast.makeText(context," "+unshowUsername.getText().toString(),Toast.LENGTH_SHORT).show();
//            }
//        });
//        RxView.clicks(copyPassword).subscribe(new Action1<Void>() {
//            @Override
//            public void call(Void aVoid) {
//                Toast.makeText(context," "+unshowPassword.getText().toString(),Toast.LENGTH_SHORT).show();
//            }
//        });
        return convertView;
    }
}
