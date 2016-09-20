package com.awingcorsair.simplepassword.Util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.awingcorsair.simplepassword.Activity.MainActivity;
import com.awingcorsair.simplepassword.R;
import com.suredigit.inappfeedback.FeedbackDialog;
import com.suredigit.inappfeedback.FeedbackSettings;

/**
 * Created by Mao on 2016/9/16.
 */
public class navigitionInit {
    static Intent intent;
    private static  FeedbackDialog feedBack;

    public static void setMenu(Context context, MenuItem item, DrawerLayout drawer){
        //Intent intent;
        boolean flag=false;
        int id = item.getItemId();

        if (id == R.id.nav_database) {
            if(!(context instanceof MainActivity)){
             //   Toast.makeText(context,""+context.toString(),Toast.LENGTH_SHORT).show();
                flag=true;
                intent=new Intent(context,MainActivity.class);
                context.startActivity(intent);
            }
        } else if (id == R.id.nav_setting) {
            Toast.makeText(context,"1",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_feedback) {
            feedback(context);
            flag=true;
        } else if (id == R.id.nav_star) {

        } else if (id == R.id.nav_about) {
            showAbout(context);
            flag=true;
        } else if (id == R.id.nav_quit) {
            utils util=new utils();
            util.setFlag(context,true);
            CloseActivityClass.exitClient(context);
        //    System.exit(0);
        }
        //     DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if(!flag){
            drawer.closeDrawer(GravityCompat.START);

        }
    }

    public static void showAbout(final Context context){
        new AlertDialog.Builder(context).setTitle("关于")
                .setMessage("感谢使用极简密码，本App基于RxJava、RxAndroid、RxBInding,欢迎到我的Github项目里Fork and Star^_^,如果想了解详细的开发过程，也欢迎来我的Blog逛一逛(｡・`ω´･)")
                .setPositiveButton("屈尊一逛", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Uri uri = Uri.parse(context.getString(R.string.Blog));   //指定网址
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);           //指定Action
                        intent.setData(uri);                            //设置Uri
                        context.startActivity(intent);        //启动Activity
                    }
                }).setNeutralButton("围观Github", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Uri uri = Uri.parse(context.getString(R.string.Github));   //指定网址
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);           //指定Action
                intent.setData(uri);                            //设置Uri
                context.startActivity(intent);        //启动Activity
            }
        })
                .show();
    }

    private static void feedback(Context context) {
        FeedbackSettings feedbackSettings = new FeedbackSettings();

        //SUBMIT-CANCEL BUTTONS
        feedbackSettings.setCancelButtonText("取消");
        feedbackSettings.setSendButtonText("发送");

        //DIALOG TEXT
        feedbackSettings.setText("如果您有更好的建议或者有Bug要反馈，请在下面的输入框里提交相应信息并留下联系方式以便开发者及时回复(ง •̀_•́)ง");
        feedbackSettings.setYourComments("请在此输入信息...");
        feedbackSettings.setTitle("反馈");

        //TOAST MESSAGE
        feedbackSettings.setToast("非常感谢！");
        feedbackSettings.setToastDuration(Toast.LENGTH_SHORT);  // Default

        //RADIO BUTTONS
        feedbackSettings.setRadioButtons(false); // Disables radio buttons
        feedbackSettings.setBugLabel("Bug");
        feedbackSettings.setIdeaLabel("建议");
        feedbackSettings.setQuestionLabel(null);

        //RADIO BUTTONS ORIENTATION AND GRAVITY
        feedbackSettings.setOrientation(LinearLayout.HORIZONTAL); // Default
        feedbackSettings.setGravity(Gravity.RIGHT); // Default

        //SET DIALOG MODAL
        feedbackSettings.setModal(true); //Default is false

        //DEVELOPER REPLIES
        feedbackSettings.setReplyTitle("来自开发者的消息");
        feedbackSettings.setReplyCloseButtonText("关闭");
        feedbackSettings.setReplyRateButtonText("OK");
        Activity activity = (Activity) context;
        feedBack = new FeedbackDialog(activity, "AF-91B15C013B82-9F",feedbackSettings);
        feedBack.show();
    }
}
