package com.example.prg6xmlandjson;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
public class ViewActivity extends AppCompatActivity {
    TextView xmldata, jsondata;
    int mode=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        xmldata=(TextView)findViewById(R.id.xmldata);
        jsondata=(TextView)findViewById(R.id.jsondata);
        mode=getIntent().getIntExtra("mode",0);
        if(mode==1)
            parseJson();
        else
            parseXml(); }
    public String parseXml()
    {
        try {
            InputStream is = getAssets().open("input.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document d = db.parse(is);
            Element e = d.getDocumentElement();
            e.normalize();
            NodeList nl = d.getElementsByTagName("employee");
            for (int i = 0; i < nl.getLength(); i++) {
                Node n = nl.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element ele = (Element) n;
                    xmldata.setText("City Name : " + getValue("city_name", ele) +
                            "\n");
                    xmldata.append("Latitude : " + getValue("Latitude", ele) + "\n");
                    xmldata.append("Longitude : " + getValue("Longitude", ele) +
                            "\n");
                    xmldata.append("Temperature : " + getValue("Temperature", ele) +
                            "\n");
                    xmldata.append("Humidity : " + getValue("Humidity", ele) + "\n");
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    private static String getValue(String tag, Element e)
    {
        NodeList nl=e.getElementsByTagName(tag).item(0).getChildNodes();
        Node n=nl.item(0);
        return n.getNodeValue();
    }
    public void parseJson()
    {
        try{
            InputStream is = getAssets().open("jsoninput.json");
            byte[] data=new byte[is.available()];
            is.read(data);
            String readdata=new String(data);
            JSONObject jobj=new JSONObject(readdata);
            JSONObject jobj1=jobj.getJSONObject("employee");
            jsondata.setText("City Name : " + jobj1.getString("city_name")+"\n");
            jsondata.append("Latitude : " + jobj1.getString("Latitude")+"\n");
            jsondata.append("Longitude : " + jobj1.getString("Longitude")+"\n");
            jsondata.append("Temperature : " + jobj1.getString("Temperature")+"\n");
            jsondata.append("Humidity : " + jobj1.getString("Humidity")+"\n");
        } catch (Exception e)

        {
            e.printStackTrace();

        }

    }
}
