package com.conf.scheduler.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.lang.Override;
import com.conf.scheduler.model.Event;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class User implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = "version")
   private int version;

   @Column
   private String fullname;

   @Column
   private String username;

   @Column
   private String email;

   @ManyToMany
   private Set<Event> events = new HashSet<Event>();

   @Column
   private boolean admin;

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
      if (!(obj instanceof User))
      {
         return false;
      }
      User other = (User) obj;
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

   public String getFullname()
   {
      return fullname;
   }

   public void setFullname(String fullname)
   {
      this.fullname = fullname;
   }

   public String getUsername()
   {
      return username;
   }

   public void setUsername(String username)
   {
      this.username = username;
   }

   public String getEmail()
   {
      return email;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }

   public Set<Event> getEvents()
   {
      return this.events;
   }

   public void setEvents(final Set<Event> events)
   {
      this.events = events;
   }

   public boolean isAdmin()
   {
      return admin;
   }

   public void setAdmin(boolean admin)
   {
      this.admin = admin;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (fullname != null && !fullname.trim().isEmpty())
         result += "fullname: " + fullname;
      if (username != null && !username.trim().isEmpty())
         result += ", username: " + username;
      if (email != null && !email.trim().isEmpty())
         result += ", email: " + email;
      result += ", admin: " + admin;
      return result;
   }
}