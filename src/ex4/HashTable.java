package ex4;

// Original source code: https://gist.github.com/amadamala/3cdd53cb5a6b1c1df540981ab0245479
// Modified by Fernando Porrino Serrano for academic purposes.

import java.util.ArrayList;

/**
 * Implementació d'una taula de hash sense col·lisions.
 * Original source code: https://gist.github.com/amadamala/3cdd53cb5a6b1c1df540981ab0245479
 */
public class HashTable {
    private int SIZE = 16;
    private int ITEMS = 0;
    private HashEntry[] entries = new HashEntry[SIZE];

    public int count(){
        return this.ITEMS;
    }

    public int size(){
        return this.SIZE;
    }

    /**
     * Permet afegir un nou element a la taula.
     * @param key La clau de l'element a afegir.
     * @param value El propi element que es vol afegir.
     */
    public void put(Object key, Object value) {
        boolean change = true;
        //guardamos el hash
        int hash = getHash(key);
        // creamos un nuevo hash en el q tudo es null
        //TODO: hash negative
        if (hash >=0){

            final HashEntry newHashEntry = new HashEntry(key, value);

            //si este hash esta vacio, lo añadismo como primero
            if(entries[hash] == null) {
                entries[hash] = newHashEntry;
            }
            else {

                //creamos un hashentry temporal
                HashEntry temp = entries[hash];

                while(temp.next != null){
                    if (temp.key.equals(key)){
                        ExceptionsCreated exceptionsCreated = throwExceptions(key, "Key duplicada, no pots modificarla: ");
                        change = false;
                    }
                    temp = temp.next;
                    if (temp.key.equals(key)){
                        ExceptionsCreated exceptionsCreated = throwExceptions(key, "Key duplicada, no pots modificarla: ");
                        change = false;
                    }
                }
                if (change){
                    temp.next = newHashEntry;
                    newHashEntry.prev = temp;
                }
            }
            if (change){
                //TODO:SUMAR LISTA ITEMS
                ITEMS++;
            }
        }else{
            ExceptionsCreated exceptionsCreated = throwExceptions(key, "Key retorna un valor negatiu, no pots afegir aquest valor: ");
        }
    }

    /**
     * Permet recuperar un element dins la taula.
     * @param key La clau de l'element a trobar.
     * @return El propi element que es busca (null si no s'ha trobat).
     */
    public Object get(Object key) {
        boolean keyexist = true;


        int hash = getHash(key);
        if(entries[hash] != null) {
            HashEntry temp = entries[hash];

            while (!temp.key.equals(key)) {
                if (temp.next == null) {
                    keyexist = false;
                    break;
                }
                temp = temp.next;
            }
            if (keyexist) {
                return temp.value;
            }
        }
        ExceptionsCreated exceptionsCreated = throwExceptions(key, "Key inexistent: ");
        return String.valueOf(exceptionsCreated);
    }

    private ExceptionsCreated throwExceptions(Object key, String s) {
        ExceptionsCreated exceptionsCreated = new ExceptionsCreated(s + key);
        System.out.println(exceptionsCreated);
        return exceptionsCreated;
    }

    /**
     * Permet esborrar un element dins de la taula.
     * @param key La clau de l'element a trobar.
     */
    public void drop(Object key) {
        boolean keyexist = true;
        //tenemos el hash
        int hash = getHash(key);
        //si en ese hash hay algo
        if(entries[hash] != null) {

            //creamos el temporal
            HashEntry temp = entries[hash];

            while (!temp.key.equals(key)){
                if (temp.next == null){
                    keyexist=false;
                    break;
                }else temp = temp.next;
            }
            if (keyexist) {
                //TODO: WORKS
                if(temp.prev == null && temp.next == null){
                    entries[hash] = null;             //esborrar element únic (no col·lissió)

                }else if (temp.prev == null && temp.next != null) { //esborrar i modificar prev i next si es el primer element
                    temp.next.prev = null;
                    HashEntry temp2 = temp.next;
                    entries[hash]=temp2;

                }else if (temp.prev != null && temp.next != null){ //esborrar i modificar prev i next si esta entre mig
                    temp.prev.next = temp.next;
                    temp.next.prev = temp.prev;
                    //recorremos hasta el inicio nuestro temporal para no perder nodos
                    while (temp.prev != null)
                        temp = temp.prev;

                    entries[hash] = temp;

                }else if (temp.prev != null && temp.next == null){ //esborrar i modificar prev i next si es l'ultim element
                    temp.prev.next = null;
                    //recorremos hasta el inicio nuestro temporal para no perder nodos
                    while (temp.prev != null)
                        temp = temp.prev;

                    entries[hash] = temp;
                }
                //TODO:RESTAR LISTA ITEMS
                ITEMS--;
            }else{
                ExceptionsCreated exceptionsCreated = throwExceptions(key, "Key inexistent: ");
            }
//            if(temp.prev == null ){
//                 if(temp.next != null) {
//                    temp.next.prev = temp.prev;   //esborrem temp, per tant actualitzem l'anterior al següent
//                    temp.prev.next = temp.next;   //esborrem temp, per tant actualitzem el següent de l'anterior
//            }

        }
    }

