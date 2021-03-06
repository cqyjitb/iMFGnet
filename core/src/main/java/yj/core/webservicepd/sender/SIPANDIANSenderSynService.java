package yj.core.webservicepd.sender;

/**
 * Created by 917110140 on 2018/7/25.
 */
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.1.3-hudson-390-
 * Generated source version: 2.0
 * <p>
 * An example of how this class may be used:
 *
 * <pre>
 * SI_PANDIAN_Sender_SynService service = new SI_PANDIAN_Sender_SynService();
 * SIPANDIANSenderSyn portType = service.getHTTPPort();
 * portType.siPANDIANSenderSyn(...);
 * </pre>
 *
 * </p>
 *
 */
@WebServiceClient(name = "SI_PANDIAN_Sender_SynService", targetNamespace = "http://www.cq-yj.com/HAP/PANDIAN/Sender", wsdlLocation = "resources/pandian.xml")
public class SIPANDIANSenderSynService extends Service {

    public final static URL WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(SIPANDIANSenderSynService.class.getName());

    static {
        URL url = null;
        url = SIPANDIANSenderSynService.class.getClassLoader().getResource("pandian.xml");
        System.out.println(url);
        WSDL_LOCATION = url;
    }

    public SIPANDIANSenderSynService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SIPANDIANSenderSynService() {
        super(WSDL_LOCATION, new QName(
                "http://www.cq-yj.com/HAP/PANDIAN/Sender",
                "SI_PANDIAN_Sender_SynService"));
    }

    /**
     *
     * @return returns SIPANDIANSenderSyn
     */
    @WebEndpoint(name = "HTTP_Port")
    public SIPANDIANSenderSyn getHTTPPort() {
        return super.getPort(new QName(
                        "http://www.cq-yj.com/HAP/PANDIAN/Sender", "HTTP_Port"),
                SIPANDIANSenderSyn.class);
    }

    /**
     *
     * @return returns SIPANDIANSenderSyn
     */
    @WebEndpoint(name = "HTTPS_Port")
    public SIPANDIANSenderSyn getHTTPSPort() {
        return super.getPort(new QName(
                        "http://www.cq-yj.com/HAP/PANDIAN/Sender", "HTTPS_Port"),
                SIPANDIANSenderSyn.class);
    }

}

