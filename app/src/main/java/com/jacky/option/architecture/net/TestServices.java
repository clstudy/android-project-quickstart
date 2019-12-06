package com.jacky.option.architecture.net;

import com.jacky.option.architecture.net.dto.Resp;
import com.jacky.option.architecture.net.dto.req.ReaKuaidi;
import com.jacky.option.architecture.net.dto.resp.RespKuaidi;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * <pre>
 *     author : jacks
 *     e-mail : 363525749@qq.com
 *     time   : 2019/12/03
 *     version: 1.0
 *     desc   :
 * </pre>
 */
public interface TestServices {
    // http://www.kuaidi100.com/query?type=yuantong&=11111111111

    String BASE_URL = "http://www.kuaidi100.com/";

    @GET("query")
    Observable<Resp<RespKuaidi>> getKuaidiByGet(@Query("type") String type, @Query("postid") long postid);
/*
成功的数据：

{"message":"ok","nu":"11111111111","ischeck":"1","com":"yuantong","status":"200","condition":"F00","state":"3"
,"data":[{"time":"2019-11-16 16:56:31","context":"查无结果","ftime":"2019-11-16 16:56:31"}]}
 */

    /**
     * 请求业务出错
     *
     * @param kuaidi
     * @return
     */
    @GET("query")
    Observable<Resp<RespKuaidi>> getKuaidiByPost(@Body ReaKuaidi kuaidi);
/*
失败的数据：

{"message":"参数错误","nu":"","ischeck":"0","condition":"","com":"","status":"400","state":"0","data":[]}
 */


}
