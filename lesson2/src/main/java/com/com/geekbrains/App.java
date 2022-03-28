package com.com.geekbrains;

public class App {

//    1 Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4. При
//    подаче массива другого размера необходимо бросить исключение MyArraySizeException.
//            2 Далее метод должен пройтись по всем элементам массива, преобразовать в int и
//    просуммировать. Если в каком-то элементе массива преобразование не удалось (например, в
//            ячейке лежит символ или текст вместо числа), должно быть брошено исключение
//    MyArrayDataException с детализацией, в какой именно ячейке лежат неверные данные.
//3 В методе main() вызвать полученный метод, обработать возможные исключения
//    MyArraySizeException и MyArrayDataException и вывести результат расчета (сумму элементов,
//                                                                             при условии что подали на вход корректный массив).
//    Заметка: Для преобразования строки к числу используйте статический метод класса Integer:
//            Integer.parseInt(сюда_подать_строку);
//    Заметка: Если Java не сможет преобразовать входную строку (в строке число криво написано), то
//    будет сгенерировано исключение NumberFormatException.



    public static void main(String[] args){
        String [][] array = new String[4][4];

        int count = 0;
        for (int i = 0; i < array.length; i++){
            for(int j = 0; j < array[3].length; j++){
                count++;
                array[i][j] = String.valueOf(count);
            }
        }

        //array[1][1] = "o";
        try{
            CheckArray(array);
        }
        catch (MyArraySizeException e){
            e.printStackTrace();
        }
        catch (MyArrayDataException e){
            e.printStackTrace();
        }


    }

    public static void CheckArray(String [][] array){
        if(array.length != 4){
            throw new MyArraySizeException(array.length);
        }

        for(int i = 0; i < array.length; i++){
            if(array[i].length != 4){
                throw new MyArraySizeException(array[i].length);
            }
        }
//        if(array[0].length != 4 || array[1].length != 4 || array[2].length != 4 || array[3].length != 4){
//
//        }

        int sum = 0;

        for (int i = 0; i < array.length; i++){
            for(int j = 0; j < array[3].length; j++){
                try{
                    sum += Integer.parseInt(array[i][j]);
                }
                catch (NumberFormatException e){
                    e.printStackTrace();
                    throw new MyArrayDataException(i, j, array[i][j]);
                }

            }
        }
        System.out.printf("Сумма элементов массива: %d\n", sum);


    }

}
