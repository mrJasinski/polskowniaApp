package polskowniaApp.user;

import java.util.List;

interface CustomerDataRepository
{
    CustomerData save(CustomerData toSave);

    void setDefaultToFalseByUserId(int userId);

    List<CustomerData> findByUserId(int userId);
}
