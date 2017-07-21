package com.servicesapi;

import com.entities.ReadingItem;
import com.entities.Resource;
import com.entities.Topic;
import com.entities.User;

import java.util.List;

public interface ResourceService {

    boolean saveLinkUrlResource(User user, String linkUrl, String description, Topic topic);
    boolean saveDocUrl(User user, String docUrl, String description, Topic topic);
    List<ReadingItem> getInboxResource(User user);
    List<Resource> getRecentResources();
}
