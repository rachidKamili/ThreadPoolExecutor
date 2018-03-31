package me.kamili.rachid.threadpoolexecutor;

import android.os.ParcelUuid;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar1;
    private ProgressBar progressBar2;
    private ProgressBar progressBar3;
    private ProgressBar progressBar4;

    private static int NUMBER_OF_CORES = 4;
    private static final int KEEP_ALIVE_TIME = 1000;
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.MILLISECONDS;
    private ThreadPoolExecutor mThreadPoolExecutor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
    }

    private void initExecutor() {
        // Initial pool size
        mThreadPoolExecutor = new ThreadPoolExecutor(
                NUMBER_OF_CORES,   // Initial pool size
                NUMBER_OF_CORES,   // Max pool size
                KEEP_ALIVE_TIME,       // Time idle thread waits before terminating
                KEEP_ALIVE_TIME_UNIT,  // Sets the Time Unit for KEEP_ALIVE_TIME
                new LinkedBlockingDeque<Runnable>());
    }

    private void bindViews() {
        progressBar1 = findViewById(R.id.progressBar1);
        progressBar2 = findViewById(R.id.progressBar2);
        progressBar3 = findViewById(R.id.progressBar3);
        progressBar4 = findViewById(R.id.progressBar4);
    }

    private void initAllProgressBars() {
        progressBar1.setProgress(0);
        progressBar2.setProgress(0);
        progressBar3.setProgress(0);
        progressBar4.setProgress(0);
    }

    public void start(View view) {
        restartRunning();

        initExecutor();

        mThreadPoolExecutor.execute(new MyEventThread(progressBar1));
        mThreadPoolExecutor.execute(new MyEventThread(progressBar2));
        mThreadPoolExecutor.execute(new MyEventThread(progressBar3));
        mThreadPoolExecutor.execute(new MyEventThread(progressBar4));
        mThreadPoolExecutor.shutdown();
    }

    private void restartRunning(){
        try {
            setRunning(false);
            Thread.sleep(100);
            setRunning(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void setRunning(boolean bl) {
        ProgressTask.RUNNING = bl;
        initAllProgressBars();
    }

    public void stop(View view) {
        setRunning(false);
    }
}
