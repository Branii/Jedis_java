package com.pivo;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pivo.model.Model;

import static spark.Spark.get;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Main {

    
static OkHttpClient client = new OkHttpClient();
   
    public static void main(String[] args) throws IOException {

         get("/hello", (req, res) -> Model.getProductList());

        // get("/hello/nii", (req, res) -> "Hello nii, please how are you!");

        // String response = Main.run("https://dummyjson.com/products?limit=10&skip=10&select=title,price");
        // JsonParser parser = new JsonParser();
        // JsonObject jsonResponse = parser.parse(response).getAsJsonObject();
        // JsonArray products = jsonResponse.getAsJsonArray("products");

        // // Deserialize each product into Product objects
        // Gson gson = new Gson();
        // for (int i = 0; i < products.size(); i++) {
        //     JsonObject productJson = products.get(i).getAsJsonObject();
        //     String id = productJson.get("id").getAsString();
        //     String title = productJson.get("title").getAsString();
        //     String price = productJson.get("price").getAsString();
        //     //Model.insertData(id, title, price);
        // }
        // System.out.println("Data inserted");

        // String result = Model.insertData("1", "title", "0.50");
        // System.out.println(result);
    }

    static String run(String url) throws IOException {
    Request request = new Request.Builder()
        .url(url)
        .build();

    try (Response response = client.newCall(request).execute()) {
        return response.body().string();
    }
    }

}