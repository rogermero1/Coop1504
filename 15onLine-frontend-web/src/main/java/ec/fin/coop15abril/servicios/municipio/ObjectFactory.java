
package ec.fin.coop15abril.servicios.municipio;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ec.fin.coop15abril.servicios.municipio package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Consulta_QNAME = new QName("http://municipio.servicios.coop15abril.fin.ec/", "consulta");
    private final static QName _Pago_QNAME = new QName("http://municipio.servicios.coop15abril.fin.ec/", "pago");
    private final static QName _ConsultaResumenResponse_QNAME = new QName("http://municipio.servicios.coop15abril.fin.ec/", "consultaResumenResponse");
    private final static QName _PagoResumen_QNAME = new QName("http://municipio.servicios.coop15abril.fin.ec/", "pagoResumen");
    private final static QName _PagoResumenResponse_QNAME = new QName("http://municipio.servicios.coop15abril.fin.ec/", "pagoResumenResponse");
    private final static QName _Error_QNAME = new QName("http://municipio.servicios.coop15abril.fin.ec/", "Error");
    private final static QName _ConsultaResumen_QNAME = new QName("http://municipio.servicios.coop15abril.fin.ec/", "consultaResumen");
    private final static QName _Resultado_QNAME = new QName("http://municipio.servicios.coop15abril.fin.ec/", "resultado");
    private final static QName _PagoResponse_QNAME = new QName("http://municipio.servicios.coop15abril.fin.ec/", "pagoResponse");
    private final static QName _ConsultaResponse_QNAME = new QName("http://municipio.servicios.coop15abril.fin.ec/", "consultaResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ec.fin.coop15abril.servicios.municipio
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ResultadoConsultaResumen }
     * 
     */
    public ResultadoConsultaResumen createResultadoConsultaResumen() {
        return new ResultadoConsultaResumen();
    }

    /**
     * Create an instance of {@link PagoIn }
     * 
     */
    public PagoIn createPagoIn() {
        return new PagoIn();
    }

    /**
     * Create an instance of {@link ResultadoConsulta }
     * 
     */
    public ResultadoConsulta createResultadoConsulta() {
        return new ResultadoConsulta();
    }

    /**
     * Create an instance of {@link PagoResponse }
     * 
     */
    public PagoResponse createPagoResponse() {
        return new PagoResponse();
    }

    /**
     * Create an instance of {@link ConsultaResponse }
     * 
     */
    public ConsultaResponse createConsultaResponse() {
        return new ConsultaResponse();
    }

    /**
     * Create an instance of {@link ConsultaResumen }
     * 
     */
    public ConsultaResumen createConsultaResumen() {
        return new ConsultaResumen();
    }

    /**
     * Create an instance of {@link PagoResumen }
     * 
     */
    public PagoResumen createPagoResumen() {
        return new PagoResumen();
    }

    /**
     * Create an instance of {@link PagoResumenResponse }
     * 
     */
    public PagoResumenResponse createPagoResumenResponse() {
        return new PagoResumenResponse();
    }

    /**
     * Create an instance of {@link Error }
     * 
     */
    public Error createError() {
        return new Error();
    }

    /**
     * Create an instance of {@link ConsultaResumenResponse }
     * 
     */
    public ConsultaResumenResponse createConsultaResumenResponse() {
        return new ConsultaResumenResponse();
    }

    /**
     * Create an instance of {@link Consulta }
     * 
     */
    public Consulta createConsulta() {
        return new Consulta();
    }

    /**
     * Create an instance of {@link Pago }
     * 
     */
    public Pago createPago() {
        return new Pago();
    }

    /**
     * Create an instance of {@link PagoInResumen }
     * 
     */
    public PagoInResumen createPagoInResumen() {
        return new PagoInResumen();
    }

    /**
     * Create an instance of {@link Rubro }
     * 
     */
    public Rubro createRubro() {
        return new Rubro();
    }

    /**
     * Create an instance of {@link PagoOut }
     * 
     */
    public PagoOut createPagoOut() {
        return new PagoOut();
    }

    /**
     * Create an instance of {@link Titulo }
     * 
     */
    public Titulo createTitulo() {
        return new Titulo();
    }

    /**
     * Create an instance of {@link ResultadoConsultaResumen.Detalle }
     * 
     */
    public ResultadoConsultaResumen.Detalle createResultadoConsultaResumenDetalle() {
        return new ResultadoConsultaResumen.Detalle();
    }

    /**
     * Create an instance of {@link PagoIn.Titulos }
     * 
     */
    public PagoIn.Titulos createPagoInTitulos() {
        return new PagoIn.Titulos();
    }

    /**
     * Create an instance of {@link ResultadoConsulta.Detalle }
     * 
     */
    public ResultadoConsulta.Detalle createResultadoConsultaDetalle() {
        return new ResultadoConsulta.Detalle();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Consulta }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://municipio.servicios.coop15abril.fin.ec/", name = "consulta")
    public JAXBElement<Consulta> createConsulta(Consulta value) {
        return new JAXBElement<Consulta>(_Consulta_QNAME, Consulta.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Pago }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://municipio.servicios.coop15abril.fin.ec/", name = "pago")
    public JAXBElement<Pago> createPago(Pago value) {
        return new JAXBElement<Pago>(_Pago_QNAME, Pago.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaResumenResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://municipio.servicios.coop15abril.fin.ec/", name = "consultaResumenResponse")
    public JAXBElement<ConsultaResumenResponse> createConsultaResumenResponse(ConsultaResumenResponse value) {
        return new JAXBElement<ConsultaResumenResponse>(_ConsultaResumenResponse_QNAME, ConsultaResumenResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PagoResumen }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://municipio.servicios.coop15abril.fin.ec/", name = "pagoResumen")
    public JAXBElement<PagoResumen> createPagoResumen(PagoResumen value) {
        return new JAXBElement<PagoResumen>(_PagoResumen_QNAME, PagoResumen.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PagoResumenResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://municipio.servicios.coop15abril.fin.ec/", name = "pagoResumenResponse")
    public JAXBElement<PagoResumenResponse> createPagoResumenResponse(PagoResumenResponse value) {
        return new JAXBElement<PagoResumenResponse>(_PagoResumenResponse_QNAME, PagoResumenResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Error }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://municipio.servicios.coop15abril.fin.ec/", name = "Error")
    public JAXBElement<Error> createError(Error value) {
        return new JAXBElement<Error>(_Error_QNAME, Error.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaResumen }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://municipio.servicios.coop15abril.fin.ec/", name = "consultaResumen")
    public JAXBElement<ConsultaResumen> createConsultaResumen(ConsultaResumen value) {
        return new JAXBElement<ConsultaResumen>(_ConsultaResumen_QNAME, ConsultaResumen.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://municipio.servicios.coop15abril.fin.ec/", name = "resultado")
    public JAXBElement<Object> createResultado(Object value) {
        return new JAXBElement<Object>(_Resultado_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PagoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://municipio.servicios.coop15abril.fin.ec/", name = "pagoResponse")
    public JAXBElement<PagoResponse> createPagoResponse(PagoResponse value) {
        return new JAXBElement<PagoResponse>(_PagoResponse_QNAME, PagoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://municipio.servicios.coop15abril.fin.ec/", name = "consultaResponse")
    public JAXBElement<ConsultaResponse> createConsultaResponse(ConsultaResponse value) {
        return new JAXBElement<ConsultaResponse>(_ConsultaResponse_QNAME, ConsultaResponse.class, null, value);
    }

}
