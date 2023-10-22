package com.github.sivaone.springwsclient;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.ws.test.client.MockWebServiceServer;
import org.springframework.ws.test.client.RequestMatchers;
import org.springframework.ws.test.client.ResponseCreators;
import org.springframework.xml.transform.StringSource;


@SpringJUnitConfig(SoapConfig.class)

//  Below two annotations or one @SpringJUnitConfig
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = SoapConfig.class)

// Below or @ContextConfiguration
//@Import(SoapConfig.class)
@TestPropertySource(properties = {
        "app.soap-url=http://localhost",
})
@ActiveProfiles("test")
class WebServiceClientTest {

    @Autowired
    WebServiceClient webServiceClient;

    MockWebServiceServer mockServer;

    // Very useful when getting dynamic values from static test containers
    // Below are @TestPropertySource
   /* @DynamicPropertySource
    static void mockPortProperty(DynamicPropertyRegistry registry) {
        registry.add("app.soap-url", () -> "http://localhost");
    }*/

   /* @BeforeAll
    static void setup() {
        System.setProperty("app.soap-url", "http://localhost");
    }*/

    @BeforeEach
    void setupServer() {
        mockServer = MockWebServiceServer.createServer(webServiceClient);
    }

    @Test
    void getCapitalCity() {

        String request = """
                <CapitalCity xmlns="http://www.oorsprong.org/websamples.countryinfo">
                  <sCountryISOCode>XY</sCountryISOCode>
                </CapitalCity>
                """;

        String response = """
                    <CapitalCityResponse xmlns="http://www.oorsprong.org/websamples.countryinfo">
                      <CapitalCityResult>TestCity</CapitalCityResult>
                    </CapitalCityResponse>
                """;

        mockServer.expect(RequestMatchers.payload(new StringSource(request)))
                .andRespond(ResponseCreators.withPayload(new StringSource(response)));

        String capitalCity = webServiceClient.getCapitalCity("XY");
        Assertions.assertThat(capitalCity).isEqualTo("TestCity");

        mockServer.verify();
    }

    @Test
    void getCountryCurrency() {
        String request = """
                <CountryCurrency xmlns="http://www.oorsprong.org/websamples.countryinfo">
                    <sCountryISOCode>YZ</sCountryISOCode>
                </CountryCurrency>
                """;

        String response = """
                <CountryCurrencyResponse xmlns="http://www.oorsprong.org/websamples.countryinfo">
                    <CountryCurrencyResult>
                      <sISOCode>YZ</sISOCode>
                      <sName>Wupiupi</sName>
                    </CountryCurrencyResult>
                </CountryCurrencyResponse>
                """;

        mockServer.expect(RequestMatchers.payload(new StringSource(request)))
                .andRespond(ResponseCreators.withPayload(new StringSource(response)));

        String currency = webServiceClient.getCountryCurrency("YZ");
        Assertions.assertThat(currency).isEqualTo("Wupiupi");

        mockServer.verify();
    }
}