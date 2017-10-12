
package services.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "findAllResponse", namespace = "http://services/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findAllResponse", namespace = "http://services/")
public class FindAllResponse {

    @XmlElement(name = "return", namespace = "")
    private List<entities.Users> _return;

    /**
     * 
     * @return
     *     returns List<Users>
     */
    public List<entities.Users> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<entities.Users> _return) {
        this._return = _return;
    }

}
