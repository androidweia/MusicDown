package com.test.musicdownload.model;

import com.test.musicdownload.javabean.BaseMp3Info;
import com.test.musicdownload.javabean.Get163Id;

import java.util.List;

public interface Get163InfoListener {
    void onIdSuccess(List<BaseMp3Info> list);
    void onUrlSuccess(String url);
    void onFailure(String msg);

}
