package com.servicesimpl;

import com.daoapi.ReadingItemDao;
import com.daoapi.ResourceDao;
import com.entities.ReadingItem;
import com.entities.Resource;
import com.entities.Topic;
import com.entities.User;
import com.servicesapi.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResouceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    private ReadingItemDao readingItemDao;

    @Override
    public boolean saveLinkUrlResource(User user, String linkUrl, String description, Topic topic) {
        return resourceDao.saveLinkUrl(user,linkUrl,description,topic);
    }

    @Override
    public boolean saveDocUrl(User user, String docUrl, String description, Topic topic) {
        return resourceDao.saveDocUrl(user,docUrl,description,topic);
    }

    @Override
    public List<ReadingItem> getInboxResource(User user) {
        return readingItemDao.getInboxResources(user);
    }

    @Override
    public List<Resource> getRecentResources(){
        return readingItemDao.getRecentResources();
    }
}
