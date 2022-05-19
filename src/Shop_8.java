//        Создать программу-базу данных магазина
//        базу реализовать в виде списка мапов
//        поля каждого товара: название, стоимость, количество на складе
//
//        функционал:
//        1) возможность добавить новый функционал вводя данные с клавиатуры https://younglinux.info/java/system-in
//        2) вывод содержимого базы в виде таблицы в консоль
//        3) управление программой осуществлять через меню вида:
//
//        ____МЕНЮ____
//        1. Добавить товар
//        2. вывести все товары
//        3. Выход
//
//        программа просит выбрать пункт меню написав цифру в терминал и
//        затем запрашивает ввод данных или выводит таблицу. После чего
//        выводит меню снова, пока не будет выбран пункт "Выход"
//
//        4*) Реализовать возможность удаления товара и изменения количества, при этом не должен перезаписываться весь мап конкретного товара


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Shop_8 {
    public static void main(String[] args) {

        HashMap<String, Product> shop = new HashMap<>();

            // наполняем магазин товаром
            fillShop(shop);


        int choise = 0;

        //вызов основного меню
        while (choise !=5) {

             printMenu();
             choise = choiceInt();

            switch (choise) {

                case 1: {printShop(shop); break;}
                case 2: {addProduct(shop); break;}
                case 3: {removeProd(shop); break;}
                case 4: {changeProd(shop); break;}
                case 5: break;
                default:
                    System.out.println("Введите цифру от 1 до 5 и нажмите Enter.");
            }

        }

        System.out.println("Выход из программы.");



//        shop.get("Гречка").cost += 20;


//        ArrayList<HashMap<String, String>> shop = new ArrayList<>();
//        HashMap<String, String> product;
//
//        product = new HashMap<>();
//        product.put("name", "Сахар");
//        product.put("cost", "100");
//        product.put("quantity", "300");
//        shop.add(product);

//
//        HashMap<String, Integer[]> products = new HashMap<>();
//        products.put("Подгузники", new Integer[]{100, 20});
//        products.put("Манка", new Integer[]{50, 40});
//



    }


    //изначальное наполнение магазина товаром
    public static void fillShop(HashMap<String,Product> shop){

        shop.put("Гречка", new Product(150,1000));
        shop.put("Сахар", new Product(100,500));

    }

    //Вывод всего товара в магазине
    public static void printShop(HashMap<String,Product> shop){
        if(shop.size() != 0) {
            int i = 0;
            System.out.println("\nСейчас на складе магазина:");
            System.out.println("\n№   Товар:     Кол-во,шт:    Цена, руб.:");
            for (Map.Entry<String, Product> h : shop.entrySet()) {
                System.out.println(++i + ".  " + h.getKey() + "        " + h.getValue().quantity + "          " + h.getValue().cost);
            }
        }else System.out.println("МАГАЗИН ПУСТОЙ!");
    }

    // основное меню программы
    public static void printMenu(){
        System.out.println();
        System.out.println("\n____МЕНЮ____\n1. Вывести все товары\n2. Добавить товар\n3. Удалить товар\n4. Изменить кол-во/цену товара\n5. Выход");
        System.out.println("\nВведите номер пункта меню 1, 2, 3, 4 или 5:");
    }

    // добавление нового товара в магазин
    public static void addProduct(HashMap<String,Product> shop){
        String newProd = "";
        while (newProd == "" || shop.containsKey(newProd)){
        System.out.print("Введите название нового товара или нажмите Enter для выхода: ");

            newProd = (new Scanner(System.in)).nextLine();
            if (newProd == "") break;
        if (shop.containsKey(newProd)) System.out.println("Такой товар уже есть!");
        }
        if(newProd != "") {
            System.out.println("Введите количество нового товара в единицах (шт, кг, л...): ");
            int quantity = choiceInt();

            System.out.println("Введите цену нового товара в рублях: ");
            int cost = choiceInt();

            shop.put(newProd, new Product(cost, quantity));

            System.out.println("Товар успешно добавлен!");
            System.out.printf("Теперь в магазине %d товаров\n", shop.size());
            printShop(shop);
        }else System.out.println("\nВозврат в меню.");

    }

    //удаление товара
    public static void removeProd(HashMap<String,Product> shop){
        printShop(shop);
        String delProd = "";
        while (delProd == "" || !(shop.containsKey(delProd))){
            System.out.print("Введите название товара для удаления или нажмите Enter для выхода: ");

            delProd = (new Scanner(System.in)).nextLine();
            if (delProd =="") break;
            if (!shop.containsKey(delProd)) {
                System.out.println("Такого товара нет!");
                printShop(shop);
            }

        }
        if (delProd != "") {
            shop.remove(delProd);
            System.out.println("Товар успешно удалён!");
            System.out.printf("Теперь в магазине %d товаров\n", shop.size());
            printShop(shop);
        }else System.out.println("\nВозврат в меню.");
    }


    //изменение количества, цены товара
    public static void changeProd(HashMap<String,Product> shop){
        String changeProd = "";
        while (changeProd == "" || !shop.containsKey(changeProd)){
            printShop(shop);
            System.out.print("Введите название товара для изменения или нажмите Enter для выхода: ");

            changeProd = (new Scanner(System.in)).nextLine();
            if (changeProd == "") break;
            if (!shop.containsKey(changeProd)) System.out.println("Такого товара нет!");
        }
        if(changeProd != "") {
            System.out.println("\nБудем менять значения товара " + changeProd );
            System.out.println("\nТекущее количество товара " + changeProd + " = " + shop.get(changeProd).quantity + " единиц.");
            System.out.print("Введите новое количество товара в единицах (шт, кг, л...): ");
            int quantity = choiceInt();
            shop.get(changeProd).quantity = quantity;
            System.out.println("Теперь товара " + changeProd + " в количестве " + quantity + " единиц\n");

            System.out.println("\nТекущая цена товара " + changeProd + " = " + shop.get(changeProd).cost + " руб.");
            System.out.print("Введите новую цену товара в рублях: ");
            int cost = choiceInt();
            shop.get(changeProd).cost = cost;
            System.out.println("Теперь товар " + changeProd + " имеет цену = " + cost + " руб.\n");


            System.out.println("Данные товара успешно изменены!\n");

            printShop(shop);
        }else System.out.println("\nВозврат в меню.");

    }





    //считывание ввода с клавиатуры с обработкой ошибки ввода только целых чисел
    public static int choiceInt(){
        while (true) {
            try {
                return (new Scanner(System.in)).nextInt();
            } catch (Exception e) {
                System.out.print("Ошибка! Введите целое число: ");
            }
        }
    }



}
