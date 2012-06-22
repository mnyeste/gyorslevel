package com.gyorslevel.it;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Our representative annotation. Can be added on Method level.  
 * 
 * @author dave00
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestResource {

    ResourceType[] resourceTypes();

}
