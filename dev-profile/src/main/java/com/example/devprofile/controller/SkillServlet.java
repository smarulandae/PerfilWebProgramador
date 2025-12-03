package com.example.devprofile.controller;

import com.example.devprofile.model.Skill;
import com.example.devprofile.repository.SkillRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@WebServlet(name = "SkillServlet", urlPatterns = {"/skills"})
public class SkillServlet extends HttpServlet {

    private SkillRepository skillRepository;

    @Override
    public void init() throws ServletException {
        Path base = Paths.get(getServletContext().getRealPath("/"));
        skillRepository = new SkillRepository(base);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Skill> skills = skillRepository.list();
        req.setAttribute("skills", skills);
        req.getRequestDispatcher("/skills.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("add".equals(action)) {
            String name = req.getParameter("name");
            String level = req.getParameter("level");
            skillRepository.add(new Skill(0, name, level));
        } else if ("update".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String level = req.getParameter("level");
            skillRepository.update(new Skill(id, name, level));
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            skillRepository.delete(id);
        }
        resp.sendRedirect(req.getContextPath() + "/skills");
    }
}
