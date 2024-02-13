def stingy(total_lambs):
    stingy_list = [1, 1]
    x, total = 2, 2
    while x <= total_lambs:
        value = stingy_list[x - 1] + stingy_list[x - 2]
        stingy_list.append(value)
        total += value
        if total > total_lambs:
            break
        x += 1
    return len(stingy_list)

def generous(total_lambs):
    generous_list = []
    x, total = 0, 0
    while x <= total_lambs:
        current = 2 ** x
        generous_list.append(current)
        total += current
        if total > total_lambs:
            break
        x += 1
    return len(generous_list)

def solution(total_lambs):
    return stingy(total_lambs) - generous(total_lambs)
