package com.swaglab.java.models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import org.yaml.snakeyaml.Yaml;

public class EnvironmentData {
    public HashMap<String, String> environmentUrls;
    public HashMap<String, HashMap<String, String>> userAccounts;

    public EnvironmentData() {
        File fileenv = new File("./src/test/resources/testdata/environment.yml");
        Yaml yaml = new Yaml();
        try {
            HashMap<String, Object> env = yaml.load(new FileInputStream(fileenv));
            environmentUrls = (HashMap<String, String>) env.get("environment_urls");
            userAccounts = (HashMap<String, HashMap<String, String>>) env.get("user_accounts");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
