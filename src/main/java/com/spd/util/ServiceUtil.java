package com.spd.util;

import com.spd.entity.Announcement;
import com.spd.entity.User;

import java.util.Optional;

public class ServiceUtil {

    public static boolean isAuthenticationUserAnnouncement(Optional<User> userOptional, Optional<Announcement> announcementOptional) {
        return announcementOptional.isPresent() && userOptional.isPresent() &&
                userOptional.get().getId().equals(announcementOptional.get().getUser().getId());
    }

}
