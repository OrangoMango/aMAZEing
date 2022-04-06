package com.orangomango.amazeing.profile;

import java.io.*;

public class Profile {
    
    private static void checkAndCreateDir(String directory){
        File f = new File(directory);
        if (!f.exists()){
            f.mkdir();
        }
    }
    
    private static void prepareDirectory(){
        String home = System.getProperty("user.home");
        checkAndCreateDir(home+File.separator+".amazeing");
        checkAndCreateDir(home+File.separator+".amazeing"+File.separator+ "resources");
        checkAndCreateDir(home+File.separator+".amazeing"+File.separator+"profiles");
    }
}
