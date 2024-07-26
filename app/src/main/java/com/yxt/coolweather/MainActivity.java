package com.yxt.coolweather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.security.NetworkSecurityPolicy;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            boolean permittedOld = NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(); // 获取旧的 cleartext 流量是否允许
            Log.i("TAG", "onCreate, permittedOld: " + permittedOld); // 记录旧的 cleartext 流量是否允许的日志

            // 动态设置 setCleartextTrafficPermitted 方法
            ReflectionUtil.invokeMethod(NetworkSecurityPolicy.getInstance(), "setCleartextTrafficPermitted", true);

            boolean permittedNew = NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(); // 获取新的 cleartext 流量是否允许
            Log.i("TAG", "onCreate, permittedNew: " + permittedNew); // 记录新的 cleartext 流量是否允许的日志
        }
    }
}
