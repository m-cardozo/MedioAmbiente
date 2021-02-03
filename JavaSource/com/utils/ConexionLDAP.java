package com.utils;

import java.util.*; 
import javax.naming.*; 
import javax.naming.directory.*; 

public class ConexionLDAP {

	public static boolean AutenticarAPP(String username, String password) {
		Integer corteString = username.indexOf("@");
		String nombreUsuario = username.substring(0, corteString);

		String ldapCF = "com.sun.jndi.ldap.LdapCtxFactory"; 
		String ldapURL = "ldap://192.168.80.100:389/";
		String ldapBaseDN = "dc=sinergia,dc=utec,dc=edu,dc=uy";
		String ldapUserID ="CN=" + nombreUsuario + ",CN=Users,DC=sinergia,DC=utec,DC=edu,DC=uy"; 
		String ldapPassword = password;

		Hashtable env = new Hashtable(4);
		
		try { 
			env.put(Context.INITIAL_CONTEXT_FACTORY, ldapCF); 
			env.put(Context.PROVIDER_URL,ldapURL + ldapBaseDN); 
			env.put(Context.SECURITY_PRINCIPAL, ldapUserID);
			env.put(Context.SECURITY_CREDENTIALS, ldapPassword);

			// Create initial context
			InitialDirContext ctx = new InitialDirContext(env);
			SearchControls ctrls = new SearchControls();
			ctrls.setSearchScope(ctrls.SUBTREE_SCOPE);

			ctrls.setReturningAttributes(new String[] {"scriptPath"});

			NamingEnumeration<javax.naming.directory.SearchResult> answers;

			answers = ctx.search("CN=Users", "sAMAccountName=" + nombreUsuario, ctrls);
			SearchResult result = answers.nextElement();
			Attributes attributes = result.getAttributes();
			Attribute atributoScriptPath = attributes.get("scriptPath");

			if (atributoScriptPath != null) {
				
				return true;
			} else {
				
				return false;
			}
		} catch(Exception e) {
			
		}

		return false;
	}
}