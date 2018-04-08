package com.test.musicdownload;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import com.test.musicdownload.ui.MainActivity;

import java.io.File;

public class FileUtil {
    private static Context context;
    public FileUtil(Context context){
        this.context=context;
    }
    private   String path= Environment.getExternalStorageDirectory()+"/xxMusic/download/";
    private   boolean isFileExist(String filename){
        File file=new File(path+filename);
        if (file.exists()){
            return true;
        }else {
            return false;
        }
    }
    public  void downMusic(String url,String filename){
        if (isFileExist(filename)){
            Toast.makeText(context,"文件已存在",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "下载开始", Toast.LENGTH_SHORT).show();
            //创建下载任务,downloadUrl就是下载链接
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
            //指定下载路径和下载文件名
            request.setDestinationInExternalPublicDir("/xxMusic/download/", filename);
            //获取下载管理器
            DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            //将下载任务加入下载队列，否则不会进行下载
            downloadManager.enqueue(request);
        }
    }
}
