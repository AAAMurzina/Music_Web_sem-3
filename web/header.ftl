<header>
    <div class="navbar navbar-dark bg-dark shadow-sm">
        <div class="container">
            <div class="dropdown open">
                <button class="btn btn-primary btn-lg dropdown-toggle" type="button" id="dropdownMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">Menu</button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenu">
                    <form action="/profile" method="get">
                        <button class="dropdown-item" type="submit" id="user_profile">My profile</button>
                    </form>
                    <form action="/index" method="get">
                        <button class="dropdown-item" type="submit" id="main_page">Main page</button>
                    </form>
                    <button class="dropdown-item" type="submit" id="forum_page">Forum</button>
                    <button class="dropdown-item" type="button" id="user_page">Edit profile</button>
                    <button class="dropdown-item" type="button" id="open_topics">Open topics</button>
                    <button class="dropdown-item" type="button" id="to_open_topocs">To open a topic</button>
                    <button class="dropdown-item" type="button" id="favorite_songs">Favorite Songs</button>
                    <button class="dropdown-item" type="button" id="favorite_topics">Favorite Topics</button>
                    <form action="/new_song" method="get">
                        <button class="dropdown-item" type="submit" id="add_song">Add a song</button>
                    </form>
                </div>
            </div>
        <#if session!false == true>
            <div class="fr row flex-xl-nowrap">
                <form action="/logout" method="get">
                    <button type="submit" class="b btn btn-lg btn-primary btn-sm" id="sign_in">Sign out</button>
                </form>
            </div>
        <#else>
            <div  class="fr row flex-xl-nowrap">
                <input class="form-control py-2" type="search" placeholder="Search..." id="example-search-input">
                <button class="mb btn btn-lg btn-primary btn-sm" type="button"></button>
                <form action="/login" method="get">
                    <button type="submit" class="b btn btn-lg btn-primary btn-sm" id="sign_in" formaction="login">Sign in</button>
                </form>
                <form action="/reg" method="get">
                    <button type="submit" class="b btn btn-lg btn-primary btn-sm" id="sign_up" formaction="reg">Sign up</button>
                </form>
            </div>
        </#if>
        </div>
    </div>
</header>
