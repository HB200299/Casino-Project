package casino;


public class Members {
    
    protected String name;
    protected int pass;
    protected int chips;

    public Members(String name, int pass, int chips) {
        this.name = name;
        this.pass = pass;
        this.chips = chips;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPass() {
        return pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    public int getChips() {
        return chips;
    }

    public void setChips(int chips) {
        this.chips = chips;
    }

    @Override
    public String toString() {
        return name + ", " + pass + ", " + chips;
    }
}