package main.java.com.hotel.model;
// Generated 21 mai 2017 11:28:46 by Hibernate Tools 5.2.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/** @author Nadir Belarouci*/

@Embeddable
public class PermissionId implements java.io.Serializable {

    private String type;
    private String information;

    public PermissionId() {
    }

    public PermissionId(String type, String information) {
        this.type = type;
        this.information = information;
    }

    @Column(name = "type", nullable = false, length = 2)
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "information", nullable = false, length = 20)
    public String getInformation() {
        return this.information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof PermissionId))
            return false;
        PermissionId castOther = (PermissionId) other;

        return ((this.getType() == castOther.getType()) || (this.getType() != null && castOther.getType() != null
                && this.getType().equals(castOther.getType())))
                && ((this.getInformation() == castOther.getInformation())
                || (this.getInformation() != null && castOther.getInformation() != null
                && this.getInformation().equals(castOther.getInformation())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + (getType() == null ? 0 : this.getType().hashCode());
        result = 37 * result + (getInformation() == null ? 0 : this.getInformation().hashCode());
        return result;
    }

}
