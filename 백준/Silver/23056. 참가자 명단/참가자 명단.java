
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    private static ArrayList<Participants> odd = new ArrayList<>(); //홀수 학급
    private static ArrayList<Participants> even = new ArrayList<>(); //짝수 학급
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
//        System.out.println("학급 수 N과 학급당 최대 인원수 M을 입력");
        String[] str = br.readLine().split(" ");

        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);

        int[] school = new int[N + 1];

        boolean isEOF = false;
        do {
//            System.out.println("학급과 이름을 입력하세요");
            String[] input = br.readLine().split(" ");

            int classNumber = Integer.parseInt(input[0]);
            String name = input[1];

            if (classNumber == 0 && name.equals("0")) {
                isEOF = true;
                continue;  //break;
            }

            if (classNumber % 2 == 0 && school[classNumber] < M) {
                even.add(new Participants(classNumber, name));
                school[classNumber]++;
            } else if (classNumber % 2 == 1 && school[classNumber] < M) {
                odd.add(new Participants(classNumber, name));
                school[classNumber]++;
            }
        } while (!isEOF);

        Collections.sort(odd);
        Collections.sort(even);

        printList(odd);
        printList(even);
    }

    private static void printList(ArrayList<Participants> list) {
//        StringBuilder sb = new StringBuilder();
//
//        for (int i = 0; i < list.size(); i++) {
//            sb.append(list.get(i).classNumber + " " + list.get(i).name + "\n");
//        }
//
//        System.out.println(sb.toString());
        for (Participants p : list) {
            System.out.println(p.classNumber + " " + p.name);
        }
    }
}

class Participants implements Comparable<Participants> {
    int classNumber;
    String name;

    Participants(int classNumber, String name) {
        this.classNumber = classNumber;
        this.name = name;
    }

    @Override
    public int compareTo(Participants o) {
        if (this.classNumber == o.classNumber) {
            if (this.name.length() == o.name.length()) {
                return this.name.compareTo(o.name);
            }
            return this.name.length() - o.name.length();
        }
        return this.classNumber - o.classNumber;
    }
}