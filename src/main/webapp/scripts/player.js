$(function(){
    $("#dialogButtons").hide();
    $('[name="playerDropdown"]').each(function(index, element){
        $(element).change(function(){
            var playerId = $(element).find(':selected').val();
            $("#"+$(element).attr("id")+"_price").val($("#player"+playerId).val());
        })
    });
    $('[name="add"]').each(function(index, element){
        $(element).click(function(){
            var playerId = getPlayerId($(element));
            var playerName = getPlayerName($(element));
            if(Number($("#user_balanceAmount").val()) - Number($("#player"+playerId).val()) > 0){
                $.ajax({
                    url: "/fetchTypes.html",
                    data: {
                        playerId: playerId,
                        ajax:true
                    },
                    type: 'GET',
                    success: function(result){
                        var types = result.split(",");
                        if(types.length > 1){
                            $("#typeSelectorDialog").dialog(opt).dialog("open");
                            $("#dataContent").html(putData(playerId, playerName, types));
                            $("#dialogButtons").show();
                            initDialog();
                        }else{
                            getEmptyPlayerSlotAndPopulate(playerId, playerName, types[0]);
                        }
                    }});
            }else{
                alert("You dont have enough balance to but this player");
            }
        });
    });
    $('[name="remove"]').each(function(){
        $(this).click(function(){
            var idCounter = $(this).attr("id").substring(6,7);
            var playerId = $("#player"+idCounter+"_id").val();
            $("#player"+idCounter+"_id").val("");
            $("#player"+idCounter+"_name").val("");
            $("#player"+idCounter+"_type").val("");
            makePlayerVisibleInDropdown(playerId);
            $("#user_balanceAmount").val(Number($("#user_balanceAmount").val()) + Number($("#player"+playerId).val()));
        });
    });
    $("#team_name").change(function(){
        $("#teamName").val($("#team_name").val());
    });
});

var opt = {
    autoOpen: false,
    modal: true,
    title: 'Select Player Type'
};

function putData(playerId, playerName, types){
    $("#selectedPlayerId").val(playerId);
    $("#selectedPlayerName").val(playerName);
    var html = '';
    html = html + '<label>Please select one of below player types<br><br></label>';
    for (i = 0; i < types.length; i++) {
        html = html + "<input type='radio' name='playerSelectionType' required='true' value="+types[i]+">"+types[i]+"<br>";
    }
    html = html + "<br>";
    return html;
}

function initDialog(){
    $("#ok").unbind();
    $("#ok").click(function(){
        if($('[name="playerSelectionType"]:checked').val()==undefined){
            alert("Select one player type");
        } else {
            getEmptyPlayerSlotAndPopulate();
            $("#dataContent").html("");
            $("#dialogButtons").hide();
            $("#typeSelectorDialog").dialog("close");
        }
    });
    $("#cancel").unbind();
    $("#cancel").click(function(){
        $("#dataContent").html("");
        $("#dialogButtons").hide();
        $("#typeSelectorDialog").dialog("close");
    });
}

function getEmptyPlayerSlotAndPopulate(playerId, playerName, playerType){
    var id;
    for (i = 1; i < 9; i++) {  // total 8 players
        if($("#player"+i+"_id").val() == ""){
            id = i;
            break;
        }
    }
    if (id==undefined){
        alert("All players already selected");
    } else {
        if(playerId == undefined){
            $("#player"+i+"_id").val($("#selectedPlayerId").val());
            playerId = $("#selectedPlayerId").val();
        } else {
            $("#player"+i+"_id").val(playerId);
        }
        if(playerName == undefined){
            $("#player"+i+"_name").val($("#selectedPlayerName").val());
        } else {
            $("#player"+i+"_name").val(playerName);
        }
        if(playerType == undefined){
            $("#player"+i+"_type").val($('[name="playerSelectionType"]:checked').val());
        } else {
            $("#player"+i+"_type").val(playerType);
        }
        hidePlayerFromDropdown(playerId);
        $("#user_balanceAmount").val(Number($("#user_balanceAmount").val()) - Number($("#player"+playerId).val()));
    }
}

