var ok1 = false;
var ok2 = false;
var ok3 = false;

var check = function() {
    var pass = document.getElementById('password').value;
    if (pass === document.getElementById('confirm_password').value) {
        document.getElementById('matching_message').style.color = 'green';
        document.getElementById('matching_message').innerHTML = 'Passwords matching';
        ok1 = true;
    } else {
        document.getElementById('matching_message').style.color = 'red';
        document.getElementById('matching_message').innerHTML = 'Passwords not matching';
        ok1 = false;
    }
    if(pass.length < 6 || !re(pass)) {
        document.getElementById('correct_message').style.color = 'red';
        document.getElementById('correct_message').innerHTML = 'Password should\'t be less then 6 symbols' +
            'and should contain numbers and letters';
        document.getElementById('sometext').innerHTML = '<br>';
        ok2 = false;
    }
    else {
        document.getElementById('correct_message').innerHTML = '';
        document.getElementById('sometext').innerHTML = '';
        ok2 = true;
    }
};

var re = function (s) {
    var reg = /[1-9][A-z]|[A-z][1-9]/;
    return reg.test(s)
};

var ok = function () {
    document.getElementById('submit').disabled = !(ok1 && ok2 && ok3);
};