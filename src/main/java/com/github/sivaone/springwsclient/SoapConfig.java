package com.github.sivaone.springwsclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;

@Configuration
public class SoapConfig {

    private final String soapUrl;

    public SoapConfig(@Value("${app.soap-url}") String soapUrl) {
        this.soapUrl = soapUrl;
    }

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("org.oorsprong.websamples");
        return marshaller;
    }

    @Bean
    public WebServiceClient webServiceClient(Jaxb2Marshaller marshaller) {
        WebServiceClient client = new WebServiceClient();
        client.setDefaultUri(this.soapUrl);
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
