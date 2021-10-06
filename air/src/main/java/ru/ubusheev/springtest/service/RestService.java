package ru.ubusheev.springtest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.ubusheev.springtest.model.Quote;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.logging.Logger;


@Component
public class RestService {

    public static final String HOST = "https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browsequotes/v1.0/RU/RUB/ru-RU";

    public static final String PLACES_FORMAT = "/apiservices/autosuggest/v1.0/%s/%s/%s/?query=%s";
    public static final String CURRENCIES_FORMAT = "/apiservices/reference/v1.0/currencies";
    public static final String COUNTRIES_FORMAT = "/apiservices/reference/v1.0/countries/%s";

    public static final String PLACES_KEY = "Places";
    public static final String CURRENCIES_KEY = "Currencies";
    public static final String COUNTRIES_KEY = "Countries";

    @Value("${x.rapid.api.key}")
    private String xRapidApiKey;

    @PostConstruct
    private void postConstruct() {
        Unirest.setObjectMapper(new com.mashape.unirest.http.ObjectMapper() {
            com.fasterxml.jackson.databind.ObjectMapper mapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public String writeValue(Object value) {
                try {
                    return mapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    return null;
                }

            }

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return mapper.readValue(value, valueType);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });
        Unirest.setDefaultHeader("Accept", "application/json");
    }
    public HttpResponse<Quote> get(String departure, String destination, String outbounddate, String inbounddate) throws UnirestException {

        HttpResponse<Quote> response = null;
        response = Unirest.get(HOST + "/" + departure + "/" + destination + "/" + outbounddate + (inbounddate.equals("0") ? "" : ("/" + inbounddate)))
                .header("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
                .header("x-rapidapi-key", "3e6f404a90msh34229234cc6f379p16b4fdjsnbb95608fb071")
                .asObject(Quote.class);

        System.out.println(response.getBody().toString());
        return response;
    }
}