package com.daoapi;

import com.entities.Topic;
import com.entities.User;

/**
 * Created by ankur on 19/7/17.
 */
public interface ResourceDao {

    boolean saveLinkUrl(User user, String linkUrl, String description, Topic topic);
    boolean saveDocUrl(User user, String docUrl, String description, Topic topic);
}
