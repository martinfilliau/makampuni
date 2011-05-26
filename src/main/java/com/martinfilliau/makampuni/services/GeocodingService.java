package com.martinfilliau.makampuni.services;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.martinfilliau.makampuni.services.utils.Geohash;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author martinfilliau
 */
@Stateless
@LocalBean
public class GeocodingService {

    /**
     * Geocode an address into a geohash
     * @param address address to geocode
     * @return geohash geohash of the given address
     */
    public String geocode(String address) {
        Geocoder geocoder = new Geocoder();
        GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(address).setLanguage("en").getGeocoderRequest();
        GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);
        return getGeoHash(geocoderResponse.getResults().get(0).getGeometry().getLocation().getLat().doubleValue(), geocoderResponse.getResults().get(0).getGeometry().getLocation().getLng().doubleValue());
    }
    
    /**
     * Encode a lat / lng into a Geohash string
     * @param lat latitude
     * @param lng longitude
     * @return string representing the geohash
     */
    public String getGeoHash(double lat, double lng) {
        Geohash geohash = new Geohash();
        return geohash.encode(lat, lng);
    }
}
