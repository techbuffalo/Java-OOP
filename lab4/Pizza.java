package lab4;

public class Pizza extends Food implements Heatable {
    static final int PIZZA_CALORIES = 800;


    public Pizza() {
        calories += PIZZA_CALORIES;
        System.out.println("Serving Pizza");
    }

    @Override
    public void heatIt() {
        temperature = HOTSERVINGTEMPERATURE;
        System.out.println("Now it's hot @ " + temperature + " degrees");
        }

    @Override
    public String eat(){
        return "Chomp Chomp";
    }
}
