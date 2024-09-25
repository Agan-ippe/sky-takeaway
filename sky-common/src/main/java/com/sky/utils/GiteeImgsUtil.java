package com.sky.utils;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.sky.constant.GiteeConstant;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Aip
 * @version 1.0
 * @date 2024/9/21  17:31
 * @description 用于gitee图床工具类
 */
public class GiteeImgsUtil {

    /**
     * 上传文件
     * @param path 文件路径
     * @param file 目标文件
     * @return
     */
    public static String upload(String path, MultipartFile file){

        String originalFilename = file.getOriginalFilename();
        //文件名判空
        if (originalFilename == null) {
            return null;
        }

        String fileUrl = GiteeImgsUtil.createUploadFileUrl(path, originalFilename);
        // 封装请求参数
        Map<String, Object> map = null;

        try {
            map = GiteeImgsUtil.getUploadBodyMap(file.getBytes());
        } catch (IOException e) {
            throw null;
        }

        String jsonResult  = HttpUtil.post(fileUrl, map);
        JSONObject jsonObject = JSONUtil.parseObj(jsonResult);
        // 提交失败
        if (jsonObject.getObj(GiteeConstant.CREATE_UPLOAD_JSON_RESULT_COMMIT) == null) {
            return null;
        }
        // 提交成功，返回下载地址
        JSONObject content = JSONUtil.parseObj(jsonObject.getObj(GiteeConstant.CREATE_UPLOAD_JSON_RESULT_CONTENT));
        return content.getObj(GiteeConstant.CREATE_UPLOAD_JSON_RESULT_CONTENT_DOWNLOAD_URL).toString();
    }

    /**
     * 封装新建文件请求体
     * 格式:https://gitee.com/api/v5/repos/Aip0_o/imgs/contents/test
     * @param bytes
     * @return
     */
    private static Map<String, Object> getUploadBodyMap(byte[] bytes) {
        HashMap<String, Object> bodyMap = new HashMap<>(3);
        bodyMap.put("access_token", GiteeConstant.ACCESS_TOKEN);
        bodyMap.put("message", GiteeConstant.ADD_MESSAGE);
        bodyMap.put("content", Base64.encode(bytes));
        return bodyMap;
    }

    /**
     * 创建上传文件的url
     * @param path 文件路径
     * @param originalFilename 文件名
     * @return
     */
    private static String createUploadFileUrl(String path, String originalFilename) {
        // 获取文件格式
//        String fileSuffix = getFileSuffix(originalFilename);
        // 拼接文件上传名
//        String fileName = UUID.randomUUID().toString() + fileSuffix;
        // 拼接上传文件的url
        return String.format(GiteeConstant.API_CREATE_POST,
                GiteeConstant.OWNER,
                GiteeConstant.REPO,
                path + originalFilename
                );
    }

    /**
     * 删除文件
     * @param path
     * @return
     */
    public static Boolean deleteFile(String path){
        String deleteFileUrl = GiteeImgsUtil.getFileUrl(path);
        Map<String, Object> bodyMap = getDeleteBodyMap(path);

        // 发送delete请求
        String jsonResult = HttpRequest.delete(deleteFileUrl).form(bodyMap).execute().body();
        // 解析返回结果
        JSONObject jsonObj = JSONUtil.parseObj(jsonResult);
        return jsonObj.getObj(GiteeConstant.CREATE_UPLOAD_JSON_RESULT_COMMIT) != null;
    }

    /**
     * 封装删除请求体
     * 格式:https://gitee.com/api/v5/repos/Aip0_o/imgs/contents/text.txt
     * ?access_token=b9284d75444e55a0507284d121ac60de
     * &sha=0fcf9d3c90ecf6fb400cd3c2c207515b703c3c5c
     * &message=testDel
     * @param path 文件路径
     * @return
     */
    private static Map<String, Object> getDeleteBodyMap(String path) {
        HashMap<String, Object> map = new HashMap<>(3);
        map.put("access_token", GiteeConstant.ACCESS_TOKEN);
        map.put("message", GiteeConstant.DEL_MESSAGE);
        map.put("sha", getSha(path));
        return map;
    }

    /**
     * 获取sha
     * @param path 文件路径
     * @return
     */
    private static Object getSha(String path) {
        String fileUrl = GiteeImgsUtil.getFileUrl(path);
        Map<String, Object> map = getFileBodyMap();
        String jsonResult = HttpUtil.get(fileUrl, map);
        JSONObject jsonObject = JSONUtil.parseObj(jsonResult);
        if (jsonObject.getObj(GiteeConstant.SHA) == null) {
            return null;
        }
        return jsonObject.getObj(GiteeConstant.SHA).toString();
    }

    /**
     * 获取sha的请求体（获取仓库具体路径下的内容）
     * https://gitee.com/api/v5/repos/Aip0_o/imgs/contents/text.txt?
     * access_token=b9284d75444e55a0507284d121ac60de
     * @return
     */
    private static Map<String, Object> getFileBodyMap() {
        HashMap<String, Object> map = new HashMap<>(3);
        map.put("access_token", GiteeConstant.ACCESS_TOKEN);
        return map;

    }

    /**
     * 获取文件地址
     * @param path
     * @return
     */
    private static String getFileUrl(String path) {
        return String.format(GiteeConstant.API_CREATE_POST,
                GiteeConstant.OWNER,
                GiteeConstant.REPO,
                path);
    }

    /**
     * 获取文件格式
     * @param originalFilename
     */
    private static String getFileSuffix(String originalFilename) {
        // 截取文件格式
        return originalFilename.contains(".") ? originalFilename.substring(originalFilename.lastIndexOf(".")) : null;
    }
}
