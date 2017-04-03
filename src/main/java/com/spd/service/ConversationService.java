package com.spd.service;

import com.spd.bean.ConversationBean;
import com.spd.entity.Announcement;
import com.spd.entity.Attender;
import com.spd.entity.Conversation;
import com.spd.entity.User;
import com.spd.repository.AttenderRepository;
import com.spd.repository.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConversationService {

    private final ConversationRepository conversationRepository;
    private final AttenderRepository attenderRepository;

    @Autowired
    public ConversationService(ConversationRepository conversationRepository, AttenderRepository attenderRepository) {
        this.conversationRepository = conversationRepository;
        this.attenderRepository = attenderRepository;
    }

    public Optional<Conversation> getAnnouncementByAnnouncementId(int idAnnouncement) {
        return conversationRepository.findOneByAnnouncementId(idAnnouncement);
    }

    public void saveConversation(Announcement announcement, List<User> attenders) {
        Conversation conversation = new Conversation();
        conversation.setAnnouncement(announcement);
        conversation.setActive(true);
        //conversation.setCreatedDate(new Date());
        //conversation.setUpdatedDate(new Date());
        conversationRepository.save(conversation);

        for (User attenderUser: attenders) {
            Attender attender = new Attender();
            attender.setConversation(conversation);
            attender.setUser(attenderUser);
            attenderRepository.save(attender);
        }
    }

    public List<ConversationBean> getConversation(String email) {
        List<ConversationBean> conversationBeans = new ArrayList<>();
        List<Integer> attenderIds = new ArrayList<>();

        Conversation conversation = conversationRepository.findOne(1);
        ConversationBean conversationBean = new ConversationBean();
        conversationBean.setId(conversation.getId());
        conversationBean.setAnnouncementId(conversation.getAnnouncement().getId());
        for (Attender attender: conversation.getAttenders()) {
            attenderIds.add(attender.getUser().getId());
        }
        conversationBean.setAttenders(attenderIds);

        conversationBeans.add(conversationBean);
        return conversationBeans;
    }
}
