package com.sky.exception;

/**
 * @author Aip
 * @version 1.0
 * @date 2024/9/27  16:44
 * @description 文件上传失败异常
 */
public class UploadFailedException extends BaseException{

    public UploadFailedException() {}

    public UploadFailedException(String message) {
        super(message);
    }
}
