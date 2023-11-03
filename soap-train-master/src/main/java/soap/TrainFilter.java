package soap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.axis2.context.MessageContext;
import org.apache.axis2.context.ServiceContext;
import org.apache.axis2.context.SessionContext;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class TrainFilter {

    public void authenticate(String firstName, String lastName, String email, String phoneNumber) {
        // Récupérer le contexte du message SOAP
    	 MessageContext messageContext = MessageContext.getCurrentMessageContext();
         if (messageContext != null) {
             // Récupérer ou créer une SessionContext associée au MessageContext
             org.apache.axis2.context.SessionContext sessionContext = messageContext.getSessionContext();

             // Stocker les informations de l'utilisateur en session
             sessionContext.setProperty("firstName", firstName);
             sessionContext.setProperty("lastName", lastName);
             sessionContext.setProperty("email", email);
             sessionContext.setProperty("phoneNumber", phoneNumber);
         }
    }

    public String searchTrains(String departure, String arrival, int nbTickets, String category, String outboundDate, String outboundTime, String returnDate, String returnTime) {
        // Appeler le service REST pour rechercher les trains
        String restServiceUrl = "http://localhost:8080/search?departure=" + departure +
                "&arrival=" + arrival +
                "&nb_tickets=" + nbTickets +
                "&category=" + category +
                "&outbound_date=" + outboundDate +
                "&outbound_time=" + outboundTime +
                "&return_date=" + returnDate +
                "&return_time=" + returnTime;
        return Rest.callRestService(restServiceUrl, null, "GET");
    }

    public String makeReservation(String trains, String seats) {
        // Récupérer les informations de l'utilisateur depuis la session
        String firstName = (String) getSessionProperty("firstName");
        String lastName = (String) getSessionProperty("lastName");
        String email = (String) getSessionProperty("email");
        String phoneNumber = (String) getSessionProperty("phoneNumber");

        // Effectuer la réservation en appelant le service REST
        String restServiceUrl = "http://localhost:8080/reservation";
        // Construire le corps de la requête de réservation avec les données de l'utilisateur, les trains et les sièges
        String reservationRequestBody = "firstName=" + firstName +
                "&lastName=" + lastName +
                "&phoneNumber=" + phoneNumber +
                "&email=" + email +
                "&trains=" + trains +
                "&seats=" + seats;

        return Rest.callRestService(restServiceUrl, reservationRequestBody, "POST");
    }


    private Object getSessionProperty(String propertyName) {
        // Récupérer la propriété de session en fonction du nom de la propriété
        MessageContext messageContext = MessageContext.getCurrentMessageContext();
        if (messageContext != null) {
            SessionContext sessionContext = messageContext.getSessionContext();
            return sessionContext.getProperty(propertyName);
        }
        return null;
    }
}