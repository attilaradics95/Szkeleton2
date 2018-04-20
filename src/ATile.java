import java.util.HashMap;

public abstract class ATile {

    //attribútumok minden mezőnek van 4 szomszédja, és lehet rajta egy visitor
    protected HashMap<Directions, ATile> neighbors;
    protected Visitor visitor;
    Tabulate tabulate = new Tabulate();

    //konstruktor
    public ATile(){
        neighbors = new HashMap<>();
        visitor = null;
    }

    //visszatér az adott irányban lévő szomszédjával
    public ATile getNeighbor(Directions d){
        return neighbors.get(d);
    }

    //visszatér a rajta lévő visitorral vagy null értékkel, ha nincs rajta visitor
    public Visitor getVisitor(){
        return visitor;
    }

    //beállítja az aktuális mezőre a v Visitor objektumot
    public void setVisitor(Visitor v){
        tabulate.in();
        System.out.println(this.toString() + ".setVisitor(" + v + ")");
        visitor = v;
        tabulate.out();
    }

    abstract public void accept(Visitor v, Directions d, int force);

    //a pálya létrehozásakor minden mezőnek megadjuk, hogy kik az ő szomszédai
    public void setNeighbors(ATile north,ATile east, ATile south, ATile west){
        tabulate.in();
        System.out.println(this.toString() + ".setNeighbors(" + north + ", " + east + ", " + south + ", " + west + ")");
            neighbors.put(Directions.NORTH, north);
            neighbors.put(Directions.EAST, east);
            neighbors.put(Directions.SOUTH, south);
            neighbors.put(Directions.WEST, west);
        tabulate.out();
    }

}
