package com.example.asyncexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.asyncexample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static final String TEXT_STATE = "currentText";
    private static final String PROGRESS_STATE = "currentProgress";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState != null) {
            binding.textView.setText(savedInstanceState.getString(TEXT_STATE));
            binding.progressBar.setProgress(savedInstanceState.getInt(PROGRESS_STATE));
        }
    }

    public void startTask(View view) {
        binding.textView.setText(R.string.napping);
        new SimpleAsyncTask(binding.textView, binding.progressBar).execute();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TEXT_STATE, binding.textView.getText().toString());
        outState.putInt(PROGRESS_STATE, binding.progressBar.getProgress());
    }
}