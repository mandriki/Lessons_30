package taskDataApi;

import java.time.*;
import java.time.format.*;
import java.time.temporal.*;
import java.util.Locale;
import static java.time.temporal.ChronoUnit.*;
public class Main {

    public static void main(String[] args) {
        /**1.	Создать объект класса LocalDate, представляющий собой дату 25.06.2020. Вывести полученную дату в консоль*/
        LocalDate date = LocalDate.of(2020,06,24);
        System.out.println(date);
        /** 2.	Создать объект LocalDate, представляющий собой сегодняшнюю дату. Используя этот объект,
         * создать другой объект LocalTime, представляющий собой дату через 3 месяца после сегодняшней. Вывести эту дату в консоль.*/
        LocalDate date1 = LocalDate.now();
        System.out.println("Дата сейчас: "+ date1);
        LocalDate newDate = date1.plusMonths(3);
        LocalDateTime time = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT).plusMonths(3);
        System.out.println("Дата new: "+ newDate);
        System.out.println("Дата new: "+ time);
        /**3.	Создать объект LocalDate, представляющий собой сегодняшнюю дату. Преобразовать дату в строку вида "05.05.2017".
         *  Вывести эту строку в консоль*/
        LocalDate date3 = LocalDate.now();
        System.out.println("Дата " + date3);
        String datas = date3.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        DateTimeFormatter newData = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(new Locale("ru"));
        String localDate = date3.format(newData);
        System.out.println("Дата " + datas);
        System.out.println("Локальная дата " + localDate);
        /**    4.	Дана строка вида "26-03-2014". Получить объект LocalDate,
         * представляющий собой дату, полученную из этой строки.*/
        String dateStr = "26-03-2014";
        LocalDate fromCustomPattern = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        System.out.println("Дата " + fromCustomPattern);

        /** 5.	Создать объект LocalDate, представляющий собой сегодняшнюю дату. Создать объект LocalDate,
         представляющий собой дату 25.06.2020. Используя созданные объекты, найти количество дней между этими двумя датами.*/
        /** 6.	Даны два объекта LocalDate из предыдущего задания. Подсчитать количество секунд между полуночью первой
         даты и полуночью второй даты.*/
        LocalDate date4 = LocalDate.now();
        LocalDate date5 = LocalDate.of(2020,06,25);
        long days = DAYS.between(date4, date5);
        long seconds1 = ChronoUnit.SECONDS.between(date4.atStartOfDay(),date5.atStartOfDay());
        long seconds2 = days*24*60*60;
        System.out.println(days);
        System.out.println(seconds1);
        System.out.println(seconds2);
        System.out.println(days*LocalTime.MAX.toSecondOfDay());
        LocalDateTime time1 = LocalDateTime.of(date4,LocalTime.MIDNIGHT);
        LocalDateTime time2 = LocalDateTime.of(date5,LocalTime.MIDNIGHT);
        long seconds3 = ChronoUnit.SECONDS.between(time1,time2);
        System.out.println(seconds3);

        /**7.	Написать свою реализацию интерфейса TemporalAdjuster, которая бы прибавляла к дате 42 дня*/
        LocalDate date42 = LocalDate.now().with(new DataAdd());
        DateTimeFormatter form = DateTimeFormatter.ofPattern("d MMMM YYYY");
        System.out.println("Через 42 дата: " + form.format(date42));

       /** Написать свою реализацию интерфейса TemporalAdjuster, которая бы изменяла дату на ближайшее (в днях) 1 января.*/
        LocalDate near = LocalDate.now().with(new Near());
        System.out.println("Ближайшее 1 января: " + form.format(near));
    }
    public static class DataAdd implements TemporalAdjuster {
        @Override
        public Temporal adjustInto (Temporal temporal) {
            return temporal.plus(42, DAYS);
        }
   }
    public static class Near implements TemporalAdjuster {
        @Override
        public Temporal adjustInto(Temporal temporal) {
            Temporal nextData = temporal.with(TemporalAdjusters.firstDayOfNextYear());
            Temporal previousData = temporal.with(TemporalAdjusters.firstDayOfYear());
            long dayNext = DAYS.between(temporal, nextData);
            long dayPrevious = DAYS.between(previousData, temporal);
            if (dayNext <= dayPrevious) {
                return nextData;
            } else {
                return previousData;
            }
        }
    }
}

/**Задание 30. DateApi
        1.	Создать объект класса LocalDate, представляющий собой дату 25.06.2020. Вывести полученную дату в консоль
        2.	Создать объект LocalDate, представляющий собой сегодняшнюю дату. Используя этот объект, создать другой объект LocalTime, представляющий собой дату через 3 месяца после сегодняшней. Вывести эту дату в консоль.
        3.	Создать объект LocalDate, представляющий собой сегодняшнюю дату. Преобразовать дату в строку вида "05.05.2017". Вывести эту строку в консоль
        4.	Дана строка вида "26-03-2014". Получить объект LocalDate, представляющий собой дату, полученную из этой строки.
        5.	Создать объект LocalDate, представляющий собой сегодняшнюю дату. Создать объект LocalDate, представляющий собой дату 25.06.2020. Используя созданные объекты, найти количество дней между этими двумя датами.
        6.	Даны два объекта LocalDate из предыдущего задания. Подсчитать количество секунд между полуночью первой даты и полуночью второй даты.
        7.	Написать свою реализацию интерфейса TemporalAdjuster, которая бы прибавляла к дате 42 дня
        8.	(*) Написать свою реализацию интерфейса TemporalAdjuster, которая бы изменяла дату на ближайшее (в днях) 1 января.*/

