<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>World Adventures Airlines</title>
    <link rel="stylesheet" href="resources/css/normalize.css"/>
    <link rel="stylesheet" href="resources/css/theme.css"/>
</head>
<body>
<!-- <h2>Welcome to World Adventures Airlines!</h2> !-->

<div class="container">
    <div class="title">Add a passenger</div>

    <%
        if (request.getAttribute("errors") != null) {
    %>
    <fieldset id="error_fieldset">
        <legend align="center">Errors</legend>
        <ul>
            <% if (request.getAttribute("firstName_error") != null) {%>
            <li class="error">First name error</li>
            <%}%>

            <% if (request.getAttribute("lastName_error") != null) {%>
            <li class="error">Last name error</li>
            <%}%>

            <% if (request.getAttribute("date_format_error") != null) {%>
            <li class="error">Data of birth invalid</li>
            <%}%>
        </ul>
    </fieldset>
    <%
        }
    %>

    <form action="AddPassenger" method="post">
        <fieldset>
            <legend align="center">Passenger detail</legend>
            </br>
            <div class="inputField">
                <label for="first_name" class="inputLabel">First name: </label>
                <input id="first_name" name="first_name" value="<%= request.getAttribute("first_name")%>" type="text"/>
            </div>
            </br>
            <div class="inputField">
                <label for="last_name" class="inputLabel">Last name: </label>
                <input id="last_name" name="last_name" value="<%= request.getAttribute("last_name")%>" type="text"/>
            </div>
            </br>
            <div class="inputField">
                <label for="date_birth" class="inputLabel">Date of birth: </label>
                <input id="date_birth" name="date_birth" value="<%= request.getAttribute("date_birth")%>" type="text"/>
            </div>
            </br>
            <div class="inputField">
                <label for="gender" class="inputLabel">Gender: </label>
                <select id="gender" name="gender">
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                </select>
            </div>
        </fieldset>
        <div class="inputField" id="submitField">
            <input id="submitBtn" type="submit" value="Add new passenger">
        </div>
    </form>

</div>
</body>
</html>
