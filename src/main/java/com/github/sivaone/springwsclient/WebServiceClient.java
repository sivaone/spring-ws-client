package com.github.sivaone.springwsclient;

import lombok.extern.slf4j.Slf4j;
import org.oorsprong.websamples.CapitalCity;
import org.oorsprong.websamples.CapitalCityResponse;
import org.oorsprong.websamples.CountryCurrency;
import org.oorsprong.websamples.CountryCurrencyResponse;
import org.slf4j.profiler.Profiler;
import org.springframework.ws.FaultAwareWebServiceMessage;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceMessageExtractor;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.soap.client.SoapFaultClientException;
import org.springframework.ws.support.MarshallingUtils;

import javax.xml.transform.TransformerException;
import java.io.IOException;

/** This class is used to do a soap call */

@Slf4j
public class WebServiceClient extends WebServiceGatewaySupport {

    // Method1: Using direct call to soap service
    public String getCapitalCity(final String countryISOCode) {

        final Profiler profiler = new Profiler("SOAP_CALL");
        profiler.start("Calling capital city api");

        WebServiceTemplate wsTemplate = getWebServiceTemplate();

        final CapitalCity capitalCity = new CapitalCity();
        capitalCity.setSCountryISOCode(countryISOCode);

        final CapitalCityResponse response = (CapitalCityResponse) wsTemplate.marshalSendAndReceive(capitalCity);

        profiler.start("Printing response");
        log.info("Capital of {} is {}", countryISOCode, response.getCapitalCityResult());
        profiler.stop().print();

        if (response.getCapitalCityResult().contains("not found")) {
            throw new RuntimeException(response.getCapitalCityResult());
        }

        return response.getCapitalCityResult();
    }


    // Method2: call soap service with spring ws callbacks
    public String getCountryCurrency(final String countryISOCode) {
        WebServiceTemplate wsTemplate = getWebServiceTemplate();
        final CountryCurrency countryCurrency = new CountryCurrency();
        countryCurrency.setSCountryISOCode(countryISOCode);

        CountryCurrencyResponse countryCurrencyResponse = wsTemplate.sendAndReceive(
                new WebServiceMessageCallback() {
                    @Override
                    public void doWithMessage(WebServiceMessage reqMessage) throws IOException, TransformerException {
                        MarshallingUtils.marshal(wsTemplate.getMarshaller(), countryCurrency, reqMessage);
                    }
                },
                new WebServiceMessageExtractor<CountryCurrencyResponse>() {
                    @Override
                    public CountryCurrencyResponse extractData(WebServiceMessage respMessage) throws IOException, TransformerException {
                        if (respMessage instanceof FaultAwareWebServiceMessage fault && fault.hasFault()) {
                            throw new SoapFaultClientException((SoapMessage) respMessage);
                        } else {
                            return (CountryCurrencyResponse) MarshallingUtils.unmarshal(wsTemplate.getUnmarshaller(), respMessage);
                        }
                    }
                });

        return countryCurrencyResponse.getCountryCurrencyResult().getSName();
    }
}
