package extension;
public class IntegerMatrix {
    public static void swap(int arr[][],int x1, int y1, int x2, int y2)
    {
        int temp = arr[x1][y1];
        arr[x1][y1] = arr[x2][y2];
        arr[x2][y2] = temp;
    }
    public static int[][] copyOf(int arr[][], int n, int m)
    {
        int[][] new_array = new int[n][m];
        for(int i=0;i<n;i++)
        {
            new_array[i] = arr[i].clone();
        }
        return new_array;
    }
    
    public static int[] getColumn(int[][] arr, int colIndex) {
        int[] column = new int[arr.length];
        for(int i=0;i<arr.length;i++)
        {
            column[i] = arr[i][colIndex];
        }
        return column;
    }
    
}
