package com.example.blocker;

import android.app.ActivityManager;
import android.content.pm.ApplicationInfo;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialise layout
        listView = findViewById(R.id.listview);
        text = findViewById(R.id.totalapp);
    }



    public void getallapps(View view) {
        // get list of all the apps installed
        List<ApplicationInfo> infos = getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA);
        // create a list with size of total number of apps
        String[] apps = new String[infos.size()];
        int i = 0;
        // add all the app name in string list
        for (ApplicationInfo info : infos) {
            apps[i] = info.packageName;
            i++;
        }
        // set all the apps name in list view
        listView.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, apps));
        // write total count of apps available.
        text.setText(infos.size() + " Apps are installed");
    }


    ArrayList<String> blockedapps = new ArrayList<String>();




    @Override
    protected void onStart() {
        super.onStart();

    }

    public static String getForegroundApplication(Context context){
        ActivityManager am=(ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.RunningTaskInfo foreground=am.getRunningTasks(1).get(0);
        return foreground.topActivity.getPackageName();
    }

    /*checa a lista e bloqueio se estiver
    if (blockedapps.size() > 0){
        String currentRunningApp = TaskChecker.getForegroundApplication(yourContext);

        if (blockedapps.contains(currentRunningApp)){
            ActivityManager am = (ActivityManager)yourContext.getSystemService(Context.ACTIVITY_SERVICE);

            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.startActivity(startMain);

            am.killBackgroundProcesses(currentRunningApp);
        }
    }*/
}
