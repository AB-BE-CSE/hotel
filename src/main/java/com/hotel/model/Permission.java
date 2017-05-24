package main.java.com.hotel.model;
// Generated 21 mai 2017 11:28:46 by Hibernate Tools 5.2.0.CR1

import javax.persistence.*;

/** @author Nadir Belarouci*/

@Entity
@Table(name = "permission", catalog = "hotel")
public class Permission implements java.io.Serializable {
    public static final int READ = 0;
    public static final int UPDATE = 1;
    public static final int CREATE = 2;
    public static final int DELETE = 3;
    private PermissionId id;
    private Usertype usertype;
    private Boolean create;
    private Boolean read;
    private Boolean updat;
    private Boolean delet;

    public Permission() {
    }

    public Permission(PermissionId id, Usertype usertype) {
        this.id = id;
        this.usertype = usertype;
    }

    public Permission(PermissionId id, Usertype usertype, Boolean create, Boolean read, Boolean updat, Boolean delet) {
        this.id = id;
        this.usertype = usertype;
        this.create = create;
        this.read = read;
        this.updat = updat;
        this.delet = delet;
    }

    @EmbeddedId

    @AttributeOverrides({
            @AttributeOverride(name = "type", column = @Column(name = "type", nullable = false, length = 2)),
            @AttributeOverride(name = "information", column = @Column(name = "information", nullable = false, length = 20))})
    public PermissionId getId() {
        return this.id;
    }

    public void setId(PermissionId id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type", nullable = false, insertable = false, updatable = false)
    public Usertype getUsertype() {
        return this.usertype;
    }

    public void setUsertype(Usertype usertype) {
        this.usertype = usertype;
    }

    @Column(name = "create")
    public Boolean getCreate() {
        return this.create;
    }

    public void setCreate(Boolean create) {
        this.create = create;
    }

    @Column(name = "read")
    public Boolean getRead() {
        return this.read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    @Column(name = "updat")
    public Boolean getUpdat() {
        return this.updat;
    }

    public void setUpdat(Boolean updat) {
        this.updat = updat;
    }

    @Column(name = "delet")
    public Boolean getDelet() {
        return this.delet;
    }

    public void setDelet(Boolean delet) {
        this.delet = delet;
    }

    @Override
    public String toString() {
        return id.getType() + ": " + id.getInformation();
    }

    public boolean hasRight(int permission) {
        switch (permission) {
            case READ:
                return getRead();
            case CREATE:
                return getCreate();
            case UPDATE:
                return getUpdat();
            case DELETE:
                return getDelet();
        }
        return false;
    }
}