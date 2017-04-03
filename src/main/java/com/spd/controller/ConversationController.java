package com.spd.controller;

import com.spd.bean.ConversationBean;
import com.spd.entity.Announcement;
import com.spd.entity.User;
import com.spd.service.AnnouncementService;
import com.spd.service.ConversationService;
import com.spd.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/conversations")
@Api("conversations")
public class ConversationController {

    private final ConversationService conversationService;
    private final UserService userService;
    private final AnnouncementService announcementService;

    @Autowired
    public ConversationController(ConversationService conversationService, UserService userService, AnnouncementService announcementService) {
        this.conversationService = conversationService;
        this.userService = userService;
        this.announcementService = announcementService;
    }


    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "send conversation")
    public void sendConversation(@RequestBody ConversationBean conversationBean) {
        Announcement announcement = announcementService.getById(conversationBean.getAnnouncementId());
        List<User> attenders = new ArrayList<>();
        List<Integer> attenders1 = conversationBean.getAttenders();
        for (Integer attenderId : attenders1) {
            User attender = userService.getById(attenderId);
            attenders.add(attender);
        }
        conversationService.saveConversation(announcement, attenders);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "get conversation")
    public List<ConversationBean> getConversations(Authentication authentication) {
        return conversationService.getConversation(authentication.getName());
    }
}
