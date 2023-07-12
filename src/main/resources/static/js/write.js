let pool = [...cards];

const answeringDisp = document.querySelector(".answering");
const wrongDisp = document.querySelector(".wrong");
const summaryDisp = document.querySelector(".section-summary");
const endDisp = document.querySelector(".end");

answeringDisp.querySelector(".submit-answer").onclick = flipCard;
wrongDisp.querySelector(".continue").onclick = moveForward;
summaryDisp.querySelector(".continue").onclick = showCard;
endDisp.querySelector(".continue").onclick = reset;

let index = 0;
showCard();

function flipCard() {
    let ans = answeringDisp.querySelector("#answer").value;
    if (ans === pool[index].term) {
        pool.splice(index, 1);
        index--;
        moveForward();
    }
    else {
        showWrong(ans);
    }
}

function showWrong(ans) {
    answeringDisp.classList.add("d-none");
    summaryDisp.classList.add("d-none");

    wrongDisp.classList.remove("d-none");
    wrongDisp.querySelector(".prompt").textContent = pool[index].definition;
    wrongDisp.querySelector(".wrong-ans").textContent = ans;
    wrongDisp.querySelector(".right-ans").textContent = pool[index].term;
}
function showCard() {
    wrongDisp.classList.add("d-none");
    summaryDisp.classList.add("d-none");

    answeringDisp.classList.remove("d-none");
    answeringDisp.querySelector(".side-disp").textContent = pool[index].definition;
    answeringDisp.querySelector("#answer").value = "";
    answeringDisp.querySelector("#answer").focus();
}
function showSummary() {
    wrongDisp.classList.add("d-none");
    answeringDisp.classList.add("d-none");

    summaryDisp.classList.remove("d-none");

}
function reset() {
    endDisp.classList.add("d-none");
    pool = [...cards];
    answeringDisp.querySelector("#answer").value = "";
    index = 0;
    showCard();
}


function moveForward() {
    if (pool.length == 0) {
        wrongDisp.classList.add("d-none");
        answeringDisp.classList.add("d-none");
        summaryDisp.classList.add("d-none");
        endDisp.classList.remove("d-none");
        return;
    }

    index++;
    if (index >= pool.length) {
        index = 0;
        showSummary();
        return;
    }
    showCard();
}