package com.asd;

import com.asd.repository.MapsApi;
import com.asd.view.MainMenu;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvBuilder;

import com.asd.logic.*;

public class App {
    public static void main(String[] args) {
        Dotenv dotenv = new DotenvBuilder().directory("./maps/src/main/java/com/asd/").load();
        String apiKey = dotenv.get("API_KEY");
        MapsApi api = new MapsApi(apiKey);
        MST mst = new MST(api);
        MainMenu mainMenu = new MainMenu(mst, api);
    }

}
