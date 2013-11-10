package edu.drexel.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Collection;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Publications {

    private Collection<Publication> publications = null;
    private RequestError error;

    public RequestError getError() {
        return error;
    }

    public void setError(RequestError error) {
        this.error = error;
    }

    public Collection<Publication> getPublications() {
        return publications;
    }

    public void setPublications(Collection<Publication> publications) {
        this.publications = publications;
    }
}
