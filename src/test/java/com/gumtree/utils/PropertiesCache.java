package com.gumtree.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class PropertiesCache
{
	webGenericLibrary webGenericLibrary = new webGenericLibrary();
    private static Map<String, Properties> map = new HashMap<String, Properties>();

    public PropertiesCache(List<String> fileNames)
    {
        for (String f : fileNames)
        {
            Properties props = new Properties();
            try {
            	props.load(webGenericLibrary.loadPropertiesPath(f));
            	map.put(f, props);
            }catch (IOException ex){
            	System.out.println(ex.getMessage());
                // handle error
            }
            
        }

    }

    public static String getProperty(String file, String key) {
        Properties props = map.get(file);
        
        if (props != null) {
        	
            return props.getProperty(key);
        }

        return null;
    }
}
