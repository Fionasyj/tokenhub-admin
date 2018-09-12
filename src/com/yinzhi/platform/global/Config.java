package com.yinzhi.platform.global;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class Config {
	public static String debug;
	public static String page_size;
	public static String page_list;
	
	static {   
        Properties prop = new Properties();   
        InputStream in = Config.class.getResourceAsStream("/global.properties");   
        try {   
            prop.load(in);   
            debug = prop.getProperty("debug").trim();  
            page_size = prop.getProperty("page_size").trim(); 
            page_list = prop.getProperty("page_list").trim(); 
            
        } catch (IOException e) {   
            e.printStackTrace();   
        }   
    }
}
