# SUB-Scripts
Different scripts for different usage

## Geocode:
### geocode_geoname_example.java
* Example script to geocode with geonames.org. Can be easily extended with FileReader and FileWriter.

* Purpose: You have a list of addresses and want the corresponding coordinates.



## ETL
### Opac2KML.java
*Input: MARC21/XML
*Output: KML (for Geo-Browser)

* ETL script to transform Marc21/XML content into kml for the DARIAH Geo-Browser application (https://geobrowser.de.dariah.eu/)
* Data is enriched with OPAC Links
* Center point of maps are provided 

* Purpose: You want to visualize OPAC Data (e.g. retrieved through SRU interface) with the GeoBrowser.
