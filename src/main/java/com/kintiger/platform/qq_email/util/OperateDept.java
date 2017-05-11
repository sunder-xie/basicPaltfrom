package com.kintiger.platform.qq_email.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.kintiger.platform.base.action.BaseAction;

public class OperateDept extends BaseAction{
	public static final String crimp = "http://openapi.exmail.qq.com:12211/openapi/party/sync";
	public static void DelDept(String path){
    	String token = (new EmailAction()).getToken();
    	HttpURLConnection httpConnection = null;
    	URL  urlcrm = null;
    	try{
    		urlcrm = new URL(crimp);
    		httpConnection = (HttpURLConnection)urlcrm.openConnection();
    		httpConnection.setDoOutput(true);
    		httpConnection.setDoInput(true);
            httpConnection.setRequestMethod("POST");
            httpConnection.setUseCaches(false);
            httpConnection.setInstanceFollowRedirects(true);
            System.out.println(token);
            httpConnection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            String params = "access_token="+token+"&action=1&dstpath="+path;
            System.out.println(params);
            OutputStreamWriter out = new OutputStreamWriter(httpConnection.getOutputStream(),"UTF-8");
            out.write(params);
            out.flush();
            out.close();
            httpConnection.getContentEncoding();
            java.io.InputStream in = httpConnection.getInputStream();
            BufferedReader breader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
            String lines ="";
            String str  = "";
            String json = "";
            while((lines = breader.readLine())!=null){
            	str = lines ;
            	json =json +str;
            }
    	}catch(Exception e){
    		e.printStackTrace();
    	}
	}
	public static void AddDept(String path){
    	String token = (new EmailAction()).getToken();
    	HttpURLConnection httpConnection = null;
    	URL  urlcrm = null;
    	try{
    		urlcrm = new URL(crimp);
    		httpConnection = (HttpURLConnection)urlcrm.openConnection();
    		httpConnection.setDoOutput(true);
    		httpConnection.setDoInput(true);
            httpConnection.setRequestMethod("POST");
            httpConnection.setUseCaches(false);
            httpConnection.setInstanceFollowRedirects(true);
            System.out.println(token);
            httpConnection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            String params = "access_token="+token+"&action=2&dstpath="+path;
            System.out.println(params);
            OutputStreamWriter out = new OutputStreamWriter(httpConnection.getOutputStream(),"UTF-8");
            out.write(params);
            out.flush();
            out.close();
            httpConnection.getContentEncoding();
            java.io.InputStream in = httpConnection.getInputStream();
            BufferedReader breader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
            String lines ="";
            String str  = "";
            String json = "";
            while((lines = breader.readLine())!=null){
            	str = lines ;
            	json =json +str;
            }
    	}catch(Exception e){
    		e.printStackTrace();
    	}
	}
	public static void ModDept(String srcpath,String dstpath){
    	String token = (new EmailAction()).getToken();
    	HttpURLConnection httpConnection = null;
    	URL  urlcrm = null;
    	try{
    		urlcrm = new URL(crimp);
    		httpConnection = (HttpURLConnection)urlcrm.openConnection();
    		httpConnection.setDoOutput(true);
    		httpConnection.setDoInput(true);
            httpConnection.setRequestMethod("POST");
            httpConnection.setUseCaches(false);
            httpConnection.setInstanceFollowRedirects(true);
            System.out.println(token);
            httpConnection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            String params = "access_token="+token+"&action=3&srcpath="+srcpath+"&dstpath="+dstpath;
            System.out.println(params);
            OutputStreamWriter out = new OutputStreamWriter(httpConnection.getOutputStream(),"UTF-8");
            out.write(params);
            out.flush();
            out.close();
            httpConnection.getContentEncoding();
            java.io.InputStream in = httpConnection.getInputStream();
            BufferedReader breader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
            String lines ="";
            String str  = "";
            String json = "";
            while((lines = breader.readLine())!=null){
            	str = lines ;
            	json =json +str;
            }
    	}catch(Exception e){
    		e.printStackTrace();
    	}
	}
}
