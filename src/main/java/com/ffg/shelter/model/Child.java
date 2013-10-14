package com.ffg.shelter.model;

import javax.persistence.*;


@Entity
public class Child {

    private Long id;
    private String firstName;
    private String lastName;
    private String dob;
    private Gender gender;
    // private Set<Client> client;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /*
   @ManyToMany
   @JoinColumn(name = "id", updatable = false, insertable = false, nullable = false)
   @org.hibernate.annotations.IndexColumn(name = "id_index")
   public Set<Client> getClient() {
       return client;
   }

   public void setClient(Set<Client> client) {
       this.client = client;
   }
    */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Child that = (Child) o;

        if (dob != null ? !dob.equals(that.dob) : that.dob != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (dob != null ? dob.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        return result;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    @Enumerated(EnumType.STRING)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
