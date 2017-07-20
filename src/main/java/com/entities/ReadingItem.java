package com.entities;

import javax.persistence.*;

/**
 * Created by ankur on 20/7/17.
 */

@Entity
public class ReadingItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer readingItemId;

    @ManyToOne
    private Resource resource;

    @ManyToOne
    private User user;

    @Column
    private boolean isRead = false;

    public Integer getReadingItemId() { return readingItemId; }

    public void setReadingItemId(Integer readingItemId) { this.readingItemId = readingItemId; }

    public Resource getResource() { return resource; }

    public void setResource(Resource resource) { this.resource = resource; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public boolean isRead() { return isRead; }

    public void setRead(boolean read) { isRead = read; }
}
