package SynopSec;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class XmlToJsonConverter {

	public static int PRETTY_PRINT_INDENT_FACTOR = 4;
	public String convertToJson(String xmlString)
	{
		
		String jsonConverter = ""; 
		try {
			JSONObject xmlJSONObj = XML.toJSONObject(xmlString);
			String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
			System.out.println(jsonPrettyPrintString);
		} catch (JSONException je) {
			System.out.println(je.toString());
		}
		
		return jsonConverter;
	}
	
	
	public static void main(String args[])
	{
		XmlToJsonConverter json = new XmlToJsonConverter();
		
		File jsonInputFile = new File("samplexml.xml");
        InputStream is;
        StringBuilder textBuilder = new StringBuilder();
        try {
            is = new FileInputStream(jsonInputFile);
            is.toString();
            
            
            
            InputStreamReader read1 =  new InputStreamReader(is, Charset.forName(StandardCharsets.UTF_8.name()));
             Reader reader = new BufferedReader(read1); 
                int c = 0;
                while ((c = reader.read()) != -1) {
                    textBuilder.append((char) c);
                }
                String jsonval =  json.convertToJson(textBuilder.toString());
                System.out.println("XmlToJsonConverter.main() ========");
                System.out.println(jsonval);
            }
	  catch (FileNotFoundException e) {
         
         e.printStackTrace();
     } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
