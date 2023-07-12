const form = document.querySelector(".needs-validation");
form.addEventListener('submit', (event) => {
    if (!form.checkValidity() || !checkTitle() || !checkCards()) {
      event.preventDefault();
      event.stopPropagation();
    }
    //form.classList.add('was-validated');
 });

function checkCards() {
    let numCards = 0;
    document.querySelectorAll(".card-form:not(.template)").forEach((card, ind) => {
        if (card.querySelector("#term" + ind).value.length > 0 || card.querySelector("#definition" + ind).value.length > 0) {
            numCards++;
        }
    });
    const warning = document.querySelector(".warning");
    if (numCards >= 3) {
        warning.classList.remove("d-block");
        return true;
    }
    warning.classList.add("d-block");
    return false;
}


document.querySelector("#title").addEventListener('focusout', checkTitle);
function checkTitle() {
    let valid = true;
    const nameInput = document.querySelector("#title");
    const name = nameInput.value;
    const label = document.querySelector("label[for='title']");
    if (name.length <= 0) {
        invalidated(nameInput, label, setName);
        valid = false;
    }
    else {
        empty(nameInput, label, setTitle);
        valid = true;
    }
    return valid;
}

let numCards = 0;
const cardTemplate = document.querySelector(".card-form.template");
const cardButton = document.querySelector(".card-button");
for (let i = 0; i < 3; i++) {
    addCard();
}

cardButton.onclick = addCard;
function addCard() {
  let newCard = cardTemplate.cloneNode(true);
  newCard.classList.remove("template");
  newCard.querySelectorAll("label").forEach((label) => label.htmlFor = label.htmlFor + numCards);
  newCard.querySelectorAll("input").forEach((input) => input.id = input.id + numCards);
  newCard.querySelector(".term").name = "term";
  newCard.querySelector(".definition").name = "definition";

  numCards++;

  cardButton.before(newCard);
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