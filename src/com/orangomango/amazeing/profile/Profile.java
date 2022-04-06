package com.orangomango.amazeing.profile;

import java.io.*;

import static com.orangomango.amazeing.Main.GAME_HOME;

public class Profile {
    
    private static void checkAndCreateDir(String directory){
        File f = new File(directory);
        if (!f.exists()){
            f.mkdir();
        }
    }
    
    public static void prepareDirectory(){
        checkAndCreateDir(GAME_HOME);
        checkAndCreateDir(GAME_HOME+File.separator+ "resources");
        checkAndCreateDir(GAME_HOME+File.separator+"profiles");
    }
}
