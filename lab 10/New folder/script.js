let currentInput = '';
let currentOperation = null;
let previousOperand = '';

function appendNumber(number) {
    currentInput += number;
    updateDisplay();
}

function setOperation(operator) {
    if (currentOperation !== null) calculateResult();
    previousOperand = currentInput;
    currentOperation = operator;
    currentInput = '';
}

function calculateResult() {
    if (currentOperation === null || currentInput === '') return;
    let result = 0;
    const prev = parseFloat(previousOperand);
    const current = parseFloat(currentInput);
    switch (currentOperation) {
        case '+':
            result = prev + current;
            break;
        case '-':
            result = prev - current;
            break;
        case '*':
            result = prev * current;
            break;
        case '/':
            if (current !== 0) result = prev / current;
            break;
        default:
            return;
    }
    currentInput = result.toString();
    currentOperation = null;
    previousOperand = '';
    updateDisplay();
}

function clearDisplay() {
    currentInput = '';
    previousOperand = '';
    currentOperation = null;
    updateDisplay();
}

function updateDisplay() {
    document.getElementById('display').value = currentInput;
}
