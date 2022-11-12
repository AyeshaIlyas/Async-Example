package com.example.asyncexample;

import android.os.AsyncTask;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.Random;
import android.widget.ProgressBar;

public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {
    final private WeakReference<TextView> mTextView;
    final private WeakReference<ProgressBar> mProgressBar;


    public SimpleAsyncTask(TextView tv, ProgressBar progressBar) {
        mTextView = new WeakReference<>(tv);
        mProgressBar = new WeakReference<>(progressBar);
    }

    @Override
    protected String doInBackground(Void... voids) {
        int number = new Random().nextInt(11) * 500;

        // sleep - publish progress every 20 ms
        for (int i = 20; i <= number; i += 20) {
            try {
                Thread.sleep(20);
                publishProgress((int) ((float) i / number * 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return String.format("Awake at last after sleeping for %s milliseconds", number);
    }

    @Override
    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
        mProgressBar.get().setProgress(0);
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        mProgressBar.get().setProgress(progress[0]);
    }
}
