package learnHybridInheritance.WebExample;


class D extends A implements B, C
{
    public void print()
    {
        System.out.println("method print()");
    }

    public void disD()
    {
        System.out.println("ClassD");
    }
}