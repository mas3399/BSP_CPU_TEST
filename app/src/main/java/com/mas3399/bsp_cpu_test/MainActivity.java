package com.mas3399.bsp_cpu_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity-yxz";
    private static final String OUTPUT_PATH_NAME = "/data/tmp.txt";
    private static final String CAL_CPU_CMD = "cat /proc/stat";
    byte buff[] = new byte[1024];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    void initFile() {
        File file = new File(OUTPUT_PATH_NAME);
        if (!file.exists()) {
            try {
                //  chmod 777 /data
                file.createNewFile();
                Log.i(TAG, "onCreate: " + "ok!");
            } catch (IOException e) {
                Log.e(TAG, "onCreate: " + "fail!");
                e.printStackTrace();
                Toast.makeText(this, "未有/data读写权限", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**得到cpu运行信息
     *
     * @return
     */
    String getAllCPUInfo() {
        Process process = null;
        ByteArrayOutputStream out = null;
        InputStream in = null;
        try {
            process = Runtime.getRuntime().exec(CAL_CPU_CMD);
            process.waitFor();
            in = process.getInputStream();
            out = new ByteArrayOutputStream();
            int len = -1;
            while ((len = in.read(buff)) != -1) {
                out.write(buff,0,len);
            }
            return new String(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if(process!=null) process.destroy();
            if(in!=null) {
                try {
                    in.close();
                } catch (IOException e) { }
            }
            if(out!=null) {
                try {
                    out.close();
                } catch (IOException e) { }
            }
        }
        return null;
    }

    /**
     * 将得到的原始数据进行整理
     * @return 得到一个包含所有cpu占用率的一个列表
     */
    List<Double> calCPUInfo() {
        List<Double> cpu = new ArrayList<>();
        //TODO 计算

        return cpu;
    }

}