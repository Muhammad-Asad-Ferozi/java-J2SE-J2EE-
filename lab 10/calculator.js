let num = [0,'',0];
let count = 0;
let ans =0;



document.getElementById("one").addEventListener("click", function(){
	
	num[count] = num[count]*10 + 1;	
	document.getElementById("result").value = num;
	
});

document.getElementById("two").addEventListener("click", function(){
	num[count] = num[count]*10 + 2;
	document.getElementById("result").value = num;
});


document.getElementById("three").addEventListener("click", function(){
	num[count] = num[count]*10 + 3;
	document.getElementById("result").value = num
});


document.getElementById("four").addEventListener("click", function(){
	num[count] = num[count]*10 + 4;
	document.getElementById("result").value = num
});

document.getElementById("five").addEventListener("click", function(){
	num[count] = num[count]*10 + 5;
	document.getElementById("result").value = num
});

document.getElementById("six").addEventListener("click", function(){
	num[count] = num[count]*10 + 6;
	document.getElementById("result").value = num
});

document.getElementById("seven").addEventListener("click", function(){
	num[count] = num[count]*10 + 7;
	document.getElementById("result").value = num
});

document.getElementById("eight").addEventListener("click", function(){
	num[count] = num[count]*10 + 8;
	document.getElementById("result").value = num
});


document.getElementById("nine").addEventListener("click", function(){
	num[count] = num[count]*10 + 9;
	document.getElementById("result").value = num
});



document.getElementById("zero").addEventListener("click", function(){
	num[count] = num[count]*10 + 0
	document.getElementById("result").value = num
});



document.getElementById("dot").addEventListener("click", function(){
	
		num[count] += '.';	
	
		document.getElementById("result").value = num;
	
});



document.getElementById("plus").addEventListener("click", function(){
	count=1;
	num[count] = '+';
	count=2;
	document.getElementById("result").value = num
});

document.getElementById("minus").addEventListener("click", function(){
	count=1;
	num[count] = '-';
	count=2;
	document.getElementById("result").value = num
});

document.getElementById("divide").addEventListener("click", function(){
	count=1;
	num[count] = '/';
	count=2;
	document.getElementById("result").value = num
});

document.getElementById("multiply").addEventListener("click", function(){
	count=1;
	num[count] = '*';
	count=2;
	document.getElementById("result").value = num
});



document.getElementById("equals").addEventListener("click", function(){
	switch (num[1])
	{
		case '+':
			ans = num[0]+num[2];
			document.getElementById("result").value = ans;
			break;
		case'-':
			ans = num[0]-num[2];
			document.getElementById("result").value = ans;
			break;
		case'*':
			ans = num[0]*num[2];
			document.getElementById("result").value = ans;
			break;
		case'/':
			if(num[2] === 0)
			{
				alert("Cant divide by zero");
			}
			else
			{
				ans = num[0]/num[2];
				document.getElementById("result").value = ans;
			}
		break;
		default:
			return;
	}
		num[0] = 0;
		num[1] = '';
		num[2] = 0;
		count =0;
		operator = '';
		ans = 0;
	
});





document.getElementById("C").addEventListener("click", function(){
	num[0] = 0;
	num[1] = '';
	num[2] = 0;
	count =0;
	operator = '';
	ans = 0;
	document.getElementById("result").value = num
});

