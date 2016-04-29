/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clima;

/**
 *
 * @author JoseDavid
 */


import java.io.BufferedReader;

import java.io.InputStreamReader;

import java.net.HttpURLConnection;

import java.net.URL;
import java.util.Scanner;

import org.json.JSONObject;



public class Clima {

	public static String getHTML(String urlToRead) throws Exception {

		StringBuilder result = new StringBuilder();

		URL url = new URL(urlToRead);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setRequestMethod("GET");

		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		String line;

		while ((line = rd.readLine()) != null) {

		   result.append(line);

		}

		rd.close();

		return result.toString();

	}

	

	public static void main(String[] args) {

		// TODO Auto-generated method stub

		try {

                    System.out.println("Ingrese la ciudad: ");
                        Scanner lea = new Scanner(System.in);
                        String c = lea.next();
                        
			String respuesta = getHTML("http://api.openweathermap.org/data/2.5/weather?q="+c+"&appid=86587174cc0cd70fe4cbb7f3f2753419");

			//System.out.println(respuesta);

			JSONObject obj = new JSONObject(respuesta);

			double temp = obj.getJSONObject("main").getDouble("temp") - 273.15;
                        double pressure = obj.getJSONObject("main").getDouble("pressure");
                       double nivelMar = obj.getJSONObject("main").getDouble("sea_level");
                       double Humedad = obj.getJSONObject("main").getDouble("humidity");
                       double latitud = obj.getJSONObject("coord").getDouble("lat");
                       double longitud = obj.getJSONObject("coord").getDouble("lon");
                       System.out.println("La temperatura en"+c+" es: "+temp+" Celsius");
                       System.out.println("La presion en"+c+" es: "+pressure);
                       System.out.println("La latitud en"+c+" es: "+latitud);
                       System.out.println("La longitud en"+c+" es: "+longitud);
                       System.out.println("El nivel del mar en"+c+" es: "+nivelMar);
                       System.out.println("La Humedad en" +c+ " es: "+Humedad);
			System.out.print("La temperatura en" +c+ " es: "+temp+" Celsius");

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}