    private int getHash(Object key) {
        // piggy backing on java string
        // hashcode implementation.
        return key.hashCode() % SIZE;
    }

    private class HashEntry {
        Object key;
        Object value;

        // Linked list of same hash entries.
        HashEntry next;
        HashEntry prev;

        public HashEntry(Object key, Object value) {
            this.key = key;
            this.value = value;
            this.next = null;
            this.prev = null;
        }

        @Override
        public String toString() {
            return "[" + key + ", " + value + "]";
        }
    }

    @Override
    public String toString() {
        int bucket = 0;
        StringBuilder hashTableStr = new StringBuilder();
        for (HashEntry entry : entries) {
            if(entry == null) {
                bucket++;
                continue;
            }

            hashTableStr.append("\n bucket[")
                    .append(bucket)
                    .append("] = ")
                    .append(entry.toString());
            bucket++;
            HashEntry temp = entry.next;
            while(temp != null) {
                hashTableStr.append(" -> ");
                hashTableStr.append(temp.toString());
                temp = temp.next;
            }
        }
        return hashTableStr.toString();
    }

    /**
     * Permet calcular quants elements col·lisionen (produeixen la mateixa posició dins la taula de hash) per a la clau donada.
     * @param key La clau que es farà servir per calcular col·lisions.
     * @return Una clau que, de fer-se servir, provoca col·lisió amb la que s'ha donat.
     */
    public String getCollisionsForKey(String key) {
        return getCollisionsForKey(key, 1).get(0);
    }

    /**
     * Permet calcular quants elements col·lisionen (produeixen la mateixa posició dins la taula de hash) per a la clau donada.
     * @param key La clau que es farà servir per calcular col·lisions.
     * @param quantity La quantitat de col·lisions a calcular.
     * @return Un llistat de claus que, de fer-se servir, provoquen col·lisió.
     */
    public ArrayList<String> getCollisionsForKey(String key, int quantity) {
        /*
          Main idea:
          alphabet = {0, 1, 2}

          Step 1: "000"
          Step 2: "001"
          Step 3: "002"
          Step 4: "010"
          Step 5: "011"
           ...
          Step N: "222"

          All those keys will be hashed and checking if collides with the given one.
        * */

        final char[] alphabet = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        ArrayList<Integer> newKey = new ArrayList();
        ArrayList<String> foundKeys = new ArrayList();

        newKey.add(0);
        int collision = getHash(key);
        int current = newKey.size() -1;

        while (foundKeys.size() < quantity){
            //building current key
            String currentKey = "";
            for(int i = 0; i < newKey.size(); i++)
                currentKey += alphabet[newKey.get(i)];

            if(!currentKey.equals(key) && getHash(currentKey) == collision)
                foundKeys.add(currentKey);

            //increasing the current alphabet key
            newKey.set(current, newKey.get(current)+1);

            //overflow over the alphabet on current!
            if(newKey.get(current) == alphabet.length){
                int previous = current;
                do{
                    //increasing the previous to current alphabet key
                    previous--;
                    if(previous >= 0)  newKey.set(previous, newKey.get(previous) + 1);
                }
                while (previous >= 0 && newKey.get(previous) == alphabet.length);

                //cleaning
                for(int i = previous + 1; i < newKey.size(); i++)
                    newKey.set(i, 0);

                //increasing size on underflow over the key size
                if(previous < 0) newKey.add(0);

                current = newKey.size() -1;
            }
        }

        return  foundKeys;
    }

    protected static void log(String msg) {
        System.out.println(msg);
    }
}