package com.spd.service;

import com.spd.bean.MessageBean;
import com.spd.entity.*;
import com.spd.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserService userService;
    private final AnnouncementService announcementService;
    private final ConversationService conversationService;

    public MessageService(MessageRepository messageRepository, UserService userService, AnnouncementService announcementService, ConversationService conversationService) {
        this.messageRepository = messageRepository;
        this.userService = userService;
        this.announcementService = announcementService;
        this.conversationService = conversationService;
    }

    public void sendMessage(String email, MessageBean messageBean) {
        int idAnnouncement = messageBean.getIdAnnouncement();
        Announcement announcement = announcementService.getById(idAnnouncement);
        Optional<Conversation> conversation = conversationService.getAnnouncementByAnnouncementId(idAnnouncement);
        User userByEmail = userService.getByEmail(email);
        User userByAnnouncement = announcementService.getById(idAnnouncement).getUser();
        if (conversation.isPresent()) {
            // TODO
        }
        else {
            Conversation conv = new Conversation();
            conv.setAnnouncement(announcement);
            Conversation conversationNew = conversationService.saveConversation(conv);
            Attender attender = new Attender();
            attender.setUser(userByAnnouncement);
            List<Attender> attenders = new ArrayList<>();
            attenders.add(attender);
            conversationNew.setAttenders(attenders);
            Message message = new Message();
            message.setText(messageBean.getText());
            message.setReceived(false);
            List<Message> messages = new ArrayList<>();
            //messages.add()
            //conversation.setMessages();
            // TODO
        }
    }
}
