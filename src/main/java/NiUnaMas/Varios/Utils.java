package NiUnaMas.Varios;

import NiUnaMas.Models.User;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.resource.Email;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.ByteBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Robert on 12/05/2017.
 */
public class Utils {
    public static void sendMail(User user, String email) throws MailjetException {
        MailjetClient client;
        MailjetRequest request;
        MailjetResponse response;
        client = new MailjetClient("80bee6745ca5404f8ac4a849141cc911", "8ebbd1ff9a0493f12481907d3c05d5bd");
        request = new MailjetRequest(Email.resource)
                .property(Email.FROMEMAIL, "niunamastaes2017@gmail.com")
                .property(Email.FROMNAME, "NiUnaMas Proyect")
                .property(Email.SUBJECT, user.getName()+" "+user.getFname()+" quiere añadirte a tu lista de contactos.")
                .property(Email.TEXTPART, "Hola, "+user.getName()+" "+user.getFname()+" desea que usted sea su contacto de emergencia." +
                        " Si no ha sido un malentendido, por favor descarguese la aplicación [Aquí link AppStore] e introduzca el siguiente" +
                        " código : EJ412uf.\n" +
                        "Gracias por ayudar.")
                .property(Email.HTMLPART, "Hola, "+user.getName()+" "+user.getFname()+" desea que usted sea su contacto de emergencia." +
                        " Si no ha sido un malentendido, por favor descarguese la aplicación [Aquí link AppStore] e introduzca el siguiente" +
                        " código : EJ412uf.\n" +
                        "Gracias por ayudar.")
                .property(Email.RECIPIENTS, new JSONArray()
                        .put(new JSONObject()
                                .put("Email", email)));
        response = client.post(request);
    }
    public static String generateCode(){
        SHA3.DigestSHA3 sha = new SHA3.Digest224();
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        byte[] digest = sha.digest(buffer.array());
        String code = Hex.toHexString(digest);
        code = code.substring(0, Math.min(code.length(), 5));
        return code;
    }

    public static Date getDate() {
        Date date = new Date();
        try {
            Date now = new Date();
            String format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.GERMANY).format(now);
            date = new SimpleDateFormat().parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
