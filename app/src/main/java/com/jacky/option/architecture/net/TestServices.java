package com.jacky.option.architecture.net;

import com.jacky.option.architecture.net.dto.Resp;
import com.jacky.option.architecture.net.dto.req.ReqKuaidi;
import com.jacky.option.architecture.net.dto.resp.RespKuaidi;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * <pre>
 *     author : jacks
 *     e-mail : 363525749@qq.com
 *     time   : 2019/12/03
 *     version: 1.0
 *     desc   : demo，可自行删掉。
 * </pre>
 */
public interface TestServices {
    // http://www.kuaidi100.com/query?type=yuantong&=11111111111

    String BASE_URL = "http://www.kuaidi100.com/";

    /**
     * 请求业务成功
     * {"message":"ok","nu":"11111111111","ischeck":"1","com":"yuantong","status":"200","condition":"F00","state":"3"
     * ,"data":[{"time":"2019-11-16 16:56:31","context":"查无结果","ftime":"2019-11-16 16:56:31"}]}
     *
     * @param type
     * @param postid
     * @return
     */
    @GET("query")
    Observable<Resp<List<RespKuaidi>>> getKuaidiByGet(@Query("type") String type, @Query("postid") long postid);


    /**
     * request body: {"postid":1111111111,"type":"yuantong"}
     *
     * 请求业务出错
     * {"message":"参数错误","nu":"","ischeck":"0","condition":"","com":"","status":"400","state":"0","data":[]}
     *
     * @param kuaidi
     * @return
     */
    @POST("query")
    Observable<Resp<List<RespKuaidi>>> getKuaidiByPost(@Body ReqKuaidi kuaidi);



}
