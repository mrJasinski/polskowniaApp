package polskowniaApp.shop;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
class ShopInitializer implements ApplicationListener<ContextRefreshedEvent>
{
    private final ShopRepository shopRepo;

    ShopInitializer(final ShopRepository shopRepo)
    {
        this.shopRepo = shopRepo;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event)
    {

//        TODO tabela bazy danych w phpMyAdmin
        this.shopRepo.save(new ShopItem(
                "Indywidualny kurs do egzaminu B1"
                , 1500
                , "Indywidualne zajęcia skupione na rozwiązywaniu zadań egzaminacyjnych oraz wyłapywaniu trudności kursanta"
                , ShopItem.Category.COURSE_INDIVIDUAL
                , 10
                , 90
                , ShopItem.Level.B1
        ));

        this.shopRepo.save(new ShopItem(
                "Polski z Wioletką(A1)"
                , 50
                , "Wprowadzenie do zagadnień języka polskiego dla obcokrajowców"
                , ShopItem.Category.BOOK
                , 0
                , 0
                , ShopItem.Level.A1
        ));

        this.shopRepo.save(new ShopItem(
                "Symulacja zadania z mówienia państwowy B1"
                , 1500
                , "Ćwiczenie polegające na orpacowniu zadania z mówienia dla egzaminu państwowego B1"
                , ShopItem.Category.EXAM_SIMULATION
                , 1
                , 90
                , ShopItem.Level.B1
        ));

        this.shopRepo.save(new ShopItem(
                "Arkusz ćwiczeń liter polskiego alfabetu"
                , 1500
                , "Arkusz ćwiczeń zawierający wzory liter polskiego alfabetu do wprawiania się w ich pisaniu - PLIK PDF DO SAMODZIELNEGO WYDRUKU"
                , ShopItem.Category.EXERCISE_SHEET
                , 0
                , 0
                , ShopItem.Level.A1
        ));


    }
}
