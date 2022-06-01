package com.asd;

import com.asd.repository.MapsApi;
import com.asd.view.MainMenu;
import com.asd.logic.*;

public class App {
    public static void main(String[] args) {
        String apiKey = "api-key here";
        MapsApi api = new MapsApi(apiKey);
        MST mst = new MST(api);
        MainMenu mainMenu = new MainMenu(mst, api);
    }

}
