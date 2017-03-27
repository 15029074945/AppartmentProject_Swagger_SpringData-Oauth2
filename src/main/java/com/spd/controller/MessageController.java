package com.spd.controller;

import com.spd.bean.MessageBean;
import com.spd.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/messages")
@Api(value = "messages")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "send message", httpMethod = "POST")
    public void message(Authentication authentication, @RequestBody MessageBean messageBean) {
        messageService.sendMessage(authentication.getName(), messageBean);
    }

}
