This is a small demonstration project that I built to demonstrate how to design and build RESTful web services using Vert.x.

After running the module using Maven (mvn vertx:runMod) you can run the services by one of the following URLs:

1. GET ALL PUBLICATIONS:  http://locahost:8080/publications
2. GET A PARTICULAR PUBLICATION: http://locahost:8080/publications/{id} where {id} is an integer so
   http://locahost:8080/publications/1 would return the publication with ID of 1.
