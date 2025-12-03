<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.example.devprofile.model.Profile" %>
<%
    Profile profile = (Profile) request.getAttribute("profile");
    if (profile == null) {
        com.example.devprofile.repository.ProfileRepository repo =
                new com.example.devprofile.repository.ProfileRepository(java.nio.file.Paths.get(application.getRealPath("/")));
        profile = repo.load();
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar perfil</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<h2>Editar perfil</h2>
<form action="profile" method="post" enctype="multipart/form-data" class="form">
    <label>Banner
        <input type="text" name="banner" value="<%= profile.getBanner() %>" />
    </label>
    <label>Nombre
        <input type="text" name="name" value="<%= profile.getName() %>" />
    </label>
    <label>Bio
        <textarea name="bio"><%= profile.getBio() %></textarea>
    </label>
    <label>Experiencia
        <textarea name="experience"><%= profile.getExperience() %></textarea>
    </label>
    <label>Contacto
        <input type="text" name="contact" value="<%= profile.getContact() %>" />
    </label>
    <label>Foto de perfil
        <input type="file" name="photo" accept="image/*" />
    </label>
    <% if (profile.getPhotoPath() != null && !profile.getPhotoPath().isEmpty()) { %>
        <img src="<%= profile.getPhotoPath() %>" alt="Foto actual" class="thumb" />
    <% } %>
    <button type="submit">Guardar cambios</button>
</form>

<div class="nav">
    <a href="./">Inicio</a> | <a href="skills">Habilidades</a>
</div>
</body>
</html>
