package edu.drexel.services;

import edu.drexel.model.*;

import java.util.Collection;
import java.util.HashMap;

public class PublicationServiceImpl implements PublicationService {

    private DbMock dbService;
    
    public PublicationServiceImpl(DbMock dbService) {
    	this.dbService = dbService;
    }

    static HashMap<Integer,Publication> pubDB = null;


    public Publications getAllPubs(){

        Publications pList = new Publications();
        Collection<Publication> pubList = dbService.getAllPubs();
        if(pubList != null) {
            pList.setPublications(pubList);
        }
        else{
            RequestError requestError = new RequestError(400,
                    "Please check server logs, an unexpected error has happened");
            pList.setError(requestError);
        }

        return pList;
    }

    public Publication getPubsById(int id){

        Publication thePub = dbService.getPubById(id);
        if(thePub == null){
            RequestError requestError = new RequestError(400,
                    "The requested index "+id+" is invalid or not in range");
            thePub = new Publication();
            thePub.setError(requestError);
        }

        return thePub;
    }

    public Publications getSelectedPubs(int[] pubList){
        return null;
    }
}
