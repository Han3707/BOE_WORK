import java.util.*;

class Solution
{
    public int solution(int []A, int []B)
    {
        // 배열을 리스트로 변환
        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();
        
        for(int i=0; i<A.length; i++){
            listA.add(A[i]);
            listB.add(B[i]);
        }
        
        // A는 오름차순 정렬
        Collections.sort(listA);
        // B는 내림차순 정렬
        Collections.sort(listB, Collections.reverseOrder());
        
        // 작은 값 × 큰 값으로 곱해서 합을 최소화
        int answer = 0;
        for(int i=0; i<A.length; i++){
            answer += listA.get(i) * listB.get(i);
        }
        
        return answer;
    }
}