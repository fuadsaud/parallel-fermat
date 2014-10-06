import java.math.BigInteger;

import edu.rit.pj2.LongVbl;
import edu.rit.pj2.LongLoop;
import edu.rit.pj2.Task;

class Fermat extends Task {
    private LongVbl witnessesCount;

    public void main(String[] args) {
        Long p = Long.valueOf(args[0]);
        Long witnessesCount = call(p);
        System.out.println(witnessesCount);
    }

    public Long call(Long p) {
        this.witnessesCount = new LongVbl.Sum(0);

        parallelFor(0L, p - 1).exec(new LongLoop() {
            private LongVbl count;

            public void start() {
                this.count = threadLocal(witnessesCount);
            }

            public void run(long i) {
                if (isWitness(p, i)) this.count.item++;
            }
        });

        return witnessesCount.item;
    }

    public Boolean isWitness(BigInteger p, BigInteger a) {
        return a.modPow(p, p).compareTo(a) != 0;
    }

    public Boolean isWitness(Long p, Long a) {
        return isWitness(BigInteger.valueOf(p), BigInteger.valueOf(a));
    }
}

