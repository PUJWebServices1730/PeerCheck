
package services.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "findResponse", namespace = "http://services/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findResponse", namespace = "http://services/")
public class FindResponse {

    @XmlElement(name = "return", namespace = "")
    private entities.Users _return;

    /**
     * 
     * @return
     *     returns Users
     */
    public entities.Users getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(entities.Users _return) {
        this._return = _return;
    }

}
