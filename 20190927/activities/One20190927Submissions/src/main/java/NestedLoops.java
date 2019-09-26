public class NestedLoops {
    public static void main( String[] args )
    {
        // this is #1 - I'll call it "CN"
        for ( char c='A'; c <= 'E'; c++ )
        {
            for ( int n=1; n <= 3; n++ )
            {
                System.out.println( c + " " + n );
            }
        }

        System.out.println("\n");

        for(int n =1; n <=3; n++) {
            for(char c = 'A'; c <='E'; c++) {
                System.out.println(c + " " + n);
            }
        }

        // this is #2 - I'll call it "AB"
        for ( int a=1; a <= 3; a++ )
        {
            for ( int b=1; b <= 3; b++ )
            {
                System.out.print( a + "-" + b + " " );
            }
            // * You will add a line of code here.
            //System.out.println(a + " - " + b + " ");
        }

        System.out.println("\n");

    }
}

// 1) The inner nested loop goes through the iteration first.
// 2) The characters go through the iteration first before the numbers.
// 3) The output changes where it is now broken by lines, but the content of the value does not change.
// 4) You cannot loop through it and will run into a compiler error as b wouldn't be recognized as a declared variable.