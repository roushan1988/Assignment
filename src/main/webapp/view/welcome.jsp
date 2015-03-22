<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
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
    <sj:head jqueryui="true"/>
    <script type="text/javascript"
            src="https://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js"></script>
    <script type="text/javascript" src="/scripts/player.js"></script>
    <link href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
    <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
</head>
<body>
<s:set var="playerPriceMap" value="%{playerPriceMap}"/>

<h2>Welcome <s:property value='%{user.name}'/></h2> <span><h5><a href="/logout.html">Logout</a></h5></span>
<h5><a href="/addPlayerPage.html">Add New Player</a></h5>


<table>
    <tr>
        <td><s:textfield label="Remaining Balance" name="user.balanceAmount" value='%{user.balanceAmount}' disabled="true"/></td>
    </tr>
    <tr>
        <td><s:textfield label="Team Name" name="team.name"/></td>
    </tr>
    <tr>
        <td><s:select id="teamComposition" label="Team Composition" name="Team Composition" headerKey="" headerValue="Select One Team Configuration" list="%{teamCompositionLookup.getAll()}"/></td>
    </tr>
    <tr>
        <td><s:select name="playerDropdown" id="all_players" label="All Players" listKey="id" listValue="name" headerKey="" headerValue="Select One" list="%{cricketPlayerLookup.getAll()}"/></td>
        <td><s:textfield label="Price" id="all_players_price" value="" disabled="true"/></td>
        <td><input type="button" id="add_all_players" name="add" value="Add"/></td>
    </tr>
    <tr>
        <td><s:select name="playerDropdown" id="batsmen" label="Batsmen" listKey="id" listValue="name" headerKey="" headerValue="Select One" list="%{cricketPlayerLookup.getBatsmanList()}"/></td>
        <td><s:textfield label="Price" id="batsmen_price" value="" disabled="true"/></td>
        <td><input type="button" id="add_batsmen" name="add" value="Add"/></td>
    </tr>
    <tr>
        <td><s:select name="playerDropdown" id="bowlers" label="Bowlers" listKey="id" listValue="name" headerKey="" headerValue="Select One" list="%{cricketPlayerLookup.getBowlerList()}"/></td>
        <td><s:textfield label="Price" id="bowlers_price" value="" disabled="true"/></td>
        <td><input type="button" id="add_bowlers"  name="add" value="Add"/></td>
    </tr>
    <tr>
        <td><s:select name="playerDropdown" id="wicket_keepers" label="WicketKeepers" listKey="id" listValue="name" headerKey="" headerValue="Select One" list="%{cricketPlayerLookup.getWicketKeeperList()}"/></td>
        <td><s:textfield label="Price" id="wicket_keepers_price" value="" disabled="true"/></td>
        <td><input type="button" id="add_wicket_keepers" name="add" value="Add"/></td>
    </tr>
    <tr>
        <td><s:select name="playerDropdown" id="all_rounders" label="All Rounders" listKey="id" listValue="name" headerKey="" headerValue="Select One" list="%{cricketPlayerLookup.getAllRounderList()}"/></td>
        <td><s:textfield label="Price" id="all_rounders_price" value="" disabled="true"/></td>
        <td><input type="button" id="add_all_rounders" name="add" value="Add"/></td>
    </tr>
</table>

<h2>Team Players</h2>

<s:iterator var="mapKey" value="%{playerPriceMapKeySet}" >
    <s:hidden id="%{'player'+#mapKey}" name="%{'player'+#mapKey}" value="%{#playerPriceMap.get(#mapKey)}"></s:hidden>
</s:iterator>
<s:form method="post" action="createTeam">
    <table>
        <tr>
            <td><s:textfield id="teamName" label="Team Name" name="teamName" readonly="true"/></td>&nbsp;&nbsp;
            <td><s:textfield label="Remaining Balance" name="balanceAmount" value='%{user.balanceAmount}' disabled="true"/></td>
        </tr>
        <s:iterator var="counter" begin="1" end="8" >
            <tr>
                <td><label for="player${counter}_name">Player${counter} Name</label>&nbsp;<input type="text" id="player${counter}_name" readonly="true"/></td>&nbsp;&nbsp;&nbsp;
                <td><label for="player${counter}_id">Player${counter} ID</label>&nbsp;<input type="text" id="player${counter}_id" name="player${counter}_id" readonly="true"/></td>&nbsp;&nbsp;&nbsp;
                <td><label for="player${counter}_type">Player${counter} Type</label>&nbsp;<input type="text" id="player${counter}_type" name="player${counter}_type" readonly="true"/></td>&nbsp;&nbsp;&nbsp;
                <td><input id="player${counter}_captain" type="radio" name="captain_id">Captain</td>
                <td><input type="button" id="player${counter}_remove" name="remove" value="Remove"/></td><br>
            </tr>
        </s:iterator>
        <tr>
            <td>
                <s:submit label="Submit" onclick="return validateTeam();"></s:submit>
            </td>
        </tr>
    </table>
    <s:hidden name="teamId" value=""/>
    <s:hidden name="batsmanCount" value=""/>
    <s:hidden name="bowlerCount" value=""/>
    <s:hidden name="wicketKeeperCount" value=""/>
    <s:hidden name="allRounderCount" value=""/>
</s:form>

<sj:dialog id="typeSelectorDialog" autoOpen="false" modal="true" title="Type Selector">
    <table>
        <div id="selectedPlayerId" name="selectedPlayerId" value=""></div>
        <div id="selectedPlayerName" name="selectedPlayerName" value=""></div>
        <div id="dataContent"/>
        <tr id="dialogButtons">
            <td><input type="button" id="ok" name="ok" value="Ok"/></td>
            <td><input type="button" id="cancel" name="cancel" value="cancel"/></td>
        </tr>
    </table>
</sj:dialog>

</body>
</html>