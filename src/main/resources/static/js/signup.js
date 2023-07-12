const form = document.querySelector(".needs-validation");
const nameGroup = document.querySelector(".username-group");
const nameReg = /^[a-zA-Z0-9._$-]+$/;
const emailGroup = document.querySelector(".email-group");
const emailReg = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
const pwdGroup = document.querySelector(".pwd-group");

nameGroup.querySelector("#username").addEventListener('focusout', checkUsername);
function checkUsername(emptyFine = true) {
    let valid = true;
    const userInput = nameGroup.querySelector("#username");
    const username = userInput.value;
    const label = nameGroup.querySelector("label[for='username']");

    if (username.length == 0 && emptyFine) {
        empty(userInput, label, username);
        valid = false;
    }
    else if (!nameReg.test(username)) {
        invalidated(userInput, label, usernameChars);
        valid = false;
    }
    else if (username.length < 4 || username.length > 32) {
        invalidated(userInput, label, usernameSize);
        valid = false;
    }
    else {
        empty(userInput, label, username);
        valid = true;
    }
    return valid;
}

emailGroup.querySelector("#email").addEventListener('focusout', checkEmail);
function checkEmail(emptyFine = true) {
    let valid = true;
    const emailInput = emailGroup.querySelector("#email");
    const email = emailInput.value;
    const label = emailGroup.querySelector("label[for='email']");

    if (email.length == 0 && emptyFine) {
        empty(emailInput, label, email);
        valid = false;
    }
    else if (!emailReg.test(email)) {
        invalidated(emailInput, label, emailValid);
        valid = false;
    }
    else {
        empty(emailInput, label, email);
        valid = true;
    }
    return valid;
}

pwdGroup.querySelector("#pwd").addEventListener('focusout', checkPwd);
function checkPwd(emptyFine = true) {
    let valid = true;
    const pwdInput = pwdGroup.querySelector("#pwd");
    const pwd = pwdInput.value;
    const label = pwdGroup.querySelector("label[for='pwd']");

    if (pwd.length == 0 && emptyFine) {
        empty(pwdInput, label, pwd);
        valid = false;
    }
    else if (pwd.length < 8 || pwd.length > 128) {
        invalidated(pwdInput, label, pwdSize);
        valid = false;
    }
    else {
        empty(pwdInput, label, pwd);
        valid = true;
    }
    return valid;
}


function invalidated(input, label, text) {
    input.classList.remove("is-valid");
    input.classList.add("is-invalid");
    label.textContent = text;
    label.style.color = "red";
}
function empty(input, label, text) {
    input.classList.remove("is-invalid");
    input.classList.remove("is-valid");
    label.style.color = "inherit";
    label.textContent = text;
}
function validated(input, label, text) {
    input.classList.remove("is-invalid");
    input.classList.add("is-valid");
    label.style.color = "inherit";
    label.textContent = text;
}

form.addEventListener('submit', (event) => {
    if (!form.checkValidity() || !checkPwd(false) || !checkEmail(false) || !checkUsername(false)) {
      event.preventDefault();
      event.stopPropagation();
    }
    //form.classList.add('was-validated');
});