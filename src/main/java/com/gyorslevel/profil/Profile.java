package com.gyorslevel.profil;

/**
 * Domain object representing a profile which is made up of
 * an domain for the given node (or for the request if multiple
 * domains are active for the node) and a LANG
 * @author dave00
 */
public class Profile {
    
    private String lang;
    private String domain;

    public Profile(String lang, String domain) {
        this.lang = lang;
        this.domain = domain;
    }
    
    public String getLang() {
        return lang;
    }

    public String getDomain() {
        return domain;
    }

    @Override
    public String toString() {
        return "Profile{" + "lang=" + lang + ", domain=" + domain + '}';
    }
    
}
