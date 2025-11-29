package com.techfirst.backend.hexagonal.adapter.in.soap;

import com.techfirst.backend.hexagonal.core.domain.Customer;
import com.techfirst.backend.hexagonal.core.port.in.CustomerUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.webservices.server.WebServiceServerTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.context.ApplicationContext;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;

import javax.xml.transform.Source;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import static org.mockito.BDDMockito.given;
import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.xpath;
import org.springframework.ws.test.server.ResponseMatcher; // Import ResponseMatcher

@WebServiceServerTest(CustomerSoapEndpoint.class)
class CustomerSoapEndpointTest {

    @Autowired
    private ApplicationContext applicationContext;

    @MockitoBean
    private CustomerUseCase customerUseCase;

    private MockWebServiceClient mockClient;

    @BeforeEach
    void setUp() {
        mockClient = MockWebServiceClient.createClient(applicationContext);
    }

    @Test
    void getCustomer_ShouldReturnCustomerXml() throws Exception {
        // given
        long customerId = 1L;
        var customer = new Customer(customerId, "Tech First", "tech@first.com");

        given(customerUseCase.getCustomer(customerId)).willReturn(customer);

        Source requestPayload = new StringSource(
                "<ns2:getCustomerRequest xmlns:ns2=\"http://techfirst.com/backend/hexagonal/soap\">" +
                        "<ns2:id>" + customerId + "</ns2:id>" +
                        "</ns2:getCustomerRequest>"
        );

        // when & then
        mockClient.sendRequest(withPayload(requestPayload))
                .andExpect(xpath("/ns2:getCustomerResponse/ns2:id",
                        java.util.Collections.singletonMap("ns2", "http://techfirst.com/backend/hexagonal/soap"))
                        .evaluatesTo("1"))
                .andExpect(xpath("/ns2:getCustomerResponse/ns2:name",
                        java.util.Collections.singletonMap("ns2", "http://techfirst.com/backend/hexagonal/soap"))
                        .evaluatesTo("Tech First"))
                .andExpect(xpath("/ns2:getCustomerResponse/ns2:email",
                        java.util.Collections.singletonMap("ns2", "http://techfirst.com/backend/hexagonal/soap"))
                        .evaluatesTo("tech@first.com"))
                .andExpect(printSoapMessages());
    }

    // Static helper method for printing SOAP messages
    private static ResponseMatcher printSoapMessages() {
        return (request, response) -> {
            ByteArrayOutputStream requestOutput = new ByteArrayOutputStream();
            request.writeTo(requestOutput);
            String rawRequestXml = requestOutput.toString(StandardCharsets.UTF_8);

            ByteArrayOutputStream responseOutput = new ByteArrayOutputStream();
            response.writeTo(responseOutput);
            String rawResponseXml = responseOutput.toString(StandardCharsets.UTF_8);

            System.out.println("--- SOAP Request (Pretty Printed) ---");
            System.out.println(prettyPrintXml(rawRequestXml));
            System.out.println("--- End SOAP Request ---");

            System.out.println("--- SOAP Response (Pretty Printed) ---");
            System.out.println(prettyPrintXml(rawResponseXml));
            System.out.println("--- End SOAP Response ---");
        };
    }

    // Static helper method for pretty printing XML
    private static String prettyPrintXml(String xmlString) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true); // 보안 강화
            DocumentBuilder builder = factory.newDocumentBuilder();
            org.w3c.dom.Document document = builder.parse(new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8)));

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", 2);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes"); // XML 선언 생략

            StringWriter stringWriter = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(stringWriter));
            return stringWriter.toString();
        } catch (Exception e) {
            // pretty-print 실패 시 원본 XML 반환
            System.err.println("Failed to pretty-print XML: " + e.getMessage());
            return xmlString;
        }
    }
}
