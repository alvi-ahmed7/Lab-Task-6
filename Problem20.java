import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Complete the classes below
// Base Flower class
class Flower {
    String whatsYourName() {
        return "I have many names and types.";
    }
}

// Flower subclasses
class Jasmine extends Flower {
    @Override
    String whatsYourName() {
        return "Jasmine";
    }
}

class Lily extends Flower {
    @Override
    String whatsYourName() {
        return "Lily";
    }
}

class Rose extends Flower {
    @Override
    String whatsYourName() {
        return "Rose";
    }
}

// Base Region (instead of State)
class Region {
    Flower yourNationalFlower() {
        return new Flower();
    }
}

// Specific regions with covariant return types
class WestBengal extends Region {
    @Override
    Jasmine yourNationalFlower() {
        return new Jasmine();
    }
}

class AndhraPradesh extends Region {
    @Override
    Lily yourNationalFlower() {
        return new Lily();
    }
}

class Karnataka extends Region {
    @Override
    Rose yourNationalFlower() {
        return new Rose();
    }
}

public class Problem20 {
  public static void main(String[] args) throws IOException {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      String s = reader.readLine().trim();
      Region region = null;
      switch (s) {
        case "WestBengal":
          region = new WestBengal();
          break;
        case "AndhraPradesh":
          region = new AndhraPradesh();
          break;
      }
      Flower flower = region.yourNationalFlower();
      System.out.println(flower.whatsYourName());
    }
}