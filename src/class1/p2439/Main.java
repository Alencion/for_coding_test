package class1.p2439;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String string = br.readLine();

        int n = Integer.parseInt(string);

        for(int i=0; i< n; i++){
            for(int j=n-i-1; j > 0; j--)System.out.print(" ");
            for(int k=0; k <= i; k++)System.out.print("*");
            System.out.println();
        }
    }
}