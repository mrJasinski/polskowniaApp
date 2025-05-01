package polskowniaApp.shop;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
class ShopInitializer implements ApplicationListener<ContextRefreshedEvent>
{
    private final ShopItemRepository shopRepo;

    ShopInitializer(final ShopItemRepository shopRepo)
    {
        this.shopRepo = shopRepo;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event)
    {
        var title1 = "Indywidualny kurs do egzaminu B1";
        if (!this.shopRepo.existsByTitle(title1))
            this.shopRepo.save(new ShopItem(
                    title1
                    , 1500
                    , "Indywidualne zajęcia skupione na rozwiązywaniu zadań egzaminacyjnych oraz wyłapywaniu trudności kursanta"
                    , ShopItem.Category.COURSE_INDIVIDUAL
                    , 10
                    , 90
                    , ShopItem.Level.B1
            ));

        var title2 = "Polski z Wioletką(A1)";
        if (!this.shopRepo.existsByTitle(title2))
            this.shopRepo.save(new ShopItem(
                    title2
                    , 50
                    , "Wprowadzenie do zagadnień języka polskiego dla obcokrajowców"
                    , ShopItem.Category.BOOK
                    , 0
                    , 0
                    , ShopItem.Level.A1
            ));

        var title3 = "Symulacja zadania z mówienia państwowy B1";
        if (!this.shopRepo.existsByTitle(title3))
            this.shopRepo.save(new ShopItem(
                    title3
                    , 1500
                    , "Ćwiczenie polegające na orpacowniu zadania z mówienia dla egzaminu państwowego B1"
                    , ShopItem.Category.EXAM_SIMULATION
                    , 1
                    , 90
                    , ShopItem.Level.B1
            ));

        var title4 = "Arkusz ćwiczeń liter polskiego alfabetu";
        if (!this.shopRepo.existsByTitle(title4))
            this.shopRepo.save(new ShopItem(
                    title4
                    , 1500
                    , "Arkusz ćwiczeń zawierający wzory liter polskiego alfabetu do wprawiania się w ich pisaniu - PLIK PDF DO SAMODZIELNEGO WYDRUKU"
                    , ShopItem.Category.EXERCISE_SHEET
                    , 0
                    , 0
                    , ShopItem.Level.A1
            ));


    }
}
