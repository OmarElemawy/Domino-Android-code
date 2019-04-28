package com.example.ashraf.myapplication;

import android.Manifest;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoMetadataBuffer;
import com.google.android.gms.location.places.PlacePhotoMetadataResponse;
import com.google.android.gms.location.places.PlacePhotoResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import adapters.ViewPagerAdapter;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    SingleShotLocationProvider.GPSCoordinates location1;
    FloatingActionButton floatingActionButton;
    private LatLng lng;
    private  BottomSheetBehavior<View> behavior;
    View mapttt;
    View viewById;
    List<Bitmap> bitmaps;
    ViewPager pager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        pager = findViewById(R.id.view_Pager);




        foo(MapsActivity.this);
        viewById= findViewById(R.id.ooooo);
        mapttt=findViewById(R.id.kkkkk);

        floatingActionButton = findViewById(R.id.floatingActionButton);
        getbuttonsheet();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, 123);
        }
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (location1 != null) {
                    mMap.clear();
                    LatLng sydney = new LatLng(location1.latitude,location1.longitude);
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15));
                    mMap.addMarker(new MarkerOptions().position(sydney).title(location1.provider));

                }
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {

                viewById.setVisibility(View.VISIBLE);
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                lng=place.getLatLng();
                getPhotos(place);
                if(mMap!=null) {
                    movecamer(place);
                }
                if (location1 != null) {
                    LatLng sydney = new LatLng(location1.latitude, location1.longitude);
                    double valueResult = CalculationByDistance(sydney, place.getLatLng());
                    double km = valueResult / 1;
                    DecimalFormat newFormat = new DecimalFormat("####");
                    int kmInDec = Integer.valueOf(newFormat.format(km));
                    double meter = valueResult % 1000;
                    int meterInDec = Integer.valueOf(newFormat.format(meter));
                    Toast.makeText(MapsActivity.this, "" + valueResult + "   KM  " + kmInDec
                            + " Meter   " + meterInDec, Toast.LENGTH_SHORT).show();
                }
                Log.i("pppp",place.getLatLng().latitude+" "+place.getLatLng().longitude);
            }


            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("", "An error occurred: " + status);
            }
        });
    }



    private void movecamer(Place place) {
        mMap.clear();
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lng, 10));
        mMap.addMarker(new MarkerOptions().position(lng).title(place.getName().toString()));
    }



    private void getbuttonsheet() {
        View buttomsheet=findViewById(R.id.design_bottom_sheet);
        behavior=BottomSheetBehavior.from(buttomsheet);
        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }





    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }


    public double CalculationByDistance(LatLng StartP, LatLng EndP) {
        int Radius = 6371;// radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));


        return Radius * c;
    }




    public void getPhotos(Place place) {
        final GeoDataClient mGeoDataClient = Places.getGeoDataClient(this, null);

        // TODO: Start using the Places API.
        String id = place.getId();
        final Task<PlacePhotoMetadataResponse> photoMetadataResponse = mGeoDataClient.getPlacePhotos(id);
        photoMetadataResponse.addOnCompleteListener(new OnCompleteListener<PlacePhotoMetadataResponse>() {
            @Override
            public void onComplete(@NonNull Task<PlacePhotoMetadataResponse> task) {
                // Get the list of photos.
                PlacePhotoMetadataResponse photos = task.getResult();
                // Get the PlacePhotoMetadataBuffer (metadata for all of the photos).
                PlacePhotoMetadataBuffer photoMetadataBuffer = photos.getPhotoMetadata();
                // Get the first photo in the list.
                bitmaps=new ArrayList<>();
                   for (int i=0;i<bitmaps.size();i++)
                   {
                    PlacePhotoMetadata photoMetadata = photoMetadataBuffer.get(0);

                    // Get a full-size bitmap for the photo.
                    Task<PlacePhotoResponse> photoResponse = mGeoDataClient.getPhoto(photoMetadata);

                    photoResponse.addOnCompleteListener(new OnCompleteListener<PlacePhotoResponse>() {
                        @Override
                        public void onComplete(@NonNull Task<PlacePhotoResponse> task) {

                            PlacePhotoResponse photo = task.getResult();
                            Bitmap bitmap = photo.getBitmap();
                            if (bitmap!=null) {
                                bitmaps.add(bitmap);
                            }
                        }

                    });

            }
                ViewPagerAdapter adapter=new  ViewPagerAdapter(MapsActivity.this,bitmaps);
                pager.setAdapter(adapter);
            }
        });
    }
    public void foo(Context context) {
        SingleShotLocationProvider.requestSingleUpdate(context,
                new SingleShotLocationProvider.LocationCallback() {
                    @Override public void onNewLocationAvailable(SingleShotLocationProvider.GPSCoordinates location) {
                        location1=location;
                         Log.i("ppp",location.latitude+"  ==  "+location.longitude);
                    }
                });
    }
}