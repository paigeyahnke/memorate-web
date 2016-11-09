function validate() {
    clearErrors();

    var password1 = document.getElementById("newPassword");
    var password2 = document.getElementById("validatePassword");

    var submit = document.getElementById("submitSignUp");

    if (password1.value === password2.value) {
        submit.disabled = false;
    } else {
        submit.disabled = true;
        var errorMessage = document.createElement("div");
        errorMessage.setAttribute("class", "error");
        errorMessage.appendChild(document.createTextNode("Passwords must match."));

        password2.parentNode.insertBefore(errorMessage, password2.nextSibling);
    }
}

function  clearErrors() {
    var errors = document.getElementsByClassName("error");
    while (errors.length > 0) {
        errors[0].parentNode.removeChild(errors[0]);
    }
}