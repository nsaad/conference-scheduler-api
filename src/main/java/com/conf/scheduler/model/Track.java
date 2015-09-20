package com.conf.scheduler.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Track implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = "version")
   private int version;

   @Column
   private String trackname;

   @ManyToMany
   private Set<Subtrack> subtracks = new HashSet<Subtrack>();

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
      if (!(obj instanceof Track))
      {
         return false;
      }
      Track other = (Track) obj;
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

   public String getTrackname()
   {
      return trackname;
   }

   public void setTrackname(String trackname)
   {
      this.trackname = trackname;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (trackname != null && !trackname.trim().isEmpty())
         result += "trackname: " + trackname;
      return result;
   }

   public Set<Subtrack> getSubtracks()
   {
      return this.subtracks;
   }

   public void setSubtracks(final Set<Subtrack> subtracks)
   {
      this.subtracks = subtracks;
   }
}