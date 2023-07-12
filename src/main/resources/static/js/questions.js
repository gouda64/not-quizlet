function genTest(length) {
    const qOrder = [newTF, newMC, newWritten];

    const arr = [];
    const qBreakdown = [Math.floor(length/3), Math.floor(length/3), Math.floor(length/3)];
    for (let i = 0; i < length - Math.floor(length/3)*3; i++) {
        qBreakdown[qBreakdown.length-1-i]++;
    }
    const qCards = randSubset(cards, length);
    arrShuffle(qCards);
    let totProg = 0;
    for (let i = 0; i < 3; i++) {
        for (let j = 0; j < qBreakdown[i]; j++) {
            arr.push(qOrder[i](qCards[totProg]));
            totProg++;
        }
    }
    return arr;
}

function newTF(card) {
    let isT = Math.random() < 0.5 ? true : false;
    let comp = card;
    if (!isT) {
        comp = randReservoir(cards, card, 1)[0];
    }

    return {
        type: "tf",
        def: card.definition,
        term: card.term,
        compare: comp.term,
        prompt: card.definition + " =? " + comp.term,
        answer: isT,
        checkAnswer(isTrue) {
           return isT === isTrue;
        },
    }
}

function newWritten(card) {
    return {
        type: "w",
        def: card.definition,
        term: card.term,
        prompt: card.definition,
        answer: card.term,
        checkAnswer(ans) {
            return card.term === ans;
        },
    };
}

function newMC(card) {
    let options = randReservoir(cards, card, 3);
    let randInd = Math.floor(Math.random() * 4);
    options.splice(randInd, 0, card);
    return {
        type: "mc",
        def: card.definition,
        term: card.term,
        prompt: card.definition,
        options: options,
        answer: randInd,
        checkAnswer(ans) {
            return randInd === ans;
        },
    };
}

function randReservoir(array, blocked, count) {
  const reservoir = [];
  let i;

  let reservoirIndex = 0;
  for (i = 0; i < array.length; i++) {
    if (array[i] !== blocked) {
      reservoir[reservoirIndex] = array[i];
      reservoirIndex++;
      if (reservoirIndex >= count) {
        break;
      }
    }
  }

  for (; i < array.length; i++) {
    if (array[i] !== blocked) {
      const j = Math.floor(Math.random() * reservoirIndex);

      if (j < count) {
        reservoir[j] = array[i];
        reservoirIndex++;
      }
    }
  }

  return reservoir;
}

function randSubset(arr, count) {
    let copy = [...arr];
    for (let i = 0; i < arr.length - count; i++) {
        copy.splice(i, 1);
    }
    return copy;
}

function arrShuffle(array) {
    for (let i = array.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        const temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}