package polskowniaApp.user;

import java.util.List;

interface CustomerDataRepository
{
    CustomerData save(CustomerData toSave);
    CustomerData findDefaultByUserId(int userId);

    void setDefaultToFalseByUserId(int userId);

    List<CustomerData> findByUserId(int userId);
}
