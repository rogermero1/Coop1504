package ec.fin.online15.backend.webbanking.modelo.repositorios;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.fin.online15.backend.consultas.modelo.entidades.clientes.IdentidadCliente;
import ec.fin.online15.backend.consultas.modelo.entidades.clientes.InformacionCliente;
import ec.fin.online15.backend.librerias.repositorios.EAOSeed;
import ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades.GeneracionConvenio;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebConvenios;

@Stateless
@LocalBean
public class TwebConveniosEAO extends EAOSeed<TwebConvenios, Long> {

	@SuppressWarnings("unchecked")
	public List<TwebConvenios> consultaConvenioWebCliente(String cedula,
			String convenioWeb) {
		Query query = getEntityManager()
				.createNativeQuery(
						"SELECT a.* "
								+ "FROM   Tweb_Convenios a, Mg_Clientes b "
								+ "WHERE  a.Codigo_Cliente = b.Codigo_Cliente "
								+ "AND    a.Numero_Identificacion = ? "
								+ "AND    a.Convenio_Web = ? "
								+ "AND    a.Estado = 'A' "
								+ "AND    NOT EXISTS (SELECT 'x' "
								+ "        			  FROM   Tweb_Usuarios x, Tweb_Usuarios_Roles y "
								+ "        			  WHERE  x.Id = y.Id_Usuario "
								+ "        			  AND    x.Estado = 'A' "
								+ "        			  AND    y.Id_Rol IN (3) "
								+ "        			  AND    x.Codigo_Cliente = a.Codigo_Cliente)",
						TwebConvenios.class);
		query.setParameter(1, cedula);
		query.setParameter(2, convenioWeb);
		// List<ConvenioWebCliente> listaConvenioWebCLiente = new
		// ArrayList<ConvenioWebCliente>();
		// List<Object[]> listaResultado = query.getResultList();
		// for (Object[] elementoResultado : listaResultado) {
		// ConvenioWebCliente convenioWebCliente = new ConvenioWebCliente();
		// convenioWebCliente
		// .setCodigoCliente(((BigDecimal) elementoResultado[0])
		// .intValue());
		// convenioWebCliente.setNombreCliente((String) elementoResultado[1]);
		// listaConvenioWebCLiente.add(convenioWebCliente);
		// }
		// return listaConvenioWebCLiente;
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public TwebConvenios validaDatosCliente(String cedula, String correo,
			String celular) {
		Query query = getEntityManager()
				.createNativeQuery(
						"SELECT a.Codigo_Cliente, "
								+ "       Decode(a.Codigo_Tipo_Identificacion, "
								+ "              2, "
								+ "              a.Razon_Social, "
								+ "              a.Primer_Apellido || ' ' || a.Segundo_Apellido || ' ' || "
								+ "              a.Nombres) "
								+ "FROM   Mg_Clientes a, Mg_Direcciones b, Mg_Telefonos_x_Clte c "
								+ "WHERE  a.Codigo_Cliente = b.Codigo_Cliente "
								+ "AND    a.Codigo_Cliente = c.Codigo_Cliente "
								+ "AND    a.Numero_Identificacion = ? "
								+ "AND    upper(b.Email) = upper(?) "
								+ "AND    c.Numero = ? "
								+ "AND    NOT EXISTS "
								+ "   (SELECT * "
								+ "    FROM   Tweb_Usuarios x, Tweb_Usuarios_Roles y, Tweb_Roles z "
								+ "    WHERE  x.Id = y.Id_Usuario "
								+ "    AND    y.Id_Rol = z.Id "
								+ "    AND    z.Id = 3 "
								+ "    AND    x.Estado = 'A' "
								+ "    AND    x.Codigo_Cliente = a.Codigo_Cliente)");
		query.setParameter(1, cedula);
		query.setParameter(2, correo);
		query.setParameter(3, celular);
		List<Object[]> listaResultado = query.getResultList();

		TwebConvenios convenio = new TwebConvenios();
		for (Object[] elementoResultado : listaResultado) {
			convenio.setCodigoCliente(((BigDecimal) elementoResultado[0])
					.intValue());
			convenio.setNombreCliente((String) elementoResultado[1]);
			convenio.setNumeroIdentificacion(cedula);
		}

		query = getEntityManager().createNativeQuery(
				"SELECT 'x' FROM Mg_Clientes WHERE Numero_Identificacion = ?");
		query.setParameter(1, cedula);
		listaResultado = query.getResultList();
		convenio.setConvenioWeb(listaResultado.isEmpty() ? "N" : "S");

		query = getEntityManager()
				.createNativeQuery(
						"SELECT 'x' "
								+ "FROM   Tweb_Usuarios x, Tweb_Usuarios_Roles y, Tweb_Roles z "
								+ "WHERE  x.Id = y.Id_Usuario "
								+ "AND    y.Id_Rol = z.Id "
								+ "AND    z.Id = 3 " + "AND    x.Estado = 'A' "
								+ "AND    x.Codigo_Cliente = "
								+ "(SELECT Codigo_Cliente "
								+ "FROM   Mg_Clientes "
								+ "WHERE  Numero_Identificacion = ?)");
		query.setParameter(1, cedula);
		listaResultado = query.getResultList();
		convenio.setConvenioWeb(convenio.getConvenioWeb() + ";"
				+ (listaResultado.isEmpty() ? "N" : "S"));

		query = getEntityManager()
				.createNativeQuery(
						"SELECT Intentos, Ip "
								+ "FROM   Tweb_Bloqueos_x_Validacion "
								+ "WHERE  Estado = 'A' "
								+ "AND    Codigo_Cliente = (SELECT Codigo_Cliente "
								+ "                         FROM   Mg_Clientes "
								+ "                         WHERE  Numero_Identificacion = ?)");
		query.setParameter(1, cedula);
		listaResultado = query.getResultList();
		if (listaResultado.isEmpty()) {
			convenio.setConvenioWeb(convenio.getConvenioWeb() + ";" + "0");
		} else {
			convenio.setConvenioWeb(convenio.getConvenioWeb() + ";"
					+ ((BigDecimal) listaResultado.get(0)[0]).intValue());
		}

		return convenio;

	}

	@SuppressWarnings("unchecked")
	public IdentidadCliente validaIdentidadCliente(String cedula,
			String cuenta, Integer anioNacimiento, Date fechaUltimaTransaccion,
			Boolean tieneCredito, Boolean tieneInversion,
			Boolean recibeTransferencia) {
		SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
		Query query = getEntityManager()
				.createNativeQuery(
						"SELECT a.Codigo_Cliente, "
								+ "       Decode(a.Codigo_Tipo_Identificacion, "
								+ "              2, "
								+ "              a.Razon_Social, "
								+ "              a.Primer_Apellido || ' ' || a.Segundo_Apellido || ' ' || "
								+ "              a.Nombres) Nombre, "
								+ "       Nvl((SELECT 'S' "
								+ "           FROM   Ca_Cuentas_De_Ahorro x, Mg_Cuentas_x_Cliente b "
								+ "           WHERE  x.Numero_Cuenta = b.Codigo_Cuenta "
								+ "           AND    x.Situacion_En_Linea != 1 "
								+ "           AND    b.Clase_De_Cliente IN ('P', 'S') "
								//+ "           AND    b.Firmantes = 'S' "
								+ "           AND    ((b.Clase_De_Cliente = 'P' AND "
								+ "                 b.Codigo_Cliente = x.Codigo_Cliente) OR "
								+ "                 (b.Clase_De_Cliente = 'S' AND x.Clase_De_Cuenta = 2)) "
								+ "           AND    x.Numero_Cuenta = to_number(:cuenta) "
								+ "           AND    b.Codigo_Cliente = a.Codigo_Cliente  "
								+ "           ),'N') Respuesta_Cuenta, "
								// + "       Nvl((SELECT 'S' "
								// + "           FROM   Ca_Cuentas_De_Ahorro "
								// +
								// "           WHERE  Situacion_En_Linea != 1 "
								// +
								// "           AND    Codigo_Cliente = a.Codigo_Cliente "
								// +
								// "           AND    Numero_Cuenta = to_number(:cuenta)  ), "
								// + "           'N') Respuesta_Cuenta, "
								+ "       CASE "
								+ "         WHEN To_Number(To_Char(nvl(a.fecha_de_nacimiento,a.fecha_escritura_publica), 'rrrr')) = :anio /**/ THEN "
								+ "          'S' "
								+ "         ELSE "
								+ "          'N' "
								+ "       END Respuesta_Anio_Nacimiento, "
								+ "       CASE "
								+ "         WHEN (Nvl((SELECT MAX(Trunc(Fecha_Valida)) "
								+ "                    FROM   Ca_Movimientos_Diarios "
								+ "                    WHERE  codigo_tipo_transaccion in (21,52)"
								+ "                    AND    Numero_Cuenta = to_number(:cuenta) /**/ ), "
								+ "                   (SELECT MAX(Trunc(Fecha_Valida)) "
								+ "                    FROM   Ca_Movimientos_Mensuales "
								+ "                    WHERE  codigo_tipo_transaccion in (21,52)"
								+ "                    AND    Numero_Cuenta = to_number(:cuenta) /**/ ))"
								+ "               = To_Date(:fecha /**/, 'yyyy-mm-dd')) THEN "
								+ "          'S' "
								+ "         ELSE "
								+ "          'N' "
								+ "       END Respuesta_Ultima_Transaccion, "
								+ "       CASE "
								+ "         WHEN ((CASE "
								+ "                WHEN (SELECT COUNT(*) "
								+ "                      FROM   Pr_Prestamos "
								+ "                      WHERE  Estado = '0' "
								+ "                      AND    Codigo_Cliente = a.Codigo_Cliente) > 0 THEN "
								+ "                 'S' "
								+ "                ELSE "
								+ "                 'N' "
								+ "              END) = :credito /**/ ) THEN "
								+ "          'S' "
								+ "         ELSE "
								+ "          'N' "
								+ "       END Repuesta_Prestamo, "
								+ "       CASE "
								+ "         WHEN ((CASE "
								+ "                WHEN (SELECT COUNT(*) "
								+ "                      FROM   Dp_Depositos_Plazos "
								+ "                      WHERE  Estado_Vigencia IN (1, 3) "
								+ "                      AND    Codigo_Propietario_Principal = a.Codigo_Cliente) > 0 THEN "
								+ "                 'S' "
								+ "                ELSE "
								+ "                 'N' "
								+ "              END) = :inversion /**/ ) THEN "
								+ "          'S' "
								+ "         ELSE "
								+ "          'N' "
								+ "       END Respuesta_Inversion, "
								+ "       CASE "
								+ "         WHEN ((CASE "
								+ "                WHEN (((SELECT COUNT(*) "
								+ "                        FROM   Ca_Movimientos_Diarios "
								+ "                        WHERE  Codigo_Tipo_Transaccion = 47 "
								+ "                        AND    Numero_Cuenta = to_number(:cuenta) /**/ ) + "
								+ "                     (SELECT COUNT(*) "
								+ "                        FROM   Ca_Movimientos_Mensuales "
								+ "                        WHERE  Codigo_Tipo_Transaccion = 47 "
								+ "                        AND    Fecha_Valida BETWEEN Add_Months(Trunc(SYSDATE), -3) AND "
								+ "                               Trunc(SYSDATE) "
								+ "                        AND    Numero_Cuenta = to_number(:cuenta) /**/ )) > 0) THEN "
								+ "                 'S' "
								+ "                ELSE "
								+ "                 'N' "
								+ "              END) = :transferencia /**/ ) THEN "
								+ "          'S' "
								+ "         ELSE "
								+ "          'N' "
								+ "       END Respuesta_Transferencia "
								+ "FROM   Mg_Clientes a "
								+ "WHERE  a.Numero_Identificacion = :cedula /**/ "
								+ "AND    NOT EXISTS "
								+ " (SELECT * "
								+ "        FROM   Tweb_Usuarios x, Tweb_Usuarios_Roles y, Tweb_Roles z "
								+ "        WHERE  x.Id = y.Id_Usuario "
								+ "        AND    y.Id_Rol = z.Id "
								+ "        AND    z.Id = 3 "
								+ "        AND    x.Estado = 'A' "
								+ "        AND    x.Codigo_Cliente = a.Codigo_Cliente)");
		query.setParameter("cuenta", cuenta);
		query.setParameter("anio", anioNacimiento == null ? 0 : anioNacimiento);
		query.setParameter("fecha", formatoDeFecha
				.format(fechaUltimaTransaccion == null ? new Date()
						: fechaUltimaTransaccion));
		query.setParameter("credito", tieneCredito ? "S" : "N");
		query.setParameter("inversion", tieneInversion ? "S" : "N");
		query.setParameter("transferencia", recibeTransferencia ? "S" : "N");
		query.setParameter("cedula", cedula);

		List<Object[]> listaResultado = query.getResultList();

		IdentidadCliente cliente = null;
		for (Object[] elementoResultado : listaResultado) {
			cliente = new IdentidadCliente(
					((BigDecimal) elementoResultado[0]).intValue(),
					(String) elementoResultado[1],
					String.valueOf(elementoResultado[2]),
					String.valueOf(elementoResultado[3]),
					String.valueOf(elementoResultado[4]),
					String.valueOf(elementoResultado[5]),
					String.valueOf(elementoResultado[6]),
					String.valueOf(elementoResultado[7]));
		}

		return cliente;
	}

	public TwebConvenios obtenerConvenio(String convenioWeb,
			Integer codigoCliente) {
		Query query = getEntityManager()
				.createNativeQuery(
						"SELECT * FROM Tweb_Convenios WHERE Convenio_Web = ? AND Codigo_Cliente = ? AND Estado = 'A'",
						TwebConvenios.class);
		query.setParameter(1, convenioWeb);
		query.setParameter(2, codigoCliente);
		try {
			return (TwebConvenios) query.getSingleResult();
		} catch (NoResultException error) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<InformacionCliente> consultaCliente(String cedula) {
		Query query = getEntityManager()
				.createNativeQuery(
						"SELECT Codigo_Cliente,"
								+ "       Nombres || ' ' || Primer_Apellido || ' ' || Segundo_Apellido Nombres, "
								+ "       Nvl((SELECT MAX(Substr(Nvl(Nvl((SELECT Nombre "
								+ "                                     FROM   Mg_Municipios "
								+ "                                     WHERE  Codigo_Departamento = z.Codigo_Departamento "
								+ "                                     AND    Codigo_Municipio = z.Codigo_Municipio), "
								+ "                                     'SN') || ', ' || "
								+ "                                 Nvl((SELECT Descripcion "
								+ "                                     FROM   Mg_Parroquias "
								+ "                                     WHERE  Codigo_Departamento = z.Codigo_Departamento "
								+ "                                     AND    Codigo_Municipio = z.Codigo_Municipio "
								+ "                                     AND    Codigo_Parroquia = z.Parroquia), "
								+ "                                     'SN') || ', ' || "
								+ "                                 Nvl((SELECT Nombre "
								+ "                                     FROM   Mg_Barrios "
								+ "                                     WHERE  Codigo_Barrio = z.Barrio "
								+ "                                     AND    Codigo_Departamento = z.Codigo_Departamento "
								+ "                                     AND    Codigo_Municipio = z.Codigo_Municipio "
								+ "                                     AND    Codigo_Parroquia = z.Parroquia), "
								+ "                                     'SN') || ' ' || z.Calle || '-' || "
								+ "                                 z.Nomenclatura, "
								+ "                                 'NO REGISTRA'), "
								+ "                             1, "
								+ "                             200)) "
								+ "           FROM   Mg_Direcciones z "
								+ "           WHERE  Codigo_Tipo_Direccion = 1 "
								+ "           AND    Codigo_Cliente = a.Codigo_Cliente), "
								+ "           'NO REGISTRA'), "
								+ "       Nvl(Sc_f_Telefonos(a.Codigo_Cliente, 1, NULL), 'NO REGISTRA') "
								+ "FROM   Mg_Clientes a "
								+ "WHERE  Numero_Identificacion = ? "
								+ "AND    NOT EXISTS (SELECT 'x' "
								+ "        			  FROM   Tweb_Usuarios x, Tweb_Usuarios_Roles y "
								+ "        			  WHERE  x.Id = y.Id_Usuario "
								+ "        			  AND    x.Estado = 'A' "
								+ "        			  AND    y.Id_Rol IN (3) "
								+ "        			  AND    x.Codigo_Cliente = a.Codigo_Cliente "
								+ "        			  UNION ALL "
								+ "        			  SELECT 'x' "
								+ "        			  FROM   Tweb_Convenios "
								+ "        			  WHERE  Estado = 'A' "
								+ "        			  AND    Codigo_Cliente = a.Codigo_Cliente)");
		query.setParameter(1, cedula);
		List<InformacionCliente> listaInformacionCliente = new ArrayList<InformacionCliente>();
		List<Object[]> listaResultado = query.getResultList();
		for (Object[] elementoResultado : listaResultado) {
			InformacionCliente informacionCliente = new InformacionCliente();
			informacionCliente
					.setCodigoCliente(((BigDecimal) elementoResultado[0])
							.intValue());
			informacionCliente.setNombreCliente((String) elementoResultado[1]);
			informacionCliente.setDireccion((String) elementoResultado[2]);
			informacionCliente.setTelefonos((String) elementoResultado[3]);
			listaInformacionCliente.add(informacionCliente);
		}
		return listaInformacionCliente;
	}

	public String generacionNumeroConvenio() {
		GeneracionConvenio generacionConvenio = new GeneracionConvenio();
		this.invocacionJDBC(generacionConvenio);
		return generacionConvenio.getNumeroConvenio();
	}

	@SuppressWarnings("unchecked")
	public List<TwebConvenios> conveniosVigentesPorCliente(Integer codigoCliente) {
		Query query = getEntityManager()
				.createNativeQuery(
						"SELECT * FROM Tweb_Convenios WHERE Estado = 'A' AND Codigo_Cliente = ?",
						TwebConvenios.class);
		query.setParameter(1, codigoCliente);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<TwebConvenios> conveniosVigentes() {
		Query query = getEntityManager()
				.createNativeQuery(
						"SELECT * FROM Tweb_Convenios a WHERE Estado = 'A' ORDER BY Nombre_Cliente",
						TwebConvenios.class);
		return query.getResultList();
	}
}
