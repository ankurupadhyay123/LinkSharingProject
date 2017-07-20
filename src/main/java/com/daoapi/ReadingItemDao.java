package com.daoapi;

import com.entities.ReadingItem;
import com.entities.Resource;
import com.entities.User;

import java.util.List;

/**
 * Created by ankur on 21/7/17.
 */
public interface ReadingItemDao {

    List<Resource> getInboxResources(User user);
}
