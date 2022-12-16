package com.example.cmake_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.cmake_app.databinding.ActivityMainBinding;

//public class MainActivity extends AppCompatActivity {
//
//    // Used to load the 'cmake_app' library on application startup.
//    static {
//        System.loadLibrary("cmake_app");
//    }
//
//    private ActivityMainBinding binding;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        // Example of a call to a native method
//        TextView tv = binding.sampleText;
//        tv.setText(stringFromJNI());
//    }
//
//    /**
//     * A native method that is implemented by the 'cmake_app' native library,
//     * which is packaged with this application.
//     */
//    public native String stringFromJNI();
//}



import androidx.appcompat.app.AppCompatActivity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final String MODEL_FILE = "centerface.tflite";

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);
        tv.setText(loadModelJNI(this.getAssets(), MODEL_FILE));
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */

    // Load model by TF Lite C++ API
    private native String loadModelJNI(AssetManager assetManager, String fileName);

}