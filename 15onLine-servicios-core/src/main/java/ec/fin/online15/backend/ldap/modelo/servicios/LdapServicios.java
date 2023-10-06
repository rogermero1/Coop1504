package ec.fin.online15.backend.ldap.modelo.servicios;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPModification;
import com.novell.ldap.LDAPSearchResults;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

//import java.util.Map;

public class LdapServicios {
	public static final String SN;
	public static final String DESCRIPTION;
	public static final String USERPASSWORD;   
	public static final String USER;
	private static final String SUFIJO_BASE;
	private static final String PREFIJO_BASE;
	private static final String PERSON;
	private static final String TOP;
	private static final String IB4CARDOBJECT; 
	private static final String OBJECTCLASS;
	static {
		SUFIJO_BASE = "ou=15OnLine,dc=coop15abril,dc=fin,dc=ec";
		SN = "sn";
		DESCRIPTION = "description";
		USERPASSWORD = "userPassword";
		USER = "USER";
		PERSON = "person";
		TOP = "top";
		IB4CARDOBJECT = "ib4cardobject";
		PREFIJO_BASE = "cn";
		OBJECTCLASS = "objectClass";
	}

	private LdapServicios() {

	}

	// -1 usuario ya existe
	// 1 usuario creado
	// 0 exception de ldap
	public static int crearUsuarioLdap(HashMap<String, String> atributos)
			throws UnsupportedEncodingException, LDAPException {
		LDAPConnection lc = null;
		try {
			lc = LdapConexion.conexionManager();
			LDAPEntry usuario = entidadUsuarioLdap(atributos);
			lc.add(usuario);
			return 1;
		} catch (LDAPException ex) {
			if (ex.getResultCode() == 68) {
				return -1;
			} else {
				throw ex;
			}
		} finally {
			if (lc != null) {
				LdapConexion.cerrarConLDAP(lc);
			}
		}
	}

	private static LDAPAttributeSet parametrosGeneralesLdapEntry() {
		LDAPAttributeSet setAtr = new LDAPAttributeSet();
		setAtr.add(new LDAPAttribute(OBJECTCLASS, PERSON));
		setAtr.add(new LDAPAttribute(OBJECTCLASS, TOP));
		setAtr.add(new LDAPAttribute(OBJECTCLASS, IB4CARDOBJECT));
		return setAtr;
	}

	static String formatoUsuarioLdap(String usuario) {
		return PREFIJO_BASE + "=" + usuario + "," + SUFIJO_BASE;
	}

	private static LDAPEntry entidadUsuarioLdap(
			HashMap<String, String> atributos) {
		LDAPAttributeSet setAtr = parametrosGeneralesLdapEntry();
		setAtr.add(new LDAPAttribute(SN, atributos.get(SN)));
		setAtr.add(new LDAPAttribute(DESCRIPTION, atributos.get(DESCRIPTION)));
		setAtr.add(new LDAPAttribute(USERPASSWORD, atributos.get(USERPASSWORD)));
		return new LDAPEntry(formatoUsuarioLdap(atributos.get(USER)), setAtr);
	}

	@SuppressWarnings("rawtypes")
	public static HashMap<String, String> buscar(String usuario)
			throws UnsupportedEncodingException, LDAPException {
		LDAPSearchResults searchResults;
		int searchScope = LDAPConnection.SCOPE_SUB;
		String filtro = "(" + PREFIJO_BASE + "=" + usuario + ")";
		LDAPConnection lc = null;
		HashMap<String, String> datosUsuario = new HashMap<String, String>();
		try {
			lc = LdapConexion.conexionManager();
			searchResults = lc.search(SUFIJO_BASE, searchScope, filtro, null,
					false);
			while (searchResults.hasMore()) {
				LDAPEntry nextEntry = null;
				try {
					nextEntry = searchResults.next();
				} catch (LDAPException e) {
					System.out.println("Error: " + e.toString());
					continue;
				}
				LDAPAttributeSet attributeSet = nextEntry.getAttributeSet();

				Iterator allAttributes = attributeSet.iterator();
				while (allAttributes.hasNext()) {
					LDAPAttribute attribute = (LDAPAttribute) allAttributes
							.next();
					String attributeName = attribute.getName();
					Enumeration allValues = attribute.getStringValues();
					if (allValues != null) {
						while (allValues.hasMoreElements()) {
							datosUsuario.put(attributeName, allValues
									.nextElement().toString());
						}
					}
				}
			}
		} finally {
			if (lc != null) {
				LdapConexion.cerrarConLDAP(lc);
			}
		}

		// System.out.println(datosUsuario.isEmpty() ? "NULO" : "LLENO");
		//
		// Iterator it = datosUsuario.entrySet().iterator();
		// while (it.hasNext()) {
		// Map.Entry e = (Map.Entry) it.next();
		// System.out.println(e.getKey() + " " + e.getValue());
		// }

		if (datosUsuario.isEmpty()) {
			datosUsuario.put("sn", "");
			datosUsuario.put("cn", "");
			datosUsuario.put("description", "");
			datosUsuario.put("userPassword", "");
			datosUsuario.put("objectClass", "");
		}

		return datosUsuario;
	}

