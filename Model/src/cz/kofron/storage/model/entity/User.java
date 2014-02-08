package cz.kofron.storage.model.entity;

import java.io.Serializable;
import java.security.MessageDigest;

public class User extends AbstractEntity implements Serializable
{
	/**
	 * Generated serial ID.
	 */
	private static final long serialVersionUID = 5355158272592041645L;
	public User(int id, String username, String hash, String salt)
	{
		super(id);
		this.username = username;
		this.hash = hash;
		this.salt = salt;
	}

	private String username;
	private String hash;
	private String salt;
	
	public static String sha256(String base) {
	    try{
	        MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        byte[] hash = digest.digest(base.getBytes("UTF-8"));
	        StringBuffer hexString = new StringBuffer();

	        for (int i = 0; i < hash.length; i++) {
	            String hex = Integer.toHexString(0xff & hash[i]);
	            if(hex.length() == 1) hexString.append('0');
	            hexString.append(hex);
	        }

	        return hexString.toString();
	    } catch(Exception ex){
	       throw new RuntimeException(ex);
	    }
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getHash()
	{
		return hash;
	}

	public void setHash(String hash)
	{
		this.hash = hash;
	}

	public String getSalt()
	{
		return salt;
	}

	public void setSalt(String salt)
	{
		this.salt = salt;
	}
	
	private String calcHash(String pass)
	{
		return sha256(salt + username + pass + salt);
	}
	
	public boolean checkPassword(String pass)
	{
		return hash.equals(calcHash(pass));
	}

	public void setPassword(String pass)
	{
		hash = calcHash(pass);
	}
}
