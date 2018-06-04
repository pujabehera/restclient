import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {

	public static void main(String[] args)throws Exception {
		
		URL url = new URL("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22nome%2C%20ak%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
        
		
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

        String inputLine;
        
        String json = "";
        
        
        while ((inputLine = in.readLine()) != null){
        	json = json+inputLine;
        }
        
        
       // System.out.println(json);
           
        in.close();
        
        
       // System.out.println(json);
        
        JSONObject jsonObject = new JSONObject(json);
        
        
        JSONObject queryObject = jsonObject.getJSONObject("query");
        
        JSONObject resultObject = queryObject.getJSONObject("results");
        
        JSONObject channelObject = resultObject.getJSONObject("channel");
        
        JSONObject locationObject = channelObject.getJSONObject("location");
        
        
        System.out.println(locationObject.getString("city"));
        
        System.out.println(locationObject.getString("country"));
        
        
        
        JSONArray forcastArray = channelObject.getJSONObject("item").getJSONArray("forecast");
        
        for(int i=0;i<forcastArray.length();i++){
        	
        	JSONObject object =  forcastArray.getJSONObject(i);
        	
        	System.out.println(object.getString("date") + " " + object.getString("text"));
        	
        	
        }
        
        
        
		

	}

}
