public class Counter {
   
    private String name;
    private double price;

    public Counter(String name, Double price){
        this.name = name;
        this.price = price;
    }

    public String getName(){
        return this.name;
    }

    public double getPrice(){
        return this.price;
    }

    public void setPrice(double p){
        this.price = p;
    }

    public void incrementPrice(double amt){
        this.price += amt;
    }

        
    
}
