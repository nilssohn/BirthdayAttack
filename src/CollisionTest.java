public class CollisionTest {
    public static void main(String[] args){
        //Two given messages
        String x1 = "Transfer $10 to Oscar.";
        String x2 = "Transfer $1000 to Oscar.";

        //The first argument is the size of hash output: try 40 later.
        Collision coll = new Collision(32, x1, x2);

        double start = System.currentTimeMillis();
        
        String[] collisionFound = coll.collisionSearch();
        
        System.out.println("Elapsed time: " +
                (System.currentTimeMillis() - start)/1000.0 + " seconds.");

        String m1 = collisionFound[0];
        String m2 = collisionFound[1];
        System.out.println(m1+":");
        System.out.println(m2+":");
        System.out.println(coll.hash(m1)== coll.hash(m2));
    }
}
