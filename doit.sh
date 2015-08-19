https://cloud.google.com/appengine/docs/java/gettingstarted/uploading
mvn appengine:update
mvn appengine:devserver

mvn clean install
mvn clean compile install
http://www.benoitschweblin.com/2013/03/run-jetty-in-maven-life-cycle.html
mvn jetty:run

mvn jetty:run

#problem: 
if not turn off google datastore from, the test would fail
src/main/resources/database.properties

it turn off google datastore from the above file, test will pass and code will be deployed. But the app has no data since it is using default hb db.
