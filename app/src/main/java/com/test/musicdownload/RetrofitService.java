package com.test.musicdownload;

import com.test.musicdownload.javabean.Get163Id;
import com.test.musicdownload.javabean.Get163Url;
import com.test.musicdownload.javabean.GetqqMid;
import com.test.musicdownload.javabean.GetqqVkey;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface RetrofitService {
    @FormUrlEncoded
    //获取ID
    @POST("search/get/")
    Observable<Get163Id> get163MusicId(@FieldMap Map<String,String> map);
    //Observable<Get163Id> get163MusicId(@Field("s") String title, @Field("limit") int limit,
     //                                             @Field("type") int type, @Field("offset") int offset);
    //Call<Get163Id> getMusicId(@Field("s") String title, @Field("limit") int limit,
    //                     @Field("type") int type, @Field("offset") int offset);
    //通过ID查URL
    @GET("song/enhance/player/url")//?br=128000&ids=[108242]
    Observable<Get163Url> get163Url(@Query("br") int br,@Query("ids") String id);
    //获取MID
    @GET("soso/fcgi-bin/search_for_qq_cp")//?format=json&w=等你下课&perpage=10&n=10&p=1
    Observable<GetqqMid> getqqMusicMid(@QueryMap Map<String,String> map);
    //通过MID获取vkey
    @GET("base/fcgi-bin/fcg_music_express_mobile3.fcg")//?g_tk=1278911659&hostUin=0&format=json&cid=205361747&songmid=001J5QJL1pRQYB&filename=C400001J5QJL1pRQYB.m4a&guid=3655047200
    Observable<GetqqVkey> getqqVkey(@QueryMap Map<String,String> map);
    //通过MID,vkey拼接得到URL
    //url=APIConstants.qqm4aUrl()+"C400"+MID+".m4a?vkey="+vkey+"&guid=3655047200&fromtag=66";
}
