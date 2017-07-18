package com.entities;

import com.enums.Visibility;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ankur on 14/7/17.
 */
@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer topicId;

    @Column
    private String name;

  //  @Column
 //   private String createdBy;
    @ManyToOne
    private User createdBy;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    @Enumerated(EnumType.STRING)
    private Visibility visibility;

    public User getCreatedBy() { return createdBy; }

    public void setCreatedBy(User createdBy) { this.createdBy = createdBy; }

    public Integer getTopicId() { return topicId; }

    public void setTopicId(Integer topicId) { this.topicId = topicId; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Date getDateCreated() { return dateCreated; }

    public void setDateCreated(Date dateCreated) { this.dateCreated = dateCreated; }

    public Date getLastUpdated() { return lastUpdated; }

    public void setLastUpdated(Date lastUpdated) { this.lastUpdated = lastUpdated; }

    public Visibility getVisibility() { return visibility; }

    public void setVisibility(Visibility visibility) { this.visibility = visibility; }

    @Override
    public String toString() {
        return "Topic [topicId=" + topicId + ", name=" + name + ", createdBy=" + createdBy + ", dateCreated="
                + dateCreated + ", lastUpdated=" + lastUpdated + ", visibility=" + visibility +"]";
    }
}
