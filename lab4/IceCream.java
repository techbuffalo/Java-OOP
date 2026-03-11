package lab4;

public class IceCream extends Food {
	static final int ICECREAM_CALORIES = 500;

    public IceCream(){
        calories += ICECREAM_CALORIES;
        System.out.println("Serving Ice Cream");
    }

    @Override
    public String eat(){
        return "Slurp Slurp";
    }
}
