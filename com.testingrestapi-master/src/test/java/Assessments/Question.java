package Assessments;

import java.util.ArrayList;

class Employee
{
    int id,sal;
    String name,address;
    Employee(int id,String name,String address,int sal)
    {
        this.id=id;
        this.sal=sal;
        this.name=name;
        this.address=address;

    }
}
public class Question {
    public static void main(String args[]) {

        ArrayList<Employee> emp = new ArrayList<>();

        emp.add(new Employee(100, "RAM", "test", 888));
        emp.add(new Employee(200, "SITA", "test", 8898));
        emp.forEach((i) -> System.out.println(i.id + " " + i.name + " " + i.address + " " + i.sal));
    }
}

