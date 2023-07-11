package it.polito.tdp.nyc.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javadocmd.simplelatlng.LatLng;

import it.polito.tdp.nyc.model.Citta;
import it.polito.tdp.nyc.model.Hotspot;

public class NYCDao {
    
    public List<Hotspot> getAllHotspot(){
   	 String sql = "SELECT * FROM nyc_wifi_hotspot_locations";
   	 List<Hotspot> result = new ArrayList<>();
   	 try {
   		 Connection conn = DBConnect.getConnection();
   		 PreparedStatement st = conn.prepareStatement(sql);
   		 ResultSet res = st.executeQuery();

   		 while (res.next()) {
   			 result.add(new Hotspot(res.getInt("OBJECTID"), res.getString("Borough"),
   					 res.getString("Type"), res.getString("Provider"), res.getString("Name"),
   					 res.getString("Location"),res.getDouble("Latitude"),res.getDouble("Longitude"),
   					 res.getString("Location_T"),res.getString("City"),res.getString("SSID"),
   					 res.getString("SourceID"),res.getInt("BoroCode"),res.getString("BoroName"),
   					 res.getString("NTACode"), res.getString("NTAName"), res.getInt("Postcode")));
   		 }
   		 
   		 conn.close();
   	 } catch (SQLException e) {
   		 e.printStackTrace();
   		 throw new RuntimeException("SQL Error");
   	 }

   	 return result;
    }
    
    public List<String> getAllProviders(){
   	 String sql = "SELECT DISTINCT(n.Provider) AS p " +
   			 "FROM nyc_wifi_hotspot_locations AS n " +
   			 "ORDER BY n.Provider";
   	 List<String> result = new ArrayList<>();
   	 try {
   		 Connection conn = DBConnect.getConnection();
   		 PreparedStatement st = conn.prepareStatement(sql);
   		 ResultSet res = st.executeQuery();

   		 while (res.next()) {
   			 result.add(res.getString("p"));
   		 }
   		 
   		 conn.close();
   	 } catch (SQLException e) {
   		 e.printStackTrace();
   		 throw new RuntimeException("SQL Error");
   	 }

   	 return result;
    }
    
    public List<Citta> getAllCitta(String p){
   	 String sql = "SELECT DISTINCT n.Location AS c, AVG (n.Latitude) AS Lat, AVG (n.Longitude) AS lng " +
   			 "FROM nyc_wifi_hotspot_locations AS n " +
   			 "WHERE n.Provider = ? " +
   			 "GROUP BY n.location " +
   			 "ORDER BY n.location";
   	 List<Citta> result = new ArrayList<>();
   	 try {
   		 Connection conn = DBConnect.getConnection();
   		 PreparedStatement st = conn.prepareStatement(sql);
   		 st.setString(1, p);
   		 ResultSet res = st.executeQuery();

   		 while (res.next()) {
   			 result.add(new Citta(res.getString("c"), new LatLng(res.getDouble("Lat"), res.getDouble("Lng"))));
   		 }
   		 
   		 conn.close();
   	 } catch (SQLException e) {
   		 e.printStackTrace();
   		 throw new RuntimeException("SQL Error");
   	 }

   	 return result;
    }
    
    

}
