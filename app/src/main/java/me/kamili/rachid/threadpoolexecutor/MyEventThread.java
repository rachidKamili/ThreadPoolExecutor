package me.kamili.rachid.threadpoolexecutor;

import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by Admin on 3/29/2018.
 */

public class MyEventThread implements Runnable{

    ProgressBar pb;

    public MyEventThread(ProgressBar pb) {
        this.pb = pb;
    }

    @Override
    public void run() {
        int count = ProgressTask.startJob(pb);
        pb.setProgress(count);
        pb.setVisibility(View.VISIBLE);
    }
}
