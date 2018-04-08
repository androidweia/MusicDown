package com.test.musicdownload.model;

public interface IMainModel {
    void getID(String name,Get163InfoListener listener);
    void getUrl(long id,Get163InfoListener listener);
    void getSongMid(String name,GetQQInfoListener listener);
    void getVkey(String mid,GetQQInfoListener listener);
}
