package ba.unsa.etf.rpr;

import java.util.Date;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );


        EmployeeDaoSQLImpl E= new EmployeeDaoSQLImpl();
      Employee e=E.getById(7);
        System.out.println(e.getEmployee_id());
        System.out.println(e.getFirst_name());
        System.out.println(e.getFirst_name()+" "+e.getLast_name()+" "+e.getAddress()+" "+e.getEmail()+" "+ e.getJob_title());
        System.out.println(e.getHire_date());
        System.out.println(e.getSalary());
        System.out.println(e.getUsername()+" "+e.getPassword());


        Date d=new Date(2019,02,23);
        System.out.println("Svi zaposleni su:");
        List<Employee> l=E.getAfterByHireDate(d);

        System.out.println("-----------------------------");
       for(Employee x:l)
           System.out.println(x.toString());


/*




       // Date d=new Date(2018,05,06);
        Employee e=new Employee(8,"user","pass","name","surename","address","mail",2018-02-01,"job",55.5);

        E.add(e);
        System.out.println("Dodat je radnik:\n");
*/


    }
}
