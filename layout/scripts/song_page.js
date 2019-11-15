ok1 = false;

var check = function() {
    var comment = document.getElementById('comment').value;
    if (comment.length < 10) {
        document.getElementById('too_much_symbols').innerHTML = '';
        ok1 = true;
    } else {
        document.getElementById('too_much_symbols').style.color = 'red';
        document.getElementById('too_much_symbols').innerHTML = 'Too much symbols';
        ok1 = false;
    }
    console.log("Key up");
};

var ok = function () {
    document.getElementById('submit').disabled = !(ok1);
    document.getElementById('comment').readonly = "false";
};