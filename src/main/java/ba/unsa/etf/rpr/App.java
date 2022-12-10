package ba.unsa.etf.rpr;

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
        Employee e=E.getById(1);
        System.out.println(e.getEmployee_id());
        System.out.println(e.getFirst_name());

    }
}
