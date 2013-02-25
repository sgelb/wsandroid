package fi.bitrite.android.ws.host.impl;

import fi.bitrite.android.ws.auth.http.HttpAuthenticationService;
import fi.bitrite.android.ws.auth.http.HttpSessionContainer;
import fi.bitrite.android.ws.host.HostContact;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Sends private message to a single host using the REST API.
 */
public class RestHostContact extends RestClient implements HostContact {

    private static final String WARMSHOWERS_HOST_CONTACT_URL = "http://www.warmshowers.org/services/rest/message/send";

    public RestHostContact(HttpAuthenticationService authenticationService, HttpSessionContainer sessionContainer) {
        super(authenticationService, sessionContainer);
    }

    @Override
    public void send(String name, String subject, String message) {
        if (!isAuthenticationPerformed()) {
            authenticate();
        }

        List<NameValuePair> args = new ArrayList<NameValuePair>();
        args.add(new BasicNameValuePair("recipients", name));
        args.add(new BasicNameValuePair("subject", subject));
        args.add(new BasicNameValuePair("body", message));
        post(WARMSHOWERS_HOST_CONTACT_URL, args);
    }

}
