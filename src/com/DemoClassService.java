/**
 * DemoClassService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com;

public interface DemoClassService extends javax.xml.rpc.Service {
    public java.lang.String getDemoClassAddress();

    public com.DemoClass getDemoClass() throws javax.xml.rpc.ServiceException;

    public com.DemoClass getDemoClass(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
