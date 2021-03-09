package ex1;

import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    /*
    * Hi ha 6 errors:
    * 1- count (No ++ , No --)
    * 2- drop first item of a colision
    * 3- next on create hashkey
    * 4- prev on create hashkey
    * 5- length on put
    * 6- duplicates
    */

    @org.junit.jupiter.api.Test
    void count_and_size() {
        HashTable ht = new HashTable();
        //Comprobar que està vuit i el size siempre es 16
        Assertions.assertEquals(0, ht.count());
        Assertions.assertEquals(16, ht.size());

        //Fiquem diferents items
        ht.put("hola0", "hello0");
        ht.put("hola1", "hello1");
        ht.put("hola2", "hello2");
        ht.put("hola3", "hello3");


        Assertions.assertEquals(4, ht.count());
        Assertions.assertEquals(16, ht.size());

        //Fiquem un item que colisiona (ho sabem previament) i el size siempre es 16
        //TODO: get one collision
        String m = ht.getCollisionsForKey("hola0");
        ht.put(m, "Collision");

        Assertions.assertEquals(5, ht.count());
        Assertions.assertEquals(16, ht.size());

        //Eliminar 2 items para comprobar q se restan i el size siempre es 16
        ht.drop("hola0");
        Assertions.assertEquals(4, ht.count());
        Assertions.assertEquals(16, ht.size());
    }

    @org.junit.jupiter.api.Test
    void put_drop_and_drop() {
//        hT.put("albondigaa", "almondiga3");


    }
}