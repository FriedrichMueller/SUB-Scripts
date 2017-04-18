

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.PrintWriter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OpacKML {
   public static void main(String[] args){

      try {	
    	  
    	  PrintWriter writer = new PrintWriter("<path of your kml file>", "UTF-8"); // Add kml destination here!
    	  writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    	  writer.println("<kml>");  
  	  	  	    	     	  
    	  
    	  
         File inputFile = new File("<path of xml file of Marc21-OPAC-Dump>");
         DocumentBuilderFactory dbFactory 
            = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(inputFile);
         doc.getDocumentElement().normalize();

         NodeList nList = doc.getElementsByTagName("recordData");
         for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               
               
               NodeList nl = eElement.getElementsByTagName("coordinates");
               if (nl.getLength() > 0) {
            	   
       String subtitle = "";
       String identifier = "";
       String searchurl = "";
            	   
            	   
       writer.println("<Placemark>");
       writer.println("<description>");
       
     
       
       
       
       NodeList id = eElement.getElementsByTagName("identifier");
       
       NodeList title = eElement.getElementsByTagName("title");
       System.out.println(title.getLength());
       System.out.println(title.item(0).getTextContent());
       
       if (title.getLength()>=3){
    	   subtitle = title.item(2).getTextContent();
       }
       
       if (title.getLength()==2){
    	   subtitle = title.item(1).getTextContent();
       }
       if (title.getLength()==1){
    	   subtitle = title.item(0).getTextContent();
       }
     //enrich the data with OPAC Links 
       if (id.getLength() > 0) {
    	   identifier = id.item(0).getTextContent();
    	   searchurl = "&lt;a href=&quot;https://opac.sub.uni-goettingen.de/DB=1/TTL=1/CMD?ACT=SRCHA&amp;IKT=1016&amp;SRT=LST_tay&amp;TRM="+identifier+"&amp;MATCFILTER=N&amp;MATCSET=N&amp;NOSCAN=N&amp;ADI_BIB=&quot; target=&quot;_blank&quot;&gt;OPAC Göttingen LINK&lt;/a&gt;";
       }else{
    	   subtitle= subtitle.replace(" ", "+").replace("[","").replace("]","").replace("<","").replace(">","").replace(".","").replace("&","");
    	   searchurl = "&lt;a href=&quot;https://opac.sub.uni-goettingen.de/DB=1/TTL=1/CMD?ACT=SRCHA&amp;IKT=1016&amp;SRT=LST_tay&amp;TRM="+subtitle+"&amp;MATCFILTER=N&amp;MATCSET=N&amp;NOSCAN=N&amp;ADI_BIB=&quot; target=&quot;_blank&quot;&gt;OPAC Göttingen LINK&lt;/a&gt;";   
         
       }
       writer.println(searchurl);
      
       
       
       for (int i = 1; i < title.getLength()  ; i++) {
           writer.println("&lt;/br&gt;&lt;b&gt;Title"+ i+" : &lt;/b&gt;" 
                   + eElement
                   .getElementsByTagName("title")
                   .item(i)
                   .getTextContent().replace("<","").replace(">","").replace(" ", "+").replace("[","").replace("]","").replace("<","").replace(">","").replace("+","").replace(".","").replace("&","&amp;"));              
      }    
            	   
       NodeList scale = eElement.getElementsByTagName("scale");
       if (scale.getLength() > 0) {
       writer.println("&lt;/br&gt;&lt;b&gt;Scale : &lt;/b&gt;" 
               + eElement
               .getElementsByTagName("scale")
               .item(0)
               .getTextContent().replace("<","").replace(">",""));
       }
       
       NodeList proj = eElement.getElementsByTagName("projection");
       if (proj.getLength() > 0) {
       writer.println("&lt;/br&gt;&lt;b&gt;Projection : &lt;/b&gt;" 
               + eElement
               .getElementsByTagName("projection")
               .item(0)
               .getTextContent().replace("<","").replace(">",""));
       }
       
       
       if (id.getLength() > 0) {
       writer.println("&lt;/br&gt;&lt;b&gt;Identifier : &lt;/b&gt;" 
               + eElement
               .getElementsByTagName("identifier")
               .item(0)
               .getTextContent().replace("<","").replace(">",""));
       
             
       }
       
       NodeList topic = eElement.getElementsByTagName("topic");
       if (topic.getLength() > 0) {
       writer.println("&lt;/br&gt;&lt;b&gt;Topic : &lt;/b&gt;" 
               + eElement
               .getElementsByTagName("topic")
               .item(0)
               .getTextContent().replace("<","").replace(">",""));
       }
       
       
       
       
    	   
           String value = nl.item(0).getTextContent();
           writer.println("&lt;/br&gt;&lt;b&gt;Coordinates: &lt;/b&gt;"+value); 
           
           NodeList pl = eElement.getElementsByTagName("publisher");
           if (pl.getLength() > 0) {
           writer.println("&lt;/br&gt;&lt;b&gt;Publisher : &lt;/b&gt;" 
                   + eElement
                   .getElementsByTagName("publisher")
                   .item(0)
                   .getTextContent().replace("<","").replace(">","").replace("&","&amp;"));
           }
           
           writer.println("&lt;/br&gt;&lt;b&gt;PublishYear : &lt;/b&gt;" 
                   +eElement
                  .getElementsByTagName("dateIssued")
                  .item(0)
                  .getTextContent().replace("<","").replace(">",""));
           
            	   
           writer.println("</description>");    	   
            //title contains address   
           writer.println("<address>");   
               writer.println( 
                  eElement
                  .getElementsByTagName("title")
                  .item(0)
                  .getTextContent().replace("<","").replace(">",""));
     
                        
               writer.println("</address>");
               
               writer.println("<TimeStamp>");
               writer.println("<when>");
               writer.println(
                        eElement
                       .getElementsByTagName("dateIssued")
                       .item(0)
                       .getTextContent().replace("<","").replace(">",""));
               writer.println("</when>");
               writer.println("</TimeStamp>");
               
                 
               
                   
                   
 // Find Center Point of given coordinate frame
                   
                   String E1="";
                   String E2="";
                   String N1="";
                   String N2="";
                   String E_sur="";
                   String N_sur="";
                   
                   
                   Pattern pattern = Pattern.compile( "E .*?--E" );
                  
                   
                   Matcher matcher = pattern.matcher(value);
                  
                   
                  
                   while (matcher.find()) {
                     
                     E1 = matcher.group().replace("--","").replace(" ", "").replace("E", "");
              
                   }
                   
                   pattern = Pattern.compile( "W .*?--W" );
                  
                   
                   Matcher matcher_2 = pattern.matcher(value);
                  
                   
   
                   while (matcher_2.find()) {
                     E_sur="-";
                     E1 = matcher_2.group().replace("--","").replace(" ", "").replace("W", "");
               
                   }
                   
                   
                   
                     
                   pattern = Pattern.compile( "--E .*?/" );
                   
                     Matcher matcher2 = pattern.matcher(value);
                   
                     while (matcher2.find()) {
                     
                     E2 = matcher2.group().replace("--","").replace(" ", "").replace("E", "").replace("/", "");
                 
                     }
                     
                     
                     pattern = Pattern.compile( "--W .*?/" );
                     
                     Matcher matcher2_1 = pattern.matcher(value);
                   
                     while (matcher2_1.find()) {
                     
                     E2 = matcher2_1.group().replace("--","").replace(" ", "").replace("W", "").replace("/", "");
        
                     }
                     
                      
                     pattern = Pattern.compile( "N .*?--N" );
                     Matcher matcher3 = pattern.matcher(value);
                     while (matcher3.find()) {
                     
                     
                     N1 = matcher3.group().replace("--","").replace(" ", "").replace("N", "");
         
                     }
                     
                     pattern = Pattern.compile( "S .*?--S" );
                     Matcher matcher3_1 = pattern.matcher(value);
                     while (matcher3_1.find()) {
                     
                    	 N_sur="-";
                     N1 = matcher3_1.group().replace("--","").replace(" ", "").replace("S", "");
        
                     }
                     
                     
                     pattern  = Pattern.compile( "--N .*?\\)");
                     Matcher matcher4 = pattern.matcher(value);
                     while (matcher4.find()) {
                     
                     N2 = matcher4.group().replace("--","").replace(" ", "").replace("N", "").replace(")","");
   
                     }
                     
                     pattern  = Pattern.compile( "--S .*?\\)");
                     Matcher matcher4_1 = pattern.matcher(value);
                     while (matcher4_1.find()) {
                     
                     N2 = matcher4_1.group().replace("--","").replace(" ", "").replace("S", "").replace(")","");
           
                     }
           
                   
                    	
          if (E1!=""&&E2!=""&&N1!=""&&N2!=""){
                   
                     if (E1.length() == 5){
                    	 
                    	 double LongitudeE1min =Integer.parseInt(E1.substring(E1.length()-2));
                    	 double LongitudeE2min =Integer.parseInt(E2.substring(E2.length()-2));
                    	 double Longitudeminutesaverage= (LongitudeE1min+LongitudeE2min)/60;
                    	 Longitudeminutesaverage= Longitudeminutesaverage/2;
                    	 double LongitudeE1Degree = Integer.parseInt(E1.substring(0,3));
                    	 double LongitudeE2Degree = Integer.parseInt(E2.substring(0,3));
                    	 double LongitudeDegreeaverage = (LongitudeE1Degree+LongitudeE2Degree)/2;
                         double Longitudeaverage= LongitudeDegreeaverage+Longitudeminutesaverage;
                    	 
                    	 
                         double LatitudeN1min =Integer.parseInt(N1.substring(N1.length()-2));
                         double LatitudeN2min =Integer.parseInt(N2.substring(N2.length()-2));
                    	 double Latitudeminutesaverage= (LatitudeN1min+LatitudeN2min)/60;
                    	 Latitudeminutesaverage= Latitudeminutesaverage/2;
                    	 double LatitudeN1Degree = Integer.parseInt(N1.substring(0,3));
                    	 double LatitudeN2Degree = Integer.parseInt(N2.substring(0,3));
                    	 double LatitudeDegreeaverage = (LatitudeN1Degree+LatitudeN2Degree)/2;
                         double Latitudeaverage= LatitudeDegreeaverage+Latitudeminutesaverage;
                         
                         writer.println("<Point>");
                         writer.println("<coordinates>");
                    	 writer.println(E_sur+Longitudeaverage+","+N_sur+Latitudeaverage+",0"); 
                  
                    	 writer.println("</coordinates>");
                    	 writer.println("</Point>");
                    	
                     }
          }        
                  
                     writer.println("</Placemark>");
                   
                   
               }
            	   
               
               
            }
           
         }
         writer.println("</kml>");
         writer.close(); 
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}