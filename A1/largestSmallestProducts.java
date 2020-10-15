public class largestSmallestProducts {
    public static void main(String[] args) {
        int[] array = new int[5];
        array[0] = 42;
        array[1] = 21;
        array[2] = 100;
        array[3] = 100;
        array[4] = 7;
        //Finding the first two max values
        int firstMax = 0;
        int secondMax = 0;
        int min = 0;
        int secondMin = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > array[firstMax]) {
                secondMax = firstMax;
                firstMax = i;
            } else if (array[i] > array[secondMax] && i != firstMax) {
                secondMax = i;
            }
            if (array[i] < array[min]) {
                secondMin = min;
                min = i;
            } else if (array[i] < array[secondMin] && i != min) {
                secondMin = i;
            }

        }
        System.out.println("First Max: " + array[firstMax] + "   Index: " + firstMax);
        System.out.println("Second Max: " + array[secondMax] + "   Index: " + secondMax);
        System.out.println("Largest Product = " + array[firstMax] * array[secondMax]);
        System.out.println("First Min: " + array[min] + "   Index: " + min);
        System.out.println("Second Min: " + array[secondMin] + "   Index: " + secondMin);
        System.out.println("Smallest Product = " + array[min] * array[secondMin]);
        //Displaying array
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
