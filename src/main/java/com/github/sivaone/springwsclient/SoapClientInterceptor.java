package com.github.sivaone.springwsclient;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.SoapBody;
import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.SoapMessage;

@Slf4j
public class SoapClientInterceptor implements ClientInterceptor {

    // Need to use Filter in logback xml config to make use of Markers
    private final Marker imp = MarkerFactory.getMarker("IMP");
    @Override
    public boolean handleRequest(MessageContext messageContext) throws WebServiceClientException {
        log.info(imp, "Handling request");
        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext) throws WebServiceClientException {
        log.info(imp, "Handling response");
        return true;
    }

    @Override
    public boolean handleFault(MessageContext messageContext) throws WebServiceClientException {
        log.error("Handling fault");

        SoapMessage soapMessage = (SoapMessage) messageContext.getResponse();
        SoapBody soapBody = soapMessage.getEnvelope().getBody();
        SoapFault soapFault = soapBody.getFault();
        String faultStringOrReason = soapFault.getFaultStringOrReason();

        log.error("Exception during soap call. Reason {}", faultStringOrReason);
        //throw new RuntimeException(String.format("Exception during soap call. Reason %s", faultStringOrReason));

        return false;
    }

    @Override
    public void afterCompletion(MessageContext messageContext, Exception ex) throws WebServiceClientException {
        log.info("Request completed");
    }
}
