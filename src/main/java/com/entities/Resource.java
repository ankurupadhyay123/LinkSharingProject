package com.entities;

import com.enums.ResourceEnum;
import com.enums.Visibility;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ankur on 17/7/17.
 */

@Entity
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer resourceId;

    @Column
    private String url;

    @Column
    private String description;

    @ManyToOne
    private User createdBy;

    @ManyToOne
    private Topic topic;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date lateUpdated;

    @Enumerated(EnumType.STRING)
    private ResourceEnum resourceEnum;

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    public ResourceEnum getResourceEnum() { return resourceEnum; }

    public void setResourceEnum(ResourceEnum resourceEnum) { this.resourceEnum = resourceEnum; }

    public Integer getResourceId() { return resourceId; }

    public void setResourceId(Integer resourceId) { this.resourceId = resourceId; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public User getCreatedBy() { return createdBy; }

    public void setCreatedBy(User createdBy) { this.createdBy = createdBy; }

    public Topic getTopic() { return topic; }

    public void setTopic(Topic topic) { this.topic = topic; }

    public Date getDateCreated() { return dateCreated; }

    public void setDateCreated(Date dateCreated) { this.dateCreated = dateCreated; }

    public Date getLateUpdated() { return lateUpdated; }

    public void setLateUpdated(Date lateUpdated) { this.lateUpdated = lateUpdated; }
}
