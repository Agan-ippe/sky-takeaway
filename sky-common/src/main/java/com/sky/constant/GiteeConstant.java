package com.sky.constant;

/**
 * @author Aip
 * @version 1.0
 * @date 2024/9/23  17:21
 * @description 码云图床常量类
 */
public class GiteeConstant {

    /**
     * 码云私人访问令牌
     */
    public static final String ACCESS_TOKEN = "b9284d75444e55a0507284d121ac60de";

    /**
     * 码云个人空间名
     */
    public static final String OWNER = "Aip0_o";

    /**
     * 码云仓库路径
     */
    public static final String REPO = "imgs";

    /**
     * 访问前缀
     */
    public static final String PRE = "https://gitee.com/" + OWNER + "/" + REPO + "/blob/master/";

    /**
     * 新建(POST)、获取(GET)、删除(DELETE)文件：()中指的是使用对应的请求方式
     * %s =>仓库所属空间地址(企业、组织或个人的地址path)  (owner)
     * %s => 仓库路径(repo)
     * %s => 文件的路径(path)
     */
    public static final String API_CREATE_POST =
            "https://gitee.com/api/v5/repos/%s/%s/contents/%s";


    /**
     * 提交描述
     */
    public static final String ADD_MESSAGE = "add file";
    public static final String DEL_MESSAGE = "del file";

    /**
     * 新建上传文件JSON结果之 commit
     */
    public static final String CREATE_UPLOAD_JSON_RESULT_COMMIT = "commit";

    public static final String CREATE_UPLOAD_JSON_RESULT_CONTENT = "content";
    public static final String CREATE_UPLOAD_JSON_RESULT_CONTENT_DOWNLOAD_URL = "download_url";
    public static final String SHA = "sha";
}
