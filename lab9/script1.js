


function calculate(operation) {
    var num1 = parseFloat(document.getElementById('number1').value);
    var num2 = parseFloat(document.getElementById('number2').value);
    var result = 0;

    switch (operation) {
        case 'add':
            result = num1 + num2;
            break;
        case 'subtract':
            result = num1 - num2;
            break;
        case 'multiply':
            result = num1 * num2;
            break;
        case 'divide':
            if (num2 !== 0) {
                result = num1 / num2;
            } else {
                alert('Division by zero is not allowed.');
                return;
            }
            break;
        default:
            return;
    }

    document.getElementById('result').value = result;
}
