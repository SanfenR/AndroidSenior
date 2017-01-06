package com.mz.sanfen.appicon;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.txt_change)
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIcon();
            }
        });
    }


    private void setIcon(){
        String ACTIVITY_ICON_1 = "com.mz.sanfen.appicon.ic_launcher";
        String ACTIVITY_ICON_2 = "com.mz.sanfen.appicon.steam_icon_72px";

        Context ctx = getApplicationContext();


        PackageManager pm = getApplicationContext().getPackageManager();
        System.out.println(getComponentName());


        ComponentName componentName1 = new ComponentName(ctx, ACTIVITY_ICON_1);

        ComponentName componentName2 = new ComponentName(ctx, ACTIVITY_ICON_2);

        pm.setComponentEnabledSetting(componentName1,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);

        pm.setComponentEnabledSetting(
                componentName2,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);

    }
}