function hidePlayerFromDropdown(playerId){
    if($('#all_players').find("[value='" + playerId + "']") != undefined){
        $('#all_players').find("[value='" + playerId + "']").attr('hidden','hidden');
    }
    if($('#batsmen').find("[value='" + playerId + "']") != undefined){
        $('#batsmen').find("[value='" + playerId + "']").attr('hidden','hidden');
    }
    if($('#bowlers').find("[value='" + playerId + "']") != undefined){
        $('#bowlers').find("[value='" + playerId + "']").attr('hidden','hidden');
    }
    if($('#wicket_keepers').find("[value='" + playerId + "']") != undefined){
        $('#wicket_keepers').find("[value='" + playerId + "']").attr('hidden','hidden');
    }
    if($('#all_rounders').find("[value='" + playerId + "']") != undefined){
        $('#all_rounders').find("[value='" + playerId + "']").attr('hidden','hidden');
    }

    $('#all_players option:first-child').attr("selected", "selected");
    $('#batsmen option:first-child').attr("selected", "selected");
    $('#bowlers option:first-child').attr("selected", "selected");
    $('#wicket_keepers option:first-child').attr("selected", "selected");
    $('#all_rounders option:first-child').attr("selected", "selected");

    resetPrices();
}

function resetPrices(){
    $('#all_players_price').val("");
    $('#batsmen_price').val("");
    $('#bowlers_price').val("");
    $('#wicket_keepers_price').val("");
    $('#all_rounders_price').val("");
}

function makePlayerVisibleInDropdown(playerId){
    if($('#all_players').find("[value='" + playerId + "']") != undefined){
        $('#all_players').find("[value='" + playerId + "']").removeAttr('hidden');
    }
    if($('#batsmen').find("[value='" + playerId + "']") != undefined){
        $('#batsmen').find("[value='" + playerId + "']").removeAttr('hidden');
    }
    if($('#bowlers').find("[value='" + playerId + "']") != undefined){
        $('#bowlers').find("[value='" + playerId + "']").removeAttr('hidden');
    }
    if($('#wicket_keepers').find("[value='" + playerId + "']") != undefined){
        $('#wicket_keepers').find("[value='" + playerId + "']").removeAttr('hidden');
    }
    if($('#all_rounders').find("[value='" + playerId + "']") != undefined){
        $('#all_rounders').find("[value='" + playerId + "']").removeAttr('hidden');
    }
}

function getPlayerId(element){
    var id = element.attr('id');
    var dropdownId = id.substring(4);
    var playerId = $('#'+dropdownId).find(':selected').val();
    return playerId;
}

function getPlayerName(element){
    var id = element.attr('id');
    var dropdownId = id.substring(4);
    var playerName = $('#'+dropdownId).find(':selected').text();
    return playerName;
}

function validateTeam(){
    var teamCompositionString = $("#teamComposition").find(':selected').val();
    if(teamCompositionString==""){
        alert("Please select team composition");
        return false;
    }else{
        var bat_length = "batsmenCount".length;
        var bow_length = "bowlerCount".length;
        var wic_length = "wicketKeeperCount".length;
        var all_length = "allRounderCount".length;
        var bat_start = teamCompositionString.indexOf("batsmanCount");
        var bow_start = teamCompositionString.indexOf("bowlerCount");
        var wic_start = teamCompositionString.indexOf("wicketKeeperCount");
        var all_start = teamCompositionString.indexOf("allRounderCount");
        $("#batsmanCount").val(parseInt(teamCompositionString.substring(bat_start+bat_length+1, bat_start+bat_length+2)));
        $("#bowlerCount").val(parseInt(teamCompositionString.substring(bow_start+bow_length+1, bow_start+bow_length+2)));
        $("#wicketKeeperCount").val(parseInt(teamCompositionString.substring(wic_start+wic_length+1, wic_start+wic_length+2)));
        $("#allRounderCount").val(parseInt(teamCompositionString.substring(all_start+all_length+1, all_start+all_length+2)));
    }
    if($("#createTeam_teamName").val() == ""){
        alert("Please add team name");
        return false;
    }
    if($('[name="captain_id"]:checked').val()==undefined){
        alert("Please select a captain");
        return false;
    }else{
        var playerId = $('[name="captain_id"]:checked').attr("id").substring(6,7);
        $('[name="captain_id"]:checked').val($("#player"+playerId+"_id").val());
    }
    var id;
    for (i = 1; i < 9; i++) {  // total 8 players
        if($("#player"+i+"_id").val() == ""){
            id = i;
            break;
        }
    }
    if(id != undefined){
        alert("Please select all players");
        return false;
    }
    return true;
}