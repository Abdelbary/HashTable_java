import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// Main class should be named 'Solution'
class Solution {
    public static void main(String[] args) {
        System.out.println("Hello, World");
    }
}

class Pair
{
    int key;
    int value;
    Pair(int k, int v)
    {
        this.key = k;
        this.value = v;
    }

    public void set(int k,int v)
    {
        this.key = k;
        this.value = v;
    }

    public int getKey()
    {
        return this.key;
    }
    public int getValue()
    {
        return this.value;
    }
}

class HashTable
{
    ArrayList<LinkedList<Pair>> hashTable;
    int capacity;
    int currentElements;
    double loadFactor;
    HashTable(int initialCapacity, double loadFactor)
    {
        hashTable = new ArrayList<LinkedList<Pair>>(initialCapacity);
        this.capacity = initialCapacity;
        this.loadFactor = loadFactor;
        this.currentElements = 0;
    }

    HashTable(int initialCapacity)
    {
        hashTable = new ArrayList<LinkedList<Pair>>(initialCapacity);
        this.capacity = initialCapacity;
        this.loadFactor = 0.75;
        this.currentElements = 0;
    }

    public int put(int k , int v)
    {
        int index = hashFunction(k);
        double alpha = this.currentElements/this.capacity;

        if(alpha >= loadFactor)
        {
            hashTable.ensureCapacity(2*this.capacity);
            this.capacity = 2*this.capacity;
            while (hashTable.size() < capacity) {
                hashTable.add(null);
            }
        }

        if(hashTable.get(index) == null)
        {
            hashTable.add(index,new LinkedList<Pair>());
            hashTable.get(index).push(new Pair(k, v));
        }
        else
        {
            for(int i = 0 ; i < hashTable.get(index).size(); i++)
            {
                if(hashTable.get(index).get(i).getKey() == k)
                {
                    hashTable.get(index).get(i).set(k,v);
                    break;
                }
            }
            hashTable.get(index).push(new Pair(k, v));
        }
        this.currentElements++;
        return index;
    }


    public Pair get(int k)
    {
        int index = hashFunction(k);

        int i = 0;
        while(hashTable.get(index).get(i).getKey() != k)
        {
            i++;
        }

        return (i == hashTable.get(index).size())? null : hashTable.get(index).get(i);

    }


    public void delete(int k)
    {
        int index = hashFunction(k);

        hashTable.get(index).remove(k);

        this.currentElements--;
    }


    private int hashFunction(int k)
    {
        return k % capacity;
    }
}
