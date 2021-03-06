package com.example.fanyishuo.fan.App;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import org.xutils.x;

import java.io.File;

/**
 * Created by hasee on 2017/8/2.
 */

public class AppApplication extends Application{
    private static AppApplication mAppApplication;

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        initImageLoader(getApplicationContext());
        mAppApplication = this;
        x.Ext.init(this);
    }

    /**
     * 获取Application
     */
    public static AppApplication getApp() {
        return mAppApplication;
    }


    /** 初始化ImageLoader */
    /**
     * 初始化ImageLoader
     */
    public static void initImageLoader(Context context) {

        String path= Environment.getExternalStorageDirectory().getPath()+"/jj";
        File file=new File(path);
        //创建配置ImageLoader(所有的选项都是可选的,只使用那些你真的想定制)，这个可以设定在APPLACATION里面，设置为全局的配置参数
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(context)
                .threadPoolSize(3)//线程池内加载的数量
                .denyCacheImageMultipleSizesInMemory()//.memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation你可以通过自己的内存缓存实现
                .memoryCacheSize(2 * 1024 * 1024)
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .diskCache(new UnlimitedDiskCache(file))
                //自定义缓存路径//.imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .build();
        ImageLoader.getInstance().init(config);//全局初始化此配置
    }
}
