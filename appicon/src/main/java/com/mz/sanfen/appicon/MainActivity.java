package com.mz.sanfen.appicon;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.txt_change)
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIcon(2);

                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                List<ResolveInfo> resolves = getPackageManager().queryIntentActivities(intent, 0);
                for (ResolveInfo res : resolves) {
                    if (res.activityInfo != null) {
                        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                        am.killBackgroundProcesses(res.activityInfo.packageName);
                    }
                }
            }
        });
    }


    private void setIcon(int useCode){
        try {
            //要跟manifest的activity-alias 的name保持一致
            String icon_1 = "com.mz.sanfen.appicon.icon_1";
            String icon_2 = "com.mz.sanfen.appicon.icon_2";

            if (useCode != 3) {
                PackageManager pm = getPackageManager();
                ComponentName normalComponentName = new ComponentName(getBaseContext(), icon_1);
                //正常图标新状态
                int normalNewState = useCode == 1 ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED
                        : PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
                if (pm.getComponentEnabledSetting(normalComponentName) != normalNewState) {//新状态跟当前状态不一样才执行
                    pm.setComponentEnabledSetting(
                            normalComponentName,
                            normalNewState,
                            PackageManager.DONT_KILL_APP);
                }

                ComponentName actComponentName = new ComponentName(getBaseContext(), icon_2);
                //正常图标新状态
                int actNewState = useCode == 2 ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED
                        : PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
                if (pm.getComponentEnabledSetting(actComponentName) != actNewState) {//新状态跟当前状态不一样才执行
                    pm.setComponentEnabledSetting(
                            actComponentName,
                            actNewState,
                            PackageManager.DONT_KILL_APP);
                }
            }
        } catch (Exception e) {
        }
    }
}
