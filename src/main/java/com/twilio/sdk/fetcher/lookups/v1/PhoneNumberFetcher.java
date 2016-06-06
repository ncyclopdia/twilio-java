/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /       
 */

package com.twilio.sdk.fetcher.lookups.v1;

import com.twilio.sdk.client.TwilioRestClient;
import com.twilio.sdk.converter.PrefixedCollapsibleMap;
import com.twilio.sdk.converter.Promoter;
import com.twilio.sdk.exception.ApiConnectionException;
import com.twilio.sdk.exception.ApiException;
import com.twilio.sdk.fetcher.Fetcher;
import com.twilio.sdk.http.HttpMethod;
import com.twilio.sdk.http.Request;
import com.twilio.sdk.http.Response;
import com.twilio.sdk.resource.RestException;
import com.twilio.sdk.resource.lookups.v1.PhoneNumber;

import java.util.List;
import java.util.Map;

public class PhoneNumberFetcher extends Fetcher<PhoneNumber> {
    private final com.twilio.sdk.type.PhoneNumber phoneNumber;
    private String countryCode;
    private String type;
    private List<String> addOns;
    private Map<String, Object> addOnsData;

    /**
     * Construct a new PhoneNumberFetcher.
     * 
     * @param phoneNumber The phone_number
     */
    public PhoneNumberFetcher(final com.twilio.sdk.type.PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * The country_code.
     * 
     * @param countryCode The country_code
     * @return this
     */
    public PhoneNumberFetcher setCountryCode(final String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    /**
     * The type.
     * 
     * @param type The type
     * @return this
     */
    public PhoneNumberFetcher setType(final String type) {
        this.type = type;
        return this;
    }

    /**
     * The add_ons.
     * 
     * @param addOns The add_ons
     * @return this
     */
    public PhoneNumberFetcher setAddOns(final List<String> addOns) {
        this.addOns = addOns;
        return this;
    }

    /**
     * The add_ons.
     * 
     * @param addOns The add_ons
     * @return this
     */
    public PhoneNumberFetcher setAddOns(final String addOns) {
        return setAddOns(Promoter.listOfOne(addOns));
    }

    /**
     * The add_ons_data.
     * 
     * @param addOnsData The add_ons_data
     * @return this
     */
    public PhoneNumberFetcher setAddOnsData(final Map<String, Object> addOnsData) {
        this.addOnsData = addOnsData;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the fetch.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Fetched PhoneNumber
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public PhoneNumber execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            TwilioRestClient.Domains.LOOKUPS,
            "/v1/PhoneNumbers/" + this.phoneNumber + "",
            client.getAccountSid()
        );
        
        addQueryParams(request);
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("PhoneNumber fetch failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
        
            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }
        
        return PhoneNumber.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested query string arguments to the Request.
     * 
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (countryCode != null) {
            request.addQueryParam("CountryCode", countryCode);
        }
        
        if (type != null) {
            request.addQueryParam("Type", type);
        }
        
        if (addOns != null) {
            for (Object prop : addOns) {
                request.addQueryParam("AddOns", prop.toString());
            }
        }
        
        if (addOnsData != null) {
            Map<String, String> params = PrefixedCollapsibleMap.serialize(addOnsData, "AddOns");
            for (Map.Entry<String, String> entry : params.entrySet()) {
                request.addQueryParam(entry.getKey(), entry.getValue());
            }
        }
    }
}