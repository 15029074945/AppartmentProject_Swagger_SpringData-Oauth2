package com.spd.service;

import com.spd.bean.ConversationBean;
import com.spd.entity.Conversation;
import com.spd.repository.ConversationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConversationService {

    private final ConversationRepository conversationRepository;

    public ConversationService(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    public Optional<Conversation> getAnnouncementByAnnouncementId(int idAnnouncement) {
        return conversationRepository.findOneByAnnouncementId(idAnnouncement);
    }

    public Conversation saveConversation(Conversation conversation) {
        return conversationRepository.save(conversation);
    }

    public List<ConversationBean> getConversation(String email) {
        // TODO
        return null;
    }
}
