package polskowniaApp.user;

import jakarta.persistence.*;
import polskowniaApp.user.dto.CustomerDataDto;

@Entity
@Table(name = "customer_datas")
public class CustomerData
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String company;
    private String taxNumber;
    private String firstName;
    private String lastName;
    private String streetName;
    private String streetNumber;
    private String localNumber;
    private String zipCode;
    private String town;
    private String phone;
    private String email;
    private boolean isDefault;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    CustomerData()
    {
    }

    CustomerData(
            final String company
            , final String taxNumber
            , final String firstName
            , final String lastName
            , final String streetName
            , final String streetNumber
            , final String localNumber
            , final String zipCode
            , final String town
            , final String phone
            , final String email
            , final boolean isDefault
            , final User user)
    {
        this.company = company;
        this.taxNumber = taxNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.localNumber = localNumber;
        this.zipCode = zipCode;
        this.town = town;
        this.phone = phone;
        this.email = email;
        this.isDefault = isDefault;
        this.user = user;
    }

    CustomerDataDto toDto()
    {
        return new CustomerDataDto(
                this.isDefault
                , this.company
                , this.taxNumber
                , this.firstName
                , this.lastName
                , this.streetName
                , this.streetNumber
                , this.localNumber
                , this.zipCode
                , this.town
                , this.phone
                , this.email
        );
    }

    public int getId()
    {
        return this.id;
    }

    public String getCompany()
    {
        return this.company;
    }

    public String getTaxNumber()
    {
        return this.taxNumber;
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

    public boolean isDefault()
    {
        return this.isDefault;
    }

    public User getUser()
    {
        return this.user;
    }
}
