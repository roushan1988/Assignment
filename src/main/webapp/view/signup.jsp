<%@ taglib prefix="s" uri="/struts-tags"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Coupondunia Assignment</title>
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
 
<h2>Add new User</h2>
<h2><s:property value='%{errorMessage}'/></h2>
 
<s:form method="post" action="signup">
    <table>
	    <tr>
	        <td><s:textfield key="label.Name" name="user.name"/></td>
	    </tr>
	    <tr>
	        <td><s:textfield key="label.Email" name="user.email"/></td>
	    </tr>
	    <tr>
	        <td><s:textfield key="label.Username" name="user.username"/></td>
	    </tr>
	    <tr>
	        <td><s:textfield key="label.Password" name="user.password"/></td>
	    </tr>
        <tr>
            <td><s:textfield key="label.ConfirmPassword"/></td>
        </tr>
	    <tr>
	        <td>
	        	<s:submit key="label.Add"></s:submit>
	        </td>
	    </tr>
        <span><h5><a href="/login.html">Login</a></h5></span>
	</table> 
</s:form>

</body>
</html>