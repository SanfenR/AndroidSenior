---
title: Android动态修改icon
date: 2017-1-7
tags:
  - Android
---

## <activity-alias/>使用

1. 以前装应用的时候有些应用会在桌面上生成两个图标,这两个图标有些是同一个Activity的入口,有些是另外一个Activity的入口,这样的效果是怎么实现的呢?在看Android原生DeskClock程序的时候看到了这个功能的实现.使用的是activity-alias：

    ```java
    <activity-alias android:enabled=["true" | "false"]
                    android:exported=["true" | "false"]
                    android:icon="drawable resource"
                    android:label="string resource"
                    android:name="string"
                    android:permission="string"
                    android:targetActivity="string" >
        . . .
    </activity-alias>
    ```
2. activity-alias中标记了一个名为android.intent.category.DESK_DOCK的category, 这个是在android设备插上桌面Dock底座的时候才会触发alias入口。设置:
    ```xml
     <category android:name="android.intent.category.DESK_DOCK" />
    ```
    实现app多入口登入。

### manifest代码：

```xml
        <activity
            android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>

        </activity>

        <activity-alias
            android:name=".icon_2"
            android:enabled="true"
            android:icon="@mipmap/steam_icon_72px"
            android:label="@string/app_name"
            android:targetActivity=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
                <category android:name="android.intent.category.DESK_DOCK" />
            </intent-filter>
        </activity-alias>
```

### 效果如下：app显示了2个应用图标

![icon](http://ohqvqufyf.bkt.clouddn.com/icon.png)


## app实现动态修改icon

> 了解完 <activity-alias>的基本知识之后，就知道动态修改桌面图标和应用名称是怎么做到的了。其实就是给整个应用的入口 Activity 添加一个 <activity-alias>
标签，并设置预先设计好的替代桌面图标和应用名称，并配置相同的 <intent-filter>
属性，动态启动即可。


### manifest代码

```xml
    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">

        <activity
            android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <!--<category android:name="android.intent.category.LAUNCHER" />-->
            </intent-filter>

        </activity>

        <activity-alias
            android:name=".icon_1"
            android:enabled="true"
            android:icon="@mipmap/ic_launcher"
            android:label="@string/app_name"
            android:targetActivity=".MainActivity">

            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity-alias>

        <activity-alias
            android:name=".icon_2"
            android:enabled="false"
            android:icon="@mipmap/steam_icon_72px"
            android:label="@string/app_name"
            android:targetActivity=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity-alias>
    </application>
```

Java代码

```java
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
```
在执行setIcon()之后，桌面会过一段时间显示新的图标，如果需要立即刷新图标，在执行setIcon()方法之后执行如下可以立即刷新。

```java
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
```

使用这个方法需要添加权限：

```java
<uses-permission android:name="android.permission.KILL_BACKGROUND_PROCESSES"/>
```

效果如下：

![image](http://ohqvqufyf.bkt.clouddn.com/changicon.gif)
