package com.martinfilliau.makampuni.services;

import com.martinfilliau.makampuni.model.CompanyBean;
import com.martinfilliau.makampuni.services.utils.SolrService;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import org.apache.solr.client.solrj.SolrServerException;

/**
 *
 * @author martinfilliau
 */
@Stateless
@LocalBean
public class CompaniesManagementService {

    @EJB
    private SolrService solr;

    @EJB
    private GeocodingService geocode;
    
    public void create(CompanyBean c) {
        String geohash = geocode.geocode(c.getAddress());
        
        c.setGeohash(geohash);
                
        try {
            solr.addDocument(c);
        } catch (SolrServerException ex) {
            Logger.getLogger(CompaniesManagementService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CompaniesManagementService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
