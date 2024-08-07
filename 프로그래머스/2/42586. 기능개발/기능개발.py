def solution(progresses, speeds):
    day_list = []  # 각 작업별 남은 일수를 저장할 리스트. 작업 순서대로 인덱스 순서도 결정됨.
    ans_list = []  # 답을 담을 list
    N = len(progresses)
    cnt = 1

    for i in range(N):
        if (100 - progresses[i]) % speeds[i] == 0:  # 나눠지는 경우
            day_list.append(int((100 - progresses[i]) / speeds[i]))
        else:  # 나눠지지 않는 경우
            day_list.append(int((100 - progresses[i]) // speeds[i] + 1))  # 몫 + 1은 일수가 됨

    for i in range(N - 1):  # N == len(day_list) == len(progresses)
        if day_list[i] >= day_list[i + 1]:
            cnt += 1
        else:
            # 만약 현재 비교하는 숫자의 앞에있는 숫자들 중 가장 큰 수보다 작은 경우는 cnt += 1
            for j in range(i):
                if day_list[i + 1] <= day_list[j]:
                    cnt += 1
                    break  # for j문 탈출
            else:
                ans_list.append(cnt)
                cnt = 1

    ans_list.append(cnt)

    answer = ans_list
    return answer