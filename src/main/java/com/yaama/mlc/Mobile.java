package com.yaama.mlc;

/**
  * Add your first API methods in this class, or you may create another class. In that case, please
  * update your web.xml accordingly.
 **/
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.users.User;
import java.util.ArrayList;
import javax.inject.Named;

/**
 * Defines v1 of a Mobile API, which provides simple "greeting" methods.
 */
@Api(
    name = "yaama",
    version = "v1",
    scopes = {Constants.EMAIL_SCOPE},
    clientIds = {Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID},
    audiences = {Constants.ANDROID_AUDIENCE}
)
public class Mobile {

  public static ArrayList<Certificate> certificates = new ArrayList<Certificate>();

  static {
   
    certificates.add(new Certificate(1, "Income Protection", "premium : $1000"));
    certificates.add(new Certificate(2, "Total Permanent Disability", "premium : $1000"));
        
  }

  public Certificate getCertificate(@Named("id") Integer id) throws NotFoundException {
    try {
      return certificates.get(id);
    } catch (IndexOutOfBoundsException e) {
      throw new NotFoundException("Certificate not found with an index: " + id);
    }
  }

  public ArrayList<Certificate> listCertificates() {
    return certificates;
  }

 
  
  public Certificate authedCertificate(User user) {
    Certificate response = new Certificate(1, "Unknown","hello " + user.getEmail());
    return response;
  }
}
