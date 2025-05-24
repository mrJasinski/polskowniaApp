package polskowniaApp.order;

import jakarta.persistence.*;

@Entity
@Table(name = "customer_datas")
class CustomerData
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String streetName;
    private String streetNumber;
    private String localNumber;
    private String zipCode;
    private String town;
    private String phone;
    private String email;

    CustomerData()
    {
    }

    CustomerData(final String firstName, final String lastName, final String streetName, final String streetNumber, final String localNumber, final String zipCode, final String town, final String phone, final String email)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.localNumber = localNumber;
        this.zipCode = zipCode;
        this.town = town;
        this.phone = phone;
        this.email = email;
    }

    public int getId()
    {
        return this.id;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public String getStreetName()
    {
        return this.streetName;
    }

    public String getStreetNumber()
    {
        return this.streetNumber;
    }

    public String getLocalNumber()
    {
        return this.localNumber;
    }

    public String getZipCode()
    {
        return this.zipCode;
    }

    public String getTown()
    {
        return this.town;
    }

    public String getPhone()
    {
        return this.phone;
    }

    public String getEmail()
    {
        return this.email;
    }
}
