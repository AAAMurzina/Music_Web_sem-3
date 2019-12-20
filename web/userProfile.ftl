<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User profile</title>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <link href="styles/main_page.css" rel="stylesheet">
    <link href="styles/user_profile.css" rel="stylesheet">
</head>
<body>
<#include "/header.ftl">
<div class="row">
    <p class="profile_name">Profile</p>
</div>
<div class="row">
    <div class="col-sm">
        <p></p>
    </div>
    <div class="fc col-sm col-sm-pull-1">
        <div class="ava_card">
            <img class="ava img-thumbnail" src="pictures/ava.jpg" align="left" alt="Set photo">
        </div>
        <div>
            <label>Your login:</label>
            <p class="user_data border-top border-bottom">${login!""}</p>
        </div>
        <div>
            <label>Name:</label>
            <p class="user_data border-top border-bottom">${name!""}</p>
        </div>
        <div>
            <label>Email:</label>
            <p class="user_data border-top border-bottom">${email!""}</p>
        </div>
        <div>
            <label>Town:</label>
            <p class="user_data border-top border-bottom">${town!""}</p>
        </div>
        <div>
            <label>Male/female:</label>
            <p class="user_data border-top border-bottom">${gender!""}</p>
        </div>
        <div>
            <label>Birth date:</label>
            <p class="user_data border-top border-bottom">${birth_date!""}</p>
        </div>
        <div>
            <label>About yourself:</label>
            <p class="user_data border-top border-bottom">${inf_about_user!""}</p>
        </div>
        <div>
            <label>Favorite music genre:</label>
            <p class="user_data border-top border-bottom">${favorite_music!""}</p>
        </div>
        <div>
            <label>Favorite music group or/and artist:</label>
            <p class="user_data border-top border-bottom">${favorite_artist!""}</p>
        </div>
    </div>
    <div class="col-sm"></div>
</div>
</body>
</html>