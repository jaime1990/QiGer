package com.rickwan.qiger.common.net;

import com.rickwan.qiger.beans.Environment;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * author wanqiang
 * date 16/12/19
 * desc${TODO}
 */

public interface Interfaces {

    String BASE_URL = "http://apicloud.mob.com/";

    /**
     * 空气质量查询
     * @param key 1a0c3b7345c16
     * @param city
     * @param province
     * @return
     */
    @GET("environment/query")
    Observable<Environment> queryEnvieronment(@Query("key") String key,
                                              @Query("city") String city,
                                              @Query("province") String province);




}
