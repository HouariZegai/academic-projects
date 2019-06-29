package pw1;

import java.util.Scanner;

public class Engine {
    private static int nbrPlace; // number of place
    private static int nbrTransition; // number of transition
    private static int model[]; // maquette
    private static int sigma[]; // number of token in each place
    private static int matPre[][];
    private static int matPost[][];
    private static int matC[][];
    private static int cSigma[];
    private static int modelPrime[]; // maquette

    public static void main(String[] args) {
        inputData();

        // calculate matrix C:
        matC = new int[nbrPlace][nbrTransition];
        for(int i = 0; i < nbrPlace; i++) {
            for(int j = 0; j < nbrTransition; j++) {
                matC[i][j] = matPost[i][j] - matPre[i][j];
            }
        }

        // calculate C x sigma
        cSigma = new int[nbrPlace];
        int result;
        for(int i = 0; i < nbrPlace; i++) {
            result = 0;
            for(int j = 0; j < nbrTransition; j++) {
                result += matC[i][j] * sigma[j];
            }
            cSigma[i] = result;
        }

        // calculate M': M' = M + C*Sigma
        modelPrime = new int[nbrPlace];
        for(int i = 0; i < nbrPlace; i++) {
            modelPrime[i] = model[i] + cSigma[i];
        }

        System.out.print("M'=[ ");
        for(int i = 0; i < modelPrime.length; i++) {
            System.out.print(modelPrime[i] + " ");
        }
        System.out.println("]");

    }

    private static void inputData() {
        Scanner mScanner = new Scanner(System.in);

        System.out.print("Type the number of place: ");
        nbrPlace = mScanner.nextInt();

        System.out.print("Type the number of transition: ");
        nbrTransition = mScanner.nextInt();

        model = new int[nbrPlace];
        System.out.println("Type the current model (maquette):");
        for(int i = 0; i < nbrPlace; i++) {
            System.out.print("P" + (i + 1) + ": ");
            model[i] = mScanner.nextInt();
        }

        mScanner.nextLine(); // clean

        System.out.print("Type the sigme (number of token in each place): ");
        String sigmaStr = mScanner.nextLine();

        sigma = new int[nbrTransition];

        // calculate sigma vector
        for(int i = 0; i < sigmaStr.length(); i++) {
            sigma[Integer.parseInt(String.valueOf(sigmaStr.charAt(i))) - 1] += 1;
        }

        System.out.println("Type pre matrice: ");
        matPre = new int[nbrPlace][nbrTransition];
        for(int i = 0; i < nbrPlace; i++) {
            for(int j = 0; j < nbrTransition; j++) {
                matPre[i][j] = mScanner.nextInt();
            }
        }

        System.out.println("Type post matrice: ");
        matPost = new int[nbrPlace][nbrTransition];
        for(int i = 0; i < nbrPlace; i++) {
            for(int j = 0; j < nbrTransition; j++) {
                matPost[i][j] = mScanner.nextInt();
            }
        }

        mScanner.close();
    }

    private static void printMat(int[][] mat) {
        for(int i = 0; i < mat.length; i++) {
            for(int j = 0; j < mat[i].length; j++)
                System.out.print(mat[i][j] + " ");
            System.out.println();
        }
    }

    private static void printMat(int[] mat) {
        for(int i = 0; i < mat.length; i++) {
                System.out.print(mat[i] + " ");
        }
        System.out.println();
    }
}
