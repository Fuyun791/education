package com.education.controller;

import com.education.entity.RespBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author dell
 */
@Api(tags = "ManageController", description = "功能管理")
@RestController
@RequestMapping("/education/manage")
public class ManageController {

    private static final String URL = "http://123.57.57.136/jklimage/";

    @Value("${web.upload-path}")
    private String path;

    private String getMyIdByTime() {
        Date date = new Date();
        SimpleDateFormat sfDate = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String time = sfDate.format(date);
        int temp = (int) ((Math.random() * 9 + 1) * 1000);
        return time + String.valueOf(temp);
    }

    @ApiOperation("添加图片")
    @RequestMapping(value = "/file",method = RequestMethod.POST)
    public RespBody uploadAvatar(@RequestParam(required = false) MultipartFile file){
        try {
            File uploadPath=new File(path);
            String type = file.getOriginalFilename().contains(".jpg") ? ".jpg" : ".png";
            String filename= getMyIdByTime() + type;
            File filepath=new File(uploadPath,filename);
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            file.transferTo(new File(uploadPath+File.separator+filename));
            return RespBody.ok(URL + filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return RespBody.error();
    }

}
