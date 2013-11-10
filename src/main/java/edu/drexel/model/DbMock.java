package edu.drexel.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.File;
import java.util.HashMap;
import java.util.Collection;
import java.util.List;

import org.vertx.java.core.file.FileSystem;

//@Service
public class DbMock {

    private HashMap<Integer,Publication> pubCache = null;

    public HashMap<Integer,Publication> getFullPubList()
    {
        return queryPubs();
    }

    public Publication getPubById(int id)
    {
        HashMap<Integer,Publication> pubDB = queryPubs();
        Integer index = new Integer(id);
        return pubDB.get(index);
    }

    public Collection<Publication> getAllPubs()
    {
        HashMap<Integer,Publication> pubDB = queryPubs();
        return pubDB.values();
    }

    private HashMap<Integer,Publication> queryPubs()
    {
        if(pubCache != null) return pubCache;

        ObjectMapper mapper = new ObjectMapper();
        HashMap<Integer,Publication> pubDB = new HashMap<Integer,Publication>();

        try{
        	List<Publication> pubList = mapper.readValue(getClass().getResourceAsStream("/pubs.json"), new TypeReference<List<Publication>>(){});

            for( Publication p : pubList)
            {
                Integer idx = new Integer(p.getId());
                pubDB.put(idx,p);
            }
            pubCache = pubDB;
            return pubDB;
        }catch(Exception e)
        {
            e.printStackTrace();
            pubCache = null;
            return null;
        }
    }
}
