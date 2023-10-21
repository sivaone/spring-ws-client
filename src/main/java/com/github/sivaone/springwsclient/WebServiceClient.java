package com.github.sivaone.springwsclient;

import lombok.extern.slf4j.Slf4j;
import org.oorsprong.websamples.CapitalCity;
import org.oorsprong.websamples.CapitalCityResponse;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

@Slf4j
public class WebServiceClient extends WebServiceGatewaySupport {

    public void sendAndReceive() {
        WebServiceTemplate wsTemplate = getWebServiceTemplate();
        //wsTemplate.setDefaultUri("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso");

        CapitalCity capitalCity = new CapitalCity();
        capitalCity.setSCountryISOCode("GB");

        CapitalCityResponse response = (CapitalCityResponse) wsTemplate.marshalSendAndReceive(capitalCity);
        log.info("Capital of {} is {}", capitalCity.getSCountryISOCode(), response.getCapitalCityResult());
    }
}
