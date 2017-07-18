package com.entities;

import com.enums.Seriousness;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by ankur on 15/7/17.
 */

@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer subcriptionID;

/*    @Column
    private String topicname;*/
    @ManyToOne
    Topic topic;

/*
    @Column
    private String username;
*/
    @ManyToOne
    User user;

    @Enumerated(EnumType.STRING)
    private Seriousness seriousness;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    public Topic getTopic() { return topic; }

    public void setTopic(Topic topic) { this.topic = topic; }

    public Integer getSubcriptionID() { return subcriptionID; }

    public void setSubcriptionID(Integer subcriptionID) {
        this.subcriptionID = subcriptionID;
    }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Seriousness getSeriousness() {
        return seriousness;
    }

    public void setSeriousness(Seriousness seriousness) {
        this.seriousness = seriousness;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "Subscription [subcriptionID=" + subcriptionID + ", topic=" + topic + ", user=" + user + ", dateCreated="
                + dateCreated + ", seriousness=" + seriousness +"]";
    }
}
