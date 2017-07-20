package com.daoapi;

import com.entities.Subscription;
import com.entities.Topic;
import com.entities.User;

import java.util.List;

/**
 * Created by ankur on 15/7/17.
 */
public interface SubscriptionDao {
    boolean subscribe(User user, Topic topic, String seriousness);
    Long getSubscriptions(User user);
    List<Topic> getSubscribedTopics(User user);
    Long getSubscriptionsForEachTopic();
}
