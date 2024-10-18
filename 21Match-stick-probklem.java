class GFG 
{
 
// Function to return the optimal strategy
static void TwentyoneMatchstick(int arr[], int N)
{
 
    // Removing matchsticks in blocks of five
    for (int i = 0; i < N; i += 1) 
    {
        System.out.print(5 - arr[i] + " ");
    }
    System.out.println();
}
 
// Driver code
public static void main(String[] args) 
{
    int arr[] = {3, 4, 2, 2};
 
    int N = arr.length;
 
    TwentyoneMatchstick(arr, N);
}
} 
