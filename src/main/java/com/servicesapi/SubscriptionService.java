package com.servicesapi;

import com.entities.Topic;
import com.entities.User;

/**
 * Created by ankur on 15/7/17.
 */
public interface SubscriptionService {
    boolean subscribe(User user, Topic topic, String seriousness);
    Long getSubcriptionsOfUser(User user);
}
