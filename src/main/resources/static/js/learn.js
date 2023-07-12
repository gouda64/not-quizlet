const roundLength = 5;
const stages = [newFlash, newTF, newMC, newWritten, newWritten];
const learningSpeed = 1;

const writTemplate = document.querySelector(".written");
const mcTemplate = document.querySelector(".mc");
const tfTemplate = document.querySelector(".tf");
const flashTemplate = document.querySelector(".flash");

const progress = Array(cards.length).fill(0);
const questionContainer = document.querySelector(".question-container");
const answerButt = document.querySelector(".submit-answer");
const wrongDisp = document.querySelector(".wrong");
const contButt = document.querySelector(".wrong .continue");
let questions = roundQuestions();
if (questions.length > 0) showQuestion(questions[0]);
else end();

answerButt.addEventListener("click", advance);
contButt.addEventListener("click", () => {
    wrongDisp.classList.add("d-none");
    answerButt.classList.remove("d-none");
    showQuestion(questions[0]);
});

//TODO: more efficient algo? sort cards by progress?
//TODO: test and adjust algo
function advance() {
    if (questions.length === 0) {
        questions = roundQuestions();
        if (questions.length === 0) {
            end();
            return;
        }
        newRound();
        showQuestion(questions[0]);
        return;
    }

    const answer = getAnswer(questionContainer.querySelector(".question"));
    if (questions[0].checkAnswer(answer)) {
        progress[questions[0].id] += questions[0].change;
        if (progress[questions[0].id] < 0) {
            progress[questions[0].id] = 0;
        }
        questions.splice(0, 1);
        if (questions.length > 0) showQuestion(questions[0]);
        else advance();
    }
    else {
        questions[0].change -= learningSpeed;
        questions.push(questions[0]);
        if (questions[0].type === "f") {
            questions.splice(0, 1);
            showQuestion(questions[0]);
        }
        else {
            showWrong(answer, questions[0]);
            questions.splice(0, 1);
        }
    }
}

function getAnswer(q) {
    if (q.classList.contains("written")) {
        return q.querySelector("input").value;
    }
    if (q.classList.contains("mc")) {
        let chosen = -1;
        const buttons = q.querySelectorAll("input");
        for (let i = 0; i < buttons.length; i++) {
            if (buttons[i].checked) {
                chosen = i;
                break;
            }
        }
        return chosen;
    }
    if (q.classList.contains("tf")) {
        if (!q.querySelector(".trueIn").checked && !q.querySelector(".falseIn").checked) {
            return null;
        }
        return q.querySelector(".trueIn").checked;
    }
    if (q.classList.contains("flash")) {
        if (!q.querySelector(".gotIn").checked && !q.querySelector(".noGotIn").checked) {
            return null;
        }
        return q.querySelector(".gotIn").checked;
    }
}

function showQuestion(q) {
    questionContainer.innerHTML = "";
    let newQ;
    switch (q.type) {
        case "w":
            newQ = writTemplate.cloneNode(true);
            break;
        case "mc":
            newQ = mcTemplate.cloneNode(true);
            newQ.querySelectorAll(".option").forEach((option, ind) => {
                option.querySelector("label").textContent = q.options[ind].term;
                option.querySelector("input").id = option.querySelector("input").id + 1;
                option.querySelector("label").htmlFor = option.querySelector("input").id;
            });
            break;
        case "tf":
            newQ = tfTemplate.cloneNode(true);
            newQ.querySelectorAll(".option").forEach((option, ind) => {
                option.querySelector("input").id = option.querySelector("input").id + 1;
                option.querySelector("label").htmlFor = option.querySelector("input").id;
            });
            break;
        case "f":
            newQ = flashTemplate.cloneNode(true);
            newQ.querySelectorAll(".option").forEach((option, ind) => {
                option.querySelector("input").id = option.querySelector("input").id + 1;
                option.querySelector("label").htmlFor = option.querySelector("input").id;
            });
            answerButt.classList.add("d-none");
            newQ.addEventListener("click", (event) => {
                questionContainer.querySelector(".prompt").textContent = questions[0].term;
                questionContainer.querySelectorAll(".option").forEach((option) => {
                    option.classList.remove("d-none")
                });
                answerButt.classList.remove("d-none");
            });
            break;
    }
    newQ.querySelector(".prompt").textContent = q.prompt;
    newQ.classList.remove("template");
    questionContainer.appendChild(newQ);
}

function roundQuestions() {
    const arr = [];
    for (let i = 0; i < stages.length; i++) {
        for (let j = 0; j < progress.length; j++) {
            if (Math.floor(progress[j]) === i) {
                let newQ = stages[i](cards[j]);
                newQ.id = j;
                newQ.change = 1;
                arr.push(newQ);
                if (arr.length >= roundLength) {
                    return arr;
                }
            }
        }
    }
    return arr;
}

function showWrong(ans, question) {
    questionContainer.innerHTML = "";
    answerButt.classList.add("d-none");

    wrongDisp.classList.remove("d-none");
    wrongDisp.querySelector(".prompt").textContent = question.prompt;
    switch (question.type) {
        case "w":
            wrongDisp.querySelector(".wrong-ans").textContent = ans;
            wrongDisp.querySelector(".right-ans").textContent = question.term;
            break;
        case "mc":
            wrongDisp.querySelector(".wrong-ans").textContent = question.options[ans].term;
            wrongDisp.querySelector(".right-ans").textContent = question.term;
            break;
        case "tf":
            wrongDisp.querySelector(".wrong-ans").textContent = ans;
            wrongDisp.querySelector(".right-ans").textContent = question.def + " = " + question.term;
            break;
    }
}

function newRound() {

}

function end() {
    questionContainer.innerHTML = "";
    answerButt.classList.add("d-none");
    questionContainer.textContent = "END";
}


