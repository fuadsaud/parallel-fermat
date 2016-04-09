import java.math.BigInteger;

import edu.rit.pj2.LongVbl;
import edu.rit.pj2.LongLoop;
import edu.rit.pj2.Task;

/**
 * This class is resbonsible for counting the number of witnesses of the Fermat's Little Theorem
 * for a given number.
 *
 * @author Fuad Saud <ffs3415@rit.edu>
 */
class Fermat extends Task {
    private LongVbl witnessesCount;

    /**
     * Main task execution method.
     *
     * @param args list of arguments for this tasks. It is supposed to contain a single element,
     * a string representation of a long integer p.
     */
    public void main(String[] args) {
        if (args.length != 1) {
            usage();

            System.exit(1);
        }

        Long p = 0L;

        try {
            p = Long.valueOf(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("Cannot parse given number: " + args[0]);

            System.exit(2);
        }

        Long witnessesCount = countWitnesses(p);

        System.out.println(witnessesCount);
    }

    /**
     * Count the number of witnesses for a given number p.
     *
     * @param p the subject number for which to count the witnesses.
     * @return the count of witnesses for the given number.
     */
    private Long countWitnesses(Long p) {
        this.witnessesCount = new LongVbl.Sum(0L);

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

    /**
     * Check if a BigInteger a is a witness for a BigInteger p.
     *
     * @param p the subject number for which to check the witness against.
     * @param a the candidate witness to check.
     *
     * @return whether a is a witness for p.
     */
    private Boolean isWitness(BigInteger p, BigInteger a) {
        return a.modPow(p, p).compareTo(a) != 0;
    }

    /**
     * Check if a Long a is a witness for a Long p.
     *
     * @param p the subject number for which to check the witness against.
     * @param a the candidate witness to check.
     *
     * @return whether a is a witness for p.
     */
    private Boolean isWitness(Long p, Long a) {
        return isWitness(BigInteger.valueOf(p), BigInteger.valueOf(a));
    }

    /**
     * Print program usage info to stdout.
     */
    private void usage() {
        System.out.println("USAGE:");
        System.out.println("\tjava pj2 Fermat <P>");
        System.out.println("\t\t<P>: the number to be test");
        System.out.println("\n\t\tExample: java pj2 Fermat 512461");
    }
}
