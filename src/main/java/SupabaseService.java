import io.github.cdimascio.dotenv.Dotenv;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SupabaseService {

    Dotenv dotenv = Dotenv.load();

    String SUPABASE_URL = dotenv.get("SUPABASE_URL");
    String SUPABASE_KEY = dotenv.get("SUPABASE_KEY");

    public void addOrder(String flower, int qty, int total) {

        try {

            URL url = new URL(SUPABASE_URL + "/rest/v1/orders");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");

            conn.setRequestProperty("apikey", SUPABASE_KEY);
            conn.setRequestProperty("Authorization", "Bearer " + SUPABASE_KEY);
            conn.setRequestProperty("Content-Type", "application/json");

            conn.setDoOutput(true);

            String json =
                    "{"
                            + "\"flower\":\"" + flower + "\","
                            + "\"quantity\":" + qty + ","
                            + "\"total\":" + total
                            + "}";

            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes());
            os.flush();
            os.close();

            System.out.println("Saved! Response: " + conn.getResponseCode());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}