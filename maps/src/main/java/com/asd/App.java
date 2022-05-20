package com.asd;

import java.io.IOException;
import java.util.List;

import com.asd.repository.MapsApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.ImageResult;
import com.google.maps.PlaceDetailsRequest;
import com.google.maps.PlacesApi;
import com.google.maps.StaticMapsApi;
import com.google.maps.StaticMapsRequest;
import com.google.maps.FindPlaceFromTextRequest.InputType;
import com.google.maps.StaticMapsRequest.Path;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixRow;
import com.google.maps.model.EncodedPolyline;
import com.google.maps.model.FindPlaceFromText;
import com.google.maps.model.GeocodedWaypoint;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.Photo;
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PlacesSearchResult;
import com.google.maps.model.Size;

import kotlin.collections.builders.ListBuilder;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import com.asd.logic.*;

public class App 
{
    public static void main( String[] args )
    {
        MapsApi api = new MapsApi("AIzaSyBVB4im3FLPvDYD_Lu6lFd7kJD27f45IS0");
        MST mst = new MST(3, api);
        String[] string = {"Jakarta", "Bali",  "Jogjakarta"};
        mst.create(string);
        mst.Prim(0);
    }

   
    
}