	/**
	 * Metodo Para Eliminar un usuario mandando como parametro el "uid"
	 * 
	 * @param strUser
	 * @param strPass
	 * @param strUIDEliminar
	 * @throws LDAPException
	 * @throws UnsupportedEncodingException
	 */

	// -1 indica que no se pudo eliminar 0 error y 1 correcto
	public static int eliminarPorUsuario(String usuario) throws LDAPException,
			UnsupportedEncodingException {
		String dn = formatoUsuarioLdap(usuario);
		LDAPConnection lc = null;
		int i = 0;
		try {
			lc = LdapConexion.conexionManager();
			lc.delete(dn);
			i = 1;
		} catch (LDAPException ex) {
			if (ex.getResultCode() == LDAPException.INSUFFICIENT_ACCESS_RIGHTS) {
				i = -1;
			} else {
				throw ex;
			}
		} finally {
			if (lc != null) {
				LdapConexion.cerrarConLDAP(lc);
			}
		}
		return i;
	}

	/**
	 * Enviar Usuario a Modificar
	 * 
	 * @param strUser
	 *            usuario
	 * @param strNombres
	 *            nuevo nombre
	 * @throws LDAPException
	 * @throws UnsupportedEncodingException
	 */

	// -1 indica que no se pudo eliminar 0 error y 1 correcto
	public static int modificarNombre(String usuario, String strNombres)
			throws LDAPException, UnsupportedEncodingException {
		LDAPConnection lc = null;
		int i = 0;
		try {
			LDAPAttribute atrubuto;
			lc = LdapConexion.conexionManager();
			atrubuto = new LDAPAttribute(SN, strNombres);
			String dn = formatoUsuarioLdap(usuario);
			lc.modify(dn, new LDAPModification(LDAPModification.REPLACE,
					atrubuto));
			i = 1;
		} catch (LDAPException ex) {
			if (ex.getResultCode() == LDAPException.INSUFFICIENT_ACCESS_RIGHTS) {
				i = -1;
			} else {
				throw ex;
			}
		} finally {
			if (lc != null) {
				LdapConexion.cerrarConLDAP(lc);
			}
		}
		return i;

	}

	/**
	 * Enviar Usuario a Modificar
	 * 
	 * @param strUser
	 *            usuario
	 * @param strContrasenia
	 * 
	 * @throws UnsupportedEncodingException
	 * @throws LDAPException
	 */

	public static String getClaveUsuario(String usuario)
			throws UnsupportedEncodingException, LDAPException {
		HashMap<String, String> datos = buscar(usuario);
		return datos.get(USERPASSWORD);
	}

	public static String getNombreUsuario(String usuario)
			throws UnsupportedEncodingException, LDAPException {
		HashMap<String, String> datos = buscar(usuario);
		return datos.get(SN);
	}

	// 1 correcto -1 sin permiso, 0 error
	public static int modificarContrasenia(String usuario, String strContrasenia)
			throws UnsupportedEncodingException, LDAPException {
		LDAPConnection lc = null;
		int i = 0;
		try {

			LDAPAttribute atrubuto;
			lc = LdapConexion.conexionManager();
			atrubuto = new LDAPAttribute(USERPASSWORD, strContrasenia);
			String dn = formatoUsuarioLdap(usuario);
			lc.modify(dn, new LDAPModification(LDAPModification.REPLACE,
					atrubuto));
			i = 1;
		} catch (LDAPException ex) {
			if (ex.getResultCode() == LDAPException.INSUFFICIENT_ACCESS_RIGHTS) {
				i = -1;
			} else {
				throw ex;
			}
		}
		return i;
	}

	public static boolean validarCredenciales(String usuario, String password)
			throws UnsupportedEncodingException, LDAPException {
		boolean correct = false;
		String dnc = formatoUsuarioLdap(usuario);
		LDAPConnection lc = null;
		try {
			lc = LdapConexion.conexionManager();
			lc.bind(LDAPConnection.LDAP_V3, dnc, password.getBytes("UTF8"));
			LDAPAttribute attr = new LDAPAttribute(USERPASSWORD, password);
			correct = lc.compare(dnc, attr);
		} finally {
			if (lc != null) {
				LdapConexion.cerrarConLDAP(lc);
			}
		}
		return correct;
	}
}