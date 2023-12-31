
package com.cooperativa15abril.facilito.controladorsoap;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "ExcepcionServicio", targetNamespace = "http://controladorsoap.facilito.cooperativa15abril.com/")
public class ExcepcionServicio_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private ExcepcionServicio faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public ExcepcionServicio_Exception(String message, ExcepcionServicio faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public ExcepcionServicio_Exception(String message, ExcepcionServicio faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.cooperativa15abril.facilito.controladorsoap.ExcepcionServicio
     */
    public ExcepcionServicio getFaultInfo() {
        return faultInfo;
    }

}
