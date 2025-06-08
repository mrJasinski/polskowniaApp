package polskowniaApp.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerDataWriteModel
{
    @JsonProperty
    private boolean isDefault;
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

    CustomerDataWriteModel()
    {
    }

    CustomerDataWriteModel(
            final boolean isDefault
            , final String company
            , final String taxNumber
            , final String firstName
            , final String lastName
            , final String streetName
            , final String streetNumber
            , final String localNumber
            , final String zipCode
            , final String town
            , final String phone
            , final String email)
    {
        this.isDefault = isDefault;
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
    }

    public boolean isDefault()
    {
        return this.isDefault;
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
}
