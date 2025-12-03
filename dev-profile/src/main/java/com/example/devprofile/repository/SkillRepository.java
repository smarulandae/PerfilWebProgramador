package com.example.devprofile.repository;

import com.example.devprofile.model.Skill;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class SkillRepository {
    private final Path dataDir;
    private final Path skillsFile;

    public SkillRepository(Path baseDir) {
        this.dataDir = baseDir.resolve("data");
        this.skillsFile = dataDir.resolve("skills.txt");
        init();
    }

    private void init() {
        try {
            if (!Files.exists(dataDir)) Files.createDirectories(dataDir);
            if (!Files.exists(skillsFile)) {
                List<String> defaultSkills = Arrays.asList(
                        "1;Java;Intermedio",
                        "2;Python;Intermedio",
                        "3;JavaScript;Básico"
                );
                Files.write(skillsFile, defaultSkills, StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error initializing skills repository", e);
        }
    }

    public List<Skill> list() {
        try (BufferedReader br = Files.newBufferedReader(skillsFile, StandardCharsets.UTF_8)) {
            return br.lines()
                    .filter(l -> !l.trim().isEmpty())
                    .map(this::parse)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(Skill s) {
        int nextId = nextId();
        s.setId(nextId);
        String line = toLine(s);
        try {
            Files.write(skillsFile, Arrays.asList(line), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Skill s) {
        List<Skill> all = list();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getId() == s.getId()) {
                all.set(i, s);
                break;
            }
        }
        writeAll(all);
    }

    public void delete(int id) {
        List<Skill> all = list().stream()
                .filter(sk -> sk.getId() != id)
                .collect(Collectors.toList());
        writeAll(all);
    }

    private void writeAll(List<Skill> all) {
        List<String> lines = all.stream().map(this::toLine).collect(Collectors.toList());
        try {
            Files.write(skillsFile, lines, StandardCharsets.UTF_8,
                    StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Skill parse(String line) {
        String[] parts = line.split(";");
        if (parts.length < 3) return null;
        try {
            int id = Integer.parseInt(parts[0].trim());
            String name = parts[1].trim();
            String level = parts[2].trim();
            return new Skill(id, name, level);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private String toLine(Skill s) {
        return s.getId() + ";" + s.getName() + ";" + s.getLevel();
    }

    private int nextId() {
        return list().stream().mapToInt(Skill::getId).max().orElse(0) + 1;
    }
}
