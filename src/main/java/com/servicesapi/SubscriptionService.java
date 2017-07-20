package com.servicesapi;

import com.entities.Subscription;
import com.entities.Topic;
import com.entities.User;

import java.util.List;

/**
 * Created by ankur on 15/7/17.
 */
public interface SubscriptionService {
    boolean subscribe(User user, Topic topic, String seriousness);
    Long getSubcriptionsOfUser(User user);
    List<Topic> getSubscribedTopics(User user);
    Long getSubscriptionsForEachTopic();
}
