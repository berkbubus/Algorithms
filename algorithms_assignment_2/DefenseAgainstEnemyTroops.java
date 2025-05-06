import java.lang.reflect.Array;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;

/**
 * This class accomplishes Mission Nuke'm
 */
public class DefenseAgainstEnemyTroops {
    private ArrayList<Integer> numberOfEnemiesArrivingPerHour;

    public DefenseAgainstEnemyTroops(ArrayList<Integer> numberOfEnemiesArrivingPerHour){
        this.numberOfEnemiesArrivingPerHour = numberOfEnemiesArrivingPerHour;
    }

    public ArrayList<Integer> getNumberOfEnemiesArrivingPerHour() {
        return numberOfEnemiesArrivingPerHour;
    }

    private int getRechargedWeaponPower(int hoursCharging){
        return hoursCharging*hoursCharging;
    }

    /**
     *     Function to implement the given dynamic programming algorithm
     *     SOL(0) <- 0
     *     HOURS(0) <- [ ]
     *     For{j <- 1...N}
     *         SOL(j) <- max_{0<=i<j} [ (SOL(i) + min[ E(j), P(j − i) ] ]
     *         HOURS(j) <- [HOURS(i), j]
     *     EndFor
     *
     * @return OptimalEnemyDefenseSolution
     */
    public OptimalEnemyDefenseSolution getOptimalDefenseSolutionDP(){
        int N=getNumberOfEnemiesArrivingPerHour().size()+1;
        int[] sol = new int[N];
        sol[0]=0;
        ArrayList<ArrayList<Integer>> hours = new ArrayList<>();
        hours.add(new ArrayList<Integer>());
        for(int i=0;i<N-1;i++){
            hours.add(null);
        }
        int k;
        ArrayList<Integer> K;
        for(int j=1;j<N;j++){
            int max=0;
            int max2=0;
            for(int i=0;i<j;i++){
                k=sol[i]+Math.min(getNumberOfEnemiesArrivingPerHour().get(j-1),getRechargedWeaponPower(j-i));
                if(k>max){
                    max=k;
                    sol[j]=max;
                    max2=i;
                }
            }
            K = new ArrayList<>(hours.get(max2));
            K.add(j);
            hours.set(j,K);
        }
        int maxNumber=0;
        int max=0;
        for(int i=0;i<sol.length;i++){
            k=sol[i]+Math.min(getNumberOfEnemiesArrivingPerHour().get(N-2),getRechargedWeaponPower(N-i-1));
            if(k>maxNumber){
                max=i;
                maxNumber=k;
            }
        }
        ArrayList<Integer> rHours = new ArrayList<>(hours.get(max));
        rHours.add(getNumberOfEnemiesArrivingPerHour().size());
        OptimalEnemyDefenseSolution ratata = new OptimalEnemyDefenseSolution(maxNumber,rHours);
        return ratata;
    }
}
