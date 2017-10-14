
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

    private final static QName _ConvertToReviewer_QNAME = new QName("http://services/", "convertToReviewer");
    private final static QName _ConvertToReviewerResponse_QNAME = new QName("http://services/", "convertToReviewerResponse");
    private final static QName _Count_QNAME = new QName("http://services/", "count");
    private final static QName _CountResponse_QNAME = new QName("http://services/", "countResponse");
    private final static QName _Create_QNAME = new QName("http://services/", "create");
    private final static QName _Edit_QNAME = new QName("http://services/", "edit");
    private final static QName _Find_QNAME = new QName("http://services/", "find");
    private final static QName _FindAll_QNAME = new QName("http://services/", "findAll");
    private final static QName _FindAllResponse_QNAME = new QName("http://services/", "findAllResponse");
    private final static QName _FindRange_QNAME = new QName("http://services/", "findRange");
    private final static QName _FindRangeResponse_QNAME = new QName("http://services/", "findRangeResponse");
    private final static QName _FindResponse_QNAME = new QName("http://services/", "findResponse");
    private final static QName _FindUserByEmail_QNAME = new QName("http://services/", "findUserByEmail");
    private final static QName _FindUserByEmailResponse_QNAME = new QName("http://services/", "findUserByEmailResponse");
    private final static QName _FindUsersByEmails_QNAME = new QName("http://services/", "findUsersByEmails");
    private final static QName _FindUsersByEmailsResponse_QNAME = new QName("http://services/", "findUsersByEmailsResponse");
    private final static QName _Remove_QNAME = new QName("http://services/", "remove");
    private final static QName _Users_QNAME = new QName("http://services/", "users");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: integration.users
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConvertToReviewer }
     * 
     */
    public ConvertToReviewer createConvertToReviewer() {
        return new ConvertToReviewer();
    }

    /**
     * Create an instance of {@link ConvertToReviewerResponse }
     * 
     */
    public ConvertToReviewerResponse createConvertToReviewerResponse() {
        return new ConvertToReviewerResponse();
    }

    /**
     * Create an instance of {@link Count }
     * 
     */
    public Count createCount() {
        return new Count();
    }

    /**
     * Create an instance of {@link CountResponse }
     * 
     */
    public CountResponse createCountResponse() {
        return new CountResponse();
    }

    /**
     * Create an instance of {@link Create }
     * 
     */
    public Create createCreate() {
        return new Create();
    }

    /**
     * Create an instance of {@link Edit }
     * 
     */
    public Edit createEdit() {
        return new Edit();
    }

    /**
     * Create an instance of {@link Find }
     * 
     */
    public Find createFind() {
        return new Find();
    }

    /**
     * Create an instance of {@link FindAll }
     * 
     */
    public FindAll createFindAll() {
        return new FindAll();
    }

    /**
     * Create an instance of {@link FindAllResponse }
     * 
     */
    public FindAllResponse createFindAllResponse() {
        return new FindAllResponse();
    }

    /**
     * Create an instance of {@link FindRange }
     * 
     */
    public FindRange createFindRange() {
        return new FindRange();
    }

    /**
     * Create an instance of {@link FindRangeResponse }
     * 
     */
    public FindRangeResponse createFindRangeResponse() {
        return new FindRangeResponse();
    }

    /**
     * Create an instance of {@link FindResponse }
     * 
     */
    public FindResponse createFindResponse() {
        return new FindResponse();
    }

    /**
     * Create an instance of {@link FindUserByEmail }
     * 
     */
    public FindUserByEmail createFindUserByEmail() {
        return new FindUserByEmail();
    }

    /**
     * Create an instance of {@link FindUserByEmailResponse }
     * 
     */
    public FindUserByEmailResponse createFindUserByEmailResponse() {
        return new FindUserByEmailResponse();
    }

    /**
     * Create an instance of {@link FindUsersByEmails }
     * 
     */
    public FindUsersByEmails createFindUsersByEmails() {
        return new FindUsersByEmails();
    }

    /**
     * Create an instance of {@link FindUsersByEmailsResponse }
     * 
     */
    public FindUsersByEmailsResponse createFindUsersByEmailsResponse() {
        return new FindUsersByEmailsResponse();
    }

    /**
     * Create an instance of {@link Remove }
     * 
     */
    public Remove createRemove() {
        return new Remove();
    }

    /**
     * Create an instance of {@link Users }
     * 
     */
    public Users createUsers() {
        return new Users();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConvertToReviewer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services/", name = "convertToReviewer")
    public JAXBElement<ConvertToReviewer> createConvertToReviewer(ConvertToReviewer value) {
        return new JAXBElement<ConvertToReviewer>(_ConvertToReviewer_QNAME, ConvertToReviewer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConvertToReviewerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services/", name = "convertToReviewerResponse")
    public JAXBElement<ConvertToReviewerResponse> createConvertToReviewerResponse(ConvertToReviewerResponse value) {
        return new JAXBElement<ConvertToReviewerResponse>(_ConvertToReviewerResponse_QNAME, ConvertToReviewerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Count }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services/", name = "count")
    public JAXBElement<Count> createCount(Count value) {
        return new JAXBElement<Count>(_Count_QNAME, Count.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CountResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services/", name = "countResponse")
    public JAXBElement<CountResponse> createCountResponse(CountResponse value) {
        return new JAXBElement<CountResponse>(_CountResponse_QNAME, CountResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Edit }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services/", name = "edit")
    public JAXBElement<Edit> createEdit(Edit value) {
        return new JAXBElement<Edit>(_Edit_QNAME, Edit.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAll }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services/", name = "findAll")
    public JAXBElement<FindAll> createFindAll(FindAll value) {
        return new JAXBElement<FindAll>(_FindAll_QNAME, FindAll.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link FindRange }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services/", name = "findRange")
    public JAXBElement<FindRange> createFindRange(FindRange value) {
        return new JAXBElement<FindRange>(_FindRange_QNAME, FindRange.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindRangeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services/", name = "findRangeResponse")
    public JAXBElement<FindRangeResponse> createFindRangeResponse(FindRangeResponse value) {
        return new JAXBElement<FindRangeResponse>(_FindRangeResponse_QNAME, FindRangeResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link FindUserByEmail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services/", name = "findUserByEmail")
    public JAXBElement<FindUserByEmail> createFindUserByEmail(FindUserByEmail value) {
        return new JAXBElement<FindUserByEmail>(_FindUserByEmail_QNAME, FindUserByEmail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindUserByEmailResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services/", name = "findUserByEmailResponse")
    public JAXBElement<FindUserByEmailResponse> createFindUserByEmailResponse(FindUserByEmailResponse value) {
        return new JAXBElement<FindUserByEmailResponse>(_FindUserByEmailResponse_QNAME, FindUserByEmailResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindUsersByEmails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services/", name = "findUsersByEmails")
    public JAXBElement<FindUsersByEmails> createFindUsersByEmails(FindUsersByEmails value) {
        return new JAXBElement<FindUsersByEmails>(_FindUsersByEmails_QNAME, FindUsersByEmails.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindUsersByEmailsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services/", name = "findUsersByEmailsResponse")
    public JAXBElement<FindUsersByEmailsResponse> createFindUsersByEmailsResponse(FindUsersByEmailsResponse value) {
        return new JAXBElement<FindUsersByEmailsResponse>(_FindUsersByEmailsResponse_QNAME, FindUsersByEmailsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Remove }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services/", name = "remove")
    public JAXBElement<Remove> createRemove(Remove value) {
        return new JAXBElement<Remove>(_Remove_QNAME, Remove.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Users }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services/", name = "users")
    public JAXBElement<Users> createUsers(Users value) {
        return new JAXBElement<Users>(_Users_QNAME, Users.class, null, value);
    }

}
