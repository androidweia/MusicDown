package com.test.musicdownload.presenter;

import android.util.Log;

import com.test.musicdownload.javabean.BaseMp3Info;
import com.test.musicdownload.model.Get163InfoListener;
import com.test.musicdownload.model.GetQQInfoListener;
import com.test.musicdownload.model.IMainModel;
import com.test.musicdownload.model.MainModel;
import com.test.musicdownload.ui.IBaseView;

import java.util.List;

public class MainPresenter implements Get163InfoListener,GetQQInfoListener {
    IBaseView view;
    IMainModel model;
    public MainPresenter(IBaseView view){
        this.view=view;
        model=new MainModel();
    }
    public void requestInfo(String name){
        view.showProgress();
        model.getID(name,this);
    }
    public void getUrl(long id){
        view.showProgress();
        model.getUrl(id,this);
    }
    public void requestInfo2(String name){
        view.showProgress();
        model.getSongMid(name,this);
    }
    public void getVkey(String mid){
        view.showProgress();
        model.getVkey(mid,this);
    }
    private void updataListview(List<BaseMp3Info> list){
        view.hideProgress();
        view.listviewUpdata(list);
    }
    @Override
    public void onIdSuccess(List<BaseMp3Info> list) {
        updataListview(list);
    }

    @Override
    public void onUrlSuccess(String url) {
        view.hideProgress();
        view.showDialog(url);
    }

    @Override
    public void onMidSuccess(List<BaseMp3Info> list) {
        updataListview(list);
    }

    @Override
    public void onVkeySuccess(String url) {
        view.hideProgress();
        //Log.e("123",url);
        view.showDialog(url);
    }

    @Override
    public void onFailure(String msg) {
        view.hideProgress();
        view.showErrorMsg(msg);
    }
}
