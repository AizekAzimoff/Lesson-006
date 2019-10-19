package ayupov.ilgam.lesson006;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://cat-fact.herokuapp.com";
    private static final String GET_FACTS = BASE_URL + "/facts/random";
    private static final String GET_FACTS_WITH_PARAMS = GET_FACTS + "?animal_type=cat&amount=20";

    private FactsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvFacts = findViewById(R.id.rvFacts);
        adapter = new FactsAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvFacts.setAdapter(adapter);
        rvFacts.setLayoutManager(layoutManager);

        getFacts();
    }

    private void getFacts() {
        new GetFactsTask().execute();
    }

    class GetFactsTask extends AsyncTask<Void, Void, Void> {

        private List<Fact> facts = new ArrayList<>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(GET_FACTS_WITH_PARAMS).openConnection();
                try {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                    StringBuilder response = new StringBuilder();
                    String data;
                    while ((data = bufferedReader.readLine()) != null) {
                        response.append(data);
                    }

                    JSONArray jsonArray = new JSONArray(response.toString());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        facts.add(new Fact(jsonArray.getJSONObject(i).getString("text")));
                    }

                    bufferedReader.close();
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    httpURLConnection.disconnect();
                }
            } catch (MalformedURLException m) {
                m.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.setFacts(facts);
        }
    }
}
