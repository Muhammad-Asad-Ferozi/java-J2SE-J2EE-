public class task3{
	
	double payAmount = 2000.00;
	int payPeroid = 12;
	double annualPay = payAmount*payPeroid;
	
	void showResult()
	{
		System.out.println(annualPay);
	}
	public static void main(String[] args){
		task3 ob = new task3();
		ob.showResult();
	}
}