
package services.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "find", namespace = "http://services/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "find", namespace = "http://services/")
public class Find {

    @XmlElement(name = "id", namespace = "")
    private Object id;

    /**
     * 
     * @return
     *     returns Object
     */
    public Object getId() {
        return this.id;
    }

    /**
     * 
     * @param id
     *     the value for the id property
     */
    public void setId(Object id) {
        this.id = id;
    }

}
