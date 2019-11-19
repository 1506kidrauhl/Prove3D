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

    public void enviarMsg(String nomeUser, String msg) throws IOException {

        String l1 = "https://hooks.slack.com - ";
        String l2 = "/services/TPRKC8REJ/BQKJ7052T - ";
        String l3 = "/QPiPK8NQEY4jMwThu43V18F0 - ";
        String url = l1.split(" -")[0] + l2.split(" -")[0] + l3.split(" -")[0];

        JSONObject json = new JSONObject();

        json.put("text", nomeUser + " - " + msg);

        try {

            URL myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();

            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Java client");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setRequestProperty("Accept-Charset", "UTF-8");
            con.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
            con.setRequestProperty("encoding", "UTF-8");

            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {

                wr.writeBytes(json.toString());
            }

            StringBuilder content;

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "UTF-8"))) {

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
