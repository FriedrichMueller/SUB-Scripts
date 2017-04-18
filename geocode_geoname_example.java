import org.geonames.*;
import org.geonames.utils.*;
import org.geonames.wikipedia.*;
import java.util.*; 


public class geocode {
   public static void main(String[] args) throws Exception{
	   
	   
	   WebService.setUserName("<your user name>"); // add your username here after registration on http://www.geonames.org/login
	   
	   
	   String searchName = "Göttingen"; // the string we want to lookup in geonames database
       ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();

       searchCriteria.setQ(searchName); // setup the main search term, in our case "Göttingen"
       searchCriteria.setMaxRows(1);
               try {
                   ToponymSearchResult searchResult = WebService.search(searchCriteria); // a toponym search result as returned by the geonames webservice.

                   for (Toponym toponym : searchResult.getToponyms()) {
                       System.out.println(toponym.getName() + " " + toponym.getCountryName()
                               + " " + toponym.getLongitude() + " "
                               + toponym.getLatitude()); // prints the search results. We have access on certain get-Functions. In our Case the Name, Country, Longitude and Latitude
                           }

                           } catch (Exception e) {
                               // TODO Auto-generated catch block
                               e.printStackTrace(); 
	   
	   
	   
   }
}
}
