package com.yanjin.qqfix;

import android.Manifest;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.yanjin.qqfix.permission.PermissionDenied;
import com.yanjin.qqfix.permission.PermissionHelper;
import com.yanjin.qqfix.permission.PermissionPermanentDenied;
import com.yanjin.qqfix.permission.PermissionSucceed;

import org.fmod.FMOD;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    private final int PERMISSION_CODE = 1;
    private int mCurrentType = QQFixUtile.MODE_NORMAL;
    private QQFixUtile mQqFixUtile;
    private String mVoiceRootDirPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FMOD.init(this);
        mVoiceRootDirPath = Environment.getExternalStorageDirectory().getPath()+ File.separator+"Voice Recorder"+File.separator+"123.m4a";
        mQqFixUtile = new QQFixUtile();
        findViewById(R.id.m_btn_normal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("yanjin","检查播放状态-"+mQqFixUtile.isPlaying());
                if(mQqFixUtile.isPlaying()){
                    Toast.makeText(MainActivity.this, "正在播放请稍后", Toast.LENGTH_SHORT).show();
                    return;
                }
                mCurrentType = QQFixUtile.MODE_NORMAL;
                requestPermission();
            }
        });
        findViewById(R.id.m_btn_luoli).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("yanjin","检查播放状态-"+mQqFixUtile.isPlaying());
                if(mQqFixUtile.isPlaying()){
                    Toast.makeText(MainActivity.this, "正在播放请稍后", Toast.LENGTH_SHORT).show();
                    return;
                }
                mCurrentType = QQFixUtile.MODE_LUOLI;
                requestPermission();
            }
        });
        findViewById(R.id.m_btn_jingsong).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("yanjin","检查播放状态-"+mQqFixUtile.isPlaying());
                if(mQqFixUtile.isPlaying()){
                    Toast.makeText(MainActivity.this, "正在播放请稍后", Toast.LENGTH_SHORT).show();
                    return;
                }
                mCurrentType = QQFixUtile.MODE_JINGSONG;
                requestPermission();
            }
        });
        findViewById(R.id.m_btn_dashu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("yanjin","检查播放状态-"+mQqFixUtile.isPlaying());
                if(mQqFixUtile.isPlaying()){
                    Toast.makeText(MainActivity.this, "正在播放请稍后", Toast.LENGTH_SHORT).show();
                    return;
                }
                mCurrentType = QQFixUtile.MODE_DASHU;
                requestPermission();
            }
        });
        findViewById(R.id.m_btn_gaoguai).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("yanjin","检查播放状态-"+mQqFixUtile.isPlaying());
                if(mQqFixUtile.isPlaying()){
                    Toast.makeText(MainActivity.this, "正在播放请稍后", Toast.LENGTH_SHORT).show();
                    return;
                }
                mCurrentType = QQFixUtile.MODE_GAOGUAI;
                requestPermission();
            }
        });
        findViewById(R.id.m_btn_kongling).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("yanjin","检查播放状态-"+mQqFixUtile.isPlaying());
                if(mQqFixUtile.isPlaying()){
                    Toast.makeText(MainActivity.this, "正在播放请稍后", Toast.LENGTH_SHORT).show();
                    return;
                }
                mCurrentType = QQFixUtile.MODE_KONGLING;
                requestPermission();
            }
        });
    }

    private void requestPermission() {
        PermissionHelper.with(this).requestCode(PERMISSION_CODE).requestPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.RECORD_AUDIO
        ).request();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionHelper.requestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @PermissionDenied(requestCode = PERMISSION_CODE)
    private void onPermissionDenied() {
        Toast.makeText(this, "您拒绝了开启权限,可去设置界面打开", Toast.LENGTH_SHORT).show();
    }


    @PermissionPermanentDenied(requestCode = PERMISSION_CODE)
    private void onPermissionPermanentDenied() {
        Toast.makeText(this, "您选择了永久拒绝,可在设置界面重新打开", Toast.LENGTH_SHORT).show();
    }

    @PermissionSucceed(requestCode = PERMISSION_CODE)
    private void onPermissionSuccess() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mQqFixUtile.fixVoice(mVoiceRootDirPath, mCurrentType);

            }
        }).start();

    }

    @Override
    protected void onDestroy() {
        FMOD.close();
        super.onDestroy();
    }
}
