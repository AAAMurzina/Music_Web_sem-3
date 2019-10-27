var check = function() {
    if (document.getElementById('password').value ===
        document.getElementById('confirm_password').value) {
        document.getElementById('matching_message').style.color = 'green';
        document.getElementById('matching_message').innerHTML = 'Passwords matching';
    } else {
        document.getElementById('matching_message').style.color = 'red';
        document.getElementById('matching_message').innerHTML = 'Passwords not matching';
    }
    if(document.getElementById('password').value.length < 6) {
        document.getElementById('correct_message').style.color = 'red';
        document.getElementById('correct_message').innerHTML = 'Password should\'t be less then 6 symbols';
    }
    else {
        document.getElementById('correct_message').innerHTML = '';
    }
};