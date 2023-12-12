package com.example.downloadVedioUsingEmbedURL.controller;

import org.json.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class EmbedUrlParserController {
    @GetMapping("/")
    public String showForm() {
        System.out.println("'index is running'");
        return "index";
    }

    @PostMapping("/process")
    public String processJson(@RequestParam("jsonString") String jsonString, Model model) {
        JSONObject embedInfo = getEmbedInfo(jsonString);
        String url720p = getAssetUrlWithDisplayName(embedInfo, "720p");
        System.out.println("URL of asset with display_name '720p': " + url720p);
        model.addAttribute("url720p", url720p);
        return "result";
    }

    private JSONObject getEmbedInfo(String jsonString) {
        // Parse the JSON string
        JSONObject jsonObject = new JSONObject(jsonString);

        // Get the embedUrl value
        String embedUrl = jsonObject.getString("embedUrl");

        // Fetch HTML content
        String htmlContent = fetchHtmlContent(embedUrl);

        // Extract assets information using regular expression
        JSONArray assetsInfo = extractAssetsInfo(htmlContent);

        // Create the final result JSON object
        JSONObject result = new JSONObject();
        result.put("embedUrl", embedUrl);
        result.put("assets", assetsInfo);

        return result;
    }

    private String fetchHtmlContent(String url) {
        // The same implementation as in your original code
        // ...
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Return the HTML content
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to fetch HTML content";
        }
    }

    private JSONArray extractAssetsInfo(String htmlContent) {
        // Define the regular expression pattern
        String regex = "W\\.iframeInit\\((\\{.+?\\})\\);";

        // Create a Pattern object
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);

        // Create a Matcher object
        Matcher matcher = pattern.matcher(htmlContent);

        // Find the matching part
        if (matcher.find()) {
            String iframeInitJson = matcher.group(1); // Extract the content inside the parentheses
            JSONObject iframeInitObject = new JSONObject(iframeInitJson);

            // Extract the "assets" array
            if (iframeInitObject.has("assets")) {
                return iframeInitObject.getJSONArray("assets");
            } else {
                return new JSONArray().put(new JSONObject().put("error", "No assets information found"));
            }
        } else {
            return new JSONArray().put(new JSONObject().put("error", "No assets information found"));
        }
    }

    private String getAssetUrlWithDisplayName(JSONObject embedInfo, String displayName) {
        JSONArray assets = embedInfo.getJSONArray("assets");

        for (int i = 0; i < assets.length(); i++) {
            JSONObject asset = assets.getJSONObject(i);
            if (asset.has("display_name") && asset.getString("display_name").equals(displayName)
                    && asset.has("url")) {
                String url = asset.getString("url");
                // Remove ".bin" extension
                if (url.endsWith(".bin")) {
                    url = url.substring(0, url.length() - 4);
                }
                return url;
            }
        }

        return "No asset found with display_name '" + displayName + "'";
    }
}
