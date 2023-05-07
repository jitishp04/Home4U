// *Inspired by ChatGpt*
const readline = require('readline');

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});


const table = {
    c: 1915,
    d: 1700,
    e: 1519,
    f: 1432,
    g: 1275,
    a: 1136,
    b: 1014,
    C: 956
}

function notesToFreqs(notes){
    let output = ""
    for(note of notes){
        output += `${table[note]} `
    }

    return output
}

rl.question("Enter notes: ", (notes) => {
    const freqs = notesToFreqs(notes)
    console.log(freqs)
})

