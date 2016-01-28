package com.twilio.sdk.resources.pricing.v1.messaging;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.twilio.sdk.clients.TwilioRestClient;
import com.twilio.sdk.exceptions.ApiConnectionException;
import com.twilio.sdk.exceptions.ApiException;
import com.twilio.sdk.fetchers.pricing.v1.messaging.CountryFetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.readers.pricing.v1.messaging.CountryReader;
import com.twilio.sdk.resources.RestException;
import com.twilio.sdk.resources.SidResource;
import com.twilio.types.InboundSmsPrice;
import com.twilio.types.OutboundSmsPrice;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Country extends SidResource {
    private static final long serialVersionUID = 155838537182593L;

    /**
     * read
     * 
     * @return CountryReader capable of executing the read
     */
    public static CountryReader read() {
        return new CountryReader();
    }

    /**
     * fetch
     * 
     * @param isoCountry The iso_country
     * @return CountryFetcher capable of executing the fetch
     */
    public static CountryFetcher fetch(final String isoCountry) {
        return new CountryFetcher(isoCountry);
    }

    /**
     * Converts a JSON String into a Country object using the provided ObjectMapper
     * 
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Country object represented by the provided JSON
     */
    public static Country fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Country.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Country object using the provided
     * ObjectMapper
     * 
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Country object represented by the provided JSON
     */
    public static Country fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Country.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String country;
    private final String isoCountry;
    private final List<OutboundSmsPrice> outboundSmsPrices;
    private final List<InboundSmsPrice> inboundSmsPrices;
    private final Currency priceUnit;
    private final URI url;

    @JsonCreator
    private Country(@JsonProperty("country")
                    final String country, 
                    @JsonProperty("iso_country")
                    final String isoCountry, 
                    @JsonProperty("outbound_sms_prices")
                    final List<OutboundSmsPrice> outboundSmsPrices, 
                    @JsonProperty("inbound_sms_prices")
                    final List<InboundSmsPrice> inboundSmsPrices, 
                    @JsonProperty("price_unit")
                    @JsonDeserialize(using = com.twilio.sdk.converters.CurrencyDeserializer.class)
                    final Currency priceUnit, 
                    @JsonProperty("url")
                    final URI url) {
        this.country = country;
        this.isoCountry = isoCountry;
        this.outboundSmsPrices = outboundSmsPrices;
        this.inboundSmsPrices = inboundSmsPrices;
        this.priceUnit = priceUnit;
        this.url = url;
    }

    /**
     * @return The iso_country
     */
    public final String getSid() {
        return this.getIsoCountry();
    }

    /**
     * @return The country
     */
    public final String getCountry() {
        return this.country;
    }

    /**
     * @return The iso_country
     */
    public final String getIsoCountry() {
        return this.isoCountry;
    }

    /**
     * @return The outbound_sms_prices
     */
    public final List<OutboundSmsPrice> getOutboundSmsPrices() {
        return this.outboundSmsPrices;
    }

    /**
     * @return The inbound_sms_prices
     */
    public final List<InboundSmsPrice> getInboundSmsPrices() {
        return this.inboundSmsPrices;
    }

    /**
     * @return The price_unit
     */
    public final Currency getPriceUnit() {
        return this.priceUnit;
    }

    /**
     * @return The url
     */
    public final URI getUrl() {
        return this.url;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        
        Country other = (Country) o;
        
        return Objects.equals(country, other.country) && 
               Objects.equals(isoCountry, other.isoCountry) && 
               Objects.equals(outboundSmsPrices, other.outboundSmsPrices) && 
               Objects.equals(inboundSmsPrices, other.inboundSmsPrices) && 
               Objects.equals(priceUnit, other.priceUnit) && 
               Objects.equals(url, other.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country,
                            isoCountry,
                            outboundSmsPrices,
                            inboundSmsPrices,
                            priceUnit,
                            url);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("country", country)
                          .add("isoCountry", isoCountry)
                          .add("outboundSmsPrices", outboundSmsPrices)
                          .add("inboundSmsPrices", inboundSmsPrices)
                          .add("priceUnit", priceUnit)
                          .add("url", url)
                          .toString();
    }
}