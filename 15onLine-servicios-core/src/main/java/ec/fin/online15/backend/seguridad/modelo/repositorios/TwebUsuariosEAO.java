package ec.fin.online15.backend.seguridad.modelo.repositorios;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.fin.online15.backend.consultas.modelo.entidades.clientes.Empleado;
import ec.fin.online15.backend.consultas.modelo.entidades.clientes.PreguntaSecreta;
import ec.fin.online15.backend.consultas.modelo.entidades.clientes.UsuarioMigrado;
import ec.fin.online15.backend.librerias.repositorios.EAOSeed;
import ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades.ConfirmacionOtp;
import ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades.EnvioOtp;
import ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades.Notificacion;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuario;

@Stateless
@LocalBean
public class TwebUsuariosEAO extends EAOSeed<TwebUsuario, Long> {

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public TwebUsuario obtenerUsuarioBase(String usuario) {
		Query query = getEntityManager().createNativeQuery(
				"select * from TWEB_usuarios t where t.estado='A' and upper(t.usuario) = ? ", TwebUsuario.class);
		query.setParameter(1, usuario);
		try {
			return (TwebUsuario) query.getSingleResult();
		} catch (NoResultException error) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<TwebUsuario> listaUsuariosVigentes() {
		Query query = getEntityManager().createNativeQuery("select a.* from TWEB_usuarios a where a.estado = 'A'",
				TwebUsuario.class);
		try {
			return query.getResultList();
		} catch (NoResultException error) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<TwebUsuario> listaUsuariosInternos() {
		Query query = getEntityManager().createNativeQuery("SELECT a.* "
				+ "FROM   Tweb_Usuarios a, Tweb_Usuarios_Roles b " + "WHERE  a.Estado = 'A' " + "AND    b.Estado = 'A' "
				+ "AND    b.Id_Usuario = a.Id " + "AND    b.Id_Rol != 3 " + "ORDER  BY Usuario", TwebUsuario.class);
		try {
			return query.getResultList();
		} catch (NoResultException error) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<TwebUsuario> listaUsuariosWeb() {
		Query query = getEntityManager().createNativeQuery("SELECT a.* "
				+ "FROM   Tweb_Usuarios a, Tweb_Usuarios_Roles b " + "WHERE  a.Estado = 'A' " + "AND    b.Estado = 'A' "
				+ "AND    b.Id_Usuario = a.Id " + "AND    b.Id_Rol =3 " + "ORDER  BY Usuario", TwebUsuario.class);
		try {
			return query.getResultList();
		} catch (NoResultException error) {
			return null;
		}
	}

	public TwebUsuario consultaUsuarioPorNumeroIdentificacion(String identificacion) {
		Query query = getEntityManager().createNativeQuery("SELECT a.* "
				+ "FROM   Tweb_Usuarios a, Tweb_Usuarios_Roles b, Mg_Clientes c " + "WHERE  a.Id = b.Id_Usuario "
				+ "AND    a.Codigo_Cliente = c.Codigo_Cliente " + "AND    a.Estado = 'A' " + "AND    b.Estado = 'A' "
				+ "AND    b.Id_Rol = 3 " + "AND    c.Numero_Identificacion = ? ", TwebUsuario.class);
		query.setParameter(1, identificacion);
		try {
			return (TwebUsuario) query.getSingleResult();
		} catch (NoResultException error) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Empleado> consultaEmpleadoPorCedula(String numeroIdentificacion) {
		Query query = getEntityManager().createNativeQuery("SELECT Codigo_Cliente, Numero_Identificacion, "
				+ "Primer_Apellido || ' ' || Segundo_Apellido || ' ' || Nombres " + "FROM   Rp_Empleados "
				+ "WHERE  Estado = 'A' " + "AND Numero_Identificacion = ? ");
		query.setParameter(1, numeroIdentificacion);
		List<Empleado> listaEmpleado = new ArrayList<Empleado>();
		List<Object[]> listaResultado = query.getResultList();
		for (Object[] elementoResultado : listaResultado) {
			Empleado empleado = new Empleado();
			empleado.setCodigoCliente(((BigDecimal) elementoResultado[0]).intValue());
			empleado.setNumeroIdentificacion((String) elementoResultado[1]);
			empleado.setNombres((String) elementoResultado[2]);
			listaEmpleado.add(empleado);
		}
		return listaEmpleado;
	}

	@SuppressWarnings("unchecked")
	public List<Empleado> consultaEmpleadoPorCodigoCliente(Integer codigoCliente) {
		Query query = getEntityManager().createNativeQuery("SELECT Codigo_Cliente, Numero_Identificacion, "
				+ "Primer_Apellido || ' ' || Segundo_Apellido || ' ' || Nombres " + "FROM   Rp_Empleados "
				+ "WHERE Codigo_Cliente = ? ");
		query.setParameter(1, codigoCliente);
		List<Empleado> listaEmpleado = new ArrayList<Empleado>();
		List<Object[]> listaResultado = query.getResultList();
		for (Object[] elementoResultado : listaResultado) {
			Empleado empleado = new Empleado();
			empleado.setCodigoCliente(((BigDecimal) elementoResultado[0]).intValue());
			empleado.setNumeroIdentificacion((String) elementoResultado[1]);
			empleado.setNombres((String) elementoResultado[2]);
			listaEmpleado.add(empleado);
		}
		return listaEmpleado;
	}

	@SuppressWarnings("unchecked")
	public List<Empleado> consultaClientePorCodigoCliente(Integer codigoCliente) {

		if (codigoCliente == null) {
			return new ArrayList<Empleado>();
		}

		Query query = getEntityManager().createNativeQuery("SELECT Codigo_Cliente, Numero_Identificacion, "
				+ "decode(codigo_tipo_identificacion, 2, razon_social, Primer_Apellido || ' ' || Segundo_Apellido || ' ' || Nombres ) "
				+ "FROM   Mg_Clientes " + "WHERE Codigo_Cliente = ? ");
		query.setParameter(1, codigoCliente);
		List<Empleado> listaEmpleado = new ArrayList<Empleado>();
		List<Object[]> listaResultado = query.getResultList();
		for (Object[] elementoResultado : listaResultado) {
			Empleado empleado = new Empleado();
			empleado.setCodigoCliente(((BigDecimal) elementoResultado[0]).intValue());
			empleado.setNumeroIdentificacion((String) elementoResultado[1]);
			empleado.setNombres((String) elementoResultado[2]);
			listaEmpleado.add(empleado);
		}
		return listaEmpleado;
	}

	@SuppressWarnings("unchecked")
	public List<Empleado> consultaClientePorNumeroIdentificacion(String identificacion) {
		Query query = getEntityManager().createNativeQuery("SELECT Codigo_Cliente, Numero_Identificacion, "
				+ "decode(codigo_tipo_identificacion, 2, razon_social, Primer_Apellido || ' ' || Segundo_Apellido || ' ' || Nombres ) "
				+ "FROM   Mg_Clientes " + "WHERE Numero_Identificacion = ? ");
		query.setParameter(1, identificacion);
		List<Empleado> listaEmpleado = new ArrayList<Empleado>();
		List<Object[]> listaResultado = query.getResultList();
		for (Object[] elementoResultado : listaResultado) {
			Empleado empleado = new Empleado();
			empleado.setCodigoCliente(((BigDecimal) elementoResultado[0]).intValue());
			empleado.setNumeroIdentificacion((String) elementoResultado[1]);
			empleado.setNombres((String) elementoResultado[2]);
			listaEmpleado.add(empleado);
		}
		return listaEmpleado;
	}

	public Integer compruebaExistenciaUsuario(String usuario) {
		Query query = getEntityManager()
				.createNativeQuery("select count(*) from TWEB_usuarios t where upper(t.usuario) = ? ");
		query.setParameter(1, usuario);
		Number numero = (Number) query.getSingleResult();
		return Integer.valueOf(numero.intValue());
	}

	public String envioOtp(Integer codigoCliente, String telefono, String correo, String otpsIguales,
			String observacion) {
		EnvioOtp envioOtp = new EnvioOtp(codigoCliente, telefono, correo, otpsIguales, observacion);
		this.invocacionJDBC(envioOtp);
		return envioOtp.getError();
	}

	public Integer confirmacionOtp(Integer codigoCliente, String otpCorreo, String otpCelular) {
		ConfirmacionOtp confirmacionOtp = new ConfirmacionOtp(codigoCliente, otpCorreo, otpCelular);
		this.invocacionJDBC(confirmacionOtp);
		return confirmacionOtp.getResultado();
	}

	public String notificacion(Integer codigoCliente, String telefono, String correo, Integer plantilla,
			List<String> parametros) {
		String parametrosNotificacion = "";
		for (String parametro : parametros) {
			parametrosNotificacion += parametro + ";";
		}
		parametrosNotificacion = parametrosNotificacion.length() > 1
				? parametrosNotificacion.substring(0, parametrosNotificacion.length() - 1)
				: parametrosNotificacion;
		Notificacion notificacion = new Notificacion(codigoCliente, telefono, correo, plantilla,
				parametrosNotificacion);
		this.invocacionJDBC(notificacion);
		return notificacion.getError();
	}

	@SuppressWarnings("unchecked")
	public List<PreguntaSecreta> consultaPreguntaSecreta(String nombreUsuario) {
		Query query = getEntityManager().createNativeQuery("SELECT c.Id             Id_Pregunta,"
				+ "       a.Codigo_Cliente," + "       c.Descripcion    Pregunta,"
				+ "       b.Descripcion    Respuesta," + "       a.Correo," + "       a.Celular,"
				+ "       a.Bloqueado " + "FROM   Tweb_Usuarios a, Tweb_Respuesta_Usuarios b, Tweb_Banco_Preguntas c "
				+ "WHERE  Upper(a.Usuario) = Upper(?) " + "AND    a.Id = b.Id_Usuario "
				+ "AND    b.Id_Banco_Pregunta = c.Id " + "AND    c.Id = (SELECT Id "
				+ "               FROM   (SELECT x.Id, y.Id_Usuario " + "                       FROM   (SELECT Id "
				+ "                               FROM   Tweb_Banco_Preguntas "
				+ "                               WHERE  Estado = 'A') x, "
				+ "                              Tweb_Respuesta_Usuarios y "
				+ "                       WHERE  x.Id = y.Id_Banco_Pregunta "
				+ "                       AND    y.Estado = 'A' "
				+ "                       ORDER  BY Dbms_Random.Value)" + "               WHERE  Id_Usuario = a.Id "
				+ "               AND    Rownum = 1) " + "AND    a.Estado = 'A' " + "AND    b.Estado = 'A' "
				+ "AND    c.Estado = 'A'");
		query.setParameter(1, nombreUsuario);
		List<PreguntaSecreta> listaPreguntaSecreta = new ArrayList<PreguntaSecreta>();
		List<Object[]> listaResultado = query.getResultList();
		for (Object[] elementoResultado : listaResultado) {
			PreguntaSecreta preguntaSecreta = new PreguntaSecreta();
			preguntaSecreta.setIdPregunta(((BigDecimal) elementoResultado[0]).intValue());
			preguntaSecreta.setCodigoCliente(((BigDecimal) elementoResultado[1]).intValue());
			preguntaSecreta.setPregunta((String) elementoResultado[2]);
			preguntaSecreta.setRespuesta((String) elementoResultado[3]);
			preguntaSecreta.setCorreo((String) elementoResultado[4]);
			preguntaSecreta.setCelular((String) elementoResultado[5]);
			preguntaSecreta.setBloqueado((String) elementoResultado[6]);
			listaPreguntaSecreta.add(preguntaSecreta);
		}
		return listaPreguntaSecreta;
	}

	// @SuppressWarnings("unchecked")
	// public List<TwebIpsAutorizadas> consultaIpAutorizada(TwebUsuario usuario,
	// String ip) {
	// Query query = getEntityManager().createNativeQuery(
	// "SELECT b.* "
	// + "FROM Tweb_Usuarios a, Tweb_Ips_Autorizadas b "
	// + "WHERE a.Id = b.Id_Usuario "
	// + "AND a.Estado = 'A' " + "AND b.Estado = 'A' "
	// + "AND a.id = ? " + "AND b.Ip = ?");
	// query.setParameter(1, usuario.getId());
	// query.setParameter(2, ip);
	// return query.getResultList();
	// }

	@SuppressWarnings("unchecked")
	public UsuarioMigrado consultaUsuarioMigrado(String usuario) {
		Query query = getEntityManager().createNativeQuery("SELECT a.Codigo_Usuario, "
				+ "       a.Direccion_Correo_Electronico, " + "       a.Codigo_Interviniente, "
				+ "       b.Numero_Identificacion, " + "       Decode(b.Codigo_Tipo_Identificacion, "
				+ "              2, " + "              b.Razon_Social, "
				+ "              b.Nombres || ' ' || b.Primer_Apellido || ' ' || b.Segundo_Apellido) "
				+ "FROM   Tsl_Usuario a, Mg_Clientes b " + "WHERE  a.Codigo_Interviniente = b.Codigo_Cliente "
				+ "AND    a.Fecha_Vencimiento = To_Date('31-12-2999', 'dd/mm/yyyy') "
				+ "AND    a.Codigo_Tipo_Usuario = 'WBK' " + "AND    a.Codigo_Usuario = ? " + "AND    NOT EXISTS "
				+ " (SELECT 'x' " + "        FROM   Tweb_Usuarios x, Tweb_Usuarios_Roles y "
				+ "        WHERE  x.Id = y.Id_Usuario " + "        AND    x.Estado IN ('A', 'I') "
				+ "        AND    y.Id_Rol IN (3) " + "        AND    x.Codigo_Cliente = a.Codigo_Interviniente)");
		query.setParameter(1, usuario);
		List<UsuarioMigrado> listaUsuarioMigrado = new ArrayList<UsuarioMigrado>();
		List<Object[]> listaResultado = query.getResultList();
		for (Object[] elementoResultado : listaResultado) {
			UsuarioMigrado usuarioMigrado = new UsuarioMigrado();
			usuarioMigrado.setUsuario((String) elementoResultado[0]);
			usuarioMigrado.setCorreo((String) elementoResultado[1]);
			usuarioMigrado.setCodigoCliente(((BigDecimal) elementoResultado[2]).intValue());
			usuarioMigrado.setNumeroIdentificacion((String) elementoResultado[3]);
			usuarioMigrado.setNombres((String) elementoResultado[4]);
			listaUsuarioMigrado.add(usuarioMigrado);
		}
		if (!listaUsuarioMigrado.isEmpty()) {
			return listaUsuarioMigrado.get(0);
		} else {
			return null;
		}
	}

	/*
	 * MAYER SE AGREGO FUNCION QUE COMPRUEBA LA EXISTENCIA DE CELULAR
	 */
	public Integer compruebaExistenciaCelular(Integer codigoCliente, String celular, String usuario) {
		Integer totalGeneral;
		Integer totalAutorizados;
		Integer totalAutorizaciones;

		// consulta el total de usuarios con ese telefono
		Query query = getEntityManager()
				.createNativeQuery("SELECT COUNT(*) " + "FROM   Tweb_Usuarios x, Tweb_Usuarios_Roles y, Tweb_Roles z "
						+ "WHERE  x.Id = y.Id_Usuario " + "AND    y.Id_Rol = z.Id " + "AND    z.Id = 3 "
						+ "AND    x.Estado = 'A' " + "AND    x.Celular = ? " + "AND    x.Usuario <> ?");
		query.setParameter(1, celular);
		query.setParameter(2, usuario);
		Number total = (Number) query.getSingleResult();
		totalGeneral = total.intValue();

		System.out.println(
				"Para cliente: " + codigoCliente + " Total de usuarios con telefono " + celular + " : " + totalGeneral);

		// no se permiten mas de 3 usuarios activos con el mismo telefono/correo
		if (totalGeneral >= 3) {
			// primer uso + uso maximo
			return Integer.valueOf(total.intValue());
		} else {
			// control para que telefono esté registrado por el cliente
			query = getEntityManager().createNativeQuery(
					"SELECT count(*) FROM Mg_Telefonos_x_Clte WHERE Codigo_Cliente = ? AND Numero = ?");
			query.setParameter(1, codigoCliente);
			query.setParameter(2, celular);
			Number registrado = (Number) query.getSingleResult();
			if (registrado.intValue() == 0) {
				System.out.println("Telefono " + celular + " no registrado para cliente" + codigoCliente);
				return (-1);
			} else {
				System.out.println("Telefono " + celular + " si se encuentra registrado para cliente" + codigoCliente);
			}

			// control cliente actual debe tener relacion con los demas
			// que tengan mismo telefono / correo autorizados
			if (totalGeneral != 0) {
				query = getEntityManager().createNativeQuery("SELECT COUNT(*) "
						+ "FROM   Ca_Cuentas_De_Ahorro a, Mg_Cuentas_x_Cliente b "
						+ "WHERE  a.Numero_Cuenta = b.Codigo_Cuenta " + "AND    b.Clase_De_Cliente IN ('P', 'S') "
						// + "AND b.Firmantes = 'S' "
						+ "AND    a.Situacion_En_Linea != 1 " + "AND    b.Codigo_Cliente = ? "
						+ "AND    a.Codigo_Cliente IN " + "       (SELECT x.Codigo_Cliente "
						+ "         FROM   Tweb_Usuarios x, Tweb_Usuarios_Roles y, Tweb_Roles z "
						+ "         WHERE  x.Id = y.Id_Usuario " + "         AND    y.Id_Rol = z.Id "
						+ "         AND    z.Id = 3 " + "         AND    x.Estado = 'A' "
						+ "         AND    x.Celular = ?  " + "         AND    x.Usuario <> ?)");
				query.setParameter(1, codigoCliente);
				query.setParameter(2, celular);
				query.setParameter(3, usuario);
				Number autorizado = (Number) query.getSingleResult();
				totalAutorizados = autorizado.intValue();

				System.out.println("Total de usuarios autorizados con telefono " + celular + " : " + totalAutorizados);

				// autorizaciones
				query = getEntityManager().createNativeQuery("SELECT COUNT(*) "
						+ "FROM   Ca_Cuentas_De_Ahorro a, Mg_Cuentas_x_Cliente b "
						+ "WHERE  a.Numero_Cuenta = b.Codigo_Cuenta " + "AND    b.Clase_De_Cliente IN ('P', 'S') "
						// + "AND b.Firmantes = 'S' "
						+ "AND    a.Situacion_En_Linea != 1 " + "AND    a.Codigo_Cliente = ? "
						+ "AND    b.Codigo_Cliente IN " + "       (SELECT x.Codigo_Cliente "
						+ "         FROM   Tweb_Usuarios x, Tweb_Usuarios_Roles y, Tweb_Roles z "
						+ "         WHERE  x.Id = y.Id_Usuario " + "         AND    y.Id_Rol = z.Id "
						+ "         AND    z.Id = 3 " + "         AND    x.Estado = 'A' "
						+ "         AND    x.Celular = ? " + "         AND    x.Usuario <> ?)");
				query.setParameter(1, codigoCliente);
				query.setParameter(2, celular);
				query.setParameter(3, usuario);
				Number autorizacion = (Number) query.getSingleResult();
				totalAutorizaciones = autorizacion.intValue();

				System.out.println(
						"Total de usuarios autorizaciones con telefono " + celular + " : " + totalAutorizaciones);
				// si esta en mas de un usuario, pero es autorizado, devolvemos 0
				if (totalAutorizados + totalAutorizaciones > 0) {
					return (0);
				} else {
					return Integer.valueOf(total.intValue());
				}
			} else {
				return (0);
			}
		}
	}

	/*
	 * MAYER SE AGREGO FUNCION QUE COMPRUEBA LA EXISTENCIA DE CORREO
	 */
	public Integer compruebaExistenciaCorreo(Integer codigoCliente, String correo, String usuario) {
		Integer totalGeneral;
		Integer totalAutorizados;
		Integer totalAutorizaciones;

		Query query = getEntityManager()
				.createNativeQuery("SELECT COUNT(*) " + "FROM   Tweb_Usuarios x, Tweb_Usuarios_Roles y, Tweb_Roles z "
						+ "WHERE  x.Id = y.Id_Usuario " + "AND    y.Id_Rol = z.Id " + "AND    z.Id = 3 "
						+ "AND    x.Estado = 'A' " + "AND    x.Correo = ? " + "AND    x.Usuario <> ?");
		query.setParameter(1, correo);
		query.setParameter(2, usuario);
		Number total = (Number) query.getSingleResult();
		totalGeneral = total.intValue();

		System.out.println(
				"Para cliente: " + codigoCliente + " Total de usuarios con correo " + correo + " : " + totalGeneral);

		// no se permiten mas de 3 usuarios activos con el mismo telefono/correo
		if (totalGeneral >= 3) {
			// primer uso + uso maximo
			return Integer.valueOf(total.intValue());
		} else {
			// control para que correo esté registrado por el cliente
			query = getEntityManager()
					.createNativeQuery("SELECT count(*) FROM Mg_Direcciones WHERE Codigo_Cliente = ? AND Email = ?");
			query.setParameter(1, codigoCliente);
			query.setParameter(2, correo);
			Number registrado = (Number) query.getSingleResult();
			if (registrado.intValue() == 0) {
				System.out.println("Correo " + correo + " no registrado para cliente" + codigoCliente);
				return (-1);
			} else {
				System.out.println("Correo " + correo + " si se encuentra registrado para cliente" + codigoCliente);
			}

			// control cliente actual debe tener relacion con los demas
			// que tengan mismo telefono / correo autorizados
			if (totalGeneral != 0) {
				query = getEntityManager().createNativeQuery("SELECT COUNT(*) "
						+ "FROM   Ca_Cuentas_De_Ahorro a, Mg_Cuentas_x_Cliente b "
						+ "WHERE  a.Numero_Cuenta = b.Codigo_Cuenta " + "AND    b.Clase_De_Cliente IN ('P', 'S') "
						// + "AND b.Firmantes = 'S' "
						+ "AND    a.Situacion_En_Linea != 1 " + "AND    b.Codigo_Cliente = ? "
						+ "AND    a.Codigo_Cliente IN " + "       (SELECT x.Codigo_Cliente "
						+ "         FROM   Tweb_Usuarios x, Tweb_Usuarios_Roles y, Tweb_Roles z "
						+ "         WHERE  x.Id = y.Id_Usuario " + "         AND    y.Id_Rol = z.Id "
						+ "         AND    z.Id = 3 " + "         AND    x.Estado = 'A' "
						+ "         AND    x.Correo = ?  " + "         AND    x.Usuario <> ?)");
				query.setParameter(1, codigoCliente);
				query.setParameter(2, correo);
				query.setParameter(3, usuario);
				Number autorizado = (Number) query.getSingleResult();
				totalAutorizados = autorizado.intValue();

				System.out.println("Total de usuarios autorizados con correo " + correo + " : " + totalAutorizados);

				// autorizaciones
				query = getEntityManager().createNativeQuery("SELECT COUNT(*) "
						+ "FROM   Ca_Cuentas_De_Ahorro a, Mg_Cuentas_x_Cliente b "
						+ "WHERE  a.Numero_Cuenta = b.Codigo_Cuenta " + "AND    b.Clase_De_Cliente IN ('P', 'S') "
						// + "AND b.Firmantes = 'S' "
						+ "AND    a.Situacion_En_Linea != 1 " + "AND    a.Codigo_Cliente = ? "
						+ "AND    b.Codigo_Cliente IN " + "       (SELECT x.Codigo_Cliente "
						+ "         FROM   Tweb_Usuarios x, Tweb_Usuarios_Roles y, Tweb_Roles z "
						+ "         WHERE  x.Id = y.Id_Usuario " + "         AND    y.Id_Rol = z.Id "
						+ "         AND    z.Id = 3 " + "         AND    x.Estado = 'A' "
						+ "         AND    x.Correo = ? " + "         AND    x.Usuario <> ?)");
				query.setParameter(1, codigoCliente);
				query.setParameter(2, correo);
				query.setParameter(3, usuario);
				Number autorizacion = (Number) query.getSingleResult();
				totalAutorizaciones = autorizacion.intValue();

				System.out
						.println("Total de usuarios autorizaciones con correo " + correo + " : " + totalAutorizaciones);

				if (totalAutorizados + totalAutorizaciones > 0) {
					return (0);
				} else {
					return Integer.valueOf(total.intValue());
				}
			} else {
				return (0);
			}
		}
	}

	public String consultaEstadoBatch() {
		Query query = getEntityManager()
				.createNativeQuery("SELECT Wb_k_Web_Banking.Wb_f_Estado_Ejecucion_Cierre FROM Dual");
		return (String) query.getSingleResult();
	}

	public String consultaNombreCliente(Integer codigoCliente) {
		Query query = getEntityManager()
				.createNativeQuery("SELECT b.Nombres FROM Mg_Clientes b WHERE Codigo_Cliente = ?");
		query.setParameter(1, codigoCliente);
		return (String) query.getSingleResult();
	}

	/* Actualiza fecha en olvido de contrasena */
	/*
	 * public void olvidoContrasena(String actualizadoPor, Integer codigoCliente) {
	 * Query query = getEntityManager().createNativeQuery(
	 * "update tweb_usuarios set" + " fecha_actualizacion = sysdate," +
	 * " actualizado_por = upper(?)," + " cambia_clave = 'N'," +
	 * " fecha_ultimo_cambio_clave = sysdate," +
	 * " intentos_fallido_autenticacion = 0" + " where estado = 'A'" +
	 * " and codigo_cliente = ?"); query.setParameter(1, actualizadoPor);
	 * query.setParameter(2, codigoCliente); query.executeUpdate(); }
	 */

	/* Actualiza estado de bloqueo y fechas en el desbloqueo de usuario */
	/*
	 * public void desbloqueoUsuario(String actualizadoPor, Integer codigoCliente) {
	 * Query query = getEntityManager().createNativeQuery(
	 * "update tweb_usuarios set" + " fecha_actualizacion = sysdate," +
	 * " actualizado_por = ?," + " bloqueado = 'N'," + " cambia_clave = 'N'," +
	 * " fecha_ultimo_cambio_clave = sysdate," +
	 * " fecha_ultimo_desbloqueo = sysdate," + " intentos_fallido_autenticacion = 0"
	 * + " where estado = 'A'" + " and codigo_cliente = ?"); query.setParameter(1,
	 * actualizadoPor); query.setParameter(2, codigoCliente); query.executeUpdate();
	 * }
	 */

}
