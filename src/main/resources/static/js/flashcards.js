let cards = cardSrc;
const carousel = document.querySelector(".carousel");
const forward = document.querySelector(".for-btn");
const backward = document.querySelector(".back-btn");
const shuffleButt = document.querySelector(".shuffle");
const progress = document.querySelector(".progress");

let index = 0;
carousel.textContent = cards[index].term;
let onTerm = true;
updateProgress();

carousel.onclick = flipCard;
shuffleButt.onclick = toggleShuffle;

function toggleShuffle() {
    if (shuffleButt.checked) {
        cards = [...cardSrc];
        arrShuffle(cards);
    }
    else {
        cards = cardSrc;
    }
    index = 0;
    carousel.textContent = cards[index].term;
    updateProgress();
}

function updateProgress() {
     progress.textContent = (index+1) + "/" + cards.length;
}

function flipCard() {
    if (onTerm) {
        onTerm = false;
        carousel.textContent = cards[index].definition;
    }
    else {
        onTerm = true;
        carousel.textContent = cards[index].term;
    }
}

forward.onclick = moveForward;
backward.onclick = moveBackward;

function moveForward() {
    index++;
    onTerm = true;
    if (index >= cards.length) {
        index = 0;
    }
    carousel.textContent = cards[index].term;
    updateProgress();
}

function moveBackward() {
    index--;
    onTerm = true;
    if (index < 0) {
        index = cards.length-1;
    }
    carousel.textContent = cards[index].term;
    updateProgress();
}

function arrShuffle(array) {
    for (let i = array.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        const temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}