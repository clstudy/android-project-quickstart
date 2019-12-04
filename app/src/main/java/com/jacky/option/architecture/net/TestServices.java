package com.jacky.option.architecture.net;

import com.jacky.option.architecture.net.dto.Resp;
import com.jacky.option.architecture.net.dto.RespData;
import com.jacky.option.architecture.net.dto.req.ReqVersion;
import com.jacky.option.architecture.net.dto.resp.RespVersion;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

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
    String BASE_URL = "http://shufu.tianyuango.cn:8321/";

    @POST("api/ApplyVersionService/updateApplyVersion")
    Observable<Resp<RespData<RespVersion>>> getVersion(@Body ReqVersion r);

}
