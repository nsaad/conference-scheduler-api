package com.conf.scheduler.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Presentation implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = "version")
   private int version;

   @Column
   private int sessionday;

   @Column
   @Temporal(TemporalType.DATE)
   private Date starttime;

   @Column
   @Temporal(TemporalType.DATE)
   private Date endtime;

   @Column
   @NotNull
   private String title;

   @Column(length = 2000)
   private String brief;

   @ManyToMany
   private Set<Speaker> speakers = new HashSet<Speaker>();

   @Column
   private String difficulty;

   @Column
   private Room room;

   @Column
   private Track sessiontrack;

   @Column
   private Subtrack sessionsubtrack;

   @ManyToMany
   private Set<Tag> tags = new HashSet<Tag>();

   public Long getId()
   {
      return this.id;
   }

   public void setId(final Long id)
   {
      this.id = id;
   }

   public int getVersion()
   {
      return this.version;
   }

   public void setVersion(final int version)
   {
      this.version = version;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (!(obj instanceof Presentation))
      {
         return false;
      }
      Presentation other = (Presentation) obj;
      if (id != null)
      {
         if (!id.equals(other.id))
         {
            return false;
         }
      }
      return true;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
   }

   public int getSessionday()
   {
      return sessionday;
   }

   public void setSessionday(int sessionday)
   {
      this.sessionday = sessionday;
   }

   public Date getStarttime()
   {
      return starttime;
   }

   public void setStarttime(Date starttime)
   {
      this.starttime = starttime;
   }

   public Date getEndtime()
   {
      return endtime;
   }

   public void setEndtime(Date endtime)
   {
      this.endtime = endtime;
   }

   public String getTitle()
   {
      return title;
   }

   public void setTitle(String title)
   {
      this.title = title;
   }

   public String getBrief()
   {
      return brief;
   }

   public void setBrief(String brief)
   {
      this.brief = brief;
   }

   public Set<Speaker> getSpeakers()
   {
      return this.speakers;
   }

   public void setSpeakers(final Set<Speaker> speakers)
   {
      this.speakers = speakers;
   }

   public String getDifficulty()
   {
      return difficulty;
   }

   public void setDifficulty(String difficulty)
   {
      this.difficulty = difficulty;
   }

   public Room getRoom()
   {
      return room;
   }

   public void setRoom(Room room)
   {
      this.room = room;
   }

   public Track getSessiontrack()
   {
      return sessiontrack;
   }

   public void setSessiontrack(Track sessiontrack)
   {
      this.sessiontrack = sessiontrack;
   }

   public Subtrack getSessionsubtrack()
   {
      return sessionsubtrack;
   }

   public void setSessionsubtrack(Subtrack sessionsubtrack)
   {
      this.sessionsubtrack = sessionsubtrack;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      result += "sessionday: " + sessionday;
      if (title != null && !title.trim().isEmpty())
         result += ", title: " + title;
      if (brief != null && !brief.trim().isEmpty())
         result += ", brief: " + brief;
      if (difficulty != null && !difficulty.trim().isEmpty())
         result += ", difficulty: " + difficulty;
      return result;
   }

   public Set<Tag> getTags()
   {
      return this.tags;
   }

   public void setTags(final Set<Tag> tags)
   {
      this.tags = tags;
   }
}