package it.unibo.collections;

import java.nio.channels.IllegalChannelGroupException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {

    private UseListsAndMaps() {
    }

    /**
     * @param s
     *            unused
     */

    public static void print(long time){
        time = System.nanoTime() - time;
        final var millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println(// NOPMD
            "doing this took "
                + time
                + "ns ("
                + millis
                + "ms)"
        );
    }
    public static void main(final String... s) {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
        ArrayList<Integer> numbers= new ArrayList<>();
        int n=1000;
        while ((n<2000)){
            numbers.add(n);
            n=n+1;
        }
        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
        LinkedList<Integer> numbersSingleRow = new LinkedList<>(numbers);
        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
        
        Integer tmp = numbers.removeFirst();
        numbers.addFirst(numbersSingleRow.pollLast());
        numbers.add(tmp);

        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
        for (Integer i : numbers) {
            System.out.println(i);
        }
        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */
        n=0;
        long time = System.nanoTime();
        while ((n<100_000)){
            numbers.add(0,n);
            n=n+1;
        }
        UseListsAndMaps.print(time);
        time = System.nanoTime();
        n=0;
        while ((n<100_000)){
            numbersSingleRow.addFirst(n);
            n=n+1;
        }
        UseListsAndMaps.print(time);


        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example TestPerformance.java.
         */
        
        time = System.nanoTime();
        while(n<1000){
            numbers.get(49_999);
            n=n+1;
        }
        UseListsAndMaps.print(time);
        
        n=0;


        time = System.nanoTime();
        while(n<1000){
            numbersSingleRow.get(49_999);
            n=n+1;
        }
        UseListsAndMaps.print(time);
        UseListsAndMaps.print(time);

        //I DON'T KNOW WHY IF I DON'T DELETE THE UPSIDE PART THE CODE DOWN ISN'T EXECUTED 
        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */
        Long population = new Long("1110635000");
        Map<String, Long> worldMap = new HashMap<>();
        worldMap.put("Africa", population);


        population.parseLong("972005000");
        worldMap.put("Americas", population);

        population.parseLong("0");
        worldMap.put("Antartica", population);

        population.parseLong("4298723000");
        worldMap.put("Asia", population);

        population.parseLong("742452000");
        worldMap.put("Europe", population);

        population.parseLong("38304000");
        worldMap.put("Oceania", population);

        /*
         * 8) Compute the population of the world
         */
        Collection<Long> populations = worldMap.values();
        Iterator<Long> it =  populations.iterator();

        while (it.hasNext()) {
            population= population+it.next();
        }
        System.out.println("world:  "+population);
    }
}
