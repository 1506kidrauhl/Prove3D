package com.projetopi.prove3dapp.dadosClasses;

import org.springframework.stereotype.Controller;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class EnviarSlack {

    private static HttpURLConnection con;

    public void enviarMsg(String nomeUser, String msg) throws IOException{
        String url = "https://hooks.slack.com/services/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";

        JSONObject json = new JSONObject();

        json.put("text", nomeUser + " - " + msg);

        try {

            URL myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();

            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Java client");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {

                wr.writeBytes(json.toString());
            }

            StringBuilder content;

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }

            System.out.println(content.toString());

        } finally {

            con.disconnect();
        }
    }

}
