package com.test.musicdownload.ui;

import com.test.musicdownload.javabean.BaseMp3Info;
import com.test.musicdownload.javabean.Get163Id;

import java.util.List;

public interface IBaseView {
    void showProgress();
    void hideProgress();
    void showDialog(String url);
    void listviewUpdata(List<BaseMp3Info> list);
    void showErrorMsg(String msg);
}
