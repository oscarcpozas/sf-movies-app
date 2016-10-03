package com.oscar.pozas.github.sf.movies.domain.main.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Film {

    @SerializedName("title") private String title;
    @SerializedName("release_year") private int releaseYear;
    @SerializedName("locations") private String location;
    @SerializedName("distributor") private String productionCompany;
    @SerializedName("director") private String director;

    @Expose private LatLng streetLocation;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProductionCompany() {
        return productionCompany;
    }

    public void setProductionCompany(String productionCompany) {
        this.productionCompany = productionCompany;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public LatLng getStreetLocation() {
        return streetLocation;
    }

    public void setStreetLocation(LatLng streetLocation) {
        this.streetLocation = streetLocation;
    }
}
