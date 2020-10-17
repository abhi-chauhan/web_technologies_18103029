import java.util.Scanner;

class Solution {
    private static final String characters_collection = "abcdefghijklmnopqrstuvwxyz";
    private static int N_rows = 0;
    private static int M_columns = 0;
    private static int minimum_changes = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int n, m;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        in.nextLine();
        String[] crops = new String[n];
        for (int i = 0; i < n; i++) {
            crops[i] = in.nextLine().trim();
        }
        N_rows = n;
        M_columns = m;
        System.out.println(replant(crops));
    }

    private static boolean isValid(int x, int y) {
        return (x < 0 || x >= N_rows || y < 0 || y >= M_columns);
    }

    private static void change(String[] crops, int x, int y, int changes) {
        int character = -1;
        for (int i = 0; i < 26; i++) {
            if ((!isValid(x - 1, y) && crops[x - 1].charAt(y) == characters_collection.charAt(i))
                    || (!isValid(x + 1, y) && crops[x + 1].charAt(y) == characters_collection.charAt(i))
                    || (!isValid(x, y - 1) && crops[x].charAt(y - 1) == characters_collection.charAt(i))
                    || (!isValid(x, y + 1) && crops[x].charAt(y + 1) == characters_collection.charAt(i))
            ) {
                continue;
            }
            character = i;
            break;
        }
        if (character == -1) {
            return;
        }
        String new_string = crops[x].substring(0, y) + characters_collection.charAt(character) + crops[x].substring(y + 1);
        String old_string = crops[x];
        crops[x] = new_string;
        replantation(crops, x, y + 1, changes + 1);
        crops[x] = old_string;
    }

    private static void replantation(String[] crops, int x, int y, int changes) {

        if (isValid(x, y)) {
            y = 0;
            x += 1;
            if (isValid(x, y)) {
                minimum_changes = Math.min(changes, minimum_changes);
                return;
            }
        }
        if ((!isValid(x - 1, y) && crops[x].charAt(y) == crops[x - 1].charAt(y)) || (!isValid(x, y - 1) && crops[x].charAt(y) == crops[x].charAt(y - 1))) {
            change(crops, x, y, changes);
        } else {
            replantation(crops, x, y + 1, changes);
        }
    }

    public static int replant(String[] crops) {

        replantation(crops, 0, 0, 0);

        return minimum_changes;

    }
}