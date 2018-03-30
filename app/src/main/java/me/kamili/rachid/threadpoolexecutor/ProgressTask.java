package me.kamili.rachid.threadpoolexecutor;

import android.widget.ProgressBar;

import java.util.Random;

/**
 * Created by Admin on 3/29/2018.
 */

class ProgressTask {
    public static int startJob(ProgressBar pb) {
        int seconds = 1+new Random().nextInt(9);
        pb.setMax(seconds*10);

        int progress = 0;
        for (int i = 0; i < seconds*10; i++) {
            try {
                Thread.sleep(100);
                pb.setProgress(++progress);
            } catch (InterruptedException e) {
                // Something
            }
        }
        return progress;
    }
}
