package com.example.devprofile.repository;

import com.example.devprofile.model.Skill;
import org.junit.jupiter.api.*;

import java.nio.file.Paths;
import java.util.List;

public class SkillRepositoryTest {

    SkillRepository repo;

    @BeforeEach
    void setUp() {
        repo = new SkillRepository(Paths.get(System.getProperty("java.io.tmpdir"), "dev-profile-test"));
    }

    @Test
    void testAddAndList() {
        int initialSize = repo.list().size();
        repo.add(new Skill(0, "C#", "Básico"));
        List<Skill> skills = repo.list();
        Assertions.assertEquals(initialSize + 1, skills.size());
        Assertions.assertTrue(skills.stream().anyMatch(s -> "C#".equals(s.getName())));
    }

    @Test
    void testUpdate() {
        repo.add(new Skill(0, "Go", "Básico"));
        Skill go = repo.list().stream().filter(s -> "Go".equals(s.getName())).findFirst().orElseThrow();
        go.setLevel("Intermedio");
        repo.update(go);
        Skill updated = repo.list().stream().filter(s -> s.getId() == go.getId()).findFirst().orElseThrow();
        Assertions.assertEquals("Intermedio", updated.getLevel());
    }

    @Test
    void testDelete() {
        repo.add(new Skill(0, "Rust", "Básico"));
        Skill rust = repo.list().stream().filter(s -> "Rust".equals(s.getName())).findFirst().orElseThrow();
        repo.delete(rust.getId());
        Assertions.assertTrue(repo.list().stream().noneMatch(s -> s.getId() == rust.getId()));
    }
}
