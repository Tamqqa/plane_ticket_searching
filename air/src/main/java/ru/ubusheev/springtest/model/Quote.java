package ru.ubusheev.springtest.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Quotes",
        "Places",
        "Carriers",
        "Currencies"
})
@Generated("jsonschema2pojo")
public class Quote {

    @JsonProperty("Quotes")
    private List<Quote__1> quotes = null;
    @JsonProperty("Places")
    private List<Place> places = null;
    @JsonProperty("Carriers")
    private List<Carrier> carriers = null;
    @JsonProperty("Currencies")
    private List<Currency> currencies = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Quotes")
    public List<Quote__1> getQuotes() {
        return quotes;
    }

    @JsonProperty("Quotes")
    public void setQuotes(List<Quote__1> quotes) {
        this.quotes = quotes;
    }

    @JsonProperty("Places")
    public List<Place> getPlaces() {
        return places;
    }

    @JsonProperty("Places")
    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    @JsonProperty("Carriers")
    public List<Carrier> getCarriers() {
        return carriers;
    }

    @JsonProperty("Carriers")
    public void setCarriers(List<Carrier> carriers) {
        this.carriers = carriers;
    }

    @JsonProperty("Currencies")
    public List<Currency> getCurrencies() {
        return currencies;
    }

    @JsonProperty("Currencies")
    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}