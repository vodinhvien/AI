package GTS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GTSSolver {

    private static String getPath(List<Integer> listPath)
    {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<listPath.size();i++)
        {
            sb.append(listPath.get(i) + 1);
            if(i<listPath.size()-1)
            {
                sb.append(" -> ");
            }
            else {
                sb.append("");
            }
        }
        return sb.toString();
    }
    /**
     * Hiển thị danh sách chu trình đường đi và chi phí ngắn nhất khi đi qua các thành phố khác nhau được xác định
     * bởi thành phố cho trước bằng giải thuật tham lam (greedy)
     *Ví dụ >1 -> 2 -> 5 -> 3 -> 4 -> 1 
     */
    public static void findShortestPath(int[][] weightMatrix, int source)
    {
        //Định nghĩa các biến
        ArrayList<Integer> visitedNode;  //đỉnh đã đi qua
        List<Integer> listPath;          //danh sách đường đi
        
        //Gán biến
        visitedNode = new ArrayList<>();
        visitedNode.add(source);
        listPath = new LinkedList<>();
        listPath.add(source);
        int sum = 0;
        int currentNode = source;
        
        while(visitedNode.size() < weightMatrix.length)
        {
            int min = Integer.MAX_VALUE;
            int i = -1;
            for(int j=0;j<weightMatrix.length;j++)
            {
                /*
                    Để thoả mãn nhỏ nhất:
                   - Không xét 2 đỉnh trùng nhau  
                   - Đỉnh j đang xét chưa được đi qua
                   - Cạnh kề với đỉnh j phải có trọng số là nhỏ nhất
                */
                if(j != currentNode && !visitedNode.contains(j) && weightMatrix[currentNode][j] < min)
                {
                    min = weightMatrix[currentNode][j];
                    i = j;
                }
            }
            sum = sum + min;
            currentNode = i;
            visitedNode.add(i);
            listPath.add(i);
        }
        
        sum = sum + weightMatrix[currentNode][source];
        listPath.add(source);
        System.out.printf("Danh sách thứ tự đường đi: %s\n", getPath(listPath));
        System.out.printf("Tổng chi phí cho đường đi: %d\n", sum);
    }
    
    public static void main(String... args)
    {
        int[][] weightMatrix = {{-1,854,864,671,613,93,157,208,457,94,870,579,638,387},
                                {315,-1,734,775,87,732,600,952,319,678,439,606,666,475},
                                {59,936,-1,397,352,719,168,33,627,823,459,385,797,724},
                                {236,877,78,-1,525,981,394,240,973,796,342,386,58,111},
                                {711,339,230,56,-1,444,430,297,418,291,966,66,750,448},
                                {615,777,340,794,746,-1,184,741,908,523,212,328,902,593},
                                {715,246,955,546,528,183,-1,445,21,158,881,525,99,808},
                                {535,746,857,539,518,116,302,-1,160,515,962,403,137,762},
                                {834,693,112,408,858,314,68,823,-1,497,975,284,354,68},
                                {835,579,307,542,75,274,952,887,384,-1,625,69,156,342},
                                {349,514,6,510,438,17,763,354,436,598,-1,608,641,467},
                                {322,642,808,986,451,978,278,182,510,671,162,-1,152,57},
                                {578,644,743,405,1,100,240,598,717,402,345,766,-1,930},
                                {170,790,274,902,832,212,104,152,413,471,574,44,491,-1}
                                };
        GTSSolver.findShortestPath(weightMatrix, 4);
    }
}
