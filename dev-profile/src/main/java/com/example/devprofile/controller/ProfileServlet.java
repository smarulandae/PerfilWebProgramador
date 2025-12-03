package com.example.devprofile.controller;

import com.example.devprofile.model.Profile;
import com.example.devprofile.repository.ProfileRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet(name = "ProfileServlet", urlPatterns = {"/", "/profile"})
@MultipartConfig
public class ProfileServlet extends HttpServlet {

    private ProfileRepository profileRepository;

    @Override
    public void init() throws ServletException {
        Path base = Paths.get(getServletContext().getRealPath("/"));
        profileRepository = new ProfileRepository(base);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        Profile profile = profileRepository.load();
        req.setAttribute("profile", profile);
        if ("/profile".equals(path)) {
            req.getRequestDispatcher("/profile.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String bio = req.getParameter("bio");
        String experience = req.getParameter("experience");
        String contact = req.getParameter("contact");
        String banner = req.getParameter("banner");

        Profile current = profileRepository.load();
        current.setName(name);
        current.setBio(bio);
        current.setExperience(experience);
        current.setContact(contact);
        current.setBanner(banner);

        Part photoPart = req.getPart("photo");
        if (photoPart != null && photoPart.getSize() > 0) {
            String uploadsPath = getServletContext().getRealPath("/uploads");
            File uploadsDir = new File(uploadsPath);
            if (!uploadsDir.exists()) uploadsDir.mkdirs();
            String fileName = System.currentTimeMillis() + "_" + photoPart.getSubmittedFileName();
            File file = new File(uploadsDir, fileName);
            photoPart.write(file.getAbsolutePath());
            current.setPhotoPath("uploads/" + fileName);
        }

        profileRepository.save(current);
        resp.sendRedirect(req.getContextPath() + "/profile");
    }
}
