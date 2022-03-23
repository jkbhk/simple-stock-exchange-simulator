public class SellCommand implements ICommand{

    @Override
    public void execute(String[] args) {
        
        if(args.length < 3){
            System.out.println("SELL command requires at least 3 parameters.");
            return;
        }

        String counter = args[0];
        String type = args[1];

        // check counter exists
        if(CounterManager.instance.getCounters().get(counter) == null){
            System.out.println("Counter does not exist");
            return;
        }

        if(type.equals("LMT")){

            // procedural check

            if(args.length != 4){
                System.out.println("invalid LMT command.");
                return;
            }
            

            if(!args[2].contains("$")){
                System.out.println("invalid LMT command, please denote limit with ($)");
                return;
            }

            double limit = 0;

            try{
                limit = Double.parseDouble(args[2].substring(1,args[2].length())); 
            } catch (NumberFormatException nfe){
                System.out.println("invalid LMT command");
                return;
            }

            int amount = 0;

            try{
                amount = Integer.parseInt(args[3]); 
            } catch (NumberFormatException nfe){
                System.out.println("invalid LMT command");
                return;
            }

            String m = "You have placed a limit sell order for " + amount + " " + counter + " shares at $" + 
            String.format("%.2f", limit) + " each.";
            System.out.println(m);
            InputManager.instance.outputToDisplayable(m);
        

        }else if(type.equals("MKT")){
            
            if(args.length != 3){
                System.out.println("invalid MKT command.");
                return;
            }          

            int amount = 0;

            try{
                amount = Integer.parseInt(args[2]); 
            } catch (NumberFormatException nfe){
                System.out.println("invalid MKT command");
                return;
            }

            String m = "You have placed a market order for " + amount + " " + counter + " shares."; 
            System.out.println(m);
            InputManager.instance.outputToDisplayable(m);
     

        }else{
            System.out.println("invalid SELL command");
            return;
        }
        
    }

    @Override
    public String getCommandName() {
        return "SELL";
    }
    
}
