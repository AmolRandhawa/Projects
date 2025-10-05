import java.util.Random;

public class Actor{
    private String name ="";
    private int health = 100;
    private int maxDamage = 10;
    private int row = 0;
    private int col = 0;


    public Actor(){
    }

    public Actor(String charName, int health){
        this.name = charName;
        this.health = health;
    }

    public int hit(){
        int damage;
        Random randomHit = new Random();
        damage = randomHit.nextInt(1, maxDamage + 1);
        return damage;
    }
    public void setAttack(int damage){
        this.maxDamage = damage;
    }

    public Boolean move(String direction, int dungeonSize){
        return false;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    public void setPosition(int row, int col){
        this.row = row;
        this.col = col;
    }

    public int getPosition(){
        return row;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public int getHealth(){
        return health;
    }
}