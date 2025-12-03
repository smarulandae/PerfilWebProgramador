<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.example.devprofile.model.Profile" %>
<%
    com.example.devprofile.repository.ProfileRepository repo =
            new com.example.devprofile.repository.ProfileRepository(java.nio.file.Paths.get(application.getRealPath("/")));
    Profile profile = repo.load();
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Inicio - Perfil</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<div class="banner">
    <h1><%= profile.getBanner() %></h1>
</div>

<div class="nav">
    <a href="profile">Editar perfil</a> |
    <a href="skills">Gestionar habilidades</a>
</div>

<div class="profile-summary">
    <div class="photo">
        <% if (profile.getPhotoPath() != null && !profile.getPhotoPath().isEmpty()) { %>
            <img src="<%= profile.getPhotoPath() %>" alt="Foto de perfil" />
        <% } else { %>
            <div class="placeholder">Sin foto</div>
        <% } %>
    </div>
    <div class="info">
        <p><strong>Nombre:</strong> <%= profile.getName() == null ? "" : profile.getName() %></p>
        <p><strong>Bio:</strong> <%= profile.getBio() == null ? "" : profile.getBio() %></p>
        <p><strong>Experiencia:</strong> <%= profile.getExperience() == null ? "" : profile.getExperience() %></p>
        <p><strong>Contacto:</strong> <%= profile.getContact() == null ? "" : profile.getContact() %></p>
    </div>
</div>
<script src="js/app.js"></script>
</body>
</html>
