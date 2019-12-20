<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="styles/main_page.css">
    <link rel="stylesheet" href="styles/add_song.css">
    <title>Add song</title>

</head>

<#include "/header.ftl">

<body>
<form action="new_song" method="post" enctype="multipart/form-data">

    <br>
    <br>
    <div class="row">
        <div class="col-sm col-sm-push-5">
            <h1>Add a song</h1>
        </div>
        <div class="col"></div>
    </div>
    <br>
    <br>
    <div class="row">
        <div class="col-sm col-sm-push-2">
            <div class="ava_default">
                <img class="ava img-thumbnail" src="pictures/cover.jpg" align="left" alt="Set photo">
            </div>
        </div>
        <div class="col">
            <div class="row">
                <label>
                    Add author:
                    <textarea id="author" name="author"></textarea>
                </label>
            </div>
            <div class="row">
                <label>
                    Add title:&nbsp&nbsp&nbsp&nbsp
                    <textarea id="title" name="title"></textarea>
                </label>
            </div>
            <div class="row">
                <label>
                    Add genre:
                    <textarea id="genre" name="genre"></textarea>
                </label>
            </div>
        </div>
        <div class="col-sm col-sm-pull-1">
            <label>
                Add text:
                <textarea class="text" name="lyrics"></textarea>
            </label>
        </div>
    </div>

    <div class="row">
        <div class="col-sm col-sm-push-2">
            <h3>Add a cover:</h3>
            <p><input type="file" name="cover" accept="image/*">
        </div>
        <div class="col"></div>
    </div>
    <br>
    <div class="row">
        <div class="col-sm col-sm-push-2">
            <h3>Add an audio file:</h3>
            <p><input type="file" name="audio" accept="audio/*">
        </div>
        <div class="col"></div>
    </div>
    <div class="row">
        <div class="col"></div>
        <div class="col-sm col-sm-push-1">
            <button type="submit" class="add_btn btn btn-lg btn-dark">Add a song</button>
        </div>
        <div class="col"></div>
    </div>
</form>
</body>
</html>