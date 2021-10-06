package ru.ubusheev.springtest.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "QuoteId",
        "MinPrice",
        "Direct",
        "OutboundLeg",
        "InboundLeg",
        "QuoteDateTime"
})
@Generated("jsonschema2pojo")
public class Quote__1 {

    @JsonProperty("QuoteId")
    private Integer quoteId;
    @JsonProperty("MinPrice")
    private Integer minPrice;
    @JsonProperty("Direct")
    private Boolean direct;
    @JsonProperty("OutboundLeg")
    private OutboundLeg outboundLeg;
    @JsonProperty("InboundLeg")
    private InboundLeg inboundLeg;
    @JsonProperty("QuoteDateTime")
    private String quoteDateTime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("QuoteId")
    public Integer getQuoteId() {
        return quoteId;
    }

    @JsonProperty("QuoteId")
    public void setQuoteId(Integer quoteId) {
        this.quoteId = quoteId;
    }

    @JsonProperty("MinPrice")
    public Integer getMinPrice() {
        return minPrice;
    }

    @JsonProperty("MinPrice")
    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    @JsonProperty("Direct")
    public Boolean getDirect() {
        return direct;
    }

    @JsonProperty("Direct")
    public void setDirect(Boolean direct) {
        this.direct = direct;
    }

    @JsonProperty("OutboundLeg")
    public OutboundLeg getOutboundLeg() {
        return outboundLeg;
    }

    @JsonProperty("OutboundLeg")
    public void setOutboundLeg(OutboundLeg outboundLeg) {
        this.outboundLeg = outboundLeg;
    }

    @JsonProperty("InboundLeg")
    public InboundLeg getInboundLeg() {
        return inboundLeg;
    }

    @JsonProperty("InboundLeg")
    public void setInboundLeg(InboundLeg inboundLeg) {
        this.inboundLeg = inboundLeg;
    }

    @JsonProperty("QuoteDateTime")
    public String getQuoteDateTime() {
        return quoteDateTime;
    }

    public boolean isExistInboundLeg(){
        if(Objects.isNull(inboundLeg))
            return false;
        return true;
    }

    @JsonProperty("QuoteDateTime")
    public void setQuoteDateTime(String quoteDateTime) {
        this.quoteDateTime = quoteDateTime;
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
