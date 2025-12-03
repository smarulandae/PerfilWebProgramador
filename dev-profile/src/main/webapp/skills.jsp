<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.devprofile.model.Skill" %>
<%
    List<Skill> skills = (List<Skill>) request.getAttribute("skills");
    if (skills == null) {
        com.example.devprofile.repository.SkillRepository repo =
                new com.example.devprofile.repository.SkillRepository(java.nio.file.Paths.get(application.getRealPath("/")));
        skills = repo.list();
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Habilidades</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<h2>Habilidades</h2>

<h3>Agregar nueva habilidad</h3>
<form action="skills" method="post" class="form-inline">
    <input type="hidden" name="action" value="add" />
    <label>Nombre
        <input type="text" name="name" required />
    </label>
    <label>Nivel
        <select name="level">
            <option>Básico</option>
            <option>Intermedio</option>
            <option>Avanzado</option>
        </select>
    </label>
    <button type="submit">Agregar</button>
</form>

<h3>Listado</h3>
<table class="table">
    <thead>
    <tr><th>ID</th><th>Edición</th><th>Nivel</th><th>Acciones</th></tr>
    </thead>
    <tbody>
    <% for (Skill s : skills) { %>
        <tr>
            <td><%= s.getId() %></td>
            <td>
                <form action="skills" method="post" class="form-inline">
                    <input type="hidden" name="action" value="update" />
                    <input type="hidden" name="id" value="<%= s.getId() %>" />
                    <input type="text" name="name" value="<%= s.getName() %>" />
                    <select name="level">
                        <option <%= "Básico".equals(s.getLevel()) ? "selected" : "" %>>Básico</option>
                        <option <%= "Intermedio".equals(s.getLevel()) ? "selected" : "" %>>Intermedio</option>
                        <option <%= "Avanzado".equals(s.getLevel()) ? "selected" : "" %>>Avanzado</option>
                    </select>
                    <button type="submit">Guardar</button>
                </form>
            </td>
            <td><%= s.getLevel() %></td>
            <td>
                <form action="skills" method="post" class="form-inline">
                    <input type="hidden" name="action" value="delete" />
                    <input type="hidden" name="id" value="<%= s.getId() %>" />
                    <button type="submit" class="danger">Eliminar</button>
                </form>
            </td>
        </tr>
    <% } %>
    </tbody>
</table>

<div class="nav">
    <a href="./">Inicio</a> | <a href="profile">Perfil</a>
</div>
<script src="js/app.js"></script>
</body>
</html>
