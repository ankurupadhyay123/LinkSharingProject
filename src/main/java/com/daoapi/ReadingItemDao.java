package com.daoapi;

import com.entities.ReadingItem;
import com.entities.Resource;
import com.entities.User;

import java.util.List;

public interface ReadingItemDao {

    List<ReadingItem> getInboxResources(User user);
    List<Resource> getRecentResources();
}
