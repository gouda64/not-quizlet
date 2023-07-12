let tLength = cards.length;
let questions = genTest(tLength);

const form = document.querySelector(".test-section");
const gradeButt = document.querySelector(".grade");
const writtenTemplate = document.querySelector(".written.template");
const mcTemplate = document.querySelector(".mc.template");
const tfTemplate = document.querySelector(".tf.template");
const grades = document.querySelector(".grade-section");

showTest();
gradeButt.onclick = gradeTest;

document.querySelector(".new-test").onclick = () => {
    grades.classList.add("d-none");
    document.querySelectorAll(".question:not(.template)").forEach((q) => {
        q.remove();
    });

    questions = genTest(tLength);
    showTest();
};

function showTest() {
    let newQ;
    for (let i = 0; i < questions.length; i++) {
        switch (questions[i].type) {
            case "w":
                newQ = writtenTemplate.cloneNode(true);
                break;
            case "mc":
                newQ = mcTemplate.cloneNode(true);
                newQ.querySelectorAll(".option").forEach((option, ind) => {
                    option.querySelector("label").textContent = questions[i].options[ind].term;
                    option.querySelector("input").id = option.querySelector("input").id + i;
                    option.querySelector("input").name = option.querySelector("input").name + i;
                    option.querySelector("label").htmlFor = option.querySelector("input").id;
                });
                break;
            case "tf":
                newQ = tfTemplate.cloneNode(true);
                newQ.querySelectorAll(".option").forEach((option, ind) => {
                    option.querySelector("input").id = option.querySelector("input").id + i;
                    option.querySelector("input").name = option.querySelector("input").name + i;
                    option.querySelector("label").htmlFor = option.querySelector("input").id;
                });
                break;
            default:
                console.err("malformed question");
        }
        newQ.querySelector(".prompt").textContent = questions[i].prompt;
        newQ.classList.remove("template");
        gradeButt.before(newQ);
    }
}

function gradeTest() {
    let right = 0;

    document.querySelectorAll(".question:not(.template)").forEach((q, ind) => {
        q.querySelectorAll("input").forEach((input) => {
            input.setAttribute("disabled", "");
         });

        switch (questions[ind].type) {
            case "w":
                if (questions[ind].checkAnswer(q.querySelector("input").value)) {
                    right++;
                    q.querySelector("input").classList.add("is-valid");
                }
                else {
                    q.querySelector("input").classList.add("is-invalid");
                    q.querySelector(".invalid-feedback").textContent = questions[ind].answer;
                }
                break;
            case "mc":
                let chosen = -1;
                const buttons = q.querySelectorAll("input");
                for (let i = 0; i < buttons.length; i++) {
                    if (buttons[i].checked) {
                        chosen = i;
                        break;
                    }
                }
                if (questions[ind].checkAnswer(chosen)) {
                    right++;
                    buttons[chosen].classList.add("is-valid");
                }
                else {
                    if (chosen != -1) {
                        buttons[chosen].classList.add("is-invalid");
                    }
                    buttons[questions[ind].answer].classList.add("is-valid");
                }
                break;
            case "tf":
                const trueS = q.querySelector(".trueIn");
                const falseS = q.querySelector(".falseIn");
                if (questions[ind].checkAnswer(true)) {
                    trueS.classList.add("is-valid");
                    if (falseS.checked) {
                        falseS.classList.add("is-invalid");
                    }
                    else if (trueS.checked) {
                        right++;
                    }
                }
                else {
                    falseS.classList.add("is-valid");
                    if (trueS.checked) {
                        trueS.classList.add("is-invalid");
                    }
                    else if (falseS.checked) {
                        right++;
                    }
                    const rightTerm = q.querySelector(".right-term");
                    rightTerm.classList.remove("d-none");
                    rightTerm.textContent = questions[ind].term;
                }
                break;
            default:
                console.err("malformed question at index " + ind);
        }
    });

    grades.classList.remove("d-none");
    grades.textContent = `You got ${right}/${questions.length}, or ${Math.round(100*right/questions.length)}% right`
}