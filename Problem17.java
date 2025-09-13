import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.lang.reflect.*;


class Singleton {
    // public string instance variable
    public String str;

    // private static variable to hold the single instance
    private static Singleton instance;

    // private constructor to prevent instantiation
    private Singleton() { }

    // static method to get the single instance
    public static Singleton getSingleInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}


public class Problem17 {

 public static void main(String args[])throws Exception{
	
	Scanner sc=new Scanner(System.in);
	Singleton s1 = Singleton.getSingleInstance(); //retrive the single instance
    Singleton s2=Singleton.getSingleInstance();
    assert(s1==s2);
    
	//verify that the constructor is private
	Class c=s1.getClass();
	Constructor[] allConstructors = c.getDeclaredConstructors();
	assert allConstructors.length==1;
	for (Constructor ctor : allConstructors) 
	{
		if(ctor.getModifiers()!=2 || !ctor.toString().equals("private Singleton()")) //The constructor must be private
		{
			System.out.println("Wrong class!");
		}
	}
    String str=sc.nextLine();
	s1.str=str;
    s2.str=str;
    System.out.println("Hello I am a singleton! Let me say "+str+" to you");
	
 }

}

