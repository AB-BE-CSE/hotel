package main.java.com.hotel.model;
// Generated 21 mai 2017 11:28:46 by Hibernate Tools 5.2.0.CR1

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/** @author Zakaria Choukchou*/

@Entity
@Table(name = "usertype", catalog = "hotel")
public class Usertype implements java.io.Serializable {

    private String idType;
    private String description;
    private Set<Permission> permissions = new HashSet(0);

    public Usertype() {
    }

    public Usertype(String idType) {
        this.idType = idType;
    }

    public Usertype(String idType, String description, Set permissions) {
        this.idType = idType;
        this.description = description;
        this.permissions = permissions;
    }

    @Id

    @Column(name = "idType", unique = true, nullable = false, length = 2)
    public String getIdType() {
        return this.idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    @Column(name = "description", length = 15)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "usertype")
    public Set<Permission> getPermissions() {
        return this.permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

}
