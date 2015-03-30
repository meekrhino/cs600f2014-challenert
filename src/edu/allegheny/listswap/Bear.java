package edu.allegheny.listswap;

public class Bear
{
    int weight, age;
    char gender;

    public Bear(int w, int a, char g)
    {
        weight = w;
        age = a;
        gender = g;
    }

    public String toString()
    {
        return "Bear: " + weight + ", " + age + ", " + gender;
    }
}
