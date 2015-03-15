<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Spring-4 + Struts-3 + Hibernate Integration Demo</title>
    <style>
        table.list
        {
            border-collapse:collapse;
            width: 40%;
        }
        table.list, table.list td, table.list th
        {
            border:1px solid gray;
            padding: 5px;
        }
    </style>
</head>
<body>

<h2>Welcome <s:property value='%{user.name}'/></h2> <span><h5><a href="/logout">Logout</a></h5></span>



</body>
</html>