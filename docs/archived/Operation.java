package homepage.algorithm.md5;

/*
 * This functional interface lets us pass references to the F, G, H, and I methods in the MD5 class
 * as arguments to the op method in the MD5 class.
 *
 * In other words, we want the op method to take a parameter of type Operation.
 * It can accept a reference to F, G, H, or I as an argument. 
 *
 * Functional interfaces are an important feature in Java.
 *
 * We can use lambda expressions or method references to quickly create an instance of a functional interface.
 *
 * Functional interfaces let us deal with methods in an abstract way,
 * just like inheritance lets us deal with classes in an abstract way. 
 */
@FunctionalInterface
public interface Operation {
    
    public int execute(int x, int y, int z);

}
