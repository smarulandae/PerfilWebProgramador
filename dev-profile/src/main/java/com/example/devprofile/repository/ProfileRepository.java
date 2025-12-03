package com.example.devprofile.repository;

import com.example.devprofile.model.Profile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

public class ProfileRepository {
    private final Path dataDir;
    private final Path profileFile;

    public ProfileRepository(Path baseDir) {
        this.dataDir = baseDir.resolve("data");
        this.profileFile = dataDir.resolve("profile.txt");
        init();
    }

    private void init() {
        try {
            if (!Files.exists(dataDir)) Files.createDirectories(dataDir);
            if (!Files.exists(profileFile)) {
                String defaultContent = "name:\\n" +
                        "bio:\\n" +
                        "experience:\\n" +
                        "contact:\\n" +
                        "banner:Bienvenido a mi portafolio\\n" +
                        "photoPath:\\n";
                Files.write(profileFile, defaultContent.getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error initializing profile repository", e);
        }
    }

    public Profile load() {
        Map<String, String> map = new HashMap<>();
        try (BufferedReader br = Files.newBufferedReader(profileFile, StandardCharsets.UTF_8)) {
            String line;
            while ((line = br.readLine()) != null) {
                int idx = line.indexOf(':');
                if (idx >= 0) {
                    String key = line.substring(0, idx).trim();
                    String value = line.substring(idx + 1).trim();
                    map.put(key, value);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Profile p = new Profile();
        p.setName(map.getOrDefault("name", ""));
        p.setBio(map.getOrDefault("bio", ""));
        p.setExperience(map.getOrDefault("experience", ""));
        p.setContact(map.getOrDefault("contact", ""));
        p.setBanner(map.getOrDefault("banner", "Bienvenido a mi portafolio"));
        p.setPhotoPath(map.getOrDefault("photoPath", ""));
        return p;
    }

    public void save(Profile p) {
        StringBuilder sb = new StringBuilder();
        sb.append("name:").append(safe(p.getName())).append("\\n");
        sb.append("bio:").append(safe(p.getBio())).append("\\n");
        sb.append("experience:").append(safe(p.getExperience())).append("\\n");
        sb.append("contact:").append(safe(p.getContact())).append("\\n");
        sb.append("banner:").append(safe(p.getBanner())).append("\\n");
        sb.append("photoPath:").append(safe(p.getPhotoPath())).append("\\n");
        try {
            Files.write(profileFile, sb.toString().getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String safe(String s) {
        return s == null ? "" : s.replace("\\n", " ").trim();
    }
}
