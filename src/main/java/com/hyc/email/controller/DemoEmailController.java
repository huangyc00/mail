package com.hyc.email.controller;

import com.hyc.email.dto.ResultDataDto;
import com.hyc.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class DemoEmailController {

    @Autowired
    private EmailService emailService;

    /**
     * 测试邮件发送
     */
//    @ApiOperation(value="测试邮件发送", notes="getEntityById")
    @RequestMapping(value = "/getTestDemoEmail", method = RequestMethod.GET)
    public  ResultDataDto getEntityById() throws Exception {
        String sendTo = "851809776@qq.com";
        String titel = "测试邮件标题";
        String content = "邮件测试专员";
        emailService.sendSimpleMail(sendTo, titel, content);
        return ResultDataDto.addSuccess();
    }

    @RequestMapping(value = "/getTestDemoEmail2", method = RequestMethod.GET)
    public  ResultDataDto getEntityById2() throws Exception {
        String sendTo = "1057130846@qq.com";
        String titel = "测试邮件标题";
        String content = "带附件";
        emailService.sendAttachmentsMail(sendTo,titel,content,"C:\\Users\\Administrator\\Desktop\\API.chw","API1.chw");
        return ResultDataDto.addSuccess();
    }
}
