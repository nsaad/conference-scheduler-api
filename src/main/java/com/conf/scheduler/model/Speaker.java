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
public class Speaker implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = "version")
   private int version;

   @Column
   private String firstname;

   @Column
   private String surname;

   @Column(length = 2000)
   private String bio;

   @Column
   private String twitter;

   @Column
   private String company;

   @ManyToMany
   private Set<Presentation> presentations = new HashSet<Presentation>();

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
      if (!(obj instanceof Speaker))
      {
         return false;
      }
      Speaker other = (Speaker) obj;
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

   public String getFirstname()
   {
      return firstname;
   }

   public void setFirstname(String firstname)
   {
      this.firstname = firstname;
   }

   public String getSurname()
   {
      return surname;
   }

   public void setSurname(String surname)
   {
      this.surname = surname;
   }

   public String getBio()
   {
      return bio;
   }

   public void setBio(String bio)
   {
      this.bio = bio;
   }

   public String getTwitter()
   {
      return twitter;
   }

   public void setTwitter(String twitter)
   {
      this.twitter = twitter;
   }

   public String getCompany()
   {
      return company;
   }

   public void setCompany(String company)
   {
      this.company = company;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (firstname != null && !firstname.trim().isEmpty())
         result += "firstname: " + firstname;
      if (surname != null && !surname.trim().isEmpty())
         result += ", surname: " + surname;
      if (bio != null && !bio.trim().isEmpty())
         result += ", bio: " + bio;
      if (twitter != null && !twitter.trim().isEmpty())
         result += ", twitter: " + twitter;
      if (company != null && !company.trim().isEmpty())
         result += ", company: " + company;
      return result;
   }

   public Set<Presentation> getPresentations()
   {
      return this.presentations;
   }

   public void setPresentations(final Set<Presentation> presentations)
   {
      this.presentations = presentations;
   }
}