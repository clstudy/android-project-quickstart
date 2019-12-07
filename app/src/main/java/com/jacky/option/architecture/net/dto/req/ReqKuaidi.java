package com.jacky.option.architecture.net.dto.req;

/**
 * <pre>
 *     author : jacks
 *     e-mail : chenlong5@ffcs.cn
 *     time   : 2019/12/06
 *     version: 1.0
 *     desc   :
 * </pre>
 */
public class ReqKuaidi {

    /**
     * type : yuantong
     * postid : 11111111111
     */

    private String type;
    private long postid;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getPostid() {
        return postid;
    }

    public void setPostid(long postid) {
        this.postid = postid;
    }
}
