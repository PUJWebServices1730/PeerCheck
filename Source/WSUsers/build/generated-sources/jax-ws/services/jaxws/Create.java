
package services.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "create", namespace = "http://services/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "create", namespace = "http://services/")
public class Create {

    @XmlElement(name = "entity", namespace = "")
    private entities.Users entity;

    /**
     * 
     * @return
     *     returns Users
     */
    public entities.Users getEntity() {
        return this.entity;
    }

    /**
     * 
     * @param entity
     *     the value for the entity property
     */
    public void setEntity(entities.Users entity) {
        this.entity = entity;
    }

}
