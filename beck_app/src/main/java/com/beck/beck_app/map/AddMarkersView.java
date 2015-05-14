/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beck.beck_app.map;
import com.beck.beck_app.model.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct; 
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
  
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
/**
 *
 * @author rober_000
 */
@Named("addMarkersViewController")
@SessionScoped
public class AddMarkersView implements Serializable {
     
    private MapModel emptyModel;
      
    private String title;
      
    private double lat;
      
    private double lng;
  
    private List<Marker> markersList;
    
    @EJB
    private com.beck.beck_app.facade.PointFacade ejbFacade;
    
    @PostConstruct
    public void init() {
        emptyModel = new DefaultMapModel();
        markersList = new ArrayList<>();
    }
      
    public MapModel getEmptyModel() {
        return emptyModel;
    }
      
    public String getTitle() {
        return title;
    }
  
    public void setTitle(String title) {
        this.title = title;
    }
  
    public double getLat() {
        return lat;
    }
  
    public void setLat(double lat) {
        this.lat = lat;
    }
  
    public double getLng() {
        return lng;
    }
  
    public void setLng(double lng) {
        this.lng = lng;
    }
      
    public void addMarker() {
        Marker marker = new Marker(new LatLng(lat, lng), title);
        emptyModel.addOverlay(marker);
        markersList.add(marker);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Added", "Lat:" + lat + ", Lng:" + lng));
        
    }
    
    //TODO dodac reszte pol z tabelko point 
    public void saveMap() {
    for ( Marker m : markersList)
    {
    LatLng cords = m.getLatlng();
    Point newPoint = new Point();
    newPoint.setLatitude(cords.getLat());
    newPoint.setLongitude(cords.getLng());
    getEjbFacade().create(newPoint);
    }
    
    }

    /**
     * @return the markersList
     */
    public List<Marker> getMarkersList() {
        return markersList;
    }

    /**
     * @param markersList the markersList to set
     */
    public void setMarkersList(List<Marker> markersList) {
        this.markersList = markersList;
    }

    /**
     * @return the ejbFacade
     */
    public com.beck.beck_app.facade.PointFacade getEjbFacade() {
        return ejbFacade;
    }

    /**
     * @param ejbFacade the ejbFacade to set
     */
    public void setEjbFacade(com.beck.beck_app.facade.PointFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }
}
