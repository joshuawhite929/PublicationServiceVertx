package edu.drexel.services;

import edu.drexel.model.Publications;
import edu.drexel.model.Publication;

public interface PublicationService {
    Publications getAllPubs();
    Publication getPubsById(int id);
    Publications getSelectedPubs(int[] pubList);
}
