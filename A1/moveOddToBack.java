public class moveOddToBack {
    public static void main(String[] args) {
        int[] array = new int[7];
        int count = 0;
        array[0] = 51;
        array[1] = 88;
        array[2] = 3;
        array[3] = 70;
        array[4] = 96;
        array[5] = 38;
        array[6] = 47;
        for (int i = 0; i < array.length; i++) {
            //all odd numbers to the back
            if (i > array.length - 1 - count) {
                break;
            }
            //Odd numbers
            if (array[i] % 2 != 0) {
                count++;
                int temp = array[i];
                for (int j = i; j < array.length - 1; j++) {
                    array[j] = array[j + 1];
                }
                array[array.length - 1] = temp;
            }
            //Even numbers
            if (array[i] % 2 == 0) {
                continue;
            }
        }
        for (int i = 0; i<array.length;i++){
            System.out.print(array[i]+ " ");
        }

    }

}

