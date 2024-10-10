package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.utils.GiteeImgsUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Aip
 * @version 1.0
 * @date 2024/9/21  17:17
 * @description 通用接口，如文件上传
 */
@RestController
@RequestMapping("/admin/common")
@Api(tags = "通用接口")
@Slf4j
public class CommonController {

    @Autowired
    private GiteeImgsUtil giteeImgsUtil;

    @PostMapping("/upload")
    public Result<String> upload(@RequestBody MultipartFile file) {
        log.info("文件上传: {}", file);
        String uploadUrl = giteeImgsUtil.upload(file);
        return Result.success(uploadUrl);
    }
}
