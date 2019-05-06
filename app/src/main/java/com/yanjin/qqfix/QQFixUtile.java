package com.yanjin.qqfix;

import android.util.Log;

public class QQFixUtile {
    public static final int MODE_NORMAL = 0;
    public static final int MODE_LUOLI = 1;
    public static final int MODE_DASHU = 2;
    public static final int MODE_JINGSONG = 3;
    public static final int MODE_GAOGUAI = 4;
    public static final int MODE_KONGLING = 5;
    public boolean playing = false;
    static {
        System.loadLibrary("fmod");
        System.loadLibrary("fmodL");
        System.loadLibrary("qqfix");
    }

    /**
     * 包房声音
     * @param path 声音路径
     * @param type 播放类型
     */
    public native void fixVoice(String path,int type);

    /**
     * 专门提供给JNI使用
     * @param flag
     */
    private void setPlaying(boolean flag){
        Log.d("yanjin","播放状态-"+flag);
        playing = flag;
    }

    /**
     * 用来判断是否正在播放，如果是就不能再播放
     * @return
     */
    public boolean isPlaying() {
        return playing;
    }
}
