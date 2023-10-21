package com.github.sivaone.springwsclient;

import lombok.extern.slf4j.Slf4j;
import org.oorsprong.websamples.CapitalCity;
import org.oorsprong.websamples.CapitalCityResponse;
import org.slf4j.profiler.Profiler;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

@Slf4j
public class WebServiceClient extends WebServiceGatewaySupport {

    public String sendAndReceive(final String countryISOCode) {

        final Profiler profiler = new Profiler("SOAP_CALL");
        profiler.start("Calling capital city api");

        WebServiceTemplate wsTemplate = getWebServiceTemplate();
        //wsTemplate.setDefaultUri("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso");

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
}
