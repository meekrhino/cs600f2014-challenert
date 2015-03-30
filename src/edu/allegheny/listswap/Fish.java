package edu.allegheny.listswap;

public class Fish 
{
    int weight, age;
    char gender;

    public Fish(int w, int a, char g)
    {
        weight = w;
        age = a;
        gender = g;
    }

    public String toString()
    {
        return "Fish: " + weight + ", " + age + ", " + gender;
    }
}
