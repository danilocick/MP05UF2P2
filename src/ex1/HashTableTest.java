package ex1;

import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    /*
    * Hi ha 6 errors:
    * 1- count (No ++ , No --)
    * 2- drop first item of a colision
    * 3-
    * 4-
    * 5-
    * 6-
    */

    @org.junit.jupiter.api.Test
    void count_and_size() {
        HashTable hT = new HashTable();
        //Comprobar que està vuit i el size siempre es 16
        Assertions.assertEquals(0, hT.count());
        Assertions.assertEquals(16, hT.size());

        //Fiquem diferents items
        hT.put("hola0", "hello0");
        hT.put("hola1", "hello1");
        hT.put("hola2", "hello2");
        hT.put("hola3", "hello3");

        Assertions.assertEquals(4, hT.count());
        Assertions.assertEquals(16, hT.size());

        //Fiquem un item que colisiona (ho sabem previament) i el size siempre es 16
        //TODO: get one collision
        hT.put(hT.getCollisionsForKey("hola0"), "Collision");

        Assertions.assertEquals(5, hT.count());
        Assertions.assertEquals(16, hT.size());

        //Eliminar 2 items para comprobar q se restan i el size siempre es 16
        hT.drop("hola0");
        Assertions.assertEquals(4, hT.count());
        Assertions.assertEquals(16, hT.size());
    }

    @org.junit.jupiter.api.Test
    void put() {
//        hT.put("albondigaa", "almondiga3");
    }

    @org.junit.jupiter.api.Test
    void get() {
    }

    @org.junit.jupiter.api.Test
    void drop() {
    }
}