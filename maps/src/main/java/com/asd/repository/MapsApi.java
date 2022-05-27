package com.asd.repository;

import java.io.IOException;
import java.util.LinkedList;

import com.google.maps.DirectionsApiRequest;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.StaticMapsRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.EncodedPolyline;
import com.google.maps.model.Size;
import com.asd.logic.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;

public class MapsApi {
    GeoApiContext context;

    public MapsApi(String apiKey) {
        context = new GeoApiContext.Builder().apiKey(apiKey).build();
    }

    public long getWeight(String origin, String destination) {
        try {
            DistanceMatrix distanceMatrix = DistanceMatrixApi.newRequest(context).origins(origin)
                    .destinations(destination).await();
            return distanceMatrix.rows[0].elements[0].duration.inSeconds;
        } catch (ApiException e) {
            System.err.println(e.getMessage());
            return -1;
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
            return -1;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }

    public EncodedPolyline getPolyline(String origin, String destination) {
        try {
            DirectionsResult directionsApiRequest = new DirectionsApiRequest(context).origin(origin)
                    .destination(destination).await();
            return directionsApiRequest.routes[0].overviewPolyline;
        } catch (ApiException e) {
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

    public BufferedImage getImage(Vertex[] vertices) {
        LinkedList<EncodedPolyline> polylines = new LinkedList<EncodedPolyline>();
        for (int i = 1; i < vertices.length; i++) {
            polylines.add(getPolyline(vertices[i].getPlaceName(), vertices[i].getParent().getPlaceName()));
        }
        try {
            StaticMapsRequest iMapsRequest = new StaticMapsRequest(context);
            while (!polylines.isEmpty()) {
                iMapsRequest.path(polylines.poll());
            }
            StaticMapsRequest.Markers marker = new StaticMapsRequest.Markers();
            for (int i = 0; i < vertices.length; i++) {
                marker.addLocation(vertices[i].getPlaceName());
            }
            iMapsRequest.markers(marker);
            iMapsRequest.size(new Size(720, 480));
            byte[] image = iMapsRequest.await().imageData;
            ByteArrayInputStream stream = new ByteArrayInputStream(image);
            BufferedImage buffer = ImageIO.read(stream);
            return buffer;

        } catch (ApiException e) {
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
