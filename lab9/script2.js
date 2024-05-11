document.getElementById('name').addEventListener('blur', function() {
	
    var name = document.getElementById('name').value.trim();
	
    if (!name || name.length < 3) {
        document.getElementById('name-error').textContent = 'Please enter the name';
    }
	
});

document.getElementById('email').addEventListener('blur', function() {
	
    var email = document.getElementById('email').value.trim();
	
    if (!validateEmail(email)) {
        document.getElementById('email-error').textContent = 'Please enter a valid email';
    }
	
});

function verify() {
			
	
    var name = document.getElementById('name').value.trim();
    var email = document.getElementById('email').value.trim();
    var flag = true;
	if(!name || name.length < 3)
	{
			document.getElementById('name-error').textContent = 'Please Enter the name';
			flag =  false;
	}
	
	if(!validateEmail(email))
	{
			document.getElementById('email-error').textContent = 'Please enter valid email';
			flag =  false;
	}
	if(flag)
	{
		alert('Submitted sucessfullly');
	}
	else
	{
		alert('Failure Form is not submit');
	}
	return flag;
	
}

function validateEmail(email) {
    return email.indexOf('@') > 0 && email.indexOf('.') > email.indexOf('@') + 1 && email.indexOf('.') < email.length - 1;
}
