package com.daoapi;

import com.entities.Topic;
import com.entities.User;

/**
 * Created by ankur on 15/7/17.
 */
public interface SubscriptionDao {
    boolean subscribe(User user, Topic topic, String seriousness);
    Long getSubscriptions(User user);
}
