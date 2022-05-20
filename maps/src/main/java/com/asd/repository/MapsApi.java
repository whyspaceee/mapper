package com.asd.repository;

import java.io.IOException;

import com.google.maps.DirectionsApiRequest;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.internal.PolylineEncoding;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.EncodedPolyline;

public class MapsApi {
    GeoApiContext context;
    public MapsApi (String apiKey){
        context = new GeoApiContext.Builder().apiKey(apiKey).build();
    }
    public long getWeight (String origin, String destination){
        try{
            DistanceMatrix distanceMatrix = DistanceMatrixApi.newRequest(context).origins(origin).destinations(destination).await();
            return distanceMatrix.rows[0].elements[0].duration.inSeconds;
        } catch (ApiException e) {
            System.err.println(e.getMessage());
            return -1;
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
            return-1;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }
    public EncodedPolyline getPolyline (String origin , String destination) {
        try{
            DirectionsResult directionsApiRequest = new DirectionsApiRequest(context).origin(origin).destination(destination).await();
            return directionsApiRequest.routes[0].overviewPolyline;
        }catch (ApiException e) {
            System.err.println(e.getMessage());
            return null;
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
            return null;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
