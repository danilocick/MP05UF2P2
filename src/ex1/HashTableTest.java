package ex1;

import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    /*
    * Hi ha 6 errors:
    * 1- count (No ++ , No --) LO TENEMOS
    * 2- drop colisions LO TENEMOS
    * 3- drop colisions LO TENEMOS
    * 4- drop colisions LO TENEMOS
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
        ht.drop("hola2");
        Assertions.assertEquals(4, ht.count());
        Assertions.assertEquals(16, ht.size());
    }

    @org.junit.jupiter.api.Test
    void put_and_drop() {
        //TODO:albondigaa no funciona y saber porque
        //        hT.put("albondigaa", "almondiga3");


        HashTable ht = new HashTable();

        // añadiremos todos los valores al hashtable
        for(int i=0; i<40; i++) {
            final String key = String.valueOf(i);
            ht.put(key, key);
        }

        Assertions.assertEquals("\n bucket[0] = [0, 0] -> [11, 11] -> [22, 22] -> [33, 33]\n" +
                " bucket[1] = [1, 1] -> [12, 12] -> [23, 23] -> [34, 34]\n" +
                " bucket[2] = [2, 2] -> [13, 13] -> [24, 24] -> [35, 35]\n" +
                " bucket[3] = [3, 3] -> [14, 14] -> [25, 25] -> [36, 36]\n" +
                " bucket[4] = [4, 4] -> [15, 15] -> [26, 26] -> [37, 37]\n" +
                " bucket[5] = [5, 5] -> [16, 16] -> [27, 27] -> [38, 38]\n" +
                " bucket[6] = [6, 6] -> [17, 17] -> [28, 28] -> [39, 39]\n" +
                " bucket[7] = [7, 7] -> [18, 18] -> [29, 29]\n" +
                " bucket[8] = [8, 8] -> [19, 19]\n" +
                " bucket[9] = [9, 9]\n" +
                " bucket[13] = [30, 30]\n" +
                " bucket[14] = [20, 20] -> [31, 31]\n" +
                " bucket[15] = [10, 10] -> [21, 21] -> [32, 32]", ht.toString());


        //borramos el primer valor
        ht.drop("2");
        //borramos el ultimo valor
        ht.drop("34");
        //borramos uno entre medio
        ht.drop("14");
        //borramos un unico valor
        ht.drop("9");

        Assertions.assertEquals("\n bucket[0] = [0, 0] -> [11, 11] -> [22, 22] -> [33, 33]\n" +
                " bucket[1] = [1, 1] -> [12, 12] -> [23, 23]\n" +
                " bucket[2] = [13, 13] -> [24, 24] -> [35, 35]\n" +
                " bucket[3] = [3, 3] -> [25, 25] -> [36, 36]\n" +
                " bucket[4] = [4, 4] -> [15, 15] -> [26, 26] -> [37, 37]\n" +
                " bucket[5] = [5, 5] -> [16, 16] -> [27, 27] -> [38, 38]\n" +
                " bucket[6] = [6, 6] -> [17, 17] -> [28, 28] -> [39, 39]\n" +
                " bucket[7] = [7, 7] -> [18, 18] -> [29, 29]\n" +
                " bucket[8] = [8, 8] -> [19, 19]\n" +
                " bucket[13] = [30, 30]\n" +
                " bucket[14] = [20, 20] -> [31, 31]\n" +
                " bucket[15] = [10, 10] -> [21, 21] -> [32, 32]", ht.toString());

        Assertions.assertEquals(16, ht.size());
        Assertions.assertEquals(36, ht.count());

    }
}