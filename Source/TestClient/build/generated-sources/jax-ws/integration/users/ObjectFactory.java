
package integration.users;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the integration.users package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _FindResponse_QNAME = new QName("http://services/", "findResponse");
    private final static QName _FindAllResponse_QNAME = new QName("http://services/", "findAllResponse");
    private final static QName _FindByUsernameResponse_QNAME = new QName("http://services/", "findByUsernameResponse");
    private final static QName _Create_QNAME = new QName("http://services/", "create");
    private final static QName _Users_QNAME = new QName("http://services/", "users");
    private final static QName _FindAll_QNAME = new QName("http://services/", "findAll");
    private final static QName _Find_QNAME = new QName("http://services/", "find");
    private final static QName _FindByUsername_QNAME = new QName("http://services/", "findByUsername");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: integration.users
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FindByUsername }
     * 
     */
    public FindByUsername createFindByUsername() {
        return new FindByUsername();
    }

    /**
     * Create an instance of {@link Find }
     * 
     */
    public Find createFind() {
        return new Find();
    }

    /**
     * Create an instance of {@link FindByUsernameResponse }
     * 
     */
    public FindByUsernameResponse createFindByUsernameResponse() {
        return new FindByUsernameResponse();
    }

    /**
     * Create an instance of {@link Create }
     * 
     */
    public Create createCreate() {
        return new Create();
    }

    /**
     * Create an instance of {@link FindResponse }
     * 
     */
    public FindResponse createFindResponse() {
        return new FindResponse();
    }

    /**
     * Create an instance of {@link FindAllResponse }
     * 
     */
    public FindAllResponse createFindAllResponse() {
        return new FindAllResponse();
    }

    /**
     * Create an instance of {@link FindAll }
     * 
     */
    public FindAll createFindAll() {
        return new FindAll();
    }

    /**
     * Create an instance of {@link Users }
     * 
     */
    public Users createUsers() {
        return new Users();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services/", name = "findResponse")
    public JAXBElement<FindResponse> createFindResponse(FindResponse value) {
        return new JAXBElement<FindResponse>(_FindResponse_QNAME, FindResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services/", name = "findAllResponse")
    public JAXBElement<FindAllResponse> createFindAllResponse(FindAllResponse value) {
        return new JAXBElement<FindAllResponse>(_FindAllResponse_QNAME, FindAllResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindByUsernameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services/", name = "findByUsernameResponse")
    public JAXBElement<FindByUsernameResponse> createFindByUsernameResponse(FindByUsernameResponse value) {
        return new JAXBElement<FindByUsernameResponse>(_FindByUsernameResponse_QNAME, FindByUsernameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Create }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services/", name = "create")
    public JAXBElement<Create> createCreate(Create value) {
        return new JAXBElement<Create>(_Create_QNAME, Create.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Users }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services/", name = "users")
    public JAXBElement<Users> createUsers(Users value) {
        return new JAXBElement<Users>(_Users_QNAME, Users.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAll }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services/", name = "findAll")
    public JAXBElement<FindAll> createFindAll(FindAll value) {
        return new JAXBElement<FindAll>(_FindAll_QNAME, FindAll.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Find }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services/", name = "find")
    public JAXBElement<Find> createFind(Find value) {
        return new JAXBElement<Find>(_Find_QNAME, Find.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindByUsername }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services/", name = "findByUsername")
    public JAXBElement<FindByUsername> createFindByUsername(FindByUsername value) {
        return new JAXBElement<FindByUsername>(_FindByUsername_QNAME, FindByUsername.class, null, value);
    }

}
