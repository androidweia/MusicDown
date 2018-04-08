package com.test.musicdownload.model;

import android.util.Log;

import com.test.musicdownload.APIConstants;
import com.test.musicdownload.RetrofitFactory;
import com.test.musicdownload.javabean.BaseMp3Info;
import com.test.musicdownload.javabean.Get163Id;
import com.test.musicdownload.javabean.Get163Url;
import com.test.musicdownload.javabean.GetqqMid;
import com.test.musicdownload.javabean.GetqqVkey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class MainModel implements IMainModel {
    private BaseMp3Info mp3Info;

    @Override
    public void getID(String name, final Get163InfoListener listener) {
        Map<String, String> map = new HashMap<>();
        map.put("s", name);
        map.put("limit", "10");
        map.put("type", "1");
        map.put("offset", "0");
        /*
        Observable<Get163Id> request = RetrofitFactory.get163Instance().get163MusicId(map);
        request.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Get163Id>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Get163Id resultBean) {
                        List<Get163Id.ResultBean.SongsBean> beans = resultBean.getResult().getSongs();
                        //Log.e("1231", beans.size() + "");
                        List<BaseMp3Info> list = new ArrayList<>();

                        if (beans!=null) {
                            if (beans != null && !beans.isEmpty()) {
                                for (int i = 0; i < beans.size(); i++) {
                                    mp3Info = new BaseMp3Info();
                                    mp3Info.setTitle(beans.get(i).getName());
                                    mp3Info.setId(beans.get(i).getId());
                                    mp3Info.setArtist(beans.get(i).getArtists().get(0).getName());
                                    list.add(mp3Info);
                                }
                                listener.onMidSuccess(list);
                            }
                        }else {
                            String msg="没有歌曲信息";
                            listener.onFailure(msg);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
*/

        Observable<Get163Id> request = RetrofitFactory.get163Instance().get163MusicId(map);
        request.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Get163Id>() {
                    @Override
                    public void accept(Get163Id resultBean) throws Exception {
                        List<Get163Id.ResultBean.SongsBean> beans = resultBean.getResult().getSongs();
                        //Log.e("1231", beans.size() + "");
                        List<BaseMp3Info> list = new ArrayList<>();

                        if (beans != null) {
                            if (!beans.isEmpty()) {
                                for (int i = 0; i < beans.size(); i++) {
                                    mp3Info = new BaseMp3Info();
                                    mp3Info.setTitle(beans.get(i).getName());
                                    mp3Info.setId(beans.get(i).getId());
                                    mp3Info.setArtist(beans.get(i).getArtists().get(0).getName());
                                    list.add(mp3Info);
                                }
                                listener.onIdSuccess(list);
                            }
                        } else {
                            String msg = "没有歌曲信息";
                            listener.onFailure(msg);
                        }
                    }
                });
    }

    @Override
    public void getUrl(long id, final Get163InfoListener listener) {
        Observable<Get163Url> request = RetrofitFactory.get163Instance().get163Url(128000, "[" + id + "]");
        request.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Get163Url>() {
                    @Override
                    public void accept(Get163Url get163Url) throws Exception {
                        String url = get163Url.getData().get(0).getUrl() + "";
                        //Log.e("1213",url);
                        listener.onUrlSuccess(url);
                    }
                });
    }

    @Override
    public void getSongMid(String name, final GetQQInfoListener listener) {
        Map<String, String> map = new HashMap<>();
        map.put("format", "json");
        map.put("w", name);
        map.put("n", "10");
        map.put("p", "1");
        Observable<GetqqMid> request = RetrofitFactory.getqqInstance().getqqMusicMid(map);
        request.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GetqqMid>() {
                    @Override
                    public void accept(GetqqMid getqqMid) throws Exception {
                        List<GetqqMid.DataBean.SongBean.ListBean> beans = getqqMid.getData().getSong().getList();
                        List<BaseMp3Info> list = new ArrayList<>();
                        if (beans != null) {
                            if (!beans.isEmpty()) {
                                for (int i = 0; i < beans.size(); i++) {
                                    mp3Info = new BaseMp3Info();
                                    mp3Info.setTitle(beans.get(i).getSongname());
                                    mp3Info.setArtist(beans.get(i).getSinger().get(0).getName());
                                    mp3Info.setMid(beans.get(i).getSongmid());
                                    list.add(mp3Info);
                                }
                                listener.onMidSuccess(list);
                            }
                        } else {
                            String msg = "没有歌曲信息";
                            listener.onFailure(msg);
                        }
                    }
                });
    }

    @Override
    public void getVkey(final String mid, final GetQQInfoListener listener) {
        Map<String, String> map = new HashMap<>();
        map.put("g_tk", "1278911659");
        map.put("hostUin", "0");
        map.put("format", "json");
        map.put("cid", "205361747");
        map.put("songmid",mid);
        map.put("filename","C400"+mid+".m4a");
        map.put("guid","3655047200");
        Observable<GetqqVkey> request=RetrofitFactory.getqqInstance().getqqVkey(map);
        request.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GetqqVkey>() {
                    @Override
                    public void accept(GetqqVkey getqqVkey) throws Exception {
                        String vkey=getqqVkey.getData().getItems().get(0).getVkey()+"";
                        String url= APIConstants.qqm4aUrl()+"C400"+mid+".m4a?vkey="+vkey+"&guid=3655047200&fromtag=66";
                        listener.onVkeySuccess(url);
                    }
                });
    }
}
