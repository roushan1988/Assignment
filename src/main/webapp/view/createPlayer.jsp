<%--
  Created by IntelliJ IDEA.
  User: roushan
  Date: 18/3/15
  Time: 10:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
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

<h2>Add New Player</h2>
<h2><s:property value='%{errorMessage}'/></h2>

<s:form method="post" action="addPlayer">
    <table>
        <tr>
            <td><s:textfield label="Name" name="cricketPlayer.name"/></td>
        </tr>
        <tr>
            <td><s:textfield label="Description" name="cricketPlayer.description"/></td>
        </tr>
        <tr>
            <td><s:textfield name="cricketPlayer.dob" label="DOB" required="true" /></td>
        </tr>
        <tr>
            <td><s:textfield label="Price" name="cricketPlayer.price"/></td>
        </tr>
        <tr>
            <td><s:checkbox label="Batsman" name="cricketPlayer.batsman" fieldValue="true"/></td>
        </tr>
        <tr>
            <td><s:checkbox label="Bowler" name="cricketPlayer.bowler" fieldValue="true"/></td>
        </tr>
        <tr>
            <td><s:checkbox label="WicketKeeper" name="cricketPlayer.wicketKeeper" fieldValue="true"/></td>
        </tr>
        <tr>
            <td><s:checkbox label="AllRounder" name="cricketPlayer.allRounder" fieldValue="true"/></td>
        </tr>
        <tr>
            <td><s:textfield label="Overall Rank" name="cricketPlayer.overallRank"/></td>
        </tr>
        <tr>
            <td><s:textfield label="Batting Rank" name="cricketPlayer.battingInfo.battingRank"/></td>
        </tr>
        <tr>
            <td><s:textfield label="Batting Stats" name="cricketPlayer.battingInfo.battingStats"/></td>
        </tr>
        <tr>
            <td><s:textfield label="Bowling Rank" name="cricketPlayer.bowlingInfo.bowlingRank"/></td>
        </tr>
        <tr>
            <td><s:textfield label="Bowling Stats" name="cricketPlayer.bowlingInfo.bowlingStats"/></td>
        </tr>
        <tr>
            <td><s:textfield label="Fielding Rank" name="cricketPlayer.fieldingInfo.fieldingRank"/></td>
        </tr>
        <tr>
            <td><s:textfield label="WicketKeeping Rank" name="cricketPlayer.fieldingInfo.wicketKeepingRank"/></td>
        </tr>
        <tr>
            <td><s:textfield label="Fielding Stats" name="cricketPlayer.fieldingInfo.fieldingStats"/></td>
        </tr>
        <tr>
            <td>
                <s:submit label="Submit"></s:submit>
            </td>
        </tr>
    </table>
</s:form>

</body>
</html>
