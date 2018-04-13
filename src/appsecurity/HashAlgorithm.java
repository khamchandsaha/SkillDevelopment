package appsecurity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author khamchand
 * This class will provide the hashcode for password
 */
public class HashAlgorithm {
	/**
	 * @param password
	 * @return hashcode
	 */
	public String createHash(String password)
	{
		String hash = null;
		try {
			
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes());
			byte byteData[] = md.digest();
			//convert the byte to hex format
	        StringBuffer hexString = new StringBuffer();
	        for (int i=0;i<byteData.length;i++){
	    		String hex=Integer.toHexString(0xff & byteData[i]);
	   	     	if(hex.length()==1) hexString.append('0');
	   	     	hexString.append(hex);
	        }
	        hash = hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return hash;
	}
}
