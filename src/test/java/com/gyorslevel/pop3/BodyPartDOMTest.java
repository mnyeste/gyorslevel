package com.gyorslevel.pop3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

public class BodyPartDOMTest {

    @Test
    public void testProcessBodyParts()
    {
        
        Pop3EmailFetcher.BodyPartDOM dom = new Pop3EmailFetcher.BodyPartDOM();
        
        List<Pop3EmailFetcher.MyBodyPart> bodyParts = new ArrayList<Pop3EmailFetcher.MyBodyPart>();
        List<String> bodyPartList = Arrays.asList(new String[]{"<html><head><meta http-equiv=\"content-type\" content=\"text/html; charset=ISO-8859-1\" /></head><body text=\"#000000\" bgcolor=\"#FFFFFF\"><img src=\"cid:part1.08020903.00070500@localhost\" alt=\"\" /></body></html>"});
        
        for (String string : bodyPartList) {
            bodyParts.add(new Pop3EmailFetcher.MyBodyPart(string, true));
        }
        
        bodyParts.add(new Pop3EmailFetcher.MyBodyPart("ShouldNotBeIncluded", false));
        
        dom.bodyParts = bodyParts;
        
        Map<String, String> mappedFiles = new HashMap<String, String>();
        mappedFiles.put("cid:part1.08020903.00070500@localhost", "/tmp/izeke.png");
        dom.mappedFiles = mappedFiles;
        
        String result = dom.processBodyParts(true);
        Assert.assertEquals("<html><head><meta http-equiv=\"content-type\" content=\"text/html; charset=ISO-8859-1\" /></head><body text=\"#000000\" bgcolor=\"#FFFFFF\"><img src=\"/tmp/izeke.png\" alt=\"\" /></body></html>", result);
        
    }
    
}
