<%@tag language="java" pageEncoding="UTF-8" %>
<%@attribute name="title" required="true" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
        <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
        <title>${title}</title>
        <jsp:doBody />
    </head>
    <body>

