package com.martinfilliau.makampuni.services.utils;

import com.martinfilliau.makampuni.model.CompanyBean;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;

/**
 *
 * @author martinfilliau
 */
@Stateless
@LocalBean
public class SolrService {

    private final static String SOLR_SERVER_URL = "http://localhost:8983/solr";
    
    public void addDocument(CompanyBean c) throws SolrServerException, IOException {
        SolrServer server = getSolrServer();
        server.addBean(c);
    }
    
    private SolrServer getSolrServer() {
        try {
            return new CommonsHttpSolrServer(SOLR_SERVER_URL);
        } catch (MalformedURLException ex) {
            Logger.getLogger(SolrService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
