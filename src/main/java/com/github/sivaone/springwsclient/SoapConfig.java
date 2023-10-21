package com.github.sivaone.springwsclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;

@Configuration
public class SoapConfig {

    public static final String SERVICE_URL = "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso";

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("org.oorsprong.websamples");
        return marshaller;
    }

    @Bean
    public WebServiceClient webServiceClient(Jaxb2Marshaller marshaller) {
        WebServiceClient client = new WebServiceClient();
        client.setDefaultUri(SERVICE_URL);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        client.setInterceptors(new ClientInterceptor[]{clientInterceptor()});
        return client;
    }

    @Bean
    public SoapClientInterceptor clientInterceptor() {
        return new SoapClientInterceptor();
    }
}
