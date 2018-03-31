package plugin.water.com.pluginapk1;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import plugin.water.com.plugininteface.ActivityPluginInteface;

/**
 * 主要重写activity相关上下文的相关方法，所以插件类都要继承这个基类
 */
public class WaterActivity   extends Activity implements ActivityPluginInteface {

    /**
     * 这个主要是存储宿主傀儡acitity
     */
    protected  Activity that;
    //通过这个方法，宿主context 传递过来
    @Override
    public void attach(Activity proxyActivity) {
        this.that = proxyActivity;
    }

    //重写所有上下文相关方法  start---------------------------------------------------
    @Override
    public void setContentView(View view) {
        if (that != null) {
            that.setContentView(view);
        }else {
            super.setContentView(view);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        that.setContentView(layoutResID);
    }

    @Override
    public ComponentName startService(Intent service) {
        Intent m = new Intent();
        m.putExtra("serviceName", service.getComponent().getClassName());
        return that.startService(m);
    }

    @Override
    public View findViewById(int id) {
        return that.findViewById(id);
    }

    @Override
    public Intent getIntent() {
        if(that!=null){
            return that.getIntent();
        }
        return super.getIntent();
    }
    @Override
    public ClassLoader getClassLoader() {
        return that.getClassLoader();
    }


    @Override
    public void startActivity(Intent intent) {
//        ProxyActivity --->className
        Intent m = new Intent();
        m.putExtra("className", intent.getComponent().getClassName());
        that.startActivity(m);
    }

    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        return that.getLayoutInflater();
    }

    @Override
    public ApplicationInfo getApplicationInfo() {
        return that.getApplicationInfo();
    }


    @Override
    public Window getWindow() {
        return that.getWindow();
    }


    @Override
    public WindowManager getWindowManager() {
        return that.getWindowManager();
    }


//重写所有上下文相关方法  end---------------------------------------------------

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }
}
