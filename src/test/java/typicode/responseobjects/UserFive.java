package typicode.responseobjects;

public class UserFive {
    int id = 5;
    String name = "Chelsey Dietrich";
    String username = "Kamren";
    String email = "Lucio_Hettinger@annie.ca";
    Address address = new Address();
    String phone = "(254)954-1289";
    String website = "demarco.info";
    Company company = new Company();
    
    public class Address{
        String street = "Skiles Walks";
        String suite = "Suite 351";
        String city = "Roscoeview";
        String zipcode = "33263";
        Geo geo = new Geo();
        
        public class Geo{
            String lat = "-31.8129";
            String lng = "62.5342";
        }
    }
    
    public class Company {
        String name = "Keebler LLC";
        String catchPhrase = "User-centric fault-tolerant solution";
        String bs = "revolutionize end-to-end systems";
    }
}
