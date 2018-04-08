package com.test.musicdownload.model;

import com.test.musicdownload.javabean.BaseMp3Info;

import java.util.List;

public interface GetQQInfoListener {
    void onMidSuccess(List<BaseMp3Info> list);
    void onVkeySuccess(String url);
    void onFailure(String msg);
}
