package com.servicesimpl;

import com.daoapi.ResourceDao;
import com.entities.Topic;
import com.entities.User;
import com.servicesapi.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ankur on 19/7/17.
 */

@Service
public class ResouceServiceImpl implements ResourceService {

    @Autowired
    ResourceDao resourceDao;

    @Override
    public boolean saveLinkUrlResource(User user, String linkUrl, String description, Topic topic) {
        return resourceDao.saveLinkUrl(user,linkUrl,description,topic);
    }

    @Override
    public boolean saveDocUrl(User user, String docUrl, String description, Topic topic) {
        return resourceDao.saveDocUrl(user,docUrl,description,topic);
    }
}
