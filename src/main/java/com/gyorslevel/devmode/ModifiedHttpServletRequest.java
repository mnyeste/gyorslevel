package com.gyorslevel.devmode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class ModifiedHttpServletRequest extends HttpServletRequestWrapper {

    public static final String DEFAULT_LANG = "hu";
    public static final String HEADER_FIELD = "X-App-Lang";
    
	
	public ModifiedHttpServletRequest(HttpServletRequest request) {
		super(request);
	}
	
	public String getHeader(String fieldName) {

		HttpServletRequest request = (HttpServletRequest)getRequest();
		
		if(fieldName.equals(HEADER_FIELD)) {
			return DEFAULT_LANG;
		}
		
		return request.getHeader(fieldName);
	}
	
	public Enumeration getHeaderNames() {
		
		List<String> list = new ArrayList<String>();
		
		HttpServletRequest request = (HttpServletRequest)getRequest();
		Enumeration e = request.getHeaderNames();

		while(e.hasMoreElements()) {
			list.add((String)e.nextElement());
		}
		
		list.add(HEADER_FIELD);
		
		Enumeration en = Collections.enumeration(list);
		return en;
	}

}
